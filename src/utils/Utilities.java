package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import items.Item;

public class Utilities {
	public static int getKnapsackSize(String filepath) throws IOException {
		final String KNAPSACKPATTERN = "^[0-9]+$";
		Pattern pattern = Pattern.compile(KNAPSACKPATTERN);
		List<String> list = Files.readAllLines(Paths.get(filepath));
		return Integer.parseInt(list.stream().filter(e -> pattern.matcher(e).matches()).findFirst().get());

	}

	public static List<Item> getItemsList(String filepath) throws IOException {
		final String ITEMPATTERN = "[0-9]+ [0-9]+";
		Pattern pattern = Pattern.compile(ITEMPATTERN);
		return Files.readAllLines(Paths.get(filepath))//
				.stream()//
				.filter(e->pattern.matcher(e).matches())//
				.map(e -> e.split(" "))//
				.map(e -> new Item(Integer.parseInt(e[1]), Integer.parseInt(e[0])))//
				.collect(Collectors.toList());
	}

}
