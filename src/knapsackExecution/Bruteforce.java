package knapsackExecution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import items.Item;
import utils.Utilities;

public class Bruteforce {
	private int arSize;
	private int KNAPSACKSIZE;
	private List<Item> ITEMS;
	int[] vector;

	public void classify(String filepath) {
		try {

			KNAPSACKSIZE = Utilities.getKnapsackSize(filepath);
			ITEMS = Utilities.getItemsList(filepath);
			arSize = ITEMS.size();
			long start = System.currentTimeMillis();
			vector = new int[arSize];
			List<Item> solution = null;

			int bestSum = -1;
			for (int i = 0; i < Math.pow(2, ITEMS.size()); ++i) {
				int[] characteristicVector = getCharVector(i);
				List<Item> currentItems = extractItems(characteristicVector);
				// String characteristicVector = getCharVectorNEW(i);
				// List<Item> currentItems = extractItemsString(characteristicVector, ITEMS);
				if (solution == null && doesntExceedCapacityItems(currentItems)) {
					solution = extractItems(characteristicVector);
					bestSum = getSumOfValues(solution);
				} else if (doesntExceedCapacityItems(currentItems)) {
					// solution = extractItems(characteristicVector);
					// bestSum = getSumOfValues(solution);
					int sum = getSumOfValues(currentItems);
					if (sum > bestSum) {
						solution = currentItems;
						bestSum = sum;
					}

				}
				// System.out.println(i);
			}

			System.out.println(getSumOfValues(solution) + " <----------- VALUES");
			System.out.println(getSumOfWeights(solution) + " <------------WEIGHTS");
			System.out.println(Arrays.toString(transformBack(solution, ITEMS.size())));
			System.out.println(solution + "  <---------BEST OPTION");

			long finish = System.currentTimeMillis();
			System.out.println("DONE in " + (finish - start) + " miliseconds");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int[] getCharVector(int number) {
		// int[] vector = new int[arSize];

		// for (int j = vector.length - 1; j >= 0; --j) {
		// vector[j] = number % 2;
		// number = number / 2;
		// }
		int index = 0;
		while (number > 0) {
			vector[index++] = number % 2;
			number = number / 2;
		}
		return vector;
	}

	private String getCharVectorNEW(int number) {
		String output = Integer.toBinaryString(number);
		int howMuch = arSize - output.length();

		String outcome = "";
		for (int i = 0; i < howMuch; i++) {
			output += "0";
		}
		return outcome + output;
	}

	private List<Item> extractItems(int[] vector) {
		List<Item> list = new ArrayList<>();
		for (int i = 0; i < vector.length; ++i) {
			if (vector[i] == 1) {
				list.add(ITEMS.get(i));
			}
		}

		return list; // ITEMS.stream().filter(e -> vector[ITEMS.indexOf(e)] ==
						// 1).collect(Collectors.toList());
	}

	private boolean isAllowed(int[] vector, int bestSum) {
		int sum = ITEMS.stream().filter(e -> vector[ITEMS.indexOf(e)] == 1).mapToInt(e -> e.value).sum();
		int weight = ITEMS.stream().filter(e -> vector[ITEMS.indexOf(e)] == 1).mapToInt(e -> e.weight).sum();
		return sum > bestSum && weight < KNAPSACKSIZE;
	}

	private List<Item> extractItemsString(String binary, List<Item> list) {

		return list.stream().filter(e -> Character.getNumericValue(binary.charAt(list.indexOf(e))) == 1)
				.collect(Collectors.toList());
	}

	private int getSumOfValues(List<Item> list) {
		int sum = 0;
		for (Item item : list) {
			sum += item.value;
		}
		return sum;// list.stream().mapToInt(e -> e.value).sum();
	}

	private int getSumOfWeights(List<Item> list) {
		int sum = 0;
		for (Item item : list) {
			sum += item.weight;
		}
		return sum;// list.stream().mapToInt(e -> e.weight).sum();
	}

	private boolean doesntExceedCapacityItems(List<Item> list) {

		return getSumOfWeights(list) <= KNAPSACKSIZE;
	}

	private int getMaxSum(Collection<Integer> collection) {
		return collection.stream().mapToInt(e -> e).max().getAsInt();
	}

	private List<List<Item>> getAnswers(Map<List<Item>, Integer> map, int maxSum) {
		return map.keySet().stream().filter(e -> getSumOfValues(e) == maxSum).collect(Collectors.toList());
	}

	private int[] transformBack(List<Item> list, int size) {
		int[] charVector = new int[size];
		list.stream().mapToInt(e -> e.currentID).forEach(e -> charVector[e] = 1);
		return charVector;
	}
}
