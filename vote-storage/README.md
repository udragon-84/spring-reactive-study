# vote-storage

* 프로젝트 구동 하기전 선행 작업
  * server.port: 8083
  * vote-gateway, vote-processor 프로젝트에서 필요한 인프라 설치 후 구동 가능
  * 위 2개 프로젝트 README.md 참고

* 팬투표 정형 데이터 저장 테이블 생성
  * CREATE TABLE fan_votes_analyze (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    kafkaSendId VARCHAR(50) NOT NULL,
    userId VARCHAR(50) NOT NULL,
    artistName VARCHAR(50) NOT NULL,
    createdAt DATETIME(6) NOT NULL,
    INDEX fan_votes_analyze_idx_01 (userId),
    INDEX fan_votes_analyze_idx_02 (kafkaSendId)
    );

 