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
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Category)obj).id;
	}
	
	@Override
	public int hashCode() {
		return ((Integer)id).hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + " : " + this.name;
	}
}
