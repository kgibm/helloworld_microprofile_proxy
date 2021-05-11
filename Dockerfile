FROM openliberty/open-liberty:full-java8-openj9-ubi

# These should be specified by `--build-arg` (e.g. in pom.xml)
ARG VERSION=0.0.0
ARG REVISION=unknown
ARG NAME=example
ARG AUTHORS=example@example.com
ARG VENDOR=Example
ARG SUMMARY=Example
ARG DESCRIPTION=Example
ARG URL=https://example.com/
ARG SOURCE=https://example.com/
ARG HTTP_PORT=9080
ARG HTTPS_PORT=9443

LABEL \
  org.opencontainers.image.authors="${AUTHORS}" \
  org.opencontainers.image.vendor="${VENDOR}" \
  org.opencontainers.image.url="${URL}" \
  org.opencontainers.image.source="${SOURCE}" \
  org.opencontainers.image.version="${VERSION}" \
  org.opencontainers.image.revision="${REVISION}" \
  vendor="${VENDOR}" \
  name="${NAME}" \
  version="${VERSION}-${REVISION}" \
  summary="${SUMMARY}" \
  description="${DESCRIPTION}" \
  maintainer="${AUTHORS}"

COPY --chown=1001:0 target/helloworld_microprofile.war /config/apps/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/server.xml /config/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/bootstrap.properties /config/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/jvm.options /config/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/configDropins/defaults/quick-start-security.xml /config/configDropins/defaults/

COPY --chown=1001:0 target/liberty/wlp/usr/servers/helloworld_microprofileServer/configDropins/overrides/liberty-plugin-variable-config.xml /config/configDropins/overrides/

EXPOSE ${HTTP_PORT} ${HTTPS_PORT}

RUN configure.sh
