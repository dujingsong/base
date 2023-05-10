package cn.imadc.application.base.common.action;

import cn.imadc.application.base.common.context.ReqCtxConstant;

/**
 * 会话信息
 *
 * @author 杜劲松
 * @since 2023-05-10
 **/
public interface IApplicationRequestContext {

    default String indicateToken() {
        return ReqCtxConstant.TOKEN;
    }

    void prepareContext();
}
