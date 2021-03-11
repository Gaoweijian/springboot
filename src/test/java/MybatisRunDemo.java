import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import home.mybatis.dao.UserAcountMapper;
import home.mybatis.dto.UserAcount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/3/11 上午 10:26
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class MybatisRunDemo {


    /**
     * 用户账户服务
     */
    @Autowired
    private UserAcountMapper acountMapper;


    @Test
    public void testMapper() {
        UserAcount acount = acountMapper.selectById(1);
        log.info("[mybatis测试]查询用户信息,SUCCESS,acount={}", acount);
    }

    @Test
    public void testWrapper() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", "1");
        UserAcount acount = acountMapper.selectOne(wrapper);
        log.info("[mybatis测试]查询用户信息,SUCCESS,acount={}", acount);
    }

}
