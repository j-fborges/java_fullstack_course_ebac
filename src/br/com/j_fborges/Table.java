package br.com.j_fborges;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value() default "This is a TableAnnotation";

}
