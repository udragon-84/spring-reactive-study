# vote-gateway

* 프로젝트 구동 하기전 선행 작업
  * server.port: 8080, 8081 
  * mysql8.0 설치: brew install mysql@8.0
  * Redis7.2.5 설치: brew install redis

* 팬 투표 도메인 관련 테이블 생성
    * CREATE TABLE fan_votes (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
userId VARCHAR(50) NOT NULL,
artistName VARCHAR(50) NOT NULL,
createdAt DATETIME(6) NOT NULL,
INDEX idx_user_id (userId)
);


* 로드벨런서를 통해서 앞단에서 받을 경우에는 gradle > bootJar로 빌드하여 service 2개로 띄울 것 
  * java -jar vote-gateway.jar --server.port=8080 
  * java -jar vote-gateway.jar --server.port=8081
* 로드벨런서 없이 진행할 경우 해당 서버만 구동하여 테스트 진행 가능
  