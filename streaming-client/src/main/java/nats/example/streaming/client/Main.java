package nats.example.streaming.client;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import io.nats.streaming.Subscription;
import io.nats.streaming.SubscriptionOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Starts a client that connects to the "test-cluster" and streams 100 numbers in from the "numbers-stream" stream.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        // Generate a random client id for service
        String clientId = UUID.randomUUID().toString();

        LOGGER.info("Starting NATS Example Streaming Client " + clientId);

        // Create NATS connection
        Connection conn = Nats.connect("nats://localhost:4222");

        // Create a Streaming Connection Factory with NATS connection
        StreamingConnectionFactory connFactory = new StreamingConnectionFactory("test-cluster", clientId);
        connFactory.setNatsConnection(conn);

        // Create Streaming connection with Connection Factory
        StreamingConnection streamingConn = connFactory.createConnection();

        final CountDownLatch latch = new CountDownLatch(100);

        Subscription sub = streamingConn.subscribe("numbers-stream", m -> {
            LOGGER.info("RECEIVED: " + new String(m.getData()));
            latch.countDown();
        }, new SubscriptionOptions.Builder().deliverAllAvailable().build());

        latch.await();

        sub.unsubscribe();

        streamingConn.close();
    }
}
