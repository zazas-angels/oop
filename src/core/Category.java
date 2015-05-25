package core;

public class Category implements CategoryInterface{
	private int id;
	private String name;
	
	public Category(int id, String name){
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}
}
