package core;

public class Category implements CategoryInterface {
	int id;
	String name;
	public Category(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((Category)obj).getId()==id;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ((Integer)(id)).hashCode();
	}
}
