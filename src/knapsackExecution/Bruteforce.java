package knapsackExecution;

import java.io.IOException;
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

	public void classify(String filepath) {
		try {
			
			final int KNAPSACKSIZE = Utilities.getKnapsackSize(filepath);
			final List<Item> ITEMS = Utilities.getItemsList(filepath);
			long start = System.currentTimeMillis();
			List<Item> solution = null;
			int bestSum = -1;
			for (int i = 0; i < Math.pow(2, ITEMS.size()-1); i++) {
				int[] characteristicVector = getCharVector(ITEMS, i);
				List<Item> currentItems = extractItems(characteristicVector, ITEMS);
				if (solution == null && doesntExceedCapacityItems(KNAPSACKSIZE, currentItems)) {
					solution = currentItems;
					bestSum = getSumOfValues(solution);
				} else if (doesntExceedCapacityItems(KNAPSACKSIZE, currentItems)) {

					int sum = getSumOfValues(currentItems);
					if (sum > bestSum) {
						solution = currentItems;
						bestSum = sum;
					}

					
				}
				System.out.println(i);
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

	private int[] getCharVector(List<Item> list, int number) {
		int[] vector = new int[list.size()];
		int temp = number;

		for (int j = vector.length - 1; j >= 0; j--) {
			vector[j] = temp % 2;
			temp = temp / 2;
		}
		return vector;
	}

	private int[] getCharVectorNEW(List<Item> list, int number) {
		int[] vector = new int[list.size()];
		int temp = number;
		String[] val = Integer.toBinaryString(temp).split("");
		for (int i = val.length - 1; i >= 0; i--) {
			vector[i] = Integer.parseInt(val[i]);
		}
		return vector;
	}

	private List<Item> extractItems(int[] vector, List<Item> list) {

		return list.stream().filter(e -> vector[list.indexOf(e)] == 1).collect(Collectors.toList());
	}

	private int getSumOfValues(List<Item> list) {
		return list.stream().mapToInt(e -> e.value).sum();
	}

	private int getSumOfWeights(List<Item> list) {
		return list.stream().mapToInt(e -> e.weight).sum();
	}

	private boolean doesntExceedCapacityItems(int capacity, List<Item> list) {

		return getSumOfWeights(list) <= capacity;
	}

	private int getMaxSum(Collection<Integer> collection) {
		return collection.stream().mapToInt(e -> e).max().getAsInt();
	}

	private List<List<Item>> getAnswers(Map<List<Item>, Integer> map, int maxSum) {
		return map.keySet().stream().filter(e -> getSumOfValues(e) == maxSum).collect(Collectors.toList());
	}

	private int[] transformBack(List<Item> list, int size) {
		int[] charVector = new int[size];
		list.stream().mapToInt(e -> e.currentID).forEachOrdered(e -> charVector[e] = 1);
		return charVector;
	}
}
