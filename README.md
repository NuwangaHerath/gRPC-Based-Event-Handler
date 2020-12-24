# gRPC Based Event Handler
This implementation of gRPC Based Event Handler enables users to implement platform independent event handlers to WSO2 Identity Server.
- [Getting Started](#getting-started)
- [Build from the source](#build-from-the-source)

## Getting Started
You can get a clear knowledge on configuring of the gRPC Based Event Handler by following this small guide which contains main sections listed below.

- [Implementing gRPC Server](#configuring-grpc-server)
- [Configuring Identity Server](#configuring-identity-server)
- [Running the sample](#running-the-sample)

Throughout the instructions `{wso2is-home}` is referred as the root directory of the WSO2 Identity Server.

### Implementing gRPC Server
Use [service.proto](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/blob/main/src/main/resources/service.proto) to implement the gRPC server.

You can download the `service.proto` file [here](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/releases/tag/v1.0.0).

Follow [this](https://grpc.io/docs/) documentation to implement a gRPC server in any preferred gRPC supported language.

You can find sample gRPC servers from the below table.

| Language | Link |
| ------ | ------ |
| Java | [gRPC Event Handler Server-Java](https://github.com/NuwangaHerath/grpc-custom-event-handler-server) |
| Python | [gRPC Event Handler Server-Python](https://github.com/NuwangaHerath/grpc-event-handler-server-python)|

- For this guide we used Python gRPC server.
- Note down the `host` and `port` of the server for [Identity Server Configurations](#configuring-identity-server).


### Configuring Identity Server
1. Download the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` from [here](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/releases/tag/v1.0.0) or [build from the source](#build-from-the-source).
2. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file into `{wso2is-home}/repository/component/dropins` directory.
3. Add following custom event configuration to `{wso2is-home}/repository/conf/deployment.toml` file to configure `identity-event.properties` file of the identity server.
- Replace the  values of `host` and `port` from the respective values you copied from the [previous step](#implementing-grpc-server).

```toml
[[event_handler]]
name="grpcBasedEventHandler"
subscriptions=["<events for subscribe>"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
```
- For this guide we will be using `POST_ADD_USER` as the subscription event.
- And host is identified as `localhost` and port is identified as`8010`.
```toml
[[event_handler]]
name="grpcBasedEventHandler"
subscriptions=["POST_ADD_USER"]
enable=true
properties.host="localhost"
properties.port="8010"
```

### Running the sample
1. Start the gRPC Server.
2. Start the Identity Server by executing following commands from `{wso2is-hom}/bin` directory.

```sh
For Windows
$ wso2server.bat --run

For Linux
$ sh wso2server.sh
```
3. You should be able to see the following log when the event handler is activated.
```
INFO {org.wso2.grpc.event.handler.internal.GrpcEventHandlerComponent} - gRPC event handler activated successfully.
```
4. Execute one of the subscribed events such as creating a user to verify the execution of the event handler.
5. You should be able to see following log
```
INFO {org.wso2.grpc.event.handler.GrpcEventHandler} - testing POST_ADD_USER event using GrpcEventHandler on Python gRPC server with UserName- testuser, TenantDomain- carbon.super

```

## Build from the source

1. Download/Clone the project into your local machine.
```sh
$ git clone https://github.com/NuwangaHerath/gRPC-Based-Event-Handler.git
```
2. Open a terminal from project directory of your machine.
2. Build the project using maven by executing following command in the terminal.
```sh
$ mvn clean install
```
3. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file from `target` directory.

