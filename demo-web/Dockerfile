# 构建容器
FROM openjdk:11-jre
# Author
MAINTAINER wangjiahao Shanghai 1332661444@qq.com
# 创建目录
RUN mkdir -p /java-server
# 指定路径z
WORKDIR /java-server
# 复制jar文件到路径
ADD target/demo.jar /java-server/app.jar
# 挂载几个有用的文件夹 比如日志
VOLUME ["/tmp","/logs"]
# 声明一个环境参数用来动态启用配置文件 默认dev
ENV ACTIVE=dev
# 暴露端口
EXPOSE 8080
#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java", "-jar", "app.jar","--spring.profiles.active=${ACTIVE}"]
