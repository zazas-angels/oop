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
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getPictures(int id){
				setForDB.add(4);
				return null;
			}
		};
		con.getPictures(3);
		assertTrue(set.contains(4));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getColors(int id){
				setForDB.add(5);
				return null;
			}
		};
		con.getColors(3);
		assertTrue(set.contains(5));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getFonts(int id){
				setForDB.add(6);
				return null;
			}
		};
		con.getFonts(3);
		assertTrue(set.contains(6));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getThemes(int id){
				setForDB.add(7);
				return null;
			}
		};
		con.getThemes(3);
		assertTrue(set.contains(7));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getElements(int id){
				setForDB.add(8);
				return null;
			}
		};
		con.getElements(3);
		assertTrue(set.contains(8));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getTexts(int id){
				setForDB.add(9);
				return null;
			}
		};
		con.getTexts(3);
		assertTrue(set.contains(9));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getElementsInfo(int id){
				setForDB.add(10);
				return null;
			}
		};
		con.getElementsInfo(3);
		assertTrue(set.contains(10));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getAlbomsElements(int id){
				setForDB.add(11);
				return null;
			}
		};
		con.getAlbomsElements(3);
		assertTrue(set.contains(11));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getAlboms(int id){
				setForDB.add(12);
				return null;
			}
		};
		con.getAlboms(3);
		assertTrue(set.contains(12));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getAlbomsPictures(int id){
				setForDB.add(13);
				return null;
			}
		};
		con.getAlbomsPictures(3);
		assertTrue(set.contains(13));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getElementsPictures(int id){
				setForDB.add(14);
				return null;
			}
		};
		con.getElementsPictures(3);
		assertTrue(set.contains(14));
		
		con = new StubDBConnection(){
			private Set<Integer> setForDB = set;
			@Override
			public ResultSet getAdmins(int id){
				setForDB.add(15);
				return null;
			}
		};
		con.getAdmins(3);
		assertTrue(set.contains(15));
	}

}
