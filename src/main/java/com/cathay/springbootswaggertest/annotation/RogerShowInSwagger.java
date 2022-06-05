package com.cathay.springbootswaggertest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// public enum RetentionPolicy {
//     SOURCE, // 編譯器處理完Annotation後不儲存在class中
//     CLASS, // 編譯器把Annotation儲存在class中，這是預設值
//     RUNTIME // 編譯器把Annotation儲存在class中，可以由虛擬機器讀取,反射需要
// }

@Retention(RetentionPolicy.RUNTIME) // 反射需要 - Swagger會需要反射，用以定位標註的method
@Target({ ElementType.METHOD })
public @interface RogerShowInSwagger {
}


