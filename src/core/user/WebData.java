package core.user;

import core.SiteConstants;
/*
 * This class is for USerPAge data (body) parsing and getting some suitable tags.
 * It only works when tags(specified) are correctly interpreted.
 * It uses lazy computation for dataView computing.
 */
public class WebData {
	private String data = "";
	private String dataView;
/*
 * Constructor makes data string (calles aonly changeData
 */
	public WebData(String data) {
		changeData(data);
	}
/*
 * Change data which is like constructor and mchanges data and it's view as null
 */
	public synchronized void changeData(String data) {
		this.data = data;
		dataView = null;
	}
/*
 * This method returned data (updated , last one)
 */
	public synchronized String getData() {
		return data;
	}
/*
 * This method returns all defined inner tags elements concatenations.
 * This use lazy computation: computes only when needed.
 */
	public synchronized String getDataView() {
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
