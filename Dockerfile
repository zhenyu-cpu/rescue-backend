# 拉取运行环境，这个镜像打包出的镜像比较小，如需要可换成oracle的jre
FROM fabric8/java-alpine-openjdk8-jre
ENV PROJECT_NAME  "rescue-backend"
ENV PROJECT_VERSION  "0.0.1"
ENV PORT "3088"

# 从编译好的镜像中将jar拷贝到运行时容器
COPY ./target/$PROJECT_NAME-$ROJECT_VERSION.jar /$PROJECT_NAME.jar

# 容器启动时执行的命令，这里可加jvm参数
ENTRYPOINT ["nohup","java","-jar","$PROJECT_NAME.jar","&"]

# 开放端口，根据自己的配置进行开放
EXPOSE $PORT
