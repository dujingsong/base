package cn.imadc.application.base.mybatisplus.repository.impl;

import cn.imadc.application.base.mybatisplus.repository.IBaseMPService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * service基类实现
 * </p>
 *
 * @author 杜劲松
 * @since 2022-05-24
 */
@Transactional(rollbackFor = Exception.class)
public class BaseMPServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseMPService<T> {

}
