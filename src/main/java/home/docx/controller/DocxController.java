package home.docx.controller;

import home.utils.DocxUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/25 下午 10:08
 * @Version: 1.0
 * @Description: word处理
 */
@Slf4j
@RequestMapping(value = "docx")
@RestController
public class DocxController {


    /**
     * @描述 读取word
     * @参数 [path, file]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/25
     * @修改人
     */
    @RequestMapping(value = "read")
    public void readDocxDel(MultipartFile docx, HttpServletResponse response) throws IOException {
        String docxStr = DocxUtil.parseDocByHWPFDocument(docx.getInputStream(), docx.getName());
        InputStream is = new ByteArrayInputStream(docxStr.getBytes());
        OutputStream os = response.getOutputStream();
        XWPFDocument doc = new XWPFDocument(is);
        response.setContentType("application/msword");
        response.setHeader("Content-disposition", "attachment;filename=" + "转换.doc");
        doc.write(os);
    }
}
