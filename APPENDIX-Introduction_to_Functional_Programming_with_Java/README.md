# 📒 [학습 노트] 부록 : Java 함수형 프로그래밍 소개

## 목록
1. [Java에서 함수형 프로그래밍 시작하기](#1단계---java에서-함수형-프로그래밍-시작하기)
2. [Java 함수형 프로그램 처음 작성하기](#2단계---java-함수형-프로그램-처음-작성하기)
3. [필터로 Java 함수형 프로그램 개선하기](#3단계---필터로-java-함수형-프로그램-개선하기)
4. [람다식으로 함수형 프로그램 개선하기](#4단계---람다식으로-함수형-프로그램-개선하기)
5. [스트림, 필터, 람다를 사용해 함수형 프로그램 예제 실습하기](#5단계---스트림-필터-람다를-사용해-함수형-프로그램-예제-실습하기)
6. [함수형 프로그램에서 map 사용하기 - 예제 포함](#6단계---함수형-프로그램에서-map-사용하기---예제-포함)

---

## 1단계 - Java에서 함수형 프로그래밍 시작하기

부록을 통해 java 함수형 프로그래밍이 무엇인지 배울 것이다. 한 가지 팁이 있다면 함수형 프로그래밍을 마스터하려면 "문제 해결에 관한 사고 방식을 바꿔야 한다"는 것이다.

#### 전통적인 방법으로 메서드 구현
```java
public class FP01Structured {

	public static void main(String[] args) {
		printAllNumbersInListStructured(List.of(12, 9, 13, 4, 6, 2, 4, 12, 15));
	}

	private static void printAllNumbersInListStructured(List<Integer> numbers) {
		// 어떻게 구현할 것인가?
	}
}
```
- 가장 먼저 떠오르는 답은 빠른 for문을 사용하는 것이다. (일반 fori문과 빠른 for문이 후보로 등장한다.)
    ```java
    private static void printAllNumbersInListStructured(List<Integer> numbers) {
        for (int number : numbers) {
            System.out.println(number);
        }
    }
    ```
  
#### 구조적인 접근법 (구조적 프로그래밍)
전통적인 접근법에서는 문제를 해결할 때마다 방법에 집중한다.

가장 처음 한 일이 숫자 리스트에 어떻게 루프를 적용할지 방법을 선택하는 것이었다.

#### 함수형 프로그래밍
문제를 해결하기 위해 필요한 것이 '무엇'인지에 초점을 맞춘다.

---

## 2단계 - Java 함수형 프로그램 처음 작성하기

#### 함수형 프로그래밍으로 1단계 메서드 개선하기
```java
public class FP01Functional {

	public static void main(String[] args) {
		printAllNumbersInListFunctional(List.of(12, 9, 13, 4, 6, 2, 4, 12, 15));
	}

	private static void printInt(int number) {
		System.out.println(number);
	}

	private static void printAllNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream()
				.forEach(FP01Functional::printInt);
	}
}
```

#### 스트림
`numbers.stream()` 을 사용해서 스트림으로 접근했다.
- 스트림은 데이터의 흐름으로, 각 요소에 대해 연산을 수행할 수 있게 해준다.
  - 리스트의 요소 (12, 9, 13, 4, 6, 2, 4, 12, 15)를 각각 하나씩 쪼개서 순서대로 개별처리한다.
  - 기본적으로 반복문이다. 그러나 추상화된 방식이라고 볼 수 있다. (반복 로직이 추상화 되어 있다.)
    -  반복의 세부사항(어떻게 반복할지)을 추상화하고, 개발자는 각 요소에 대해 수행할 작업(무엇을 할지)만 지정한다.

#### 메서드 참조
`FP01Functional::printInt` 을 사용해서 메서드 참조를 했다.
- 간결성 : 코드를 더 짧고 읽기 쉽게 만든다.
- 가독성 향상 : 메서드 이름을 직접 사용햐서 코드의 의도가 명확해진다
- 재사용성 : 특정 로직에 의존적이지 않은 코드를 선언해 쉽게 재사용 할 수 있다.

---

## 3단계 - 필터로 Java 함수형 프로그램 개선하기

#### 코드 개선 printAllNumbersInListFunctional() 메서드 개선
```java
private static void printAllNumbersInListFunctional(List<Integer> numbers) {
    numbers.stream()
            .forEach(System.out::println);
}
```
- 새로 작성한 printInt() 메서드 대신 내장 메서드인 sout을 직접 사용해서 코드를 개선했다.
  - 함수형 접근법 : 요소 리스트가 있으면 각 요소에 수행할 작업을 정의하고, 이를 지정하기만 하면 된다.
  - 구조적 접근법 : 숫자에 루프를 실행하는 방법을 결정한 후 출력해야 한다.

아직까지는 추상적으로 느껴지고, 명확한 장점이 있는지 잘 모르겠다. 그럼 예제를 좀 더 복잡하게 만들어보자.

#### 추가 요건 : 리스트에서 짝수인 숫자만 노출하기
- 구조적 프로그래밍
  ```java
  private static void printEvenNumbersInListStructured(List<Integer> numbers) {
      for (int number : numbers) {
          if(number % 2 == 0) {
              System.out.println(number);
          }
      }
  }
  ```
  - for문 안에 if문이 들어오면서 들여쓰기가 두 번 발생했다. (가독성이 떨어짐)

- 함수형 프로그래밍
```java
private static boolean isEven(int number) {
    return number % 2 == 0;
}

private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
    numbers.stream()
    .filter(FP01Functional::isEven)
    .forEach(System.out::println);
}
```
- filter()을 통해 '필터링'을 할 것이라는 추상적인 구현을 먼저한 후, '어떻게 필터링' 할 것인지는 주입하는 방식으로 구현했다.

#### 구조적 프로그래밍 vs 함수형 프로그래밍
- 구조적 프로그래밍 : 짝수를 구분하는 방법을 '어떻게' 할지 고민하고 구현한다.
  - 문제 해결 과정의 각 단계를 명시적으로 기술한다.
  - 제어 흐름(조건문, 반복문 등)을 직접 관리한다.
- 함수형 프로그래밍 : 짝수를 구분하기 위해 '무엇을' 할지 고민하고 구현한다. (isEven을 사용한다.)
  - 문제를 작은 함수들의 조합으로 해결한다.

만약 isEven() 메서드를 자신이 아닌 다른 개발자가 구현해 놓았고 이것을 사용한다고 가정해보자.

```java
numbers.stream()
    .filter()
    .forEach();
```
해당 코드는 '필터링'하고, '작업하라'로 이해할 수 있다. 구체적이지 않고 추상적인 방법이다. (누군가 이렇게 업무를 지시했다고 생각해보자)

```java
numbers.stream()
.filter(FP01Functional::isEven)
.forEach(System.out::println);
```
이제 요구사항이 명확해진다. isEven()를 사용해서 짝수만 필터링하고, sout()을 사용해서 콘솔에 노출 시켜라.

이와 같이 함수형 프로그램의 핵심 개념은 '추상화된 연산'으로 먼저 구조화 하고, '구체적 주입'으로 구체적인 작업을 지시한다.

직접 방법을 작성하는 코드보다는 뭔가 더 세련된 느낌을 준다. 

'필터링을 담당하는 filter'와 '작업을 담당하는 forEach'에게 각각 자신이 수행할 수 있는 수준의 작업을 지시해서

최종 결과물로 "짝수만 콘솔에 노출"하는 큰 작업을 얻어낸 것이다.

---

## 4단계 - 람다식으로 함수형 프로그램 개선하기
람다식 : 이름 없는 익명 함수. 
- 함수가 무엇을 하는지만 명확하게 알려주면 굳이 이름이 필요없다. (컴퓨터 입장에서)
```java
private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
    numbers.stream()
            .filter(number -> number % 2 == 0)
            .forEach(System.out::println);
}
```
- `number -> number % 2 == 0`
  - 'number'를 '->' 통해 지정한다. 이후 'number'를 '2'로 나눈(%) 값이 '0'인지 확인한다. 
    - 나는 마피아 게임을 떠올리며 이해했다.
    - `number % 2 == 0` 부분을 단순 로직이 아닌 함수로 생각해야 한다.
  - '->' : 람다식의 핵심이다. 

---

##  5단계 - 스트림, 필터, 람다를 사용해 함수형 프로그램 예제 실습하기

#### 예제 과제
```java
List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
```
과제는 지금까진 배운 함수형 프로그래밍을 사용해서 구현해야 한다. 
1. `numbers`의 요소 중 홀수만 콘솔 출력 메서드
2. `courses`의 모든 요소 콘솔 출력 메서드
3. `courses`의 요소 중 "Spring"이라는 문자열이 포함된 것만 콘솔 출력 메서드
4. `courses`의 요소 중 문자열의 길이가 4 이상인 것만 콘솔 출력 메서드

---

## 6단계 - 함수형 프로그램에서 map 사용하기 - 예제 포함

#### map() 사용 실습 : printSquaresOfEvenNumbers() 신규 메서드 작성 (짝수 숫자만 제곱해서 콘솔에 출력하기)
```java
private static int SquaresNumber(int number) {
        return number * number;
}

private static void printSquaresOfEvenNumbers(List<Integer> numbers) {
        numbers.stream()
        .filter(number -> number % 2 == 0)
        .map(FP01Functional::SquaresNumber)
        .forEach(System.out::println);
}
```
- map() : 각 요소를 다른 요소로 매핑(변환)한다.

#### printSquaresOfEvenNumbers() 람다식으로 개선
```java
private static void printSquaresOfEvenNumbers(List<Integer> numbers) {
    numbers.stream()
            .filter(number -> number % 2 == 0)
            .map(number -> number * number)
            .forEach(System.out::println);
}
```

#### 부록 : 함수형 프로그램 패러다임의 이해.
- 추상화
  - 함수형 프로그래밍은 프로그래밍에서 반복되된 특정 작업을 추상화 시키는 인터페이스가 중요하다.
  - 스트림의 filter, map 모두 필터링, 매핑이라는 작업을 추상화 시켰다.
    - "필터링 해야 해", "매핑해야 해"만 존재하고 어떻게 할지는 알려주지 않는다.
- 선언적 프로그래밍
  - "필터링 해야 해", "매핑해야 해"라고 선언만 한다. 어떻게 할지는 아직도 추상적이다.
    - 그래서 '무엇을'에 집중한다고 하는 것이다. "필터링을 해야 해"라고만 선언하고 어떻게 할지는 신경쓰지 않는다.

이러한 관점을 이해하기 위해서는 단순히 스트림을 사용하는 것이 아닌 스트림을 만든 입장에서 이해해야 한다.

스트림을 만든 사람은 "필터링 해야 해", "매핑해야 해"만 선언했다. 그것을 어떻게 할지는 사용자의 몫으로 두었다.

이와 같이 스트림을 만든 입장에서 바라볼 때 함수형 프로그래밍의 개념을 이해할 수 있다.

즉, 스트림을 만든 사람 처럼 '무엇을' 할지 작업을 선언하고, 이것을 '어떻게' 할지는 나중에 생각하는 것이 함수형 프로그래밍 방식이다.

---