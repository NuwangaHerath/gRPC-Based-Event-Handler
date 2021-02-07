# gRPC Based Identity Event Handler
This implementation of gRPC Based Identity Event Handler enables users to implement platform independent event handlers to WSO2 Identity Server.

<img src="https://github.com/NuwangaHerath/images/blob/master/Architecture/Architecture.png" align="center" width="700">

- [Getting Started](#getting-started)
- [Build from the source](#build-from-the-source)
- [Generating TLS Certificates](#generating-tls-certificates)
- [gRPC Based Multiple Event Handlers](#grpc-based-multiple-event-handlers)

## Getting Started
You can get a clear knowledge on configuring of the gRPC Based Identity Event Handler by following this small guide which contains main sections listed below.

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

- For this guide, we used Python gRPC server.
- Note down the `host` and `port` of the server for [Identity Server Configurations](#configuring-identity-server).


### Configuring Identity Server
1. Download the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` from [here](https://github.com/NuwangaHerath/gRPC-Custom-Event-Handler/releases/tag/v1.0.0) or [build from the source](#build-from-the-source).
2. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file into `{wso2is-home}/repository/component/dropins` directory.
3. Add following custom event configuration to `{wso2is-home}/repository/conf/deployment.toml` file to configure `identity-event.properties` file of the identity server.
- Add the names of gRPC Based Identity Event Handlers as handlers in `grpc` configurations.

```toml
handlers=["<handler name>"]
```
- Replace the values of `host` and `port` from the respective values you copied from the [previous step](#implementing-grpc-server).

```toml
[[event_handler]]
name="<handler name>"
subscriptions=["<events for subscribe>"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
properties.hasCert="<true/false>"
properties.certPath="<Path_to_CA's_certificate>"
```
- If the gRPC server uses authentication using SSL/TLS certificates, set the value of `hasCert` as `true`. If not, set it as `false`
- If you set the value of `hasCert` as `true`, Give the full path of the CA's certificate(`ca-cert.pem`) generated from [Generating TLS Certificates](#generating-tls-certificates) as `certPath`. If not, just leave `certPath` blank.
- For this guide, we will be using the [gRPC Event Handler Server-Python](https://github.com/NuwangaHerath/grpc-event-handler-server-python) as gRPC server.
- For this guide, we will be using `PRE_ADD_USER` as the subscription event.
- And host is identified as `localhost` and port is identified as`8010`.

```toml
[[grpc]]
handlers=["grpcBasedeventHandlerPython"]
[[event_handler]]
name="grpcBasedEventHandlerPython"
subscriptions=["PRE_ADD_USER"]
enable=true
properties.host="localhost"
properties.port="8010"
properties.hasCert="true"
properties.certPath="<path_to_ca's-certificate>/ca-cert.pem"
```

### Running the sample
1. Start the gRPC Server.
2. Start the Identity Server by executing the following commands from `{wso2is-hom}/bin` directory.

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
5. You should be able to see the following log
```
INFO {org.wso2.grpc.event.handler.GrpcEventHandler} - testing POST_ADD_USER event using GrpcEventHandler on Python gRPC server with UserName- testuser, TenantDomain- carbon.super
```

## Build from the source

1. Download/Clone the project into your local machine.
```sh
$ git clone https://github.com/NuwangaHerath/gRPC-Based-Event-Handler.git
```
2. Open a terminal from the project directory of your machine.
2. Build the project using maven by executing the following command in the terminal.
```sh
$ mvn clean install
```
3. Copy the `org.wso2.grpc.event.handler-1.0.0-SNAPSHOT.jar` file from `target` directory.

## Generating TLS Certificates

1. Make sure to have `OpenSSL` installed in your PC.
2. Use the following script to generate TLS certificates.

```sh
rm *.pem
# 1. Generate CA's private key and self-signed certificate
openssl req -x509 -newkey rsa:4096 -days 365 -nodes -keyout ca-key.pem -out ca-cert.pem -subj "/C=US/ST=CA/L=Mountain View/O=WSO2/OU=WSO2/CN=localhost/emailAddress=example1@gmail.com"
# 2. Generate web server's private key and certificate signing request (CSR)
openssl req -newkey rsa:4096 -nodes -keyout server-key.pem -out server-req.pem -subj "/C=LK/ST=WP/L=Colombo/O=GRPC/OU=GRPC/CN=localhost/emailAddress=example2@gmail.com"
# 3. Use CA's private key to sign web server's CSR and get back the signed certificate
openssl x509 -req -in server-req.pem -days 60 -CA ca-cert.pem -CAkey ca-key.pem -CAcreateserial -out server-cert.pem -extfile server-ext.cnf
```
You have to provide identity information for the certificates. To do that add the -subj (subject) option to the OpenSSL req command as you can see above.
In the subject string,
- `/C` for the country code
- `/ST` for the state or province
- `/L` for the locality name or city
- `/O` for the organization 
- `/OU` for the organization unit
- `/CN` for the common name or domain name
- `/emailAddress` for the email address

Make sure to give different values as identity information for the self-signed certificate and the server's certificate. 

Use the path name of `ca-cert.pem` for [Identity Server Configurations](#configuring-identity-server).

Use `server-key.pem` and `server-cert.pem` for the step, [Implementing gRPC Server](#implementing-grpc-server).

## gRPC Based Multiple Event Handlers

gRPC Based Multiple Event Handlers enables users to implement platform-independent multiple identity event handlers to WSO2 Identity Server.

<img src="https://github.com/NuwangaHerath/images/blob/master/Architecture/multi.jpeg" align="center" width="700">

- Add following custom event configuration to `{wso2is-home}/repository/conf/deployment.toml` file to configure `identity-event.properties` file of the identity server.
- Add event handler names as `handlers` in `grpc` configuration section.

```toml
[[grpc]]
handlers=["<handler-name-1>","<handler-name-2>"
```
- Add event handler configurations for each event handler which has unique handler properties as every handler is required connect with an unique gRPC server
```toml
[[event_handler]]
name="<handler-name-1>"
subscriptions=["<events for subscribe>"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
properties.hasCert="<true/false>"
properties.certPath="<Path_to_CA's_certificate>"
[[event_handler]]
name="<handler-name-2>"
subscriptions=["<events for subscribe>"]
enable=true
properties.host="<gRPC_server_host>"
properties.port="<gRPC_server_port>"
properties.hasCert="<true/false>"
properties.certPath="<Path_to_CA's_certificate>"
```
