# streaming-service
Starts an example NATS streaming service that publishes the numbers 0 - 99 at once second intervals to the `numbers-stream` stream on cluster `test-cluster`.
        
## Running the Service
You can run the example service using the following command:

    $ ../gradlew :streaming-service:run