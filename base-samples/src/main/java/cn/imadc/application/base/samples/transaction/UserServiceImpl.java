package cn.imadc.application.base.samples.transaction;

import cn.imadc.application.base.mybatisplus.repository.impl.BaseMPServiceImpl;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-14
 */
@Service
public class UserServiceImpl extends BaseMPServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @DS("slave")
    public User find() {
        return super.getById(1L);
    }
}
