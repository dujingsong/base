package cn.imadc.application.base.common.action;

import cn.imadc.application.base.common.context.ReqCtxConstant;
import cn.imadc.application.base.common.context.RequestContext;
import cn.imadc.application.base.common.exception.NotLoginException;
import cn.imadc.application.base.common.exception.UnauthorizedException;

/**
 * 认证鉴权
 *
 * @author 杜劲松
 * @since 2023-05-10
 **/
public interface IApplicationAuthentication {

    default void authentication() {
        RequestContext currentContext = RequestContext.getCurrentContext();

        boolean notLogin = !currentContext.containsKey(ReqCtxConstant.USER);
        if (notLogin) throw new NotLoginException();

        boolean noPermission = !currentContext.containsKey(ReqCtxConstant.USER);
        if (noPermission) throw new UnauthorizedException();
    }
}
