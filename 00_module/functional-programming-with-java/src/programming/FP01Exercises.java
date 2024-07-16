package programming;

import java.util.List;

public class FP01Exercises {

	public static void main(String[] args) {
		List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
		List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

		printOddNumber(numbers);
		System.out.println("-------------------------");
		printAllCourse(courses);
		System.out.println("-------------------------");
		printContainsSpring(courses);
		System.out.println("-------------------------");
		printLengthOfCourse(courses);
	}

	private static void printOddNumber(List<Integer> numbers) {
		numbers.stream()
				.filter(number -> number % 2 == 1)
				.forEach(System.out::println);
	}

	private static void printAllCourse(List<String> courses) {
		courses.stream()
				.forEach(System.out::println);
	}

	private static void printContainsSpring(List<String> courses) {
		courses.stream()
				.filter(course -> course.contains("Spring"))
				.forEach(System.out::println);
	}

	private static void printLengthOfCourse(List<String> courses) {
		courses.stream()
				.filter(course -> course.length() >= 4)
				.forEach(System.out::println);
	}
}
