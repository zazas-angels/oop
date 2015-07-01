package core.database;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import core.StubDBConnection;
import core.administrator.Administrator;
import core.administrator.StubCategoryTree;
import core.administrator.SuperAdministrator;
import core.category.CategoryTree;
import core.category.CategoryTreeInterface;

public class DBConnectionTest {
	/**
	 * testing some get options
	 */
	@Test
	public void testSomeGetters() {
		Set<Integer> set = new HashSet<Integer>();
		Connection con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getUsers(int id){
				setForDB.add(1);
				return null;
			}
		};
		con.getUsers(3);
		assertTrue(set.contains(1));
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getCategories(int id){
				setForDB.add(2);
				return null;
			}
		};
		con.getCategories(3);
		assertTrue(set.contains(2));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getUsersCategories(int id){
				setForDB.add(3);
				return null;
			}
		};
		con.getUsersCategories(3);
		assertTrue(set.contains(3));
		
		
	}

}
