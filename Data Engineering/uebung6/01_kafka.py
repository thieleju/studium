from kafka import KafkaConsumer
import json

# Kafka consumer erstellen
consumer = KafkaConsumer(
    'numbers',
    bootstrap_servers=['localhost:9092'],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    value_deserializer=lambda x: json.loads(x.decode('utf-8'))
)

count = 0
total_sum = 0

for message in consumer:
    new_value = int(message.value)  # Neue Zahl vom Kafka-Topic
    count += 1
    total_sum += new_value
    average = total_sum / count
    print(f'New value: {new_value}, New average: {average}')

