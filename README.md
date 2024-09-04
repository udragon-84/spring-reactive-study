# Spring Webflux Study

이메일: youzang7@gmail.com

도메인 설명: 사용자가 제한된 시간 내에 투표를 진행하며, 실시간으로 변동되는 인기 순위를 바탕으로 1위 가수를 결정하는 타임어택 형식의 인기투표 시스템입니다. 

* 기술스택:
  * BackEnd: Jdk17, SpringBoot, Spring-Cloud, Spring-WebFlux, Spring-R2DBC, Reactor-Redis, Reactor-Cassandra Reactor-Kafka, Lombok, 
  * Infra: Mysql8.0, Cassandra-5.0-rc1, Redis-7.0, Kafka-3.5.2

* 프로젝트 설명 (타임어택 기준으로 서버의 부하를 줄여야되기 때문에 전부 비동기 방식으로 개발 되었습니다.)
  * vote-loadbalancer: 가장 처음 EndPoint 진입점입니다. 로드벨런서 설정을 통해 vote-gateway 모듈을 라운드로빈 방식으로 호출합니다.
  * vote-gateway: 사용자 팬 투표 처리를 하기 위한 처음 진입점이며, 8080, 8081 2개 서버로 구성이 됩니다. 그리고 해당 서버에서 처리율 제한 역할까지 합니다(Redis사용).
  * vote-processor: 팬 투표 비즈니스 로직 처리를 담당하는 서버이며, 투표 비즈니스 처리 완료 후 카프카로 팬투표 데이터를 Publish 합니다. Kafka로 전송한 데이터 이력은 CassanDra에 json형식으로 데이터를 남깁니다.
  * vote-storage: 최종적으로 사용자 팬 투표 정형화된 데이터를 저장하는 서버입니다. 카프카 Subscribe를 통해서 데이터를 MySql에 저장합니다.

* 과제 참고 문헌
  * 스프링으로 시작하는 리액티브 프로그래밍 / 황정식 지음
  * 클라우드 네이티브 스프링 인 액션 / 토마스 비탈레 지음
  * 가상 면접 사례로 배우는 대규모 시스템 설계 기초 / 알렉스 쉬 지음
