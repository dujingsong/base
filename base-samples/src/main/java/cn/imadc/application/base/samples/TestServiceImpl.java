package cn.imadc.application.base.samples;

import cn.imadc.application.base.common.exception.BizException;
import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-01
 */
@Slf4j
@Service
public class TestServiceImpl extends BaseMPServiceImpl<UserMapper, User> implements ITestService {


    @Override
    public void asdasd(User user) {
//        log.info("开始保存");
        super.save(user);
//        log.info("保存");

//        throw new BizException("adasd");
    }
}
