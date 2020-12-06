FROM java:8  
COPY ./ObslugaBazy.java /
WORKDIR /  
#RUN javac ObslugaBazy.java
CMD ["java", "-classpath", "mysql-connector-java-5.1.6-bin.jar:.","ObslugaBazy"]
