FROM openjdk:23

COPY out/artifacts/JavaJun_practica4_jar/JavaJun_practica4.jar /tmp/JavaJun_practica4.jar
WORKDIR /tmp
CMD ["java", "-jar", "/tmp/JavaJun_practica4.jar"]