package com.example.basic.ex;

// *프레임워크(Framework)
// 소프트웨어 개발에서 특정 작업을 수행하기 위한 기본 구조와 규칙을 제공하는 플랫폼이다.
// 프레임워크는 코드의 재사용성을 높이고, 개발자가 특정 문제를 해결하는 데 필요한 기본적인 기능을 제공하여 개발 시간을 단축시켜준다.
// 프레임워크는 일련의 규칙과 관례(convention)를 제공하며, 이러한 규칙에 따라 개발자는 애플리케이션의 구조를 설계하고, 필요한 부분만 코드를 작성하면 된다.

// *라이브러리(Library)
// 특정 기능을 수행하는 코드의 집합체이다.
// 라이브러리는 개발자가 애플리케이션을 작성할 때 필요한 기능을 제공하며,
// 개발자는 라이브러리의 기능을 호출하여 애플리케이션에 필요한 기능을 구현할 수 있다.
// 라이브러리는 프레임워크와 달리 프로그램의 흐름을 제어하지 않으며, 개발자가 필요에 따라 라이브러리를 호출하여 사용한다.

// * 클라이언트란?
// 클라이언트는 서버로 요청하는 프로그램을 모두 일컬어 말한다.

// * 서버란?
// 클라이언트의 요청을 받아 처리하는 주체이다.

// * 데이터베이스
// 여러 사람이 데이터를 한 군데에 모아놓고 여러 사람이 사용할 목적으로 관리하는 데이터 저장소이다.
// 1. RDB (Relational Database)
// 개요
//	•	RDB는 데이터를 표 형태의 테이블에 저장하는 전통적인 데이터베이스 시스템이다.
//	테이블은 행과 열로 구성되며, 행은 개별 데이터 항목을 나타내고, 열은 해당 데이터 항목의 속성을 나타낸다.
//	•	관계형 데이터베이스에서는 데이터가 정규화(Normalization)되어 있으며,
//	테이블 간의 관계는 기본 키(Primary Key)와 외래 키(Foreign Key)를 통해 관리된다.

// * 주요 특징
//	•	스키마 기반(Schema-based): 데이터베이스 스키마는 데이터베이스에 저장될 데이터의 구조를 정의하며, 모든 데이터는 이 스키마를 따라야 한다.
//	•	ACID 속성: 관계형 데이터베이스는 트랜잭션의 일관성과 신뢰성을 보장하기 위해 ACID(Atomicity, Consistency, Isolation, Durability) 속성을 따른다.
//	•	SQL: 관계형 데이터베이스는 구조화된 쿼리 언어(SQL)를 사용하여 데이터를 관리한다.
//	SQL은 데이터베이스에 대한 질의, 삽입, 수정, 삭제 등의 작업을 수행할 수 있는 강력한 언어입니다.
//	•	데이터 무결성: RDB는 데이터의 무결성을 보장하기 위해 제약 조건(Constraints)을 사용한다.
//	예를 들어, 외래 키 제약 조건을 사용하여 데이터 일관성을 유지한다.

// 2. NoSQL (Not Only SQL)
// 개요
//	•	NoSQL 데이터베이스는 비정형 또는 반정형 데이터를 저장하는 데 사용된다.
//	NoSQL 데이터베이스는 전통적인 RDB와 달리 테이블 기반의 구조를 따르지 않으며, 스키마가 없거나 유연한 스키마를 가지고 있다.
//	•	NoSQL 데이터베이스는 대규모 데이터의 고속 처리가 필요한 애플리케이션에서 많이 사용된다.

// * 주요 특징
//	•	유연한 스키마(Flexible Schema): NoSQL 데이터베이스는 데이터를 저장하기 전에 구조를 정의할 필요가 없으며,
//	동적으로 데이터 구조를 변경할 수 있다.
//	•	수평적 확장성(Horizontal Scalability): NoSQL 데이터베이스는 데이터베이스 인스턴스를 수평으로 확장하여 대량의 데이터를 처리할 수 있다.
//	•	CAP 이론: NoSQL 데이터베이스는 일반적으로 CAP 이론(Consistency, Availability, Partition Tolerance)의 제약을 따른다.
//	모든 특성을 동시에 만족시킬 수 없기 때문에, NoSQL 시스템은 이러한 특성 중 하나를 포기하거나 절충한다.
//	•	다양한 데이터 모델: NoSQL 데이터베이스는 데이터 모델에 따라 크게 네 가지 유형으로 나뉜다:
//	•	키-값(Key-Value) 스토어: Redis, DynamoDB
//	•	도큐먼트(Document) 스토어: MongoDB, CouchDB
//	•	컬럼(Column) 스토어: Cassandra, HBase
//	•	그래프(Graph) 데이터베이스: Neo4j, ArangoDB

// ** NoSQL 데이터베이스가 대규모 데이터를 빠르게 처리하거나 데이터 구조가 자주 변경되는 경우에 RDB(관계형 데이터베이스)보다 더 적합한 이유
//
//1. 유연한 스키마
//
//	•	RDB: 관계형 데이터베이스는 엄격한 스키마를 필요로 한다.
//	모든 데이터는 사전에 정의된 구조(테이블, 컬럼)로 저장되어야 하며, 스키마 변경은 복잡하고 시간이 많이 걸릴 수 있다.
//	스키마를 변경하려면 테이블을 재구성하거나, 데이터 마이그레이션이 필요할 수 있다.
//	•	NoSQL: NoSQL 데이터베이스는 스키마가 없거나 매우 유연한 스키마를 가지고 있다.
//	데이터 구조가 미리 정의되지 않기 때문에, 새로운 필드를 추가하거나 구조를 변경할 때 데이터베이스의 재구성 없이도 가능하다.
//	이 유연성은 데이터 구조가 자주 변경되는 애플리케이션에서 매우 유용하다.
//
// 예를 들어, 도큐먼트 기반 NoSQL 데이터베이스인 MongoDB에서는 각 도큐먼트(행)가 서로 다른 구조를 가질 수 있다.
// 개발 중에 새로운 필드를 추가하거나 변경할 때 스키마를 미리 정의할 필요가 없으며, 이는 빠른 개발과 반복적인 변경에 매우 유리하다.
//
//2. 수평적 확장성(Horizontal Scalability)
//
//	•	RDB: 전통적인 RDBMS는 주로 수직적 확장(Scale-Up)을 염두에 두고 설계되었다.
//	즉, 더 많은 데이터를 처리하려면 더 강력한 서버(더 많은 CPU, RAM 등)를 도입해야 한다.
//	하지만, 수직적 확장은 물리적 한계와 비용적인 한계가 있다.
//	수평적 확장(서버를 여러 대로 나누어 처리하는 방식)은 RDBMS에서 일반적으로 복잡한 작업이며,
//	데이터 파티셔닝이나 샤딩을 구현해야 한다. 이로 인해 관리가 복잡하고, 확장이 어려울 수 있다.
//	•	NoSQL: NoSQL 데이터베이스는 설계 단계부터 수평적 확장을 염두에 두고 개발되었다.
//	즉, 더 많은 데이터를 처리해야 할 때 더 많은 서버를 추가하여 데이터를 분산 처리할 수 있다.
//	대부분의 NoSQL 시스템은 데이터를 자동으로 분산하고, 여러 서버에 걸쳐 데이터의 일관성을 유지한다.
//
// 예를 들어, Cassandra와 같은 NoSQL 데이터베이스는 여러 데이터 센터에 걸쳐 데이터를 자동으로 분산하며, 고가용성과 확장성을 보장한다.
// 이는 대규모 트래픽을 처리하는 애플리케이션에 매우 유리하다.
//
//3. 고성능 읽기/쓰기
//
//	•	RDB: RDBMS는 트랜잭션 무결성을 보장하기 위해 ACID(Atomicity, Consistency, Isolation, Durability) 속성을 따른다.
//	이로 인해 특히 많은 쓰기 작업이 발생하는 상황에서 성능이 저하될 수 있다.
//	또한, 복잡한 조인 연산이나 관계가 많은 데이터를 처리할 때 성능에 영향을 받을 수 있다.
//	•	NoSQL: NoSQL 데이터베이스는 대부분 BASE(Basically Available, Soft state, Eventually consistent) 원칙을 따른다.
//	이는 약한 일관성을 허용하는 대신, 고가용성과 높은 성능을 제공하는 구조이다.
//	NoSQL 시스템은 ACID보다 가벼운 일관성 모델을 사용하므로, 읽기와 쓰기 작업에서 매우 높은 성능을 발휘할 수 있다.
//
// 예를 들어, Redis와 같은 키-값 저장소는 메모리 기반으로 동작하며, 매우 빠른 읽기/쓰기 성능을 제공한다.
// 이는 실시간 애플리케이션, 캐싱 시스템 등에서 특히 유용하다.
//
//4. 빅데이터와 실시간 데이터 처리
//
//	•	RDB: RDBMS는 빅데이터나 실시간 데이터 스트림 처리에 적합하지 않은 경우가 많다.
//	RDBMS는 정규화된 테이블 구조와 트랜잭션 무결성을 유지하는 데 초점을 맞추고 있어, 대규모 데이터의 빠른 처리에는 한계가 있다.
//	•	NoSQL: NoSQL 데이터베이스는 데이터 모델의 유연성 덕분에 빅데이터 및 실시간 데이터 처리에 매우 적합하다.
//	NoSQL 시스템은 대규모 데이터를 효율적으로 분산 저장하고, 필요한 경우 실시간으로 분석할 수 있는 기능을 제공한다.
//
// 예를 들어, Hadoop HBase는 분산 파일 시스템 위에 구축된 NoSQL 데이터베이스로, 페타바이트 규모의 데이터를 처리하고 분석할 수 있다.
// 또한, Apache Kafka와 같은 분산 스트리밍 플랫폼과 함께 사용하여 실시간 데이터 스트리밍을 처리할 수 있다.
//
//5. CAP 이론과 절충
//
//	•	RDB: 관계형 데이터베이스는 일관성(Consistency)과 가용성(Availability)을 중시하며, 분할 내구성(Partition Tolerance)보다 트랜잭션의 일관성을 우선시한다.
//	이는 분산 시스템에서 데이터베이스의 가용성보다 일관성을 더 중요하게 고려해야 할 때 적합하다.
//	•	NoSQL: NoSQL 데이터베이스는 CAP 이론에 따라 분할 내구성을 보장하면서 가용성 또는 일관성 중 하나를 절충한다.
//	대부분의 NoSQL 데이터베이스는 높은 가용성과 분할 내구성을 중시하면서, 결국 일관성(Eventual Consistency)을 보장한다.
//	이는 대규모 분산 시스템에서 데이터의 가용성을 극대화하고, 시스템의 성능을 최적화할 수 있게 한다.
//
// 예를 들어, Amazon DynamoDB는 높은 가용성과 분산 내구성을 제공하며, 결국 일관성 모델을 기반으로 작동한다.
// 이로 인해 글로벌 분산 시스템에서 매우 빠른 성능과 고가용성을 제공한다.

public class ex_01 {
}