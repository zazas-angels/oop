package core;

import java.util.ArrayList;
import java.util.List;

public class CategoryTree implements CategoryTreeInterface {

	@Override
	public int add(CategoryInterface newOne, CategoryInterface parent) {
		// TODO Auto-generated method stub
		return 0;
	}
	//just for testing
	@Override
	public List<CategoryInterface> getChilds(int id) {
		// TODO Auto-generated method stub\
		List<CategoryInterface>  results= new ArrayList<CategoryInterface>();
		results.add(new Category(5,"Category5"));
		results.add(new Category(7,"Category7"));
		results.add(new Category(20,"Category20"));
		return results;
	}
	//just for testing
	@Override
	public List<CategoryInterface> getRoots() {
		// TODO Auto-generated method stub
		List<CategoryInterface>  results= new ArrayList<CategoryInterface>();
		results.add(new Category(1,"Category1"));
		results.add(new Category(2,"Category2"));
		results.add(new Category(3,"Category3"));
		results.add(new Category(4,"Category4"));
		return results;
		
	}

	@Override
	public CategoryInterface getParent(CategoryInterface cur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remove(CategoryInterface cur) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<CategoryInterface> getChildBush(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CategoryInterface> getParentBranch(int id) {
		List<CategoryInterface>  results= new ArrayList<CategoryInterface>();
		results.add(new Category(4,"Category4"));
		results.add(new Category(1,"Category1"));
		results.add(new Category(2,"Category2"));
		return results;
	}

}
