package volume02.chapter01.collecting;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * downstream collectors
 * @author mhts
 * @date 2018Äê6ÔÂ28ÈÕ
 */
public class DownstreamCollectors {
	
	public static Stream<City> readCities(String filename) throws IOException {
		Path path = Paths.get(System.getProperty("user.dir") + "\\resources\\" + filename);
		return Files.lines(path).map(line -> line.split(", "))
				.map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
	}

	public static void main(String[] args) throws IOException {
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(
				Locale::getCountry, toSet()));
		System.out.println("countryToLocaleSet: " + countryToLocaleSet);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(
				Locale::getCountry, counting()));
		System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);
		
		Stream<City> cities = readCities("cities.txt");
		Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(
				City::getState, summingInt(City::getPopulation)));
		System.out.println("stateToCityPopulation: " + stateToCityPopulation);
		
		cities = readCities("cities.txt");
		Map<String, Optional<String>> stateToLongestCityName = cities.collect(groupingBy(
				City::getState, mapping(
						City::getName, maxBy(
								Comparator.comparing(String::length)))));
		System.out.println("stateToLongestCityName: " + stateToLongestCityName);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryToLanguages = locales.collect(groupingBy(
				Locale::getDisplayCountry, mapping(
						Locale::getDisplayLanguage, toSet())));
		System.out.println("countryToLanguages: " + countryToLanguages);
		
		cities = readCities("cities.txt");
		Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(groupingBy(
				City::getState, summarizingInt(City::getPopulation)));
		System.out.println(stateToCityPopulationSummary.get("NY"));
		
		cities = readCities("cities.txt");
		Map<String, String> stateToCityNames = cities.collect(groupingBy(
				City::getState, reducing(
						"", City::getName, (s, t) -> 
							s.length() == 0 ? t : s + ", " + t)));
		System.out.println("stateToCityNames: " + stateToCityNames);
		
		cities = readCities("cities.txt");
		stateToCityNames = cities.collect(groupingBy(
				City::getState, mapping(
						City::getName, joining(". "))));
		System.out.println("stateToCityNames: " + stateToCityNames);
	}

}
