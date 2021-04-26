import com.grpc_test_server.grpc.GreetingServiceGrpc;
import com.grpc_test_server.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:9200")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Andrew")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);

        while (response.hasNext()) {
            System.out.println(response.next());
        }
        channel.shutdown();
    }
}
