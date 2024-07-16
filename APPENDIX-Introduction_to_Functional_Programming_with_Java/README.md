# 📒 [학습 노트] 부록 : Java 함수형 프로그래밍 소개

## 목록
1. [Java에서 함수형 프로그래밍 시작하기](#1단계---java에서-함수형-프로그래밍-시작하기)

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