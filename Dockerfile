FROM openliberty/open-liberty:full-java8-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Example" \
  org.opencontainers.image.vendor="Example" \
  org.opencontainers.image.url="https://github.com/kgibm/helloworld_microprofile" \
  org.opencontainers.image.source="https://github.com/kgibm/helloworld_microprofile" \
  org.opencontainers.image.version="${VERSION}" \
  org.opencontainers.image.revision="${REVISION}" \
  vendor="Example" \
  name="helloworld_microprofile" \
  version="${VERSION}-${REVISION}" \
  summary="Hello World MicroProfile Application" \
  description="Example demonstrating a MicroProfile application"

COPY --chown=1001:0 target/helloworld_microprofile.war /config/apps/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/server.xml /config/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/bootstrap.properties /config/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/jvm.options /config/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/configDropins/defaults/quick-start-security.xml /config/configDropins/defaults/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/configDropins/overrides/liberty-plugin-variable-config.xml /config/configDropins/overrides/

EXPOSE 9080 9443

RUN configure.sh
