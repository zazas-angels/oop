package core.administrator;

import java.util.List;

import core.category.CategoryInterface;
import core.category.CategoryTreeInterface;

public class StubCategoryTree implements CategoryTreeInterface{

	@Override
	public void add(CategoryInterface newOne, CategoryInterface parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryInterface> getChilds(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryInterface> getRoots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(CategoryInterface newOne, int parentID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryInterface getParent(CategoryInterface cur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(CategoryInterface cur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryInterface> getChildBush(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryInterface> getParentBranch(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChilds(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteWantedCategory(int wcID) {
		// TODO Auto-generated method stub
		
	}

}
