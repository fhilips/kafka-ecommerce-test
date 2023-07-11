## kafka-ecommerce-test

Projeto realizado no curso de Kafka da alura, para testes com kafka.

### Comandos:

Start zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

#### Start kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties

#### Listar topicos
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

#### Decrição dos topicos
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --describe

#### Criar topicos
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVO_PEDIDO

#### Alterar numero de partições de um topic
.\bin\windows\kafka-topics.bat --alter --bootstrap-server localhost:9092 --topic ECOMMERCE_NEW_ORDER --partitions 3

#### Descrever os grupos 
.\bin\windows\kafka-consumer-groups.bat --all-groups --bootstrap-server localhost:9092 --describe
