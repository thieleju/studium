# terminal window 1
cd kafka_2.13-3.5.0/
bin/zookeeper-server-start.sh config/zookeeper.properties

# terminal window 2
cd kafka_2.13-3.5.0/
bin/kafka-server-start.sh config/server.properties

# terminal window 3
cd kafka_2.13-3.5.0/
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092

# terminal window 4 - 1 (vertical split)
cd kafka_2.13-3.5.0/
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092

# terminal window 4 - 2 (vertical split)
cd kafka_2.13-3.5.0/
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
