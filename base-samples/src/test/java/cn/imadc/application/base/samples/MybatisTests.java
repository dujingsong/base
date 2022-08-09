package cn.imadc.application.base.samples;

import cn.imadc.application.base.samples.mybatis.IUserDao;
import cn.imadc.application.base.samples.mybatis.MapperProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-27
 */
public class MybatisTests {

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();

        sqlSession.put("cn.imadc.application.base.samples.mybatis.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.imadc.application.base.samples.mybatis.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10001");
        log.info("测试结果：{}", res);
        Integer res1 = userDao.queryUserAge("10001");
        log.info("测试结果：{}", res1);
    }

}
