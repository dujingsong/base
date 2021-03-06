package cn.imadc.application.base.common.constant;

/**
 * <p>
 * 系统基础常量
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
public class BaseConstant {

    // --------------------基础常量--------------------
    public static final String COMMA = ",";
    public static final String BLANK = "";
    public static final String SLASH = "/";
    public static final String WELL_NUMBER = "#";
    public static final String EQUAL_SIGN = "=";
    public static final String COLON = ":";

    // --------------------日期常量--------------------
    public static final String YYYY_MM_DD = "YYYY-MM-dd";

    // --------------------数据列标识-------------------
    public final static String CREATE_BY = "create_by";
    public final static String UPDATE_BY = "update_by";

    public final static String CREATE_BY_ID = "create_by_id";
    public final static String UPDATE_BY_ID = "update_by_id";

    // --------------------删除标识--------------------
    public static final String DEL_FLAG = "del_flag";
    public static final int DEL_VAL = 1;
    public static final int NOT_DEL_VAL = 0;

    // --------------------认证授权--------------------
    public static final String ACCESS_TOKEN = "Access-Token";

    // --------------------其他常量--------------------
    /**
     * 错误的ID
     */
    public static final Long ERROR_ID = -9999L;
}
