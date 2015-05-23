package core;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


public interface CategoryTreeInterface {
	int add(CategoryInterface newOne, CategoryInterface parent);
	ArrayList<CategoryInterface> getChilds(CategoryInterface cur);
	CategoryInterface getParent(CategoryInterface cur);
	int remove(CategoryInterface cur);
}
