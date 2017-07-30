# Apache Kafka Tutorial

Einfaches Beispiel für einen Kafka-gesteuerten Ablauf

Lokale Installtion nötig (https://dzone.com/articles/running-apache-kafka-on-windows-os):
Apache Zookeeper (http://zookeeper.apache.org/releases.html) -> starten mit zkserver
Apache Kafka (http://kafka.apache.org/) -> starten mit .\bin\windows\kafka-server-start.bat .\config\server.properties

Example from https://github.com/eugenp/tutorials/tree/master/spring-kafka

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic baeldung
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic partitioned
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic filtered
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic greeting

Create topic
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testtopic


Simple calls
Create a local producer
kafka-console-producer.bat --broker-list localhost:9092 --topic test

Create a local consumer
kafka-console-consumer.bat --zookeeper localhost:2181 --topic test


List Topics
kafka-topics.bat --list --zookeeper localhost:2181
Describe Topic
kafka-topics.bat --describe --zookeeper localhost:2181 --topic [Topic Name]
Read messages from beginning
kafka-console-consumer.bat --zookeeper localhost:2181 --topic [Topic Name] --from-beginning
Delete Topic
kafka-run-class.bat kafka.admin.TopicCommand --delete --topic [topic_to_delete] --zookeeper localhost:2181
