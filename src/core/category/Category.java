package core.category;

public class Category implements CategoryInterface {
	private int id;
	private String name;

	public Category(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public synchronized String getName() {
		return this.name;
	}

	public synchronized int getId() {
		return this.id;
	}

	@Override
	public synchronized boolean equals(Object obj) {
		return this.id == ((Category) obj).id;
	}

	@Override
	public synchronized int hashCode() {
		return ((Integer) id).hashCode();
	}

	@Override
	public synchronized String toString() {
		// TODO Auto-generated method stub
		return this.id + " : " + this.name;
	}
}
