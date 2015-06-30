package core.category;

import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryTest {
	
	@Test
	public void testConstructorAndGetters() {
		
		Category tmp = new Category(0, null);
		assertEquals(tmp.getId(), 0);
		assertEquals(tmp.getName(), null);
		
		Category tmp1 = new Category(13, "Lashas mama");
		assertEquals(tmp1.getId(), 13);
		assertEquals(tmp1.getName(), "Lashas mama");
		
		Category tmp2 = new Category(25, null);
		assertEquals(tmp2.getId(), 25);
		assertEquals(tmp2.getName(), null);
		
		Category tmp3 = new Category(0, "avejidarame");
		assertEquals(tmp3.getId(), 0);
		assertEquals(tmp3.getName(), "avejidarame");
		
		Category tmp4 = new Category(15, "gadazidvebi");
		assertEquals(tmp4.getId(), 15);
		assertEquals(tmp4.getName(), "gadazidvebi");
		
		Category tmp5 = new Category(11253, "metlaxi");
		assertEquals(tmp5.getId(), 11253);
		assertEquals(tmp5.getName(), "metlaxi");
		
		Category tmp6 = new Category(23, "arsena");
		assertEquals(tmp6.getId(), 23);
		assertEquals(tmp6.getName(), "arsena");
	}
	public void testEquals(){
		Category tmp = new Category(0, null);
		Category tmp1 = new Category(0,"lashas mama");
		assertTrue(tmp.equals(tmp1));
		
		Category tmp2 = new Category(15, "aia");
		Category tmp3 = new Category(15,"aia");
		assertTrue(tmp3.equals(tmp2));
		
		Category tmp4 = new Category(12, "aveji");
		Category tmp5 = new Category(7,"s gadazidva");
		assertFalse(tmp4.equals(tmp5));
		
		Category tmp6 = new Category(23, null);
		Category tmp7 = new Category(23, null);
		assertTrue(tmp6.equals(tmp7));
		
		Category tmp8 = new Category(42, "nodari");
		Category tmp9 = new Category(42,"lashas mama");
		assertTrue(tmp8.equals(tmp9));
	}
	public void testToString(){
		Category tmp = new Category(0, "lashas mama");
		assertEquals(tmp.toString(),"0:lashas mama");
		
		Category tmp1 = new Category(56, "gadazidva");
		assertEquals(tmp1.toString(),"56:gadazidva");
		
		Category tmp2 = new Category(31, "aveji");
		assertEquals(tmp2.toString(),"31:aveji");
		
		Category tmp3 = new Category(4,"random");
		assertEquals(tmp3.toString(),"4:random");
	}

}
