# helloworld_microprofile

## Local Development

1. `mvn clean liberty:dev`
1. After Liberty starts (`CWWKF0011I`), access <http://localhost:9080/> or <https://localhost:9443/>
    1. For secure endpoints such as `/metrics`, user `admin` and password `grep keystore_password target/liberty/wlp/usr/servers/helloworld_microprofileServer/server.env`
1. To run tests, press `Enter`
1. Logs:
    1. `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/messages.log`
    1. `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/console.log`
    1. `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/trace.log`
    1. `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/http_access.log`
    1. `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/verbosegc*log`
    1. Javacores/etc. in `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/diagnostics/`
1. To stop, `Ctrl^C`

## Packaging and running a container

1. `mvn package`
1. The above command prints the `docker run` command to use in the last `[echo]` line. For example:
   ```
   docker run --rm -p 9080:9080 -p 9443:9443 -it kgibm/helloworld_microprofile:0.1.0
   ```
1. After Liberty starts (`CWWKF0011I`), access <http://localhost:9080/> or <https://localhost:9443/>
    1. For secure endpoints such as `/metrics`, user `admin` and password `grep keystore_password /opt/ol/wlp/usr/servers/defaultServer/server.env`
1. Logs:
    1. `/logs/messages.log`
    1. `/logs/trace.log`
    1. `/logs/http_access.log`
    1. `/opt/ol/wlp/output/defaultServer/logs/verbosegc*log`
    1. Javacores/etc. in `/opt/ol/wlp/output/defaultServer/logs/diagnostics/`

## MicroProfile Links

* Health Check: <http://localhost:9080/health/>
* Metrics: <http://localhost:9080/metrics/>
* OpenAPI JSON: <http://localhost:9080/openapi/>
* Swagger UI: <http://localhost:9080/openapi/ui/>

## Appendix

1. Liberty maven targets: <https://github.com/OpenLiberty/ci.maven>
1. MicroProfile Overview: <https://openliberty.io/docs/latest/microprofile.html>
1. `microProfile-4.0` feature: <https://openliberty.io/docs/latest/reference/feature/microProfile-4.0.html>
