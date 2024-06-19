# 📒 [학습 노트] 챕터 2 : Spring Framework를 사용하여 Java 객체를 생성하고 관리하기

## 1단계 - Java 객체의 생성 및 관리를 위한 Spring Framework 이해하기

#### 스프링이 객체를 자동 생성하도록 하는 방법
1. configuration 클래스와 app 클래스 하나의 파일로 통합
```java
@Configuration
class GamingConfiguration {

	@Bean
	public GamingConsole game() {
		var game = new PacmanGame();
		return game;
	}

	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		var gameRunner = new GameRunner(game);
		return gameRunner;
	}
}
public class App03GamingSpringBeansJava {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
			context.getBean(GamingConsole.class).up();
			context.getBean(GameRunner.class).run();
		}
	}
}
```
이와 같은 방법으로 Configuration 파일을 app 파일과 하나의 파일로 관리할 수 있다.

2. app 클래스를 configuration 클래스로 만들기
```java
@Configuration
public class App03GamingSpringBeansJava {
	@Bean
	public GamingConsole game() {
		var game = new PacmanGame();
		return game;
	}

	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		var gameRunner = new GameRunner(game);
		return gameRunner;
	}
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(App03GamingSpringBeansJava.class)) {
			context.getBean(GamingConsole.class).up();
			context.getBean(GameRunner.class).run();
		}
	}
}
```
해당 방법으로 app 클래스를 Configuration로 만들 수 있다. `AnnotationConfigApplicationContext`는 `App03GamingSpringBeansJava`를 받아야 한다.

3. `@Bean` 어노테이션 사용하지 않고 Spring에 Bean 생성 요청
```java
@Component //해당 어노테이션으로 Spring이 PacmaGame 클래스를 관리하도록 설정
public class PacmanGame implements GamingConsole {

	@Override
	public void up() {
		System.out.println("위로 이동");
	}

	@Override
	public void down() {
		System.out.println("아래로 이동");
	}

	@Override
	public void left() {
		System.out.println("왼쪽으로 이동");
	}

	@Override
	public void right() {
		System.out.println("오른쪽으로 이동");
	}
}
```
Bean에 등록해야 하는 `PacmanGame`에 `@Component` 어노테이션을 부여한다.

```java
@Configuration
@ComponentScan("com.in28minutes.learn_spring_framework.game")
public class App03GamingSpringBeansJava {
	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		var gameRunner = new GameRunner(game);
		return gameRunner;
	}
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(App03GamingSpringBeansJava.class)) {
			context.getBean(GamingConsole.class).up();
			context.getBean(GameRunner.class).run();
		}
	}
}
```
`PacmanGame` Bean을 사용하는 클래스에 `@ComponentScan` 어노테이션을 부여한다.

- @Component : 해당 어노테이션이 적용된 클래스는 스프링 IOC 컨테이너에 의해 Bean으로 자동 등록(예약으로 생각해야 함)된다.
- @ComponentScan : @Component 어노테이션이 적용된 클래스를 자동으로 스캔하여 스프링 Bean으로 등록(실제 등록, 사용으로 생각해야 함)한다.
  - 파라미터로 경로를 설정할 경우 해당 경로에서 찾는다.
  - @ComponentScan({"com.in28minutes.learn_spring_framework.game", "com.example.myapp"}) 와 같이 여러 패키지 경로를 설정할 수도 있다.
    - @ComponentScan을 여러개 사용하는 것도 가능하다.

