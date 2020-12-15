package org.wso2.custom.event.handler.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service.proto")
public final class serviceGrpc {

  private serviceGrpc() {}

  public static final String SERVICE_NAME = "service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.Empty,
      org.wso2.custom.event.handler.grpc.Service.HandlerName> getGetNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getName",
      requestType = org.wso2.custom.event.handler.grpc.Service.Empty.class,
      responseType = org.wso2.custom.event.handler.grpc.Service.HandlerName.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.Empty,
      org.wso2.custom.event.handler.grpc.Service.HandlerName> getGetNameMethod() {
    io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.Empty, org.wso2.custom.event.handler.grpc.Service.HandlerName> getGetNameMethod;
    if ((getGetNameMethod = serviceGrpc.getGetNameMethod) == null) {
      synchronized (serviceGrpc.class) {
        if ((getGetNameMethod = serviceGrpc.getGetNameMethod) == null) {
          serviceGrpc.getGetNameMethod = getGetNameMethod = 
              io.grpc.MethodDescriptor.<org.wso2.custom.event.handler.grpc.Service.Empty, org.wso2.custom.event.handler.grpc.Service.HandlerName>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service", "getName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.custom.event.handler.grpc.Service.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.custom.event.handler.grpc.Service.HandlerName.getDefaultInstance()))
                  .setSchemaDescriptor(new serviceMethodDescriptorSupplier("getName"))
                  .build();
          }
        }
     }
     return getGetNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.MessageContext,
      org.wso2.custom.event.handler.grpc.Service.Priority> getGetPriorityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPriority",
      requestType = org.wso2.custom.event.handler.grpc.Service.MessageContext.class,
      responseType = org.wso2.custom.event.handler.grpc.Service.Priority.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.MessageContext,
      org.wso2.custom.event.handler.grpc.Service.Priority> getGetPriorityMethod() {
    io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.MessageContext, org.wso2.custom.event.handler.grpc.Service.Priority> getGetPriorityMethod;
    if ((getGetPriorityMethod = serviceGrpc.getGetPriorityMethod) == null) {
      synchronized (serviceGrpc.class) {
        if ((getGetPriorityMethod = serviceGrpc.getGetPriorityMethod) == null) {
          serviceGrpc.getGetPriorityMethod = getGetPriorityMethod = 
              io.grpc.MethodDescriptor.<org.wso2.custom.event.handler.grpc.Service.MessageContext, org.wso2.custom.event.handler.grpc.Service.Priority>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service", "getPriority"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.custom.event.handler.grpc.Service.MessageContext.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.custom.event.handler.grpc.Service.Priority.getDefaultInstance()))
                  .setSchemaDescriptor(new serviceMethodDescriptorSupplier("getPriority"))
                  .build();
          }
        }
     }
     return getGetPriorityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.Event,
      org.wso2.custom.event.handler.grpc.Service.Log> getHandleEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "handleEvent",
      requestType = org.wso2.custom.event.handler.grpc.Service.Event.class,
      responseType = org.wso2.custom.event.handler.grpc.Service.Log.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.Event,
      org.wso2.custom.event.handler.grpc.Service.Log> getHandleEventMethod() {
    io.grpc.MethodDescriptor<org.wso2.custom.event.handler.grpc.Service.Event, org.wso2.custom.event.handler.grpc.Service.Log> getHandleEventMethod;
    if ((getHandleEventMethod = serviceGrpc.getHandleEventMethod) == null) {
      synchronized (serviceGrpc.class) {
        if ((getHandleEventMethod = serviceGrpc.getHandleEventMethod) == null) {
          serviceGrpc.getHandleEventMethod = getHandleEventMethod = 
              io.grpc.MethodDescriptor.<org.wso2.custom.event.handler.grpc.Service.Event, org.wso2.custom.event.handler.grpc.Service.Log>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service", "handleEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.custom.event.handler.grpc.Service.Event.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.custom.event.handler.grpc.Service.Log.getDefaultInstance()))
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
    public void getName(org.wso2.custom.event.handler.grpc.Service.Empty request,
        io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.HandlerName> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNameMethod(), responseObserver);
    }

    /**
     */
    public void getPriority(org.wso2.custom.event.handler.grpc.Service.MessageContext request,
        io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.Priority> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPriorityMethod(), responseObserver);
    }

    /**
     */
    public void handleEvent(org.wso2.custom.event.handler.grpc.Service.Event request,
        io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.Log> responseObserver) {
      asyncUnimplementedUnaryCall(getHandleEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.wso2.custom.event.handler.grpc.Service.Empty,
                org.wso2.custom.event.handler.grpc.Service.HandlerName>(
                  this, METHODID_GET_NAME)))
          .addMethod(
            getGetPriorityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.wso2.custom.event.handler.grpc.Service.MessageContext,
                org.wso2.custom.event.handler.grpc.Service.Priority>(
                  this, METHODID_GET_PRIORITY)))
          .addMethod(
            getHandleEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.wso2.custom.event.handler.grpc.Service.Event,
                org.wso2.custom.event.handler.grpc.Service.Log>(
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
    public void getName(org.wso2.custom.event.handler.grpc.Service.Empty request,
        io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.HandlerName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPriority(org.wso2.custom.event.handler.grpc.Service.MessageContext request,
        io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.Priority> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPriorityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void handleEvent(org.wso2.custom.event.handler.grpc.Service.Event request,
        io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.Log> responseObserver) {
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
    public org.wso2.custom.event.handler.grpc.Service.HandlerName getName(org.wso2.custom.event.handler.grpc.Service.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.wso2.custom.event.handler.grpc.Service.Priority getPriority(org.wso2.custom.event.handler.grpc.Service.MessageContext request) {
      return blockingUnaryCall(
          getChannel(), getGetPriorityMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.wso2.custom.event.handler.grpc.Service.Log handleEvent(org.wso2.custom.event.handler.grpc.Service.Event request) {
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
    public com.google.common.util.concurrent.ListenableFuture<org.wso2.custom.event.handler.grpc.Service.HandlerName> getName(
        org.wso2.custom.event.handler.grpc.Service.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.wso2.custom.event.handler.grpc.Service.Priority> getPriority(
        org.wso2.custom.event.handler.grpc.Service.MessageContext request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPriorityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.wso2.custom.event.handler.grpc.Service.Log> handleEvent(
        org.wso2.custom.event.handler.grpc.Service.Event request) {
      return futureUnaryCall(
          getChannel().newCall(getHandleEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_NAME = 0;
  private static final int METHODID_GET_PRIORITY = 1;
  private static final int METHODID_HANDLE_EVENT = 2;

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
        case METHODID_GET_NAME:
          serviceImpl.getName((org.wso2.custom.event.handler.grpc.Service.Empty) request,
              (io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.HandlerName>) responseObserver);
          break;
        case METHODID_GET_PRIORITY:
          serviceImpl.getPriority((org.wso2.custom.event.handler.grpc.Service.MessageContext) request,
              (io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.Priority>) responseObserver);
          break;
        case METHODID_HANDLE_EVENT:
          serviceImpl.handleEvent((org.wso2.custom.event.handler.grpc.Service.Event) request,
              (io.grpc.stub.StreamObserver<org.wso2.custom.event.handler.grpc.Service.Log>) responseObserver);
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
      return org.wso2.custom.event.handler.grpc.Service.getDescriptor();
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
      synchronized (serviceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new serviceFileDescriptorSupplier())
              .addMethod(getGetNameMethod())
              .addMethod(getGetPriorityMethod())
              .addMethod(getHandleEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
