package cn.imadc.application.base.toolkit.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 日期格式化
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "YYYY-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMAT_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    /**
     * 日期格式化
     *
     * @param localDateTime     日期
     * @param dateTimeFormatter 格式
     * @return 格式化后的日期
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return null == localDateTime ? null : localDateTime.format(dateTimeFormatter);
    }

    /**
     * 格式化日期
     *
     * @param localDateTime 日期
     * @return 格式化后的日期
     */
    public static String formatFull(LocalDateTime localDateTime) {
        return null == localDateTime ? null : localDateTime.format(FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 解析日期
     *
     * @param yyyyMMDDHHMMSSDateStr 字符串格式的日期
     * @return 解析后的日期
     */
    public static LocalDateTime parse(String yyyyMMDDHHMMSSDateStr) {
        return null == yyyyMMDDHHMMSSDateStr ? null : LocalDateTime.parse(yyyyMMDDHHMMSSDateStr, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
}
