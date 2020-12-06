FROM java:8  
COPY ./obslugaBazy.java /
WORKDIR /  
RUN javac obslugaBazy.java
CMD ["java", "-classpath", "mysql-connector-java-5.1.6-bin.jar:.","obslugaBazy"]
