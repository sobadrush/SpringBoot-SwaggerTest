FROM openjdk:latest
MAINTAINER Roger-Lo
# 設定 TimeZone，避免容器內部有時差問題
ENV TZ="Asia/Taipei"
# 設定jar檔名稱
ENV JAR_NAME="SprintBoot-SwaggerTest.jar"
# 複製jar檔到容器內(COPY不會解壓縮，ADD會解壓縮)
COPY ./target/SpringBoot-SwaggerTest-*.jar /SpringBootJar/${JAR_NAME}
# 切換工作目錄
WORKDIR /SpringBootJar/
# 修改 SprintBoot-SwaggerTest.jar 的時間戳記為目前時間
RUN sh -c 'touch ${JAR_NAME}'
ENTRYPOINT ["java", "-jar", "SprintBoot-SwaggerTest.jar"]