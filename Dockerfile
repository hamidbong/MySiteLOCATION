FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn -B -DskipTests clean package


FROM tomcat:10.1-jdk21-temurin

ARG APP_UID=10001
ARG APP_GID=10001

RUN rm -rf /usr/local/tomcat/webapps/*

RUN apt-get update \
	&& apt-get install -y --no-install-recommends curl \
	&& rm -rf /var/lib/apt/lists/*

RUN groupadd --system --gid "${APP_GID}" appgroup \
	&& useradd --system --uid "${APP_UID}" --gid appgroup \
	   --no-create-home --home-dir /nonexistent --shell /usr/sbin/nologin appuser \
	&& mkdir -p /usr/local/tomcat/temp /usr/local/tomcat/work /usr/local/tomcat/logs \
	&& chown -R appuser:appgroup /usr/local/tomcat

COPY --from=build --chown=appuser:appgroup /app/target/MySiteLOCATION.war /usr/local/tomcat/webapps/MySiteLOCATION.war

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
	CMD curl -fsS http://localhost:8080/MySiteLOCATION/ || exit 1

USER appuser

CMD ["catalina.sh", "run"]
