package cn.imadc.application.base.common.annoations;

import cn.imadc.application.base.common.enums.AuthType;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface Api {

    AuthType authType() default AuthType.AUTHORIZED;
}
