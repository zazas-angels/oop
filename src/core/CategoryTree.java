package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTree implements CategoryTreeInterface {
	private HashMap<Integer, String> db;
	private ArrayList<CategoryInterface> roots;
	private HashMap<CategoryInterface, CategoryInterface> chParent;
	private HashMap<CategoryInterface, ArrayList<CategoryInterface>> map;
	
	public CategoryTree(ResultSet s){
		db = new HashMap<Integer, String>();
		roots = new ArrayList<CategoryInterface>();
		chParent = new HashMap<CategoryInterface, CategoryInterface>();
		map = new HashMap<CategoryInterface, ArrayList<CategoryInterface>>();
		try {
			while(s.next()){
				int id = Integer.parseInt(s.getString("ID"));
				String name = s.getString("name");
				Category cur = new Category(id, name);
				db.put(id, name);
				String pId = s.getString("ParentId");
				if(pId == null){
					roots.add(cur);
				}else{
					CategoryInterface par = new Category(Integer.parseInt(pId), db.get(Integer.parseInt(pId)));
					chParent.put(cur, par);
					ArrayList<CategoryInterface> temp;
					if(map.containsKey(par)){
						temp = map.get(par);
					}else{
						temp = new ArrayList<CategoryInterface>();
					}
					temp.add(cur);
					map.put(par, temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CategoryInterface> getChilds(int id) {
		Category fake = new Category(id, "");
		return map.get(fake);
	}

	@Override
	public List<CategoryInterface> getRoots() {
		
		return roots;
	}

	@Override
	public int add(CategoryInterface newOne, CategoryInterface parent) {
		if(parent == null){
			roots.add(newOne);
			return 0;
		}else{
			if(map.containsKey(parent)){
				ArrayList<CategoryInterface> arr = map.get(parent);
				arr.add(newOne);
				map.put(parent, arr);
				chParent.put(newOne, parent);
				return 0;
			}
		}
		return 1;
	}


	@Override
	//this method returns parent of current category, if given one is root it returns false
	public CategoryInterface getParent(CategoryInterface cur) {
		return chParent.get(cur);
	}


	@Override
	//amas gonia racxa akliaa :/
	public int remove(CategoryInterface cur) {
		if(roots.contains(cur)){
			roots.remove(cur);
			ArrayList<CategoryInterface> temp = map.remove(cur);
			for(int i = 0; i < temp.size(); i++){
				chParent.remove(temp.get(i));
				roots.add(temp.get(i));
			}
		}else{
			CategoryInterface par = chParent.remove(cur);
			ArrayList<CategoryInterface> temp = map.remove(cur);
			ArrayList<CategoryInterface> parChilds = map.get(par);
			for(int i = 0; i < temp.size(); i++){
				parChilds.add(temp.get(i));
				chParent.put(temp.get(i), par);
			}
			map.put(par, parChilds);
		}
		return 0;
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

}