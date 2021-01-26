package home.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file + "\\" + fileName);
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
}
