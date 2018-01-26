# nats-streaming-example
[![Build Status](https://travis-ci.org/gregwhitaker/nats-streaming-example.svg?branch=master)](https://travis-ci.org/gregwhitaker/nats-streaming-example)

An example of using [NATS](https://nats.io) for streaming messaging.

This example contains a service that pushes 100 numbers to NATS streaming cluster named `test-cluster` and a stream named 
`numbers-stream`. Clients can subscribe to that stream and get all numbers that have been produced since the service started.

## Prerequisites
The examples require a local NATS streaming server to be running. To start a NATS streaming server as a Docker container run the following commands:

    $ docker pull nats-streaming
    $ docker run -p 4222:4222 -p 8222:8222 -d --name nats-streaming nats-streaming

## Running the Example
### Start the Streaming Service
You can start the [Streaming Service](streaming-service/README.md) using the following command:

    $ ./gradlew :streaming-service:run

Once the client and service is running you will see messages similar to the following in the terminal:

    [main] INFO nats.example.streaming.service.Main - Starting NATS Example Streaming Service 374b5838-9040-4482-a014-97453cdb06a4
    [main] INFO nats.example.streaming.service.Main - PUBLISHING: 0
    [main] INFO nats.example.streaming.service.Main - PUBLISHING: 1
    [main] INFO nats.example.streaming.service.Main - PUBLISHING: 2
    
### Start the Streaming Client
You can start the [Streaming Client](streaming-client/README.md) using the following command:

    $ ./gradlew :streaming-client:run
    
Once the client and service is running you will see messages similar to the following in the terminal:

    [main] INFO nats.example.streaming.client.Main - Starting NATS Example Streaming Client ec47ba2f-b210-42aa-90bd-cbe661c85b7e
    [jnats-subscriptions] INFO nats.example.streaming.client.Main - RECEIVED: 0
    [jnats-subscriptions] INFO nats.example.streaming.client.Main - RECEIVED: 1
    [jnats-subscriptions] INFO nats.example.streaming.client.Main - RECEIVED: 2

Notice how the stream was persisted by the NATS streaming server and all messages were delivered to the client to catch it up to the currently emitted message.

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/gregwhitaker/nats-streaming-example/issues).

## License
MIT License

Copyright (c) 2018 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
