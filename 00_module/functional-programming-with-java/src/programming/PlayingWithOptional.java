package programming;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PlayingWithOptional {

	public static void main(String[] args) {
		List<String> fruits = List.of("apple", "banana", "mango", "pineapple");

		Predicate<String> predicate = fruit -> fruit.startsWith("b");

		Optional<String> startsWithBFruits = fruits.stream()
				.filter(predicate)
				.findFirst();

		System.out.println(startsWithBFruits);
		System.out.println(startsWithBFruits.isEmpty());
		System.out.println(startsWithBFruits.isPresent());
		System.out.println(startsWithBFruits.get());
	}
}
