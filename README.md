# Flink-Scala-Learning
This project is a POC of Flink, Kafka using Scala

# Starting
1. _**cd**_ to the kafka install directory
```
    bin/zookeeper-server-start.sh config/zookeeper.properties
    bin/kafka-server-start.sh config/server.properties.
```
2. Create the required topics
```
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic input
    bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 6 --topic output
```
# Running
1. _**mvn clean package**_ in the project
2.  _**cd**_ to the flink installed directory
3. Start the flink cluster
```
    ./bin/start-cluster.sh
```
3. Run the job
```
    ./bin/flink run /<path to the project>/target/flink-kafka-stream-1.0-SNAPSHOT.jar
```
# Input - Output
1. Give input to the input topic
```
   bin/kafka-console-producer.sh --broker-list localhost:9092 --topic input
```
Then give a string
2. View the output from the topic output
```
    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic output --from-beginning
```
