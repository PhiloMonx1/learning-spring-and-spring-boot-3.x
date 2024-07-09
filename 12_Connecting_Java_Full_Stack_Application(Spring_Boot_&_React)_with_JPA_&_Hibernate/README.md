# 📒 [학습 노트] 챕터 12: JPA 및 Hibernate를 사용한 Java 풀스택 애플리케이션 연결하기(Spring Boot & React)

## 목록
1. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot](#1단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot)
2. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - 테이블 준비하기](#2단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot---테이블-준비하기)
3. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - Todo CRUD](#3단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot---todo-crud)
4. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - 새로운 Todo 추가하기](#4단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot---새로운-todo-추가하기)

---

## 1단계 - 풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot

#### 라이브러리 추가
- jpa
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```
- h2 데이터베이스
    ```xml
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
    ```

#### h2 데이터베이스 기본 url 설정
```properties
spring.datasource.url=jdbc:h2:mem:testdb
```

#### h2 콘솔
['/h2-console/'](http://localhost:8080/h2-console/) 접근
- JwtSecurityConfig의 필터체인에서 '/h2-console/*' 엔드포인트의 권한을 열어주었기에 인증 없이 접근이 가능하다.

---

## 2단계 - 풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - 테이블 준비하기

#### Todo 엔티티 생성 
1. 클래스에 @Entity 어노테이션 부여
2. id 필드에 @Id, @GeneratedValue 어노테이션 부여
3. 기본 생성자 생성

이 세 가지는 필수이다.

#### 시작 데이터 삽입
```properties
spring.jpa.defer-datasource-initialization=true
```
- properties에 해당 설정을 해주어야 한다.
```sql
insert into todo(id, description, done, target_date, username)
values (10001, 'JPA 배우기', false, CURRENT_DATE(), 'eh13');

insert into todo(id, description, done, target_date, username)
values (10002, 'SQL 배우기', false, CURRENT_DATE(), 'eh13');

insert into todo(id, description, done, target_date, username)
values (10003, 'Spring 배우기', false, CURRENT_DATE(), 'eh13');
```
- src/resources/data.sql

---

##  3단계 - 풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - Todo CRUD

#### Todo 레포지토리 생성
```java
interface TodoRepository extends JpaRepository<Todo, Integer> {
}
```
1. 인터페이스로 생성한다.
2. JpaRepository를 상속한다.
   - 엔티티 객체와 id의 타입을 `<>` 안에 명시한다.

#### 레포지토리 메서드 추가
```java
interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUsername(String username);
}
```
- Todo 클래스에 username 필드가 있기 때문에 jpa data 메서드를 선언하는 것이 가능하다.

#### 컨트롤러 개선
```java
@RestController
public class TodoResource {
	private TodoRepository todoRepository;

	public TodoResource(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoRepository.findByUsername(username);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
		return todoRepository.findById(id).get();
	}

