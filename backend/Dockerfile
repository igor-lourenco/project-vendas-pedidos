FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/vendas-pedidos-0.0.1-SNAPSHOT.jar vendas-pedidos.jar
ENTRYPOINT ["java","-jar","/vendas-pedidos.jar"]