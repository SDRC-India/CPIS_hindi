package org.sdrc.cpis.services;

import java.util.List;
import java.util.Map;

import org.sdrc.cpis.models.DashboardIndicatorComparasionModel;
import org.sdrc.cpis.models.IndicatorUnitSubgroupModel;
import org.sdrc.cpis.models.LineChart;
import org.sdrc.cpis.models.ReportModel;
import org.sdrc.cpis.models.SectorModel;
import org.sdrc.cpis.models.TimePeriodModel;

/**
 * 
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public interface DashboardService {
	/**
	 * This method will generate the Line chart average of data for a given ius
	 * at state level for last 12 months from the given timeperiod
	 * 
	 * @param iusid
	 * @param timeperiodid
	 * @param areaId
	 * @return List<List<LineChart>>
	 */
	public List<List<LineChart>> getLineChartData(int iusid, int timeperiodid,
			int areaId);

	/**
	 * This method will return all the timeperiods
	 * 
	 * @return List<TimePeriodModel>
	 */
	public Map<String, List<TimePeriodModel>> getAllTimePeriods();

	/***
	 * This method will return all the sectors their respective ius
	 * 
	 * @return List<SectorModel>
	 */
	public List<SectorModel> getAllSectorsAndIUS();

	/**
	 * This method will data for the map Area Wise for a given ius and given
	 * time
	 * 
	 * @param iusid
	 * @param timeperiodid
	 * @return MapDataModelCollection
	 */
	public Map<String, Object> getMapData(int iusid, int timeperiodid);

	/**
	 * This method will give the comparison of ius for two time periods
	 * 
	 * @param sectorId
	 * @return List<DashboardIndicatorComparasionModel>
	 */
	public List<DashboardIndicatorComparasionModel> getIUSComparision(
			int sectorId);

	/**
	 * This will return the Indicators Names for Report Section
	 * 
	 * @return List<IndicatorUnitSubgroupModel>
	 */
	public List<IndicatorUnitSubgroupModel> getIndicators();

	/**
	 * This Method Will give the reports i.e. for selected indicator it will
	 * give value of indicator for each unit and subgroup ordered by
	 * area(districts)
	 * 
	 * @param indicatorName
	 * @param indicatorId
	 * @param timeperiodId
	 * @return
	 */
	public Object getReports(String indicatorName, int indicatorId,
			int startTimeperiod, int endTimeperiod);

	/**
	 * This Method will create the PDF format of dashboard's map and Line chart
	 * and
	 * 
	 * @param mapImageSvg
	 * @param legendImage
	 * @param lineChartImageSvg
	 * @param iusnId
	 * @param timeperiodId
	 * @param areaId
	 * @return String - Path of the pdf generated
	 */
	public String exportToPdf(String mapImageSvg, String legendImage,
			String lineChartImageSvg, int iusnId, int timeperiodId, int areaId)
			throws Exception;

	/**
	 * This Method will return the sum of value of all time period for Some
	 * constant ius for the
	 * 
	 * @return
	 */
	public List<DashboardIndicatorComparasionModel> getIusDataForCpisHome(Integer selectedLang);
	
	
	/**
	 * @param indicatorName name of indicator
	 * @param indicatorId id of indicator
	 * @param startTimeperiod from timeperiod id
	 * @param endTimeperiod to timeperiod id
	 * @return list of data for that indicator in the given time range
	 */
	Object getReportsForIndicatorForChildAbove18(String indicatorName, int indicatorId, int startTimeperiod, int endTimeperiod);
	
	/**
	 * This method will return list of child details of a perticular ius using comma separated child ids as string
	 * @param childs comma separated child ids of a perticular ius
	 * @return list of child details of a perticular ius
	 */
	public List<ReportModel> getChildDetailsForIUS(String childs,String indicator);
	
	
	/**
	 * This method will return the data for a selected IUS for a time period  of each cci within a selected district 
	 * @param iusid
	 * @param timeperiodid
	 * @param areaId
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getCCIData(int iusid, int timeperiodid,
			int areaId);
	
	
	/**
	 * This method will generate the Line chart for last 12 months for a selected cci
	 * 
	 * @param iusid
	 * @param timeperiodid
	 * @param areaId
	 * @return List<List<LineChart>>
	 */
	public List<List<LineChart>> getCCILineChartData(int iusid, int timeperiodid,
			int areaId);
}
