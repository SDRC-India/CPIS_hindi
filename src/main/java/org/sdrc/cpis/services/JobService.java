package org.sdrc.cpis.services;

import java.text.ParseException;
/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface JobService {
	/**
	 * This method is for monthly Job
	 * It will insert the data for each ius for each district in DataValue table
	 * @param timeNid -Timeperiod Id of last Month from the current Date
	 */
	public void dashBoardJob(int timeNid);

	/**
	 * This method will create a new timeperiod each month 
	 * timeperiod will be of last month from the current date 
	 * 
	 * @return timeperiod id of the last month created
	 */
	public int createPreviousMonth() throws ParseException ;

	//public void icpTotalJob(int timeNid);

}
