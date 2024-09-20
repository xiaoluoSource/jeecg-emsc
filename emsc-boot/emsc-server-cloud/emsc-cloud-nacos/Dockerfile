FROM registry.cn-hangzhou.aliyuncs.com/dockerhub_mirror/java:21-anolis


WORKDIR /emsc-cloud-nacos

EXPOSE 8848

ADD ./emsc-cloud-nacos-3.7.0.jar ./

java -Dfile.encoding=utf-8 -Djava.security.egd=file:/dev/./urandom -jar emsc-cloud-nacos-3.7.0.jar/