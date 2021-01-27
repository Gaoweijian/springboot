package home.utils;

import home.enums.ProblemTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/25 下午 10:16
 * @Version: 1.0
 * @Description: 读取docx工具类
 */
@Slf4j
public class DocxUtil {

    /**
     * @描述 读取docx内容
     * @参数 [filePath, fileName]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static String readDocx(String filePath, String fileName) throws IOException {
        String docText = "";
        try {
            log.info("[DocxUtil]读取word内容,START,filePath={},fileName={}", filePath, fileName);
            File file = new File(filePath + "\\" + fileName);
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            Range rang = doc.getRange();
            docText = doc.getDocumentText();
            log.info("[DocxUtil]读取word内容,SUCCESS");
            return docText;
        } catch (Exception e) {
            log.error("[DocxUtil]读取word内容,FAIL", e);
            throw e;
        }
    }

    /**
     * @描述 使用HWPFDocument解析word文档, wps按doc处理即可
     * @参数 [filePath, fileName]
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static String parseDocByHWPFDocument(InputStream inputStream, String fileName) throws IOException {
        StringBuffer docxNew = new StringBuffer();
        ProblemTypeEnum statusEnum = null;
        try (
                FileInputStream is = (FileInputStream) inputStream;
                HWPFDocument document = new HWPFDocument(is);
        ) {
            log.info("[DocxUtil]读取docx内容,START,fileName={}", fileName);
            //区间
            Range range = document.getRange();
            for (int i = 0; i < range.numParagraphs(); i++) {
                //段落
                Paragraph p = range.getParagraph(i);
                //段落文本
                String paragraphText = p.text();
                //如果遇到填空题/选择题/判断题三个字,下一行就开始处理数据
                ProblemTypeEnum typeEnum = ProblemTypeEnum.getProblemTypeEnumContainsDesc(paragraphText);
                if (typeEnum != null) {
                    if (ProblemTypeEnum.ANSWER.code.equals(typeEnum.code)) {
                        break;
                    }
                    docxNew.append(paragraphText);
                    statusEnum = typeEnum;
                    continue;
                }
                if (statusEnum != null) {
                    docxNew.append(problemStrExecute(paragraphText, statusEnum));
                }
            }
            log.info("[DocxUtil]读取docx内容,SUCCESS");
            return docxNew.toString();
        } catch (Exception e) {
            log.error("[DocxUtil]读取docx内容,FAIL,msg={}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * @描述
     * @参数 [content, typeEnum]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static String problemStrExecute(final String content, final ProblemTypeEnum typeEnum) {
        switch (typeEnum) {
            case BLANK: {
                //分解内容
                String matchStr = matchStr("(?<=（).*?(?=）)", content);
                String subContent = content.replace(matchStr, "").replace("\r", " [填空题]");
                return subContent + "\n" + "答案：" + matchStr + "\r\n";
            }
            case CHOICE: {
                if (content.contains("A.") && content.contains("A")) {
                    //拆解内容 A B C
                    String matchA = matchStr("A.*?(?=B)", content).trim();
                    String matchB = matchStr("B.*?(?=C)", content).trim();
                    String matchC = matchStr("C.*?(?=\r)", content).trim();
                    return matchA + "\n" + matchB + "\n" + matchC + "\r\n";
                } else {
                    return content.replace("\r", "[单选题]\r");
                }
            }
            case JUDGE: {
                String convContent = content.replace("X", "错");
                convContent = convContent.replace("×", "错");
                convContent = convContent.replace("√", "对");
                return convContent;
            }
            default:
        }
        return "";
    }

    /**
     * @描述 正则匹配中间字符
     * @参数 [regx, content]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static String matchStr(String regx, String content) {
        Pattern pattern = Pattern.compile(regx);
        Matcher match = pattern.matcher(content);
        if (match.find()) {
            return match.group();
        }
        return "";
    }

}
