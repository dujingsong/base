package cn.imadc.application.base.common.validator;


import cn.imadc.application.base.common.exception.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * <p>
 * 参数校验工具
 * </p>
 *
 * @author 杜劲松
 * @since 2023-05-10
 */
public class ValidationUtil {
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = validatorFactory.getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ParamException 校验不通过，则报ParamException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws ParamException {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage());
            }
            throw new ParamException(msg.toString());
        }
    }
}
