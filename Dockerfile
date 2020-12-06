FROM java:8  
COPY ./ObslugaBazy.java /
COPY ./mysql-connector-java-5.1.6.jar /
WORKDIR /  
RUN javac ObslugaBazy.java
CMD ["java", "-classpath", "mysql-connector-java-5.1.6.jar:.","ObslugaBazy"]
