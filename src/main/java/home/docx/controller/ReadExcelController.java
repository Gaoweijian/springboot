package home.docx.controller;

import home.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/2 上午 10:56
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "read")
public class ReadExcelController {
    @RequestMapping(value = "excel")
    public String readExcel2Sql(@RequestBody MultipartFile excel) throws Exception {
        return ExcelUtil.readExcel2Sql(excel.getInputStream(), excel.getOriginalFilename());
    }
}
