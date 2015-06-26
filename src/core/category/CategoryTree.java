package core.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryTree implements CategoryTreeInterface {
	private HashMap<Integer, String> db;
	private ArrayList<CategoryInterface> roots;
	private HashMap<CategoryInterface, CategoryInterface> chParent;
	private HashMap<CategoryInterface, ArrayList<CategoryInterface>> map;

	public CategoryTree(ResultSet s) {
		db = new HashMap<Integer, String>();
		roots = new ArrayList<CategoryInterface>();
		chParent = new HashMap<CategoryInterface, CategoryInterface>();
		map = new HashMap<CategoryInterface, ArrayList<CategoryInterface>>();
		try {
			while (s.next()) {
				int id = Integer.parseInt(s.getString("ID"));
				String name = s.getString("name");
				Category cur = new Category(id, name);
				db.put(id, name);
				String pId = s.getString("ParentId");
				if (pId == null) {
					roots.add(cur);
				} else {
					CategoryInterface par = new Category(Integer.parseInt(pId), db.get(Integer.parseInt(pId)));
					chParent.put(cur, par);
					ArrayList<CategoryInterface> temp;
					if (map.containsKey(par)) {
						temp = map.get(par);
					} else {
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
	public void add(CategoryInterface newOne, CategoryInterface parent) {
		if (parent == null) {
			roots.add(newOne);
		} else {
			if (map.containsKey(parent)) {
				ArrayList<CategoryInterface> arr = map.get(parent);
				arr.add(newOne);
				map.put(parent, arr);
				chParent.put(newOne, parent);
			}
		}
	}
	
	@Override
	public void add(CategoryInterface newOne, int parentID) {
		CategoryInterface parent = null;
		if(parentID == -1){
			roots.add(newOne);
		}else{
			if(db.containsKey(parentID)){
				parent = new Category(parentID, db.get(parentID));
				if (map.containsKey(parent)) {
					ArrayList<CategoryInterface> arr = map.get(parent);
					arr.add(newOne);
					map.put(parent, arr);
					chParent.put(newOne, parent);
				}
			}
		}
		
	}


	@Override
	//this method returns parent of current category, if given one is root it returns false
	public CategoryInterface getParent(CategoryInterface cur) {
		return chParent.get(cur);
	}


	@Override
	//amas gonia racxa akliaa :/
	public void remove(CategoryInterface cur) {
		if (roots.contains(cur)) {
			roots.remove(cur);
			ArrayList<CategoryInterface> temp = map.remove(cur);
			for (int i = 0; i < temp.size(); i++) {
				chParent.remove(temp.get(i));
				roots.add(temp.get(i));
			}
		} else {
			CategoryInterface par = chParent.remove(cur);
			ArrayList<CategoryInterface> temp = map.remove(cur);
			ArrayList<CategoryInterface> parChilds = map.get(par);
			for (int i = 0; i < temp.size(); i++) {
				parChilds.add(temp.get(i));
				chParent.put(temp.get(i), par);
			}
			map.put(par, parChilds);
		}
	}


	@Override
	public List<CategoryInterface> getChildBush(int id) {
		CategoryInterface obj = new Category(id, db.get(id));
		List<CategoryInterface> cur = new ArrayList<CategoryInterface>();
		fillChildBush(cur, obj);
		return cur;
	}
	
	private void fillChildBush(List<CategoryInterface> list, CategoryInterface cat){
		List<CategoryInterface> temp = this.getChilds(cat.getId());
		list.add(cat);
		if(temp == null) return;
		for(int i = 0; i < temp.size(); i++){
			fillChildBush(list, temp.get(i));
		}
	}

	@Override
	public List<CategoryInterface> getParentBranch(int id) {
		List<CategoryInterface> cur = new ArrayList<CategoryInterface>();
		CategoryInterface obj = new Category(id, "");
		while(chParent.containsKey(obj)){
			obj = chParent.get(obj);
			cur.add(obj);
		}
		return cur;
	}

	@Override
	public boolean hasChilds(int id) {
		CategoryInterface obj = new Category(id, "");
		if(map.containsKey(obj)) return true;
		return false;
	}

	

}