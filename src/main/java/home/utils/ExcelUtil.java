package home.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/2 上午 11:01
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class ExcelUtil {


    private static Map<Integer, String> keyMap = new HashMap();

    static {
        keyMap.put(0, "cityName");
        keyMap.put(1, "cityCode");
        keyMap.put(2, "empName");
        keyMap.put(3, "empNo");
    }

    /**
     * @描述
     * @参数 [inputStream, fileName]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/2
     * @修改人
     */
    public static String readExcel2Sql(InputStream inputStream, String fileName) throws Exception {

        StringBuilder sb = new StringBuilder();
        //文件流对象
        FileInputStream fis = (FileInputStream) inputStream;
        Workbook wb = null;
        //根据文件后缀（xls/xlsx）进行判断
        if (fileName.endsWith(".xls")) {
            wb = new HSSFWorkbook(fis);
        } else if (fileName.endsWith(".xlsx")) {
            wb = new XSSFWorkbook(inputStream);
        }
        //开始解析
        Sheet sheet = wb.getSheetAt(0);
        //第一行是列名，所以不读
        int firstRowIndex = sheet.getFirstRowNum() + 1;
        int lastRowIndex = sheet.getLastRowNum();
        //遍历行
        for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
            Row row = sheet.getRow(rIndex);
            if (row != null) {
                int firstColumn = row.getFirstCellNum();
                int lastColumn = row.getLastCellNum();
                HashMap map = new HashMap<>();
                for (int i = firstColumn; i < lastColumn; i++) {
                    //获取单元格
                    Cell cell = row.getCell(i);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = cell.getStringCellValue();
                    map.put(keyMap.get(i), value);
                }
                map.put("id", UUIDGenerator.getUUID());
                sb.append(FreemarkerUtil.renderHtml("createUserCitySql.html", map));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
