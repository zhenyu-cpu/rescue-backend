# 拉取运行环境，这个镜像打包出的镜像比较小，如需要可换成oracle的jre
FROM fabric8/java-alpine-openjdk8-jre
ENV PROJECT_NAME  "rescue-backend"
ENV PROJECT_VERSION  "0.0.1"
ENV PORT "3088"
# 调整时区
RUN rm -f /etc/localtime \
&& ln -sv /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone
# 从编译好的镜像中将jar拷贝到运行时容器
COPY ./target/$PROJECT_NAME-$PROJECT_VERSION.jar /work/app.jar

# 容器启动时执行的命令，这里可加jvm参数
ENTRYPOINT ["nohup","java","-jar","app.jar","&"]

# 开放端口，根据自己的配置进行开放
EXPOSE $PORT