package cn.imadc.application.base.common.context;

import cn.imadc.application.base.common.constant.BaseConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * 应用上下文信息
 *
 * @author 杜劲松
 * @since 2023-05-11
 **/
public class AppCtx {


    /**
     * 获取当前会话用户ID
     *
     * @param currentContext 当前会话
     * @return 当前会话用户ID
     */
    public static Long uId(RequestContext currentContext) {

        Long uId = currentContext.get(ReqCtxConstant.ID, Long.TYPE);
        if (null != uId) return uId;

        return BaseConstant.SYSTEM_ID;
    }

    /**
     * 获取当前会话用户ID
     *
     * @return 当前会话用户ID
     */
    public static Long uId() {
        RequestContext currentContext = RequestContext.getCurrentContext();

        Long uId = currentContext.get(ReqCtxConstant.ID, Long.TYPE);
        if (null != uId) return uId;

        return BaseConstant.SYSTEM_ID;
    }

    /**
     * 获取当前会话用户名称
     *
     * @param currentContext 当前会话
     * @return 当前会话用户名称
     */
    public static String uName(RequestContext currentContext) {

        String uName = currentContext.get(ReqCtxConstant.NAME, String.class);
        if (StringUtils.isNotBlank(uName)) return uName;

        return BaseConstant.SYSTEM_NAME;
    }

    /**
     * 获取当前会话用户名称
     *
     * @return 当前会话用户名称
     */
    public static String uName() {
        RequestContext currentContext = RequestContext.getCurrentContext();

        String uName = currentContext.get(ReqCtxConstant.NAME, String.class);
        if (StringUtils.isNotBlank(uName)) return uName;

        return BaseConstant.SYSTEM_NAME;
    }
}
