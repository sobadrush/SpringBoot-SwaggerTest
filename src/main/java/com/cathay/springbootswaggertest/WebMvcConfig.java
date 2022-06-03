package com.cathay.springbootswaggertest;

/**
 * @author RogerLo
 * @date 2022/6/3
 */
// Spring Boot引入swagger-ui 後swagger-ui.html無法訪問404的問題
// ref. https://www.796t.com/article.php?id=89674
// @Configuration
// public class WebMvcConfig implements WebMvcConfigurer {
//
//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         registry.addResourceHandler("/**")
//                 .addResourceLocations("classpath:/static/");
//         registry.addResourceHandler("swagger-ui.html")
//                 .addResourceLocations("classpath:/META-INF/resources/");
//         registry.addResourceHandler("/webjars/**")
//                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
//     }
//
// }