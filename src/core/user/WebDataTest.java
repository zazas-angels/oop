package core.user;

import static org.junit.Assert.*;

import org.junit.Test;

import core.SiteConstants;

public class WebDataTest {
	@Test
	public void simpleGetDataTest() {
		String data = SiteConstants.startInnerTagName + "hjrevbfhubrefh"
				+ SiteConstants.endInnerTagName
				+ "fvrferrregvfetrvg\n rfrfrfrfrfgrfgvgr";
		WebData webData = new WebData(data);
		assertEquals(data, webData.getData());
		data = "evrfvRFbdhnygtrfedwfrgt5rfry" + SiteConstants.startInnerTagName
				+ SiteConstants.startInnerTagName
				+ SiteConstants.startInnerTagName
				+ SiteConstants.startInnerTagName
				+ SiteConstants.startInnerTagName + "hjrevbfhubrefh"
				+ SiteConstants.endInnerTagName
				+ "fvrferrregvfetrvg\n rfrfrfrfrfgrrefrfgvgr";
		webData = new WebData(data);
		assertEquals(data, webData.getData());
		assertEquals(data, webData.getData());// re use
	}

	@Test
	public void simpleGetDataView() {
		String dataView = SiteConstants.startInnerTagName
				+ "<div>gfvfvfetrgfM</div>  revfe \n rvrev"
				+ SiteConstants.endInnerTagName;// random
		String data = dataView;
		WebData webData = new WebData(data);
		assertEquals(dataView, webData.getDataView());
		assertEquals(dataView, webData.getDataView());// re use
		data = "ervfrvgfre" + dataView + "<div>Erdvbegvgf";
		webData = new WebData(data);
		assertEquals(dataView, webData.getDataView());

		data = "EFvfebv" + dataView + dataView + "efvf fbafefv fs/n\n"
				+ dataView;
		webData = new WebData(data);
		assertEquals(dataView + dataView + dataView, webData.getDataView());

	}

	@Test
	public void simpleChangeDataTest() {

		String dataView = SiteConstants.startInnerTagName
				+ "<divrefvr///><</div>  revfe \n rvrev"
				+ SiteConstants.endInnerTagName;// random
		String data = dataView;
		WebData webData = new WebData(data);
		assertEquals(dataView, webData.getDataView());
		assertEquals(data, webData.getData());
		data = "ervfrvgfre" + dataView + dataView + "<div>Erdvbegvgf";
		webData.changeData(data);
		assertEquals(dataView + dataView, webData.getDataView());
		assertEquals(dataView + dataView, webData.getDataView());// re use
		assertEquals(data, webData.getData());

		data = "EFvfebv" + dataView + dataView + "efvf fbafefv fs/n\n"
				+ dataView;
		webData.changeData(data);
		assertEquals(dataView + dataView + dataView, webData.getDataView());
		assertEquals(data, webData.getData());
	}

	@Test
	public void complexWebDataMixedTest() {
		String dataView1 = SiteConstants.startInnerTagName
				+ "<divrerergergredgbfhyrgtfed revfe \n rvrev"
				+ SiteConstants.endInnerTagName;// random
		String dataView2 = SiteConstants.startInnerTagName
				+ "<divrefvwefcewfcew///235rfe6fr/><</div>  revfe \n rvrev"
				+ SiteConstants.endInnerTagName;// random
		String dataView3 = SiteConstants.startInnerTagName
				+ "<diethgwiv>  revfe \n rvrev" + SiteConstants.endInnerTagName;// random
		String dataView4 = SiteConstants.startInnerTagName
				+ "<divweferwfewfewfewf/div>  revfe \n rvrev"
				+ SiteConstants.endInnerTagName;// random
		String data = "rbvfr <dinction(isResize) { };dragresize.ondragmove = function(isResize) { };"
				+ dataView1
				+ "dragresize.ondragend = function(isResize) { };dragresize.ondragblur = function() { };"
				+ dataView2 + dataView3 + "dfhbng<div></div> " + dataView4;
		WebData webData = new WebData(data);
		assertEquals(data, webData.getData());
		assertEquals(dataView1 + dataView2 + dataView3 + dataView4,
				webData.getDataView());

		assertEquals(dataView1 + dataView2 + dataView3 + dataView4,
				webData.getDataView());// re use
		assertEquals(data, webData.getData());// re use
		data = dataView1
				+ dataView4
				+ "rehntbfgrewdqfghtjngb cbg5366y7ujrgbdfvfer32tgrhbf v^%$%$##$/n\n"
				+ dataView4 + dataView4;
		webData.changeData(data);
		assertEquals(data, webData.getData());
		assertEquals(dataView1 + dataView4 + dataView4 + dataView4,
				webData.getDataView());

	}

	@Test
	public void wierdWebDataTest() {
	//empty test
		WebData webData = new WebData("");
		assertEquals("", webData.getData());
		assertEquals("",
				webData.getDataView());
		webData.changeData(null);
		//null test
		assertEquals(null, webData.getData());
		assertEquals(null,
				webData.getDataView());
		assertEquals(null,
				webData.getDataView());//re use
		
	}

}
