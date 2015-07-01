package core.category;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import core.administrator.StubCategoryTree;

public class CategoryTreeTest {

	@Test
	public void testGetChilds() {
		Set<Integer> set = new HashSet<Integer>();
		CategoryTreeInterface tree = new StubCategoryTree(){
			private Set<Integer> setForDB = set;
			@Override
			public List<CategoryInterface> getChilds(int id){
				setForDB.add(5);
				return null;
			}
		};
		tree.getChilds(3);
		assertTrue(set.contains(5));
	}
	
	@Test
	public void testRoots() {
		Set<Integer> set = new HashSet<Integer>();
		CategoryTreeInterface tree = new StubCategoryTree(){
			private Set<Integer> setForDB = set;
			@Override
			public List<CategoryInterface> getRoots(){
				setForDB.add(5);
				return null;
			}
		};
		tree.getRoots();
		assertTrue(set.contains(5));
	}
	@Test
	public void testGetParent() {
		Set<Integer> set = new HashSet<Integer>();
		CategoryTreeInterface tree = new StubCategoryTree(){
			private Set<Integer> setForDB = set;
			@Override
			public CategoryInterface getParent(CategoryInterface cur){
				setForDB.add(5);
				return null;
			}
		};
		tree.getParent(null);
		assertTrue(set.contains(5));
	}

}
