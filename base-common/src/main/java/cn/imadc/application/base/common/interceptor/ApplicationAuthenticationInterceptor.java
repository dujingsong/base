package cn.imadc.application.base.common.interceptor;

import cn.imadc.application.base.common.action.IApplicationAuthentication;
import cn.imadc.application.base.common.annoations.Api;
import cn.imadc.application.base.common.context.ReqCtxConstant;
import cn.imadc.application.base.common.context.RequestContext;
import cn.imadc.application.base.common.context.SpringApplicationContext;
import cn.imadc.application.base.common.enums.AuthType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 鉴权拦截器
 * </p>
 *
 * @author 杜劲松
 * @since 2023-05-10
 */
@Slf4j
public class ApplicationAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;

        RequestContext currentContext = RequestContext.getCurrentContext();

        ApplicationContext applicationContext = SpringApplicationContext.getApplicationContext();
        IApplicationAuthentication applicationAuthentication
                = applicationContext.getBean(IApplicationAuthentication.class);

        Api api = currentContext.get(ReqCtxConstant.API, Api.class);
        if (null != api) currentContext.put(ReqCtxConstant.API, api);

        if (null != api && null != api.authType() && api.authType().equals(AuthType.ANONYMOUS)) {
            return true;
        }

        applicationAuthentication.authentication();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
