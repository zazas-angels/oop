package core.category;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import core.administrator.StubCategoryTree;

public class CategoryTreeTest {

	/**
	 * testing getchilds method
	 */
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
	/**
	 * testing roots method
	 */
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
	/**
	 * testing getParent method
	 */
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
	/**
	 * testing hasChilds 
	 */
	@Test
	public void testHasChilds() {
		Set<Integer> set = new HashSet<Integer>();
		CategoryTreeInterface tree = new StubCategoryTree(){
			private Set<Integer> setForDB = set;
			@Override
			public boolean hasChilds(int id){
				setForDB.add(5);
				return true;
			}
		};
		tree.hasChilds(14);
		assertTrue(set.contains(5));
	}
	/**
	 * test add
	 */
	@Test
	public void testAdd() {
		Set<Integer> set = new HashSet<Integer>();
		CategoryTreeInterface tree = new StubCategoryTree(){
			private Set<Integer> setForDB = set;
			@Override
			public void add(CategoryInterface newOne, CategoryInterface parent){
				setForDB.add(5);
			}
		};
		tree.add(null,null);
		assertTrue(set.contains(5));
	}
	/**
	 * testing remove
	 */
	@Test
	public void testRemove() { 
		Set<Integer> set = new HashSet<Integer>();
		CategoryTreeInterface tree = new StubCategoryTree(){
			private Set<Integer> setForDB = set;
			@Override
			public void add(CategoryInterface newOne, CategoryInterface parent){
				setForDB.add(5);
			}
		};
		tree.add(null,null);
		tree.getChildBush(5);
		tree.add(null, 4);
		tree.getParentBranch(3);
		assertTrue(set.contains(5));
	}
	
}
