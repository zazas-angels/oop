package core;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


public interface CategoryTreeInterface {
	int add(CategoryInterface newOne, CategoryInterface parent);
	List<CategoryInterface> getChilds(int id);
	//returns all children (children of children's ....)
	List<CategoryInterface> getChildBush(int id);
	//returns all parents list
	List<CategoryInterface> getParentBranch(int id);
	List<CategoryInterface> getRoots();
	CategoryInterface getParent(CategoryInterface cur);
	int remove(CategoryInterface cur);
}
