package core;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


public interface CategoryTreeInterface {
	public int add(CategoryInterface newOne, CategoryInterface parent);
	public List<CategoryInterface> getChilds(int id);
	public List<CategoryInterface> getRoots();
	public CategoryInterface getParent(CategoryInterface cur);
	public int remove(CategoryInterface cur);
}
