# gRPC Custom Event Handler


[![Build Status](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fwso2.org%2Fjenkins%2Fjob%2Fasgardio%2Fjob%2Fasgardio-tomcat-saml-agent%2F&style=flat)](https://wso2.org/jenkins/job/asgardio/job/asgardio-tomcat-saml-agent/) [![Stackoverflow](https://img.shields.io/badge/Ask%20for%20help%20on-Stackoverflow-orange)](https://stackoverflow.com/questions/tagged/wso2is)
[![Join the chat at https://join.slack.com/t/wso2is/shared_invite/enQtNzk0MTI1OTg5NjM1LTllODZiMTYzMmY0YzljYjdhZGExZWVkZDUxOWVjZDJkZGIzNTE1NDllYWFhM2MyOGFjMDlkYzJjODJhOWQ4YjE](https://img.shields.io/badge/Join%20us%20on-Slack-%23e01563.svg)](https://join.slack.com/t/wso2is/shared_invite/enQtNzk0MTI1OTg5NjM1LTllODZiMTYzMmY0YzljYjdhZGExZWVkZDUxOWVjZDJkZGIzNTE1NDllYWFhM2MyOGFjMDlkYzJjODJhOWQ4YjE)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/wso2/product-is/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/follow/wso2.svg?style=social&label=Follow)](https://twitter.com/intent/follow?screen_name=wso2)
---

## Getting Started
You can get a clear knowledge on configuring of the gRPC Custom Event Handler by following this small guide wich contains main sections listed below.

- [Configuring Event Handler](#configuring-event-handler)
- [Configuring Identity Server](#configuring-identity-server)
- [Configuring gRPC Server](#configuring-grpc-server)
- [Running the sample](#running-the-sample)

### Configuring Event Handler
1. Download/Clone the project into your machine.
2. Build the project using maven.
```sh
$ mvn clean install
```
3. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file into `{wso2is-home}/repository/component/dropins` directory.

### Configuring Identity Server
Add following custom event configuration to `{wso2is-home}/repository/conf/deployment.toml` file.
```sh
[[event_handler]]
name="grpcBasedEventHandler"
subscriptions=["PRE_ADD_USER","POST_ADD_USER"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
```
### Configuring gRPC Server
Use [service.proto](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/blob/main/src/main/resources/service.proto) to implement the gRPC server.
- You can find sample gRPC servers for Custom Event Handler from the below table.

| Language | Link |
| ------ | ------ |
| Java | [gRPC Event Handler Server-Java](https://github.com/NuwangaHerath/grpc-custom-event-handler-server) |
| Python | [gRPC Event Handler Server-Python](https://github.com/NuwangaHerath/grpc-event-handler-server-python)|

### Running the sample
