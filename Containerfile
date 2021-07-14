FROM registry.access.redhat.com/ubi8/openjdk-11-runtime:1.9

COPY target/quarkus-app .

CMD [ "java", "-jar", "quarkus-run.jar" ]