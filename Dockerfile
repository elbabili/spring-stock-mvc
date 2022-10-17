FROM openjdk:8
EXPOSE 8080
ADD target/devops-spring-stock.jar devops-spring-stock.jar
ENTRYPOINT ["java","-jar","/devops-spring-stock.jar"]