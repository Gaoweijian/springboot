import home.utils.FreemarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/1 下午 10:57
 * @Version: 1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
@Slf4j
public class FrameMarkerTest {

    @Test
    public void frameMarkerRun() {
        HashMap map = new HashMap();
        map.put("cityName", "深圳");
        map.put("rate", "100%");
        try {
            String maker = FreemarkerUtil.renderHtml("volume.html", map);
            log.info("[编译后内容]marker={}", maker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
