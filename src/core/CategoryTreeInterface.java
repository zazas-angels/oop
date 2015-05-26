package core;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


public interface CategoryTreeInterface {
	public int add(Category newOne, Category parent);
	public List<Category> getChilds(int id);
	public List<Category> getRoots();
	public Category getParent(Category cur);
	public int remove(Category cur);
}
