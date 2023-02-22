FROM adoptopenjdk/openjdk8:x86_64-centos-jre8u312-b07

RUN mkdir -p /home/admin/cloudrun/

ADD ./target/cloudrun-0.0.1.jar /home/admin/cloudrun/

RUN chown -R 777 /home/admin/cloudrun/

WORKDIR /home/admin/cloudrun/

ENTRYPOINT ["java","-jar","cloudrun-0.0.1.jar"]
