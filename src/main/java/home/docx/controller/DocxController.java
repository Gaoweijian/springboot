package home.docx.controller;

import home.utils.DocxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String readDocxDel(MultipartFile docx) throws IOException {
        String fileName = docx.getName();
        return DocxUtil.parseDocByHWPFDocument(docx.getInputStream(), fileName);
    }
}
