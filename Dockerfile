# 拉取编译环境
FROM maven:3.6.1 as builder

#拷贝源码到固定的目录，注意前面有个 '.'
COPY . /project

# 切换到源码目录
WORKDIR /project

# 使用maven进行编译
RUN mvn clean package -Dmaven.test.skip=true

# 拉取运行环境，这个镜像打包出的镜像比较小，如需要可换成oracle的jre
FROM fabric8/java-alpine-openjdk8-jre
ENV PROJECT_NAME  "rescue-backend"
ENV PROJECT_VERSION  "0.0.1"
ENV PORT "3088"

# 从编译好的镜像中将jar拷贝到运行时容器
COPY --from=builder /project/target/$PROJECT_NAME-$ROJECT_VERSION.jar /

# 容器启动时执行的命令，这里可加jvm参数
ENTRYPOINT ["java","-jar","$PROJECT_NAME-$ROJECT_VERSION.jar"]

# 开放端口，根据自己的配置进行开放
EXPOSE $PORT
