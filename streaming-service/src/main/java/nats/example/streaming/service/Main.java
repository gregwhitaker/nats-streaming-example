package nats.example.streaming.service;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Starts a service that connects to the "test-cluster" and pushes 100 numbers to the "numbers-stream" stream.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        // Generate a random client id for service
        String clientId = UUID.randomUUID().toString();

        LOGGER.info("Starting NATS Example Streaming Service " + clientId);

        // Create NATS connection
        Connection conn = Nats.connect("nats://localhost:4222");

        // Create a Streaming Connection Factory with NATS connection
        StreamingConnectionFactory connFactory = new StreamingConnectionFactory("test-cluster", clientId);
        connFactory.setNatsConnection(conn);

        // Create Streaming connection with Connection Factory
        StreamingConnection streamingConn = connFactory.createConnection();

        for (int i = 0; i < 100; i++) {
            LOGGER.info("PUBLISHING: " + i);
            streamingConn.publish("numbers-stream", Integer.toString(i).getBytes());
            Thread.sleep(1_000);
        }

        streamingConn.close();
    }
}
