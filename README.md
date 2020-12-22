# gRPC Based Event Handler
gRPC Based Event Handler implements its methods on a remote gRPC server rather than implements in its own.Using this event handler, developers can be able to handle events only by configuring the `identity-event.properties` file of the WSO2 Identity Server and the gRPC server.
- [Getting Started](#getting-started)
- [Building from the source](#building-from-the-source)

## Getting Started
You can get a clear knowledge on configuring of the gRPC Based Event Handler by following this small guide which contains main sections listed below.

- [Configuring gRPC Server](#configuring-grpc-server)
- [Configuring Identity Server](#configuring-identity-server)
- [Running the sample](#running-the-sample)

Throughout the instructions `{wso2is-home}` is referred as the root directory of the WSO2 Identity Server.

### Configuring gRPC Server
Use [service.proto](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/blob/main/src/main/resources/service.proto) to implement the gRPC server.

You can download the `service.proto` file [here](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/releases/tag/v1.0.0).

Follow [this](https://grpc.io/docs/) documentation to implement a gRPC server in any preferred gRPC supported language.

You can find sample gRPC servers for Custom Event Handler from the below table.

| Language | Link |
| ------ | ------ |
| Java | [gRPC Event Handler Server-Java](https://github.com/NuwangaHerath/grpc-custom-event-handler-server) |
| Python | [gRPC Event Handler Server-Python](https://github.com/NuwangaHerath/grpc-event-handler-server-python)|

Note down the `host` and `port` of the server for [Identity Server Configurations](#configuring-identity-server).

- In the samples, It is used `localhost` as the server host and `8010` as the server port.


### Configuring Identity Server
1. Download the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` from [here](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/releases/tag/v1.0.0) or [Building from the source](#building-from-the-source).
2. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file into `{wso2is-home}/repository/component/dropins` directory.
3. Add following custom event configuration to `{wso2is-home}/repository/conf/deployment.toml` file.
```sh
[[event_handler]]
name="grpcBasedEventHandler"
subscriptions=["<events for subscribe>"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
```
- As a demo, `PRE_ADD_USER` and `POST_ADD_USER` are used as subscription events.
- And here it is used `localhost` as host and `8010` as port.
```sh
[[event_handler]]
name="grpcBasedEventHandler"
subscriptions=["PRE_ADD_USER","POST_ADD_USER"]
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
3. Check activation of the event handler by checking the logs.
```sh
INFO {org.wso2.grpc.event.handler.internal.GrpcEventHandlerComponent} - gRPC event handler activated successfully.
```
4. Execute the subscribed events to check the working of the event handler.
- For our samples, add a user to identity server as event handler subscribed `PRE_ADD_USER` and `POST_ADD_USER` events.
- check the logs in the terminal

## Building from the source

1. Download/Clone the project into your local machine.
2. Open a terminal from project directory of your machine.
2. Build the project using maven by executing following command in the terminal.
```sh
$ mvn clean install
```
3. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file from `targer` directory.

