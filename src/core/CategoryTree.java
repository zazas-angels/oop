package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTree implements CategoryTreeInterface {
	private HashMap<Integer, String> db;
	private ArrayList<Category> roots;
	private HashMap<Category, Category> chParent;
	private HashMap<Category, ArrayList<Category>> map;
	
	public CategoryTree(ResultSet s){
		db = new HashMap<Integer, String>();
		roots = new ArrayList<Category>();
		chParent = new HashMap<Category, Category>();
		map = new HashMap<Category, ArrayList<Category>>();
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
					Category par = new Category(Integer.parseInt(pId), db.get(Integer.parseInt(pId)));
					chParent.put(cur, par);
					ArrayList<Category> temp;
					if(map.containsKey(par)){
						temp = map.get(par);
					}else{
						temp = new ArrayList<Category>();
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
	public List<Category> getChilds(int id) {
		Category fake = new Category(id, "");
		return map.get(fake);
	}

	@Override
	public List<Category> getRoots() {
		
		return roots;
	}

	@Override
	public int add(Category newOne, Category parent) {
		if(parent == null){
			roots.add(newOne);
			return 0;
		}else{
			if(map.containsKey(parent)){
				ArrayList<Category> arr = map.get(parent);
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
	public Category getParent(Category cur) {
		return chParent.get(cur);
	}


	@Override
	//amas gonia racxa akliaa :/
	public int remove(Category cur) {
		if(roots.contains(cur)){
			roots.remove(cur);
			ArrayList<Category> temp = map.remove(cur);
			for(int i = 0; i < temp.size(); i++){
				chParent.remove(temp.get(i));
				roots.add(temp.get(i));
			}
		}else{
			Category par = chParent.remove(cur);
			ArrayList<Category> temp = map.remove(cur);
			ArrayList<Category> parChilds = map.get(par);
			for(int i = 0; i < temp.size(); i++){
				parChilds.add(temp.get(i));
				chParent.put(temp.get(i), par);
			}
			map.put(par, parChilds);
		}
		return 0;
	}

}
