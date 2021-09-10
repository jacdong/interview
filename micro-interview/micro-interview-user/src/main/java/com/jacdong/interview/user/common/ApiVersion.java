package com.jacdong.interview.user.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: ApiVersion
 * @Description: API 版本控制
 * @author DongBin
 * @date 2021-09-06 04:47:39
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
    /**
     * @return 版本号
     */
    int value() default 1;
}
