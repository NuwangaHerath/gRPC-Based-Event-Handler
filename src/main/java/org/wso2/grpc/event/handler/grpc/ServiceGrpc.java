package org.wso2.grpc.event.handler.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service.proto")
public final class ServiceGrpc {

  private ServiceGrpc() {}

  public static final String SERVICE_NAME = "service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.wso2.grpc.event.handler.grpc.Service.Event,
      org.wso2.grpc.event.handler.grpc.Service.Log> getHandleEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "handleEvent",
      requestType = org.wso2.grpc.event.handler.grpc.Service.Event.class,
      responseType = org.wso2.grpc.event.handler.grpc.Service.Log.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.wso2.grpc.event.handler.grpc.Service.Event,
      org.wso2.grpc.event.handler.grpc.Service.Log> getHandleEventMethod() {
    io.grpc.MethodDescriptor<org.wso2.grpc.event.handler.grpc.Service.Event, org.wso2.grpc.event.handler.grpc.Service.Log> getHandleEventMethod;
    if ((getHandleEventMethod = ServiceGrpc.getHandleEventMethod) == null) {
      synchronized (ServiceGrpc.class) {
        if ((getHandleEventMethod = ServiceGrpc.getHandleEventMethod) == null) {
          ServiceGrpc.getHandleEventMethod = getHandleEventMethod =
              io.grpc.MethodDescriptor.<org.wso2.grpc.event.handler.grpc.Service.Event, org.wso2.grpc.event.handler.grpc.Service.Log>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service", "handleEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.grpc.event.handler.grpc.Service.Event.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.grpc.event.handler.grpc.Service.Log.getDefaultInstance()))
                  .setSchemaDescriptor(new serviceMethodDescriptorSupplier("handleEvent"))
                  .build();
          }
        }
     }
     return getHandleEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static serviceStub newStub(io.grpc.Channel channel) {
    return new serviceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static serviceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new serviceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static serviceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new serviceFutureStub(channel);
  }

  /**
   */
  public static abstract class serviceImplBase implements io.grpc.BindableService {

    /**
     */
    public void handleEvent(org.wso2.grpc.event.handler.grpc.Service.Event request,
        io.grpc.stub.StreamObserver<org.wso2.grpc.event.handler.grpc.Service.Log> responseObserver) {
      asyncUnimplementedUnaryCall(getHandleEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHandleEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.wso2.grpc.event.handler.grpc.Service.Event,
                org.wso2.grpc.event.handler.grpc.Service.Log>(
                  this, METHODID_HANDLE_EVENT)))
          .build();
    }
  }

  /**
   */
  public static final class serviceStub extends io.grpc.stub.AbstractStub<serviceStub> {
    private serviceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private serviceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected serviceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new serviceStub(channel, callOptions);
    }

    /**
     */
    public void handleEvent(org.wso2.grpc.event.handler.grpc.Service.Event request,
        io.grpc.stub.StreamObserver<org.wso2.grpc.event.handler.grpc.Service.Log> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHandleEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class serviceBlockingStub extends io.grpc.stub.AbstractStub<serviceBlockingStub> {
    private serviceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private serviceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected serviceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new serviceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.wso2.grpc.event.handler.grpc.Service.Log handleEvent(org.wso2.grpc.event.handler.grpc.Service.Event request) {
      return blockingUnaryCall(
          getChannel(), getHandleEventMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class serviceFutureStub extends io.grpc.stub.AbstractStub<serviceFutureStub> {
    private serviceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private serviceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected serviceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new serviceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.wso2.grpc.event.handler.grpc.Service.Log> handleEvent(
        org.wso2.grpc.event.handler.grpc.Service.Event request) {
      return futureUnaryCall(
          getChannel().newCall(getHandleEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final serviceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(serviceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDLE_EVENT:
          serviceImpl.handleEvent((org.wso2.grpc.event.handler.grpc.Service.Event) request,
              (io.grpc.stub.StreamObserver<org.wso2.grpc.event.handler.grpc.Service.Log>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class serviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    serviceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.wso2.grpc.event.handler.grpc.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("service");
    }
  }

  private static final class serviceFileDescriptorSupplier
      extends serviceBaseDescriptorSupplier {
    serviceFileDescriptorSupplier() {}
  }

  private static final class serviceMethodDescriptorSupplier
      extends serviceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    serviceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new serviceFileDescriptorSupplier())
              .addMethod(getHandleEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
