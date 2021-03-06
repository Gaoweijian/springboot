package home.docx.controller;

import home.utils.DocxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Controller
public class DocxController {

    @GetMapping(value = "home")
    public String home() {
        return "home";
    }

    /**
     * @描述 读取word
     * @参数 [path, file]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/25
     * @修改人
     */
    @PostMapping(value = "read")
    @ResponseBody
    public String readDocxDel(@RequestBody MultipartFile docx, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return DocxUtil.parseDocByHWPFDocument(docx.getInputStream(), docx.getName());
    }
}
