package home.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/1 下午 10:47
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class FreemarkerUtil {
    /**
     * 读取freemarker模板
     *
     * @param path  模板所在目录（template/模板名称.html）
     * @param model 模板要跳出的数据
     * @return
     * @throws Exception
     */
    public static String renderHtml(String path, Map<String, Object> model) throws Exception {

        StringWriter stringWriter = new StringWriter();
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
            //模板所在目录 这里是放在resources/static目录下
            String templatePath = ResourceUtils.getURL("classpath:static/freemarker").getPath();
            cfg.setLocale(Locale.CHINA);
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setClassicCompatible(true);
            cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
            cfg.setWhitespaceStripping(true);
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            Template temp = cfg.getTemplate(path);
            temp.process(model, stringWriter);
            stringWriter.flush();
            return stringWriter.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                stringWriter.close();
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        }
        return null;
    }
}
