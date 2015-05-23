package core;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


public interface CategoryTreeInterface {
	int add(CategoryInterface newOne, CategoryInterface parent);
	List<CategoryInterface> getChilds(CategoryInterface cur);
	List<CategoryInterface> getRoots();
	CategoryInterface getParent(CategoryInterface cur);
	int remove(CategoryInterface cur);
}
