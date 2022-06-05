# SpringBoot-SwaggerTest

---
## 簡介：SpringBoot 測試 Swagger 的專案

---
## How to Run?
1. 執行 SpringBootSwaggerTestApplication.java
2. SpringBoot根據 application.properties 設定初始化 H2-DB
3. 會在專案目錄下產生: MyH2DB.mv.db 資料庫檔案
4. HttpGet: http://localhost:8080/RogerSpringBoot/TestController/sayHello 可測試 SayHello API
5. 進入Swagger API: http://localhost:8080/RogerSpringBoot/swagger-ui/index.html
6. 進入Swagger API: http://localhost:8080/RogerSpringBoot/v2/api-docs
7. 使用 mvnw 啟動專案：```./mvnw spring-boot:run```
8. 使用 mvnw 打包成 runnable jar：```./mvnw clean package -Dmaven.test.skip=true```
9. 使用 java cmd啟動專案：```java -jar ./target/SpringBoot-SwaggerTest-0.0.1-SNAPSHOT.jar```

## How to 容器化?
1. 編寫 Dockerfile
2. 打包成Image：```docker build -t springboot-swagger-app .```
3. 將Image run成容器：```docker run -idt --name my-swagger-app -p 9990:8080 springboot-swagger-app```

---
## 使用技術
|  #  |      使用技術       | 版本      |
|:---:|:---------------:|---------|
|  1  |   SpringBoot    | 2.7.0   |
|  2  |   H2 Database   | 2.1.212 |
|  3  |     Swagger     | 3.0.0   |
|  4  | Spring Data JPA |         |
|  5  |     Docker      |         |

---
## 參考資料
|  #  |                      說名                      | URL                                                             |
|:---:|:--------------------------------------------:|-----------------------------------------------------------------|
|  1  |               Spring Data JPA                | https://morosedog.gitlab.io/springboot-20190328-springboot14/   |
|     |                                              | https://www.796t.com/content/1541025858.html                    |
|     | springboot：jpa：@repository的使用技巧（CRUD以及模糊查询等） | https://blog.csdn.net/weixin_38750084/article/details/102670177 |

---
## Notion 筆記
Notion搜尋：SpringBoot + Swagger