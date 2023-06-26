package cn.imadc.application.base.common.constant;

import java.time.format.DateTimeFormatter;

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
    public static final String QUESTION_MARK = "?";
    public static final String SEMICOLON = ";";
    public static final String PERCENT = "%";

    // --------------------日期常量--------------------
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter FORMAT_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    // --------------------数据列标识-------------------
    public final static String ID = "id";
    public final static String CREATE_BY = "create_by";
    public final static String UPDATE_BY = "update_by";

    public final static String CREATE_BY_F = "createBy";
    public final static String UPDATE_BY_F = "updateBy";

    public final static String CREATE_BY_ID = "create_by_id";
    public final static String UPDATE_BY_ID = "update_by_id";

    public final static String CREATE_BY_ID_F = "createById";
    public final static String UPDATE_BY_ID_F = "updateById";

    public final static String CREATE_TIME = "create_time";
    public final static String UPDATE_TIME = "update_time";

    public final static String CREATE_TIME_F = "createTime";
    public final static String UPDATE_TIME_F = "updateTime";

    public final static Long SYSTEM_ID = 0L;
    public final static String SYSTEM_NAME = "system";

    public static final int YES_VAL = 0;
    public static final int NO_VAL = 1;

    // --------------------删除标识--------------------
    public static final String DEL_FLAG = "del_flag";
    public static final String DEL_FLAG_F = "delFlag";
    public static final int DEL_VAL = 1;
    public static final int NOT_DEL_VAL = 0;
    public static final String DEL_SUFFIX = "_DELETED" + System.currentTimeMillis();

    // --------------------认证授权--------------------
    public static final String ACCESS_TOKEN = "Access-Token";

    // --------------------日志相关--------------------
    public static final String TRACE_ID = "traceId";

    // --------------------其他常量--------------------
    /**
     * 错误的ID
     */
    public static final Long ERROR_ID = -9999L;
    /**
     * 接口全路径匹配
     */
    public static final String GLOBAL_PATH_PATTERNS = "/**";
}
