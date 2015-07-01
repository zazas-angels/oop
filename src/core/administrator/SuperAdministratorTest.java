package core.administrator;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import core.StubDBConnection;
import core.category.CategoryTree;
import core.category.CategoryTreeInterface;
import core.database.Connection;

public class SuperAdministratorTest {

	@Test
	public void testSetAdmin() {
		
		CategoryTreeInterface tree = new StubCategoryTree();
		Set<Integer> set = new HashSet<Integer>();
		Connection con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public Administrator setAdmin(int userID,CategoryTree categoryTree){
				setForDB.add(userID);
				return null;
			}
		};
		SuperAdministrator superAdmin = new SuperAdministrator(1,"zaza@mail.ru","paroli",con,tree);
		superAdmin.setAdmin(7, null);
		assertTrue(set.contains(7));
		
	}
	@Test
	public void testDeleteAdmin() {
		
		CategoryTreeInterface tree = new StubCategoryTree();
		Set<Integer> set = new HashSet<Integer>();
		Connection con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public void deleteAdmin(int adminID){
				setForDB.add(adminID);
			}
			
		};
		SuperAdministrator superAdmin = new SuperAdministrator(5,"zaza@mail.ru","paroli",con,tree);
		superAdmin.deleteAdmin(9);
		assertTrue(set.contains(9));
	}
	

}
