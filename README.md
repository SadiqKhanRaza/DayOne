# DayOne
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxRepeatedWord {

	public static void maxRepeatedWord(final String filePath) {
		try (final Stream<String> stream = Files.lines(Paths.get(filePath));) {
			final List<String> list = stream.collect(Collectors.toList());
			/**
			 * Step 1 : Get the number word count map for the entire file Convert the array
			 * to string to Stream<String[]> FlatMap - Converts Stream<String[]> to
			 * Stream<String> GroupingBy groups the entire output
			 */
			final Map<String, Long> countMap = list.stream().filter(element -> element != null && !element.isEmpty())
					.flatMap(element -> {
						final String[] array = element.split(" ");
						return Arrays.stream(array);
					}).collect(Collectors.groupingBy(element -> element, Collectors.counting()));
			System.out.println("Count From Stream Implementation");

			/**
			 * Step 2: Compare by value and get the max key of the Map
			 */
			final Optional<Entry<String, Long>> entry1 = countMap.entrySet().stream()
					.collect(Collectors.maxBy((element1, element2) -> {
						return element1.getValue().compareTo(element2.getValue());
					}));
			entry1.ifPresent(element -> System.out.println(element.getKey() + " " + element.getValue()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filePath = "C:\\Puvi\\Documents\\Training\\2019\\hands-on\\1\\SampleLogs.log";
		maxRepeatedWord(filePath);
	}
}