	@PostMapping("/users/{username}/todos")
	public Todo addTodo(@PathVariable String username, @RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
		todo.setId(id);
		todo.setUsername(username);
		todo.setDone(false);
		todoRepository.save(todo);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
```
- 기존 TodoService를 사용하던 로직을 TodoRepository 사용으로 변경한다.

---

## 4단계 - 풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - 새로운 Todo 추가하기

#### 신규 Todo 생성 시 Todo 객체의 id
신규 Todo 생성 시 신규 Todo의 id는 어떻게 처리해야 할까?
1. Todo 엔티티의 id의 타입을 래퍼 클래스(Integer)로 변경.
2. API 로직 추가
    ```java
    @PostMapping("/users/{username}/todos")
    public Todo addTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setId(null);
        todo.setUsername(username);
        return todoRepository.save(todo);
    }
    ```
    - null을 넣어주면 JPA가 자동으로 최신 id를 지정해서 저장한다.
        - @GeneratedValue 어노테이션을 사용해서 JPA에게 엔티티의 id 생성 전략을 위탁했기 때문이다.
        - 클라이언트에서 신규 생성의 경우 '-1'을 보내주고 있는데, 이 역시 자동으로 최신 id로 변경되서 저장된다.
    - DTO, 빌더 패턴을 사용해서 해당 코드를 개선할 수 있다.

#### 부록 : JWT에서 username 추출해서 사용하기
JWT의 클레임을 설정할 때, 'subject'로 username을 담았었다. JWT를 기반으로 API요청 보냈기에 JWT의 클레임 정보를 기반으로 사용자를 판단하고 Todo 생성자를 설정하는 것이 더 적절하다.

```java
@PostMapping("/users/{username}/todos")
public Todo addTodo(@PathVariable String username, @RequestBody Todo todo, @AuthenticationPrincipal Jwt jwt) {
    todo.setId(null);
	todo.setUsername(jwt.getSubject());
	return todoRepository.save(todo);
}
```
- @AuthenticationPrincipal : 현재 인증된 주체(Principal)를 주입한다.
- oauth2 리소스 서버는 Spring Security의 기본 JWT 처리를 진행한다.
  - JwtSecurityConfig 체인 필터의 `.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))` 설정 때문이다.
  - 기본 JWT 처리 과정에서 Spring Security는 JWT 토큰을 파싱하여 Jwt 객체를 생성한다.
  - 생성된 Jwt 객체가 `@AuthenticationPrincipal` 어노테이션에 의해 자동으로 주입되는 것이다.
- 주의점 : Jwt가 아닌 다른 인증 방식으로 변경이 까다로울 수 있다.
  - `@AuthenticationPrincipal` 어노테이션과 자주 사용되는 것은 `UserDetails`이다.
  - UserDetails :  Spring Security의 표준 인터페이스로, 다양한 인증 방식과 호환되며, 유연하다. (PSA 원칙)
    - JWT의 모든 클레임 대신 필요한 정보만 선택적으로 노출할 수 있다. (최소 권한의 원칙, 일종의 DTO 처럼 사용할 수 있으며 보안 향상의 효과가 있다.)

복잡한 권한 처리나 사용자 정보 매핑이 필요한 경우, 커스텀 JwtAuthenticationConverter 정의해서 사용하는 것도 가능하다. 아래는 예시이다.
```java
//...(생략)
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//...(생략)
public class JwtSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				//...(생략)
				.oauth2ResourceServer(oauth2 -> oauth2.
						jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
				//...(생략)
				.build();
	}
	//...(생략)
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
		grantedAuthoritiesConverter.setAuthorityPrefix("SCOPE_");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

		return jwtAuthenticationConverter;
	}
}
```
기본 값인 `Customizer.withDefaults()` 대신 직접 정의한 `jwtAuthenticationConverter()`를 사용하는 것도 가능하다.
- JwtGrantedAuthoritiesConverter :  JWT의 클레임에서 권한 정보를 추출하는 컨버터
  - setAuthoritiesClaimName : 권한 정보를 추출할 때 사용할 클레임의 이름을 설정
    - Authorities 정보를 가진 클레임의 이름을 scope로 설정하는 것이다.
    - scope는 OAuth 2.0 표준이며 OAuth 2.0 리소스 서버 구현의 디폴트 값이다.
    - OAuth 2.0 리소스 서버에서 자동으로 'scope'라는 이름의 클레임을 찾아 그 값을 권한 정보로 사용한다.
  - setAuthorityPrefix : 추출된 각 권한 문자열 앞에 특정 접두사 추가
    - 토큰에서 온 권한과 애플리케이션 내부에서 정의된 권한 간의 이름 충돌을 방지하기 위해 사용한다.
    - SCOPE_ 가 포함된 권한을 따로 분리해서 토큰으로부터 온 권한을 별도 처리할 수 있다.
- JwtAuthenticationConverter : JWT를 Spring Security의 Authentication 객체로 변환하는 역할을 하는 컴포넌트 컨버터
  - setJwtGrantedAuthoritiesConverter : 앞서 설정한 grantedAuthoritiesConverter 객체를 사용해서 JWT을 Authentication 객체로 변환한다.

---