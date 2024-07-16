# 📒 [학습 노트] 부록 : Java 함수형 프로그래밍 소개

## 목록
1. [Java에서 함수형 프로그래밍 시작하기](#1단계---java에서-함수형-프로그래밍-시작하기)
2. [Java 함수형 프로그램 처음 작성하기](#2단계---java-함수형-프로그램-처음-작성하기)

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