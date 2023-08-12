package ecnu.edu.yjsy.annotations;

import java.lang.annotation.*;

/**
 * @date 2023/8/12  00:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopLog {

    // 开启表示记录方法执行耗时
    boolean enableTimeRecord() default false;
}
