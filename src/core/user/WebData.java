package core.user;

import core.SiteConstants;

public class WebData {
	private String data = "";
	private String dataView;

	public WebData(String data) {
		changeData(data);
	}

	public void changeData(String data) {
		this.data = data;
		dataView = null;
	}

	public String getData() {
		return data;
	}

	public String getDataView() {
		if (dataView != null)
			return dataView;
		if (data == null)
			return null;
		dataView = "";
		int startIndex = 0;
		int endIndex = 0;
		while (true) {

			startIndex = data.indexOf(SiteConstants.startInnerTagName,
					startIndex);
			System.out.println(startIndex);
			endIndex = data.indexOf(SiteConstants.endInnerTagName, endIndex);
			System.out.println(endIndex);
			if (startIndex == -1 || endIndex == -1)
				break;
			dataView += data.substring(startIndex, endIndex
					+ SiteConstants.endInnerTagName.length());
			startIndex++;
			endIndex++;
		}
		return dataView;

	}

}
