package cn.imadc.application.base.samples.mybatis;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-09
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
