# 📒 [학습 노트] 챕터 2 : Spring Framework를 사용하여 Java 객체를 생성하고 관리하기

## 1단계 - Spring Framework Beans의 지연 초기화와 즉시 초기화 알아보기

#### 즉시 초기화 (Eager Initialization)
- Spring Bean의 기본 초기화 방식
```java
@Component
class ClassA { }
@Component
class ClassB {
	private ClassA classA;
	public ClassB(ClassA classA) {
		System.out.println("초기화를 진행합니다.");
		this.classA = classA;
	}
}
@Configuration
@ComponentScan
public class LazyInitializationContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(
				LazyInitializationContextLauncherApplication.class)) {
		}
	}
}
```
![Eager-Initialization-test.png](image/Eager-Initialization-test.png)
- `LazyInitializationContextLauncherApplication` 에서는 `context`를 선언할 뿐 `ClassB`에 대한 호출이 없다.
- Spring 컨텍스트를 실행하면 기본적으로 초기화가 적용된다. (객체의 Bean이 생성될 때 자동 초기화)

#### 지연 초기화 (Lazy Initialization)
```java
@Component
@Lazy
class ClassB {
	private ClassA classA;
	public ClassB(ClassA classA) {
		System.out.println("초기화를 진행합니다.");
		this.classA = classA;
	}
}
```
- `@Lazy` 어노테이션을 부여해서 초기화를 지연시킬 수 있다.
- `getBean()` 메서드를 통해 `ClassB` 클래스의 Bean을 호출할 때 초기화가 진행된다.
- `@Component`를 부여한 클래스나 `@Bean`을 부여한 메서드에 사용할 수 있다.
  - `@Configuration` 클래스에도 사용 가능

#### 지연 초기화 특징
- 기본적으로 제공되는 즉시 초기화를 사용하는 것이 권장된다.
  - Spring 구성에 오류가 있을 경우 애플리케이션 실행 단계에서 오류를 조기 발견할 수 있기 때문.
- 실제 의존성 대신 '해결 프록시(Lazy-resolution proxy)'가 주입된다.
  - 실제 의존성 객체와 동일한 인터페이스를 구현하고 있다.
