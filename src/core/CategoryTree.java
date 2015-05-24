package core;

import java.util.ArrayList;
import java.util.List;

public class CategoryTree implements CategoryTreeInterface {

	@Override
	public int add(CategoryInterface newOne, CategoryInterface parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CategoryInterface> getChilds(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryInterface> getRoots() {
		// TODO Auto-generated method stub
		List<CategoryInterface> results = new ArrayList<CategoryInterface>();
		results.add(new Category(1,"category1"));
		results.add(new Category(2,"category2"));
		results.add(new Category(3,"category3"));
		results.add(new Category(4,"category4"));
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

}
