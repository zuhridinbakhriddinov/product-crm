ARG JAVA_VERSION=17
FROM openjdk:${JAVA_VERSION}
COPY target/task-0.0.1-SNAPSHOT.jar task.jar
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 CMD redis-cli ping || exit 1
CMD ["java","-jar","/task.jar"]