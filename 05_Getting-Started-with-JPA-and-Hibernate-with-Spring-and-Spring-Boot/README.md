# 📒 [학습 노트] 챕터 5 : Spring과 Spring Boot로 JPA와 Hibernate 시작하기

## 목록
1. [JPA와 Hibernate 시작하기 - 목표](#1단계---jpa와-hibernate-시작하기---목표)
2. [JPA와 Hibernate에 맞는 새 Spring Boot 프로젝트 설정하기](#2단계---jpa와-hibernate에-맞는-새-spring-boot-프로젝트-설정하기)
3. [H2 콘솔 실행하기 및 H2에서 과정 테이블 생성하기](#3단계---h2-콘솔-실행하기-및-h2에서-과정-테이블-생성하기)
4. [Spring JDBC 시작하기](#4단계---spring-jdbc-시작하기)
5. [Spring JDBC를 사용하여 하드코드로 작성된 데이터 삽입하기](#5단계---spring-jdbc를-사용하여-하드코드로-작성된-데이터-삽입하기)
6. [Spring JDBC를 사용하여 데이터 삽입 및 삭제하기](#6단계---spring-jdbc를-사용하여-데이터-삽입-및-삭제하기)
7. [Spring JDBC를 사용하여 데이터 쿼리하기](#7단계---spring-jdbc를-사용하여-데이터-쿼리하기)
8. [JPA와 EntityManager 시작하기](#8단계---jpa와-entitymanager-시작하기)
9. [JPA의 마법 살펴보기](#9단계---jpa의-마법-살펴보기)
10. [Spring Data JPA 시작하기](#10단계---spring-data-jpa-시작하기)
11. [Spring Data JPA 특징 살펴보기](#11단계---spring-data-jpa-특징-살펴보기)
12. [Hibernate와 JPA의 차이 이해하기](#12단계---hibernate와-jpa의-차이-이해하기)

## 1단계 - JPA와 Hibernate 시작하기 - 목표
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/e1319cb7b506e6be25a92fec685f13cad137ca27)

#### 학습 목표
1. JPA 이전의 세계 이해하기
2. JDBC & Spring JDBC
3. JPA 와 Hibernate의 차이
4. Spring Boot Data JPA 사용해보기

#### 학습 접근 방식
1. H2(인메모리 DB)로 Spring Boot 프로젝트 생성
2. H2 Database에 `COURSE` 테이블 생성
3. JDBC를 사용해서 `COURSE` 테이블의 데이터 활용
4. JPA와 Hibernate 사용해서 `COURSE` 테이블의 데이터 활용
5. JPA와 Hibernate 차이점
6. Spring Data JPA를 사용
---

## 2단계 - JPA와 Hibernate에 맞는 새 Spring Boot 프로젝트 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/326cf858b8e306690287918ad438e727000e5cb6)

#### 프로젝트 생성
![Spring-initializer.png](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 라이브러리 목록
  - Spring Web
  - Spring Data JDBC
  - Spring Data JPA
  - H2 Database

#### 프로젝트 실행
![run-spring-boot.png](image/run-spring-boot.png)
- JDBC와 JPA가 실행된 것을 볼 수 있다.
---

## 3단계 - H2 콘솔 실행하기 및 H2에서 과정 테이블 생성하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/ed7b46895b19782cd69d667c001b43ce4e723505)

#### H2 데이터베이스 연결
1. 서버 실행 로그를 보면 H2 데이터베이스 로그를 찾을 수 있다.
   ![h2-database-log.png](image/h2-database-log.png)
2. [application.properties](..%2F00_module%2Flearn-jpa-and-hibernate%2Fsrc%2Fmain%2Fresources%2Fapplication.properties) 설정
    ```
    spring.h2.console.enabled=true
    ```
3. http://localhost:8080/h2-console 접속
   ![h2-console.png](image/h2-console.png)
4. 데이터베이스 로그인
    - JDBC URL 은 별도 설정하지 않았기에 랜덤으로 설정된다.
    - 로그에서 'url=jdbc:h2:mem:' 키워드로 찾을 수 있다.
      ![h2-jdbc-url.png](image/h2-jdbc-url.png)
5. 정적 URL 설정
    ```
    spring.datasource.url=jdbc:h2:mem:testdb
    ```
   - `spring.datasource.url=jdbc:h2:mem:` 다음으로 원하는 값을 주면 된다.
6. 인텔리제이 데이터베이스 툴에 연결
   ![IntelliJ-connect-h2.png](image/IntelliJ-connect-h2.png)
   1. 오른쪽 탭에서 '데이터베이스 클릭'
   2. '+'버튼(새로작성)을 클릭
   3. '데이터소스'에서 'H2' 선택
   4. '데이터소스 드라이버' 입력

#### 데이터베이스 테이블 생성
1. `schema.sql` 파일 생성
   - 'src/main/resources' 경로
2. 테이블 작성
    ```sql 
    create table course
    (
    id          bigint not null,
    name        varchar(255) not null,
    author      varchar(255) not null,
    primary key (id)
    );
    ```
3. 프로젝트 실행 후 테이블 확인
   ![check-creat-table.png](image/check-creat-table.png)
---

## 4단계 - Spring JDBC 시작하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/49ee4a4f341709798b80d0d0181f652ac57dd899)

#### H2 데이터베이스 테이블에 데이터 조작(생성,조회,삭제) 방법
- 콘솔창에 SQL 문법을 입력하고 실행할 수 있다.
  ![insert-int-ocourse.png](image/insert-into-course.png)
- 데이터 확인이 가능하다.
  ![SELECT-FROM-COURSE.png](image/SELECT-FROM-COURSE.png)
- 삭제
  ![delete-from-course.png](image/delete-from-course.png)

#### SQL (Structured Query Language)
- 관계형 데이터베이스 관리 시스템에서 데이터를 관리하고 조작하기 위해 사용하는 언어
- JDBC 와 Spring JDBC도 SQL을 작성해서 데이터를 관리해야 한다.
- Spring JDBC는 JDBC에 비해 더 적은 량의 Java code를 작성해도 된다는 장점이 있다.
  - JDBC 예시 코드
    ```java
    class exJDBC {
        public void deleteTodo(int id) {
            PreparedStatement st = null;
            try {
                st = db.conn.prepareStatement("delete from todo where id=?");
                st.setInt(1, id);
                st.execute();
            } catch (SQLException e) {
                logger.fatal("쿼리 실패 : ", e);
            } finally {
                if (st != null){
                    try {st.close();}
                    catch (SQLException e) {}
                }
            }
        }
    }
    ```
  - Spring JDBC 예시 코드
    ```java
    class exSpringJDBC {
        public void deleteTodo(int id){
            jdbcTemplate.update("delete from todo where id=?", id);
        }
    }
    ```
---

## 5단계 - Spring JDBC를 사용하여 하드코드로 작성된 데이터 삽입하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/9904ca242437397fd6f29aa0ff376d1271da3d2f)

#### Spring JDBC 사용
```sql
insert into course (id, name, author)
values (1, 'Learn AWS', 'in28minutes');

select * from course;

delete form course where id=1;
```
Spring JDBC를 사용해서 해당 쿼리를 실행해보려고 한다.

1. `CourseJdbcRepository` 클래스 생성 
    ```java
    @Repository
    public class CourseJdbcRepository {
        private JdbcTemplate springJdbcTemplate;
    }
    ```
   - @Repository : 데이터베이스에 연결되는 컴포넌트
   - JdbcTemplate : Spring JDBC 에서 제공하는 데이터베이스 조작 템플릿 클래스
2. 쿼리문 입력
    ```java
    @Repository
    public class CourseJdbcRepository {
        private static String INSERT_COURSE_SQL =
                        """
                        insert into course (id, name, author)
                        values (1, 'Learn AWS', 'in28minutes');
                        """;
   
   	    @Autowired
        private JdbcTemplate springJdbcTemplate;
    
        public void insert( ) {
            springJdbcTemplate.update(INSERT_COURSE_SQL);
        }
    }
    ```
   - springJdbcTemplate.update() 에 파라미터로 쿼리문을 줄 수 있다.
3. CommandLineRunner
    ```java
    @Component
    public class CourseJdbcCommandLineRunner implements CommandLineRunner {
        @Autowired
        private CourseJdbcRepository repository;
   
        @Override
        public void run(String... args) throws Exception {
            repository.insert();
        }
    }
    ```
    - Bean 이 SpringApplication 안에 포함되어 있을 때 실행할 특정 로직을 작성할 수 있는 인터페이스
    - `CommandLineRunner` 인터페이스를 구현하고 내부의 `run()` 를 구현하면 애플리케이션이 실행될 때 `run()` 메서드가 자동으로 실행된다.
---

## 6단계 - Spring JDBC를 사용하여 데이터 삽입 및 삭제하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/2bd810601aae0dbe264d2f1607a01901c07e4103)

#### 하드코딩된 CourseJdbcRepository::insert( ) Course 객체 연결 실습
1. [Course.java](..%2F00_module%2Flearn-jpa-and-hibernate%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Fspringboot%2Flearn_jpa_and_hibernate%2Fcourse%2FCourse.java) 클래스 선언
   - 생성자와 getter, setter 메서드도 함께 만든다.
2. `CourseJdbcRepository`에 연결하기
    ```java
    public class CourseJdbcRepository {
        private static String INSERT_COURSE_SQL =
                """
               insert into course (id, name, author)
               values (?, ?, ?);
                """;
   
   	    public void insert(Course course) {
		    springJdbcTemplate.update(INSERT_COURSE_SQL, course.getId(), course.getName(), course.getAuthor());
	    }
    }
    ```
    - `values` 값을 '?'로 줄 수 있다.
    - 기존 `insert()` 메서드에 course 를 넘겨주어서 사용한다.
3. `CourseJdbcCommandLineRunner` 에서 의존성 주입
    ```java
    @Component
    public class CourseJdbcCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            repository.insert(new Course(1L, "Learn AWS Now!", "in28minutes"));
            repository.insert(new Course(2L, "Learn Azure Now!", "in28minutes"));
            repository.insert(new Course(3L, "Learn DevOps Now!", "in28minutes"));
        }
    }
    ```
    - 기존에 사용했던 `CourseJdbcCommandLineRunner::run()` 에서 Course 객체의 의존성을 주입해서 메서드를 사용한다.

#### 데이터 삭제 로직 구현하기
```java
@Repository
public class CourseJdbcRepository {
	//...(생략)
	public void deleteById(long id) {
		springJdbcTemplate.update("delete from course where id=?", id);
	}
}
```
- 텍스트 블록을 상수로 선언하지 않고 바로 사용하는 것도 가능하다.
---

## 7단계 - Spring JDBC를 사용하여 데이터 쿼리하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/8472ddc84518273289061300c36876eecd7d6d21)

#### 조회 쿼리 작성 실습
```java
@Repository
public class CourseJdbcRepository {
    //...(생략)
	public Course findById(long id) {
		return springJdbcTemplate.queryForObject(SELECT_COURSE_SQL,
				new BeanPropertyRowMapper<>(Course.class), id);
	}
}
```
- DB에서 조회된 데이터를 `Course`의 Bean 객체로 변환 매핑 해야 한다.
  - `BeanPropertyRowMapper` 사용할 수 있다.
  - 주의!! `Course` 클래스에 Setter 메서드가 없으면 매핑이 불가능하다.
- `RowMapper` : JDBC를 통해 데이터베이스에서 쿼리 결과를 객체로 변환을 돕는 인터페이스
- `BeanPropertyRowMapper` : Bean 객체로 변환 (RowMapper의 구현체)
- `.class` : 자바 리플렉션(reflection) API 에서 지원하는 클래스의 메타데이터
  - 자바 코드로 특정한 클래스를 선언하면 자바 런타임 시스템이 해당 클래스의 Class 객체를 만든다.
  - 클래스 이름, 패키지, 상위 클래스, 상속 관계 등의 정보를 담고 있으며, 자바 리플렉션을 통해 조작하는 것도 가능하다. (백기선님의 인프런 강의 '[더 자바, 코드를 조작하는 다양한 방법](https://inf.run/DH6Y)'에서 배울 수 있다.)

#### 추가 학습 : DataClassRowMapper
`BeanPropertyRowMapper` 를 사용하기 위해서는 매핑되는 클래스에 Setter 메서드가 존재해야 한다. 그렇지 않으면 null로 나타난다. Setter 메서드 대신 생성자를 사용해서 객체를 매핑할 순 없을까?

방법은 Custom RowMapper를 구현하는 것이다. 하지만 Spring 5.3부터는 생성자를 이용한 매핑을 지원하는 `DataClassRowMapper`가 도입되었다.
```java
public class CourseJdbcRepository {
	public Course findById(long id) {
		return springJdbcTemplate.queryForObject(SELECT_COURSE_SQL,
				new DataClassRowMapper<>(Course.class), id);
	}
}
```
사용법은 `BeanPropertyRowMapper` 대신 `DataClassRowMapper`를 사용하면 된다. 그러나 주의사항이 존재한다.
1. `Course` 객체의 기본 생성자가 존재할 시 기본 생성자가 우선 선택되어 null로 초기화 된다.
2. `Course` 객체의 생성자가 여러개 일 시 런타임 에러가 발생할 수 있다. (어떤 생성자를 선택해야 할지 헷갈리기 때문)

#### 추가학습 : 자바 Bean 규약
사실 `BeanPropertyRowMapper` 를 사용하기 위해 클래스에 Setter 메서드가 존재해야 하는 이유는 `BeanPropertyRowMapper`이 자바 Bean 규약에 따라 동작하기 때문이다. 그렇다면 자바 Bean 규약이란 무엇일까?

1. 기본 생성자 : JavaBean 클래스는 이는 객체를 쉽게 생성할 수 있도록 매개변수가 없는 기본 생성자를 가져야 한다.
2. Getter & Setter 메서드 : JavaBean 클래스는 속성(필드)에 접근하기 위한 표준 getter 및 setter 메서드를 제공해야 한다.
3. Serializable 인터페이스 구현 (선택 사항) : JavaBeans는 일반적으로 직렬화 가능하도록 Serializable 인터페이스를 구현한다. (네트워크를 통해 전송되거나 파일로 저장될 수 있도록)
4. <b> 자바Bean과 Spring Bean은 다르다 : [챕터 1 ReadMe](..%2F01_Getting_Started_with_Java_Spring_Framework%2FREADME.md) 13단계 참고</b>
---

## 8단계 - JPA와 EntityManager 시작하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/244fcd63252630e7a174d7fa267074a3ccadfd3f)

Spring JDBC는 편리하지만 SQL 쿼리를 직접 작성해야 한다는 단점이 있다. 데이터베이스가 거대할 수록 쿼리문은 길고 복잡해진다. 또한 텍스트를 직접 입력하는 방식은 오탈자와 같은 실수가 발생할 여지를 준다.

#### JPA
JPA를 사용하면 이 문제를 해결할 수 있다. JPA를 활용해서 Course Bean 객체를 데이터베이스에 존재하는 테이블로 직접 매핑할 수 있다.

1. `@Entity` 생성
    ```java
    @Entity
    public class Course {
        @Id
        private Long id;
        @Column
        private String name;
        @Column
        private String author;
        //...(생략)}
    }
    ```
    - `@Entity` : 해당 클래스가 DB 테이블과 매핑됨을 명시하는 어노테이션
      - 해당 어노테이션은 컴포넌트 스캔의 대상이 아니다. 즉 엔티티는 스프링 IoC 컨테이너가 관리하지 않는다.
    - `@Id` :  테이블의 기본 키
    - `@Column` : DB 테이블 컴럼에 매핑됨 (클래스의 필드명과 테이블의 컬럼명이 같을 경우 생략할 수 있다.)

2. Repository 생성
    ```java
    @Repository
    @Transactional
    public class CourseJpaRepository {
        @PersistenceContext
        private EntityManager entityManager;
    }
    ```
    - `EntityManager` : 엔티티를 관리하고, 데이터베이스와 상호작용을 담당하는 JPA의 인터페이스
      - 엔티티는 컴포넌트 스캔의 대상이 아니며, 스프링 IoC 컨테이너의 관리 대상도 아니다. 
      - 대신 Repository는 스프링 IoC 컨테이너의 관리 대상이다. 즉 Repository 는 스프링 IoC 컨테이너의 하청업체, `EntityManager`는 하청업체가 사용하는 도구, 엔티티는 하청업체가 관리하는 대상으로 비유할 수 있다.
    - `@PersistenceContext` : EntityManager는 `@Autowired` 가 아닌 `@PersistenceContext`으로 주입한다.
      - 엔티티는 컴포넌트 스캔의 대상이 아닌 것과 밀접한 관련이 있다.
      - Spring IoC 컨테이너에 의해 관리되고 Bean으로 주입된다.
      - JPA의 표준이다.
      - 역할 : JPA의 영속성 컨텍스트를 관리하기 위해 사용된다.
        - 트랜잭션 관리가 가능하다.
      - @Autowired로 주입하는 것이 불가능하지는 않지만 권장되지 않으며 추가 구현이 필요하다.
    - `@Transactional` : 트랜잭션 관리에 사용 (붙이지 않으면 런타임 예외 발생함)
      - 트랜잭션 :  데이터베이스의 일관성과 무결성을 보장하기 위해 반드시 지켜야 하는 특성 'ACID 속성'이라고도 부름
        - Atomicity (원자성): 트랜잭션은 더 이상 나눌 수 없는 최소 로직 단위이다. 트랜잭션 내의 모든 작업이 성공적으로 완료되거나, 하나라도 실패하면 모든 작업이 취소되어야 한다.
          - ex) 은행 계좌 이체 시, 출금과 입금이 모두 성공하거나 모두 실패해야 함
        - Consistency (일관성): 트랜잭션이 완료된 후 데이터베이스가 일관된 상태를 유지되어야 한다.
          - ex) 200만원의 계좌 잔액에서 100만원을 출금하면 언제나 잔액이 100만원으로 줄어들어야 한다. (일관된 규칙과 제약을 유지해야 하기 때문)
        - Isolation (격리성): 하나의 트랜잭션이 완료될 때까지 다른 트랜잭션이 그 작업 결과를 볼 수 없어야 한다.
          - ex) A,B 두 명의 사용자 동시에 같은 계좌에서 출금을 할 때, A의 출금이 완료된 후에 B의 출금이 시작되어야 하며, 중복 출금은 발생하지 않아야 한다.
        - Durability (지속성): 트랜잭션이 성공적으로 완료되면 그 즉시 데이터베이스에 기록되어야 하며, 시스템 오류가 발생해도 저장 데이터는 보존해야 함
          - ex) 100만원의 출금 트랜잭션이 완료되면 영구적으로 그 사실이 기록되며 은행 시스템이 오류가 일어나도 해당 데이터는 유지되어야 함
      - 세부 속성 : 다양한 속성을 통해 트랙잭션의 동작을 세부적으로 정의 가능
        - propagation (전파) : 트랜잭션 경계를 정의하며, 트랜잭션 메서드가 다른 트랜잭션 메서드를 호출할 때 어떻게 동작할지를 지정
        - isolation (격리 수준) : 트랜잭션 간의 격리 수준 정의
        - timeout (제한 시간) : 트랜잭션이 완료되기까지의 최대 시간을 초 단위로 지정
        - readOnly (읽기 전용) : 트랜잭션을 읽기 전용으로 설정하여, 데이터 변경 작업 제한
        - rollbackFor (롤백 조건) : 특정 예외가 발생했을 때 트랜잭션을 롤백하도록 지정
        - noRollbackFor (롤백 제외 조건) : 특정 예외가 발생해도 트랜잭션을 롤백하지 않도록 지정

3. 데이터베이스 상호작용 로직 작성
```java
public class CourseJpaRepository {
	// 삽입
	public void insert(Course course) {
		entityManager.merge(course);
	}
	//조회
	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}
	//삭제
	public void deleteById(long id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}
}
```
#### JPA SQL문 확인하기 
    ```
    spring.jpa.show-sql=true 
    ```
    `application.properties` 파일에 해당 값을 선언하면 JPA를 실행하고 있을 때 콘솔에 SQL문이 출력된다.
---

## 9단계 - JPA의 마법 살펴보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/03395f51617c2828f74bcbf7dae7ee07f059b8ec)

JPA는 JDBC와 다르게 쿼리는 전혀 작성하지 않고, 엔티티만 테이블로 매핑하는 것으로 사용할 수 있다. 하지만 최종적으로는 여전히 SQL 쿼리가 실행되고 있다.

[application.properties](..%2F00_module%2Flearn-jpa-and-hibernate%2Fsrc%2Fmain%2Fresources%2Fapplication.properties) 파일에 `spring.jpa.show-sql=true` 라인을 추가한 후 애플리케이션을 실행한다.
```
//CourseJpaRepository::insert()
Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
Hibernate: insert into course (author,name,id) values (?,?,?)
Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
Hibernate: insert into course (author,name,id) values (?,?,?)
Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
Hibernate: insert into course (author,name,id) values (?,?,?)

//CourseJpaRepository::findById()
Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
Hibernate: delete from course where id=?

//CourseJpaRepository::deleteById()
Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
Course{id=2, name='Learn Azure Jpa!', author='in28minutes'}
Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0 where c1_0.id=?
Course{id=3, name='Learn DevOps Jpa!', author='in28minutes'}
```
    JPA가 자바 코드를 해석해서 SQL 쿼리문을 대신 작성해 주는 것이다.
---

## 10단계 - Spring Data JPA 시작하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/2ef761754e5750db7eff475cf3dd13148b4f5a02)

지금까지 JDBC, Spring JDBC, JPA를 배웠다.
- JDBC : 많은 퀴리문과 많은 자바 코드를 작성해야 한다.
- Spring JDBC : 자바 코드는 줄어들었다! 하지만 여전히 많은 쿼리문을 작성해야 한다.
- JPA : 드디어! 쿼리문으로부터 해방되었다. 이제 간단한 자바 코드만으로 데이터베이스 조작이 가능하다!

그렇다면 Spring Data JPA는 어떤 문제를 해결해줄까?

#### Spring Data JPA : JPA가 한결 더 간편해진다.
- EntityManager를 신경 쓸 필요가 없다.

1. Repository 인터페이스 생성
    ```java
    @Repository
    public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
    
    }
    ```
    - Spring Data JPA 의 Repository는 클래스가 아닌 인터페이스로 생성한다.
    - `JpaRepository`를 상속받는다. (<Course, Long>는 관리할 엔티티와 해당 엔티티 ID의 타입이다.)

2. 로직 작성을 건너뛰고 `CourseCommandLineRunner`에서 바로 사용.
    ```java
    @Component
    public class CourseCommandLineRunner implements CommandLineRunner {
    
        @Autowired
        private CourseSpringDataJpaRepository repository;
    
        @Override
        public void run(String... args) throws Exception {
            repository.save(new Course(1L, "Learn AWS Jpa!", "in28minutes"));
            repository.save(new Course(2L, "Learn Azure Jpa!", "in28minutes"));
            repository.save(new Course(3L, "Learn DevOps Jpa!", "in28minutes"));
    
            repository.deleteById(1L);
    
            System.out.println(repository.findById(2L));
            System.out.println(repository.findById(3L));
        }
    }
    ```
    - 별도의 로직 구현 없이 JpaRepository의 기본 CRUD 메서드를 사용할 수 있다.
    - ![Spring-Data-Jpa-method.png.png](image/Spring-Data-Jpa-method.png)
      - JpaRepository 가 제공하는 여러 메서드를 선택해서 사용할 수 있다.
---

## 11단계 - Spring Data JPA 특징 살펴보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/84764aa94c10b2c83f43b5fa2a3d88a35a36559b)

#### Spring Data JPA 메서드 더 알아보기
- findAll() : SELECT * FROM COURSE
    코드 :
    ```java
    public class CourseCommandLineRunner implements CommandLineRunner {
        public void run(String... args) throws Exception {
            System.out.println(repository.findAll());
        }
    }
    ```
    
    결과 :
    ```
    Hibernate: select c1_0.id,c1_0.author,c1_0.name from course c1_0
    [Course{id=2, name='Learn Azure Jpa!', author='in28minutes'}, Course{id=3, name='Learn DevOps Jpa!', author='in28minutes'}]
    ```
- count() : SELECT COUNT(*) 

#### 커스텀 메서드
- `author` 컬럼을 기준으로 데이터 검색 커스텀 메서드
    ```java
    @Repository
    public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
        List<Course> findByAuthor(String author);
    }
    ```
- 커스텀 메서드 선언 방법
  - List<Course> : 반환 타입 입력
  - findBy : By 다음 나타나는 필드 기준으로 찾겠다는 의미
  - Author : 기준이 되는 필드
  - (String author) : 파라미터
- 커스텀 메서트 선언 방법 심화 : 커스텀 메서드 선언 문법에는 규칙이 있다.
  - 접두어 : findBy, getBy, readBy 등 데이터 검색을 위한 메서드라는 것을 의미.
  - 속성 이름 : 접두어 뒤에 오는 엔티티의 속성 이름 첫 글자는 대문자로 써야 함.
  - 연산자 :  And, Or, Between, LessThan, GreaterThan 등 다양한 연산자를 사용해서 구체화 할 수 있음
    - ex) `List<Course> findByAuthorAndName(String author, String name);` : name과 author가 모두 일치하는 데이터 조회
---

## 12단계 - Hibernate와 JPA의 차이 이해하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/a818e46be5dbaa9e58a3777f23212c8d1a534fcb)

#### Hibernate vs JPA
- [CourseJpaRepository.java](..%2F00_module%2Flearn-jpa-and-hibernate%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Fspringboot%2Flearn_jpa_and_hibernate%2Fcourse%2Fjpa%2FCourseJpaRepository.java)와 [Course.java](..%2F00_module%2Flearn-jpa-and-hibernate%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Fspringboot%2Flearn_jpa_and_hibernate%2Fcourse%2FCourse.java)의 임포트 목록을 보자.
    ```java
    //CourseJpaRepository (챕터에서 새로 등장한 일부 임포트)
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.PersistenceContext;
    import jakarta.transaction.Transactional;
    
    //Course
    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    ```
    - 임포트 목록을 보면 Hibernate가 전혀 등장하지 않았다는 것을 알 수 있다. 
- Maven 종속성 확인
  - 목록에서 "jakarta.persistence:jakarta.persistence-api:3.1.0"를 찾아볼 수 있다.
    - 해당 패키지의 jar 파일이 JPA JAR이다.
  -  "org.hibernate.orm:hibernate-core:6.5.2.Final" 또한 클래스 경로에 있는 것을 확인할 수 있다.
- pom.xml 확인
  - 라이브러리 중 spring-boot-starter-data-jpa 내부에서 jpa와 hibernate를 확인 할 수 있다.

#### 왜 코드에서는 JPA만 사용했을까?
spring-boot-starter-data-jpa가 jpa와 hibernate를 둘 다 가져오는 것을 확인했다. 그런데 우리는 코드에서 jpa의 어노테이션만 사용하고 있다.

- JPA : 기술 명세를 정의한다. 
  - 즉, 데이터베이스와 상호작용하기 위한 일련의 규칙, 인터페이스, 클래스 등을 정의하는 일을 한다.
    - 인터페이스와 비슷하다. JPA 자체는 구현체가 아니다.
  - 사용 방법 
    - @Entity : 엔티티를 정의한다.
    - @Id : 테이블의 기본 키를 정의한다.
    - @Column : 필드와 컬럼의 매핑을 정의한다.
    - EntityManager(인터페이스) : 정의된 엔티티를 관리하고 DB와 상호작용 한다. 
      - JPA는 EntityManager의 활용법 역시 정의하고 있음.
- Hibernate : JPA의 구현체 
  - 대표적인 JPA의 구현체로 JPA 명세를 따르면서 추가적인 기능과 최적화를 제공하는 ORM 프레임워크이다. 
  - @Entity, @Id, @Column 등의 어노테이션을 Hibernate가 구현한 어노테이션으로 대체할 수도 있다. (import 패키지를 변경)
- Hibernate 외의 JPA 구현체
  - Toplink
  - EclipseLink
  - OpenJPA
  - DataNucleus
  - ...

#### 추가 학습 : Spring Data JPA도 구현체인가?
Spring Data JPA는 구현체가 아닌 JPA를 더 쉽게 사용할 수 있도록 도와주는 상위 레벨의 추상화 라이브러리이다. 

---