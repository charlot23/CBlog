package com.charlotte.cblog.common.annotation;


import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
}
