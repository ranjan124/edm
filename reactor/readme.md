docker exec --workdir /opt/kafka/bin/ -it broker sh
./kafka-topics.sh --bootstrap-server localhost:9092 --topic hello-world --create
./kafka-topics.sh --bootstrap-server localhost:9092 --topic hello-world --delete
./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic order-events --property key.separator=: --property parse.key=true
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic order-events --property print.offset=true --property print.key=true --group cg
