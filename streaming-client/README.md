# streaming-client
Starts an example NATS streaming client that subscribes to the `numbers-stream` stream, on cluster `test-cluster`, and prints the numbers it receives.

The client subscribes with the `deliverAllAvailable` option which means it will receive all messages published to the stream
prior to it subscribing to "catch-up" to the current message.

## Running the Client
The client can be started by running the following command:

    $ ../gradlew :streaming-client:run