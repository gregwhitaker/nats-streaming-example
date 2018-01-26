package nats.example.streaming.service;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.streaming.NatsStreaming;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 *
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        // Generate a random client id for consumer
        String clientId = UUID.randomUUID().toString();

        LOGGER.info("Starting NATS Example Streaming Service " + clientId);

        // Create NATS connection
        Connection conn = Nats.connect("nats://localhost:4222");

        // Create a Streaming Connection Factory with NATS connection
        StreamingConnectionFactory connFactory = new StreamingConnectionFactory("example-cluster", clientId);
        connFactory.setNatsConnection(conn);

        // Create Streaming connection with Connection Factory
        StreamingConnection streamingConn = connFactory.createConnection();
    }
}
