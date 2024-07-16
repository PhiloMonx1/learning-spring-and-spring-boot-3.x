package programming;

import java.util.List;

public class FP01Functional {

	public static void main(String[] args) {
		printEvenNumbersInListFunctional(List.of(12, 9, 13, 4, 6, 2, 4, 12, 15));
	}

//	private static void printInt(int number) {
//		System.out.println(number);
//	}

	private static boolean isEven(int number) {
		return number % 2 == 0;
	}

	private static void printAllNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream()
				.forEach(System.out::println);
	}

	private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream()
				.filter(FP01Functional::isEven)
				.forEach(System.out::println);
	}
}
