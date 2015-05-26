package core.category;

import java.util.List;


public interface CategoryTreeInterface {

	public int add(CategoryInterface newOne, CategoryInterface parent);

	public List<CategoryInterface> getChilds(int id);

	public List<CategoryInterface> getRoots();

	public CategoryInterface getParent(CategoryInterface cur);

	public int remove(CategoryInterface cur);

	//returns all children and children's children and so on. (including itself)
	public List<CategoryInterface> getChildBush(int id);
	//returns all parents list 
	public List<CategoryInterface> getParentBranch(int id);
	//returnes if it has childs
	public boolean hasChilds(int id);
}
