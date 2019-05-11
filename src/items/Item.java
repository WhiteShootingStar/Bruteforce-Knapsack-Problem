package items;

public class Item {

	public int weight, value, currentID;
	public static int id = 0;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
		currentID = id++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentID;
		result = prime * result + value;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (currentID != other.currentID)
			return false;
		if (value != other.value)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [weight=" + weight + ", value=" + value + ", currentID=" + currentID + "]";
	}
}
