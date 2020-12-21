# gRPC Custom Event Handler


## Getting Started
You can get a clear knowledge on configuring of the gRPC Custom Event Handler by following this small guide which contains main sections listed below.

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
subscriptions=["<events for subscribe>"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
```
### Configuring gRPC Server
Use [service.proto](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/blob/main/src/main/resources/service.proto) to implement the gRPC server.
You can find sample gRPC servers for Custom Event Handler from the below table.

| Language | Link |
| ------ | ------ |
| Java | [gRPC Event Handler Server-Java](https://github.com/NuwangaHerath/grpc-custom-event-handler-server) |
| Python | [gRPC Event Handler Server-Python](https://github.com/NuwangaHerath/grpc-event-handler-server-python)|

Make sure to change `host` and `port` properties of the custom event configuration in the `{wso2is-home}/repository/conf/deployment.toml` file according to the server.

### Running the sample
1. Start the gRPC Server.
2. Start the Identity Server.
3. Check activation of the event handler by checking the logs.
```sh
INFO {org.wso2.grpc.event.handler.internal.GrpcEventHandlerComponent} - gRPC event handler activated successfully.
```
4. Execute the subscribed events to check the working of the event handler.

