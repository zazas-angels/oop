package core.administrator;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import core.StubDBConnection;
import core.category.Category;
import core.category.CategoryInterface;
import core.category.CategoryTreeInterface;
import core.database.Connection;
import core.user.User;

public class AdministratorTest {
	/**
	 * some simple and empty tests for getters
	 */
	@Test
	public void testGetters() {
		Connection con = new StubDBConnection();
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(1, admin.getId());
		assertEquals("zaza@gmail.com", admin.getEmail());
		assertEquals(User.generatePassword("paroli"), admin.getPassword());
		assertEquals(con, admin.getDbConnection());
		assertEquals(tree, admin.getCategoryTree());

		admin = new Administrator(0, null, null, null, null);
		assertEquals(0, admin.getId());
		assertEquals(null, admin.getEmail());
		assertEquals(User.generatePassword(null), admin.getPassword());
		assertEquals(null, admin.getDbConnection());
		assertEquals(null, admin.getCategoryTree());

		admin = new Administrator(12, "asdf@1.1", "1234", con, tree);
		assertEquals(12, admin.getId());
		assertEquals("asdf@1.1", admin.getEmail());
		assertEquals(User.generatePassword("1234"), admin.getPassword());
		assertEquals(con, admin.getDbConnection());
		assertEquals(tree, admin.getCategoryTree());

		admin = new Administrator(9, "random@mail.ru", "qwerty", con, tree);
		assertEquals(9, admin.getId());
		assertNotEquals("zaza@gmail.com", admin.getEmail());
		assertEquals(User.generatePassword("qwerty"), admin.getPassword());
		assertEquals(con, admin.getDbConnection());
		assertEquals(tree, admin.getCategoryTree());

		admin = new Administrator(1123, "abrakad@abra.ge", "raisparoli", con,
				tree);
		assertEquals(1123, admin.getId());
		assertEquals("abrakad@abra.ge", admin.getEmail());
		assertEquals(User.generatePassword("raisparoli"), admin.getPassword());
		assertEquals(con, admin.getDbConnection());
		assertEquals(tree, admin.getCategoryTree());

		admin = new Administrator(0, "", "", null, null);
		assertEquals(0, admin.getId());
		assertEquals("", admin.getEmail());
		assertEquals(User.generatePassword(""), admin.getPassword());
		assertEquals(null, admin.getDbConnection());
		assertEquals(null, admin.getCategoryTree());
	}

	@Test
	public void testGetReports() {
		ResultSet set = new StubResultSet();
		Connection con = new StubDBConnection() {
			public ResultSet getReports() {
				return set;
			}
		};
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(set, admin.getReports());
		assertEquals(set, admin.getReports());// re use
		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getReports());
		con = new StubDBConnection() {
			public ResultSet getReports() {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getReports());

	}

	@Test
	public void testGetReportsDays() {
		ResultSet set = new StubResultSet();
		ResultSet set1 = new StubResultSet();
		Connection con = new StubDBConnection() {
			public ResultSet getReports(int num) {
				return set;
			}
		};
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(set, admin.getReports(7));
		assertEquals(set, admin.getReports(9));// re use
		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getReports(3));
		con = new StubDBConnection() {
			public ResultSet getReports(int num) {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getReports(23));
		con = new StubDBConnection() {
			public ResultSet getReports(int num) {
				if (num > 10)
					return set1;
				else
					return set;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(set, admin.getReports(7));
		assertEquals(set1, admin.getReports(17));
		assertEquals(set1, admin.getReports(17));// re use
	}

	@Test
	public void testGetWantedCategories() {
		ResultSet set = new StubResultSet();
		Connection con = new StubDBConnection() {
			public ResultSet getWantedCategories() {
				return set;
			}
		};
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(set, admin.getWantedCategories());
		assertEquals(set, admin.getWantedCategories());// re use
		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getWantedCategories());
		con = new StubDBConnection() {
			public ResultSet getWantedCategories() {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getWantedCategories());

		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getWantedCategories());
		con = new StubDBConnection() {
			public ResultSet getWantedCategories() {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getWantedCategories());
	}

	@Test
	public void testGetWantedCategoriesDays() {
		ResultSet set = new StubResultSet();
		Set<User> seta = new HashSet<User>();
		Connection con = new StubDBConnection() {
			private Set<User> bannedUsers = seta;

			public ResultSet getWantedCategories(int num) {
				return set;
			}

		};
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(set, admin.getWantedCategories(7));
		assertEquals(set, admin.getWantedCategories(7));// re use
		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getWantedCategories(12));
		con = new StubDBConnection() {
			public ResultSet getWantedCategories(int num) {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getWantedCategories(14));

		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getWantedCategories(23));
		con = new StubDBConnection() {
			public ResultSet getWantedCategories(int num) {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getWantedCategories(11));
	}

	@Test
	public void testGetNotifications() {
		ResultSet set = new StubResultSet();
		Connection con = new StubDBConnection() {
			public ResultSet getNotifications() {
				return set;
			}
		};
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(set, admin.getNotifications());
		assertEquals(set, admin.getNotifications());// re use
		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getNotifications());
		con = new StubDBConnection() {
			public ResultSet getNotifications() {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getNotifications());

		admin = new Administrator(1, "zaza@gmail.com", "paroli", null, tree);
		assertEquals(null, admin.getNotifications());
		con = new StubDBConnection() {
			public ResultSet getNotifications() {
				return null;
			}
		};
		admin = new Administrator(1, "zaza@gmail.com", "paroli", con, tree);
		assertEquals(null, admin.getNotifications());
	}

	@Test
	public void testFindUser() {
		ResultSet set = new StubResultSet();
		Connection con = new StubDBConnection() {
			public ResultSet getUsersByCriterias(String name, String ban,
					String active, String categoryName) {
				return set;
			}
		};
		CategoryTreeInterface tree = new StubCategoryTree();
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		assertEquals(set, admin.findUser("zaza", "nope", "yep", "anzori"));
		assertNotEquals(set, admin.findUser(null, "nope", "yep", "anzori"));
		assertNotEquals(set, admin.findUser(null, null, null, null));

	}

	@Test
	public void testAddCategory() throws SQLException {
		Set<String> setForDB = new HashSet<String>();
		Set<String> setForTree = new HashSet<String>();
		CategoryTreeInterface tree = new StubCategoryTree() {
			private Set<String> set = setForTree;

		 @Override
			public void add(CategoryInterface base,
					CategoryInterface cat) {
					set.add(base.getName());
				
				} 
		};
		Connection con = new StubDBConnection() {
			private Set<String> set = setForDB;

			public int addCategory(String name,
					int id) {
				set.add(name);
				return 0;
			}
		};
		AdminInterface admin = new Administrator(1, "zaza@gmail.com", "paroli",
				con, tree);
		admin.addCategory("zaza",1);
		assertEquals(setForDB.contains("zaza"), true);
		assertEquals(setForDB.contains("cxoveli"), false);
		assertEquals(setForTree.contains("zaza"), true);

	}

}
