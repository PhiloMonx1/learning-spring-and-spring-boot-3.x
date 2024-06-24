# 📒 [학습 노트] 챕터 6 : Spring과 Spring Boot로 JPA와 Hibernate 시작하기


## 1단계 - JPA와 Hibernate 시작하기 - 목표

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