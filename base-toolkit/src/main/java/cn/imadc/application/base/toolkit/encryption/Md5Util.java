package cn.imadc.application.base.toolkit.encryption;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>
 * md5工具类
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
public class Md5Util {

    /**
     * 获取字符串的md5值
     *
     * @param source 待获取md5值的字符串
     * @return md5值
     */
    public static String md5(String source) {
        return DigestUtils.md5Hex(source);
    }

}
