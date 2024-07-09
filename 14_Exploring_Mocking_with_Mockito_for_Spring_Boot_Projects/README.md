# 📒 [학습 노트] 챕터 14: Spring Boot 프로젝트에서 Mockito로 모킹하기

## 목록
0. [섹션 소개: Mockito 사용 5단계](#0단계---챕터-소개-mockito-사용-5단계)
1. [Spring Boot 프로젝트 설정하기](#1단계---spring-boot-프로젝트-설정하기)
2. [Stub의 문제점 이해하기](#2단계---stub의-문제점-이해하기)
3. [Mock을 이용해 첫 Mockito 테스트 작성하기](#3단계---mock을-이용해-첫-mockito-테스트-작성하기)
4. [Mockito 어노테이션(@Mock, @InjectMocks)을 이용헤 테스트 단순화하기](#4단계---mockito-어노테이션mock-injectmocks을-이용헤-테스트-단순화하기)
5. [인터페이스 모킹을 통해 Mock 더 자세히 알아보기](#5단계---list-인터페이스-모킹을-통해-mock-더-자세히-알아보기)

---

## 0단계 - 챕터 소개: Mockito 사용 5단계
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/a3711763379dc7e607b6b1a9f39fb78ef68a73da)

#### Mocking
테스트를 위해 실제 객체 대신 가짜 객체를 사용하는 기법, Mocking을 사용하면 복잡한 의존성을 가진 객체도 쉽게 테스트할 수 있다.
- Mockito : 널리 사용되는 Mocking 프레임워크로, 가짜 객체 생성과 동작 제어를 지원한다.

#### Mocking의 필요성
소프트웨어에 있어 단위 테스트가 훌륭하면 유지 보수가 간단해진다. 
그러나 여러 개의 종속성이 포함되어 있는 등 비즈니스 로직이 복잡하다면 단위 테스트를 작성하는 비용이 증가하게 된다. 
이 문제를 Mocking이 해결해준다.

#### Stub vs Mock
데이터베이스에 저장된 데이터를 사용하지 않고 비즈니스 계층의 단위 테스트를 실행하고 싶다면 크게 두 가지 방법을 사용할 수 있다.
1. Stub
2. Mock

이번 챕터에서는 Stub은 무엇이고 Mock은 무엇인지, 그리고 Mockito에서 이 방법들을 어떻게 사용하는지 다룰 것이다.

---

## 1단계 - Spring Boot 프로젝트 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/520025d7c93374f90bd921472c0f77f0e75acd1b)

#### 프로젝트 생성
![Spring-initializer.png](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 라이브러리는 추가하지 않았다.
  - `spring-boot-starter-web` 라이브러리가 없기 때문에 애플리케이션 실행 시 웹 서버가 켜지지 않는다.

#### Mockito 라이브러리
```xml
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>5.11.0</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-junit-jupiter</artifactId>
      <version>5.11.0</version>
      <scope>compile</scope>
    </dependency>
```
- `spring-boot-starter-test` 라이브러리 내부에서 'mockito' 라이브러리를 포함하고 있는 것을 확인할 수 있다.
- Spring initializer 에서 라이브러리를 아무것도 추가하지 않아도 'mockito' 는 기본 스프링 스타터에 제공된다.

#### 연습용 클래스 작성
```java
package com.in28minutes.mockito.mockito_demo.business;

import java.util.Arrays;

public class SomeBusinessImpl {

	private DataService dataService;

	public int findTheGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();
		return Arrays.stream(data).max().getAsInt();
	}

}

interface DataService {

	int[] retrieveAllData();
}
```
- DataService 인터페이스의 구현체를 의존성으로 가지는 클래스를 생성.
- PSA를 적용할 수 있는 기반 구현으로 볼 수 있다.

#### PSA(Portable Service Abstraction) 원칙
Spring 프레임워크의 핵심 설계 철학 중 하나로 특정 기술에 종속되지 않는 방식으로 추상화된 상위 계층의 인터페이스를 제공하는 것을 의미한다.
- 기술 독립성: 특정 기술에 종속되지 않는 코드 작성
- 유연성: 기술 변경 시 최소한의 코드 수정으로 대응 가능
- 테스트 용이성: 모의 객체를 사용한 단위 테스트 촉진
- 일관성: 다양한 기술에 대해 일관된 프로그래밍 모델 제공

코드 재사용성이 늘어나고 클래스간의 의존도를 낮추어 변경 및 확장에 유연하게 대처할 수 있다.

---

## 2단계 - Stub의 문제점 이해하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/cdaa0b986c52e05584faed9dae2cc94d2a5c754b)

#### Stub 사용해서 단위테스트 작성
```java
class MockitoDemoApplicationTest {

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		DataService dataServiceStub = new DataServiceStub();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, result);
	}
}

class DataServiceStub implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[] { 25, 15, 5 };
	}
}
```

#### Stub의 문제점
- `DataService` 인터페이스에 신규 메서드가 추가될 때마다 `DataServiceStub` 구현체에서도 메서드를 구현해야 한다.
  - 추가된 메서드를 실제로 사용하지 않는다고 하더라도 인터페이스의 메서드는 반드시 구현해야 하기 때문에 구현이 강제된다.
- 다양한 시나리오 케이스를 테스트하기가 어렵다
  - { 25, 15, 5 } 말고 다른 시나리오를 테스트 하기 위해 새로운 Stub를 추가해야 한다.

---

## 3단계 - Mock을 이용해 첫 Mockito 테스트 작성하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/75c83a7f1a429d1d4c0fb84d042bde4a636dedb5)

#### Mock 사용해서 단위테스트 작성
```java
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//...(생략)
class SomeBusinessImplMockTest {

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, result);
	}
}
```
- when : Mock 객체의 retrieveAllData() 메서드가 호출될 때 
- thenReturn : {25, 15, 5} 배열을 반환하도록 설정

---

## 4단계 - Mockito 어노테이션(@Mock, @InjectMocks)을 이용헤 테스트 단순화하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/c39edd22eb9e3d841570e5789e731f3fb4f19159)

#### Mockito 확장 어노테이션 사용해서 테스트코드 리팩토링
```java
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//...(생략)
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});
		assertEquals(25, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	void findTheGreatestFromAllData_withOneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
		assertEquals(5, businessImpl.findTheGreatestFromAllData());
	}
}
```
- @ExtendWith(MockitoExtension.class) : Mockito 확장 기능을 활성화 한다.
- @Mock : 주입되어야 하는 의존성 필드에 부여하면 해당 필드의 Mock 객체를 자동으로 생성한다. 
  - 인터페이스에 부여하는 것이 가능하다.
- @InjectMocks : 주입받아야 하는 필드에 부여하면 자동으로 @Mock 어노테이션에 의해 생성된 Mock 객체를 주입한다.
- 각 단위 테스트 내에서 Mock 구현체와 SomeBusinessImpl를 선언할 필요가 없어졌다.

---

## 5단계 - List 인터페이스 모킹을 통해 Mock 더 자세히 알아보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/cfa1d47235376fb7328a78c50494ffff1a18b639)

#### 다양한 Mocking 실습
```java
@SpringBootTest
public class ListTest {

	@Test
	void simpleTest() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);

		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	void multipleReturns() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2).thenReturn(5);

		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(5, listMock.size());
		assertEquals(5, listMock.size());
		assertEquals(5, listMock.size());
	}

	@Test
	void specificParameters() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("SomeString");

		assertEquals("SomeString", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	void genericParameters() {
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");

		assertEquals("SomeOtherString", listMock.get(0));
		assertEquals("SomeOtherString", listMock.get(231));
		assertEquals("SomeOtherString", listMock.get(5444));
	}
}
```
다음 테스트 코드는 모두 성공 케이스이다. 하나씩 살펴보자
- simpleTest : 한 번 설정된 when/thenReturn 은 여러 번 요청해도 동일한 값을 리턴한다.
- multipleReturns : thenReturn() 은 여러 개를 체인으로 사용할 수 있다.
  - thenReturn() 체인 횟수 만큼의 assert 로직에 대응한다. 
  - 마지막 thenReturn() 이후의 호출에 대해서는 마지막에 지정된 값이 계속 반환된다
- specificParameters : 파라미터를 설정하는 것이 가능하다.
  - 특정 인덱스에 대한 반환 값을 설정할 수 있으며, 설정되지 않은 인덱스에 대해서는 null을 반환한다.
- genericParameters : 특정 파라미터가 아닌 파라미터의 범위를 설정하는 것이 가능하다.
  - 코드에서는 Mockito.anyInt()를 사용하여 모든 정수 인덱스에 대해 동일한 반환 값을 설정했다.
    - anyBoolean(), anyChar(), anyByte(), any() 등 다양한 범위를 제공한다.

---