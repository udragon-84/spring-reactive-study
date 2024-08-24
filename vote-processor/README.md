# vote-processor

* 프로젝트 구동 하기전 선행 작업
  * server.port: 8082

* 카산드라 설치
    * brew install pyenv
    * pyenv install 3.8.12
    * pyenv global 3.8.12
    * https://cassandra.apache.org/_/download.html 5.0-RC1 다운로드
* 카산드라 인증 방식 변경
  * 카산드라 yaml 파일 위치: {cassandra_path}/cassandra/conf/cassandra.yaml
  * authenticator: org.apache.cassandra.auth.PasswordAuthenticator 
  * authorizer: org.apache.cassandra.auth.CassandraAuthorizer
* 카산드라 서버 구동 및 kill
  * 서버구동: cassandra
  * 카산드라 구동 상태 확인: nodetool status
  * 카산드라 cli: cqlsh -u {user} -p {password} 
  * 카프카 전송 이력 테이블 생성
  * CREATE TABLE vote_processor.fan_vote_events (
    id UUID PRIMARY KEY,
    userId text,
    createdAt timestamp,
    jsonData text
    );

* 카프카 설치
  * https://kafka.apache.org/downloads
  * 3.5.2 버전 다운로드
* 카프카 환경 변수 세팅
  * 브로커를 Kraft 모드로 실행 (주키퍼 필요없음 테스트용도)
  * config/server.properties 오픈 및 수정
    * process.roles=broker,controller
    * node.id=1
    * controller.quorum.voters=1@localhost:9093
    * controller.listener.names=CONTROLLER
    * listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
    * advertised.listeners=PLAINTEXT://localhost:9092
    * log.dirs=/Users/youchangkeun/Desktop/dev/messagebroker/kafka/logs
    * metadata.log.dir=/Users/youchangkeun/Desktop/dev/messagebroker/kafka/data
    * log.segment.bytes=1073741824
    * auto.create.topics.enable=true
  * 클러스터 ID 생성(최초 1회)
    * kafka-storage.sh random-uuid
  * Kafka 저장소 포맷(최초 1회)
    * 위 에서 생성된 난수를 넣을 것
    * kafka-storage.sh format -t PHUaomEeSNKtCzqr5A3ivg -c /Users/youchangkeun/Desktop/dev/messagebroker/kafka/config/server.properties
  * kafka 브로커 실행
    * kafka-server-start.sh /Users/youchangkeun/Desktop/dev/messagebroker/kafka/config/server.properties
    * 카프카 운용 필수 토픽 생성: kafka-topics.sh --bootstrap-server localhost:9092 --create --topic __consumer_offsets --partitions 50 --replication-factor 1
    * vote 토픽생성: kafka-topics.sh --bootstrap-server localhost:9092 --create --topic vote-topic --partitions 1 --replication-factor 1
    * 카프카 메시지 전송 테스트: kafka-console-producer.sh --bootstrap-server localhost:9092 --topic vote-topic
    * 카프카 메시지 수신 테스트: kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic vote-topic --from-beginning
    * 토픽삭제: kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic vote-topic
    * 토픽 리스트: kafka-topics.sh --bootstrap-server localhost:9092 --list
 