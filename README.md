# helloworld_microprofile

## Basics

1. `mvn clean install liberty:dev`
1. <http://localhost:9080/> or <https://localhost:9443/>
1. To stop, `Ctrl^C`

## Logs

Logs under `target/liberty/wlp/usr/servers/helloworld_microprofileServer/logs/`

## Security

Some endpoints such as `/metrics/` are secured. The username is `admin` and password may be found with:

```
grep keystore target/liberty/wlp/usr/servers/helloworld_microprofileServer/server.env
```

## MicroProfile Links

* Health Check: <http://localhost:9080/health/>
* Metrics: <http://localhost:9080/metrics/>
* OpenAPI JSON: <http://localhost:9080/openapi/>
* OpenAPI UI: <http://localhost:9080/openapi/ui/>

## Appendix

1. Liberty maven targets: <https://github.com/OpenLiberty/ci.maven>
1. MicroProfile Overview: <https://openliberty.io/docs/latest/microprofile.html>
1. `microProfile-4.0` feature: <https://openliberty.io/docs/latest/reference/feature/microProfile-4.0.html>
