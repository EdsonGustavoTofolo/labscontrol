FROM maven:3.6.3-jdk-8-slim AS builder

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

FROM payara/server-full:5.2022.2

# HTTP
EXPOSE 8080:8080
# HTTPS
EXPOSE 8081:8081
# CONSOLE ADMIN
EXPOSE 4848:4848
# DEBUG
EXPOSE 9009:9009

ARG MYSQL_CONTAINER_NAME=mysql_57

USER root

RUN \
    apt-get update \
    && apt-get -y install curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && curl -o mysql-connector.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.29/mysql-connector-java-8.0.29.jar \
    && mkdir /opt/connector/ \
    && mv mysql-connector.jar /opt/connector/mysql-connector.jar

RUN echo add-library /opt/connector/mysql-connector.jar >> $POSTBOOT_COMMANDS
RUN echo create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.DataSource --property User=root:Password=root:PortNumber=3306:ServerName=localhost:UseSSL=false:DatabaseName=labscontrol:Port=3306:URL="jdbc\:mysql\://$MYSQL_CONTAINER_NAME\:3306\/labscontrol":AllowPublicKeyRetrieval=true labscontrolpool >> $POSTBOOT_COMMANDS
RUN echo create-jdbc-resource --connectionpoolid labscontrolpool jdbc/labscontrol >> $POSTBOOT_COMMANDS

COPY --from=builder /home/app/target/labscontrol.war ${DEPLOY_DIR}