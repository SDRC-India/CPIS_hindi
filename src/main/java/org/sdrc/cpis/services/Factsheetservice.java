package org.sdrc.cpis.services;

import java.io.File;

public interface Factsheetservice {

	String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	File getFactsheet(int startTimeperiod, int endTimeperiod, String divisionId, String districtId);

}
