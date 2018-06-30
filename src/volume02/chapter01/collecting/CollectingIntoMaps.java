package volume02.chapter01.collecting;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * test toMap()
 * @author mhts
 * @date 2018Äê6ÔÂ28ÈÕ
 */
public class CollectingIntoMaps {

	public static Stream<Person> people() {
		return Stream.of(new Person(1001, "jack"), new Person(1002, "mike"),
				new Person(1003, "sam"));
	}
	
	public static void main(String[] args) {
		Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
		System.out.println("idToName: " + idToName);
		
		Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
		System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
		
		idToPerson = people().collect(
				Collectors.toMap(
						Person::getId,
						Function.identity(),
						(oldValue, newValue) -> {throw new IllegalStateException();},
						TreeMap::new));
		System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
		
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		Map<String, String> languageNames = locales.collect(
				Collectors.toMap(
						Locale::getDisplayLanguage,
						l -> l.getDisplayLanguage(l),
						(oldValue, newValue) -> oldValue));
		System.out.println("languageNames: " + languageNames);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryLanguageSets = locales.collect(
				Collectors.toMap(
						Locale::getDisplayCountry,
						l -> Collections.singleton(l.getDisplayLanguage()),
						(a, b) -> {
							Set<String> union = new HashSet<String>(a);
							union.addAll(b);
							return union;
						}));
		System.out.println("countryLanguageSets: " + countryLanguageSets);
	}

}
