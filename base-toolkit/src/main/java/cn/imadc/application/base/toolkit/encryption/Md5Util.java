package cn.imadc.application.base.toolkit.encryption;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    public static String md5(String source) {
        return DigestUtils.md5Hex(source);
    }

}
