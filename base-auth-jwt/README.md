# 权限认证模块

## 权限认证模块，身份认证采用jwt依赖实现，加密方式为rsa

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-auth-jwt</artifactId>
</dependency>
~~~

模块中已预置rsa公私钥
key/rsa_pri.key  私钥
key/rsa_pub.key  公钥


用法
~~~
# 生成一个带有超时时间的token

import cn.imadc.application.base.auth.jwt.JWT;
import cn.imadc.application.base.common.action.IEnumAble;
import java.util.concurrent.TimeUnit;

String token = JWT.generate(IEnumAble issuer, String subject, long expireTime, TimeUnit expireTimeUnit);
↓
String token = JWT.generate(ST.USER, "admin", 168, TimeUnit.DAYS);

# 生成一个不带超时时间的token

import cn.imadc.application.base.auth.jwt.JWT;
import cn.imadc.application.base.common.action.IEnumAble;

String token = JWT.generate(IEnumAble issuer, String subject);
↓
String token = JWT.generate(ST.USER, "admin");

# 获取主体类型信息，token错误或失效或抛出JWTVerificationException异常

import cn.imadc.application.base.auth.jwt.JWT;

String IEnumAbleString = JWT.issuer(token);

# 获取主体信息，token错误或失效或抛出JWTVerificationException异常

import cn.imadc.application.base.auth.jwt.JWT;

String subject = JWT.subject(token);

# 解析token，token错误或失效或抛出JWTVerificationException异常

import cn.imadc.application.base.auth.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

DecodedJWT decodedJWT = JWT.decodedJWT(token);

~~~
