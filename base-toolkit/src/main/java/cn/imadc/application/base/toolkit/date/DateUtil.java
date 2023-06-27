package cn.imadc.application.base.toolkit.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private static final SimpleDateFormat YMD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat yDateFormat = new SimpleDateFormat("yyyy");
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
     * 日期格式化
     *
     * @param time   日期
     * @param format 格式
     * @return 格式化后的日期
     */
    public static String format(Date time, String format) {
        SimpleDateFormat noTimeFormat = new SimpleDateFormat(format);
        return noTimeFormat.format(time);
    }

    /**
     * 日期格式化
     *
     * @param date 日期
     * @return 格式化后的日期
     */
    public static String getYMdDateStr(Date date) {
        return YMD_DATE_FORMAT.format(date);
    }

    /**
     * 日期格式化
     *
     * @param date 日期
     * @return 格式化后的日期
     */
    public static String getYDateStr(Date date) {
        return yDateFormat.format(date);
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
