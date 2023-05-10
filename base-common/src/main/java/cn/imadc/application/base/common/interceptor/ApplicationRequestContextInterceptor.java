package cn.imadc.application.base.common.interceptor;

import cn.imadc.application.base.common.action.IApplicationRequestContext;
import cn.imadc.application.base.common.annoations.Api;
import cn.imadc.application.base.common.context.ReqCtxConstant;
import cn.imadc.application.base.common.context.RequestContext;
import cn.imadc.application.base.common.context.SpringApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 会话拦截器
 * </p>
 *
 * @author 杜劲松
 * @since 2023-05-10
 */
@Slf4j
public class ApplicationRequestContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return true;

        RequestContext requestContext = RequestContext.getCurrentContext();

        ApplicationContext applicationContext = SpringApplicationContext.getApplicationContext();
        IApplicationRequestContext applicationRequestContext
                = applicationContext.getBean(IApplicationRequestContext.class);

        String tokenName = applicationRequestContext.indicateToken();
        String token = request.getHeader(tokenName);
        if (StringUtils.isNotBlank(token)) {
            requestContext.put(tokenName, token);
        }

        applicationRequestContext.prepareContext();

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Api api = handlerMethod.getMethodAnnotation(Api.class);
        if (null != api) {
            requestContext.put(ReqCtxConstant.API, api);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext.getCurrentContext().release();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}