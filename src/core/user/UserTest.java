package core.user;

import static org.junit.Assert.*;

import org.junit.Test;

import core.SiteConstants;

public class UserTest {


	@Test
	public void testConstructorAndGetters() {
		User tmp = new User("asdf", "asdf@a.a", "1234", "zaza", SiteConstants.Type.email, 1);
		assertEquals(tmp.getName(),"asdf");
		assertEquals(tmp.getEmail(),"asdf@a.a");
		assertEquals(tmp.getType(),SiteConstants.Type.email);
		assertEquals(tmp.getURL(),"zaza.chveniSaiti.ge");
		assertEquals(tmp.getID(), 1);
		
		User tmp1 = new User(null, null, null, null, null, 0);
		assertEquals(tmp1.getName(),null);
		assertEquals(tmp1.getEmail(),null);
		assertEquals(tmp1.getType(),null);
		assertEquals(tmp1.getURL(),"null.chveniSaiti.ge");
		assertEquals(tmp1.getID(), 0);
		
		User tmp2 = new User("emzaria", "emzaria@a.a", "1234", "emzaria", SiteConstants.Type.googlePlus, 27);
		assertEquals(tmp2.getName(),"emzaria");
		assertEquals(tmp2.getEmail(),"emzaria@a.a");
		assertEquals(tmp2.getType(),SiteConstants.Type.googlePlus);
		assertEquals(tmp2.getURL(),"emzaria.chveniSaiti.ge");
		assertEquals(tmp2.getID(), 27);
		
		User tmp3 = new User("davita777", "datoie@mail.ru", "1234", "datoieSimon", SiteConstants.Type.facebook, 111);
		assertEquals(tmp3.getName(),"davita777");
		assertEquals(tmp3.getEmail(),"datoie@mail.ru");
		assertEquals(tmp3.getType(),SiteConstants.Type.facebook);
		assertEquals(tmp3.getURL(),"datoieSimon.chveniSaiti.ge");
		assertEquals(tmp3.getID(), 111);
		
		User tmp4 = new User("nika", "niko@edu.ge", "1234", "shxamqimikatebi", SiteConstants.Type.email, 0);
		assertEquals(tmp4.getName(),"nika");
		assertEquals(tmp4.getEmail(),"niko@edu.ge");
		assertEquals(tmp4.getType(),SiteConstants.Type.email);
		assertEquals(tmp4.getURL(),"shxamqimikatebi.chveniSaiti.ge");
		assertEquals(tmp4.getID(), 0);
		
		User tmp5 = new User("amirspasalari", "pirveliKaci@a.a", "1234", "rai?", SiteConstants.Type.email, 1);
		assertEquals(tmp5.getName(),"amirspasalari");
		assertEquals(tmp5.getEmail(),"pirveliKaci@a.a");
		assertEquals(tmp5.getType(),SiteConstants.Type.email);
		assertEquals(tmp5.getURL(),"rai?.chveniSaiti.ge");
		assertEquals(tmp5.getID(), 1);
		
	}
	public void testEquals(){
		User tmp = new User("asdf", "asdf@a.a", "1234", "zaza", SiteConstants.Type.email, 1);
		User tmp2 = new User("asdf", "asdf@a.a", "1234", "zaza", SiteConstants.Type.email, 1);
		assertTrue(tmp.equals(tmp2));
		
		User tmp3 = new User("asdf", "qwerty@a.a", "1234", "zaza", SiteConstants.Type.email, 1);
		User tmp4 = new User("asdf", "asdf@a.a", "1234", "zaza", SiteConstants.Type.email, 1);
		assertFalse(tmp3.equals(tmp4));
		
		User tmp5 = new User("asdf", "asdf@a.a", "1234", "zaza", SiteConstants.Type.email, 1);
		User tmp6 = new User("asdf", "asdf@a.a", "1234", "zaza", SiteConstants.Type.email, 4);
		assertFalse(tmp5.equals(tmp6));
		
		User tmp7 = new User("asdf", null, "1234", "zaza", SiteConstants.Type.email, 1);
		User tmp8 = new User(null, null, "1234", "zaza", SiteConstants.Type.email, 1);
		assertTrue(tmp7.equals(tmp8));
		
		User tmp9 = new User("asdf", "qwertyuiop", "1234", "zaza", SiteConstants.Type.email, 12);
		User tmp10 = new User("asdf", "asdfghjkytrgfd", "1234", "zaza", SiteConstants.Type.email, 31);
		assertFalse(tmp9.equals(tmp10));
	}
}
