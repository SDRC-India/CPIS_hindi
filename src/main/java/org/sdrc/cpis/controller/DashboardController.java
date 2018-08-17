/**
 * 
 */
package org.sdrc.cpis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.domains.TimePeriod;
import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.DashboardIndicatorComparasionModel;
import org.sdrc.cpis.models.IndicatorUnitSubgroupModel;
import org.sdrc.cpis.models.LineChart;
import org.sdrc.cpis.models.ReportModel;
import org.sdrc.cpis.models.SectorModel;
import org.sdrc.cpis.models.TimePeriodModel;
import org.sdrc.cpis.repository.TimePeriodRepository;
import org.sdrc.cpis.services.DashboardService;
import org.sdrc.cpis.services.Factsheetservice;
import org.sdrc.cpis.services.JobService;
import org.sdrc.cpis.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
@Controller
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private Factsheetservice factsheetservice;
	
	@Autowired
	private JobService jobService;
	
	@Autowired 
	private LoginService loginService;
	
	@Autowired
	private TimePeriodRepository timePeriodRepository;
	
	@RequestMapping("runJob")
	@ResponseBody
	public String runJob(@RequestParam("tp") Integer tp) throws ParseException {
		//jobService.createPreviousMonth();
		for(TimePeriod timePeriod:timePeriodRepository.findAll())
		{
		jobService.dashBoardJob(timePeriod.getTimePeriodId());
		//jobService.icpTotalJob(timePeriod.getTimePeriodId());		
		}
		return "success";
	}
	
	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getAllSectors")
	@ResponseBody
	public List<SectorModel> getAllSectorsAndIus() throws ParseException {
//		jobService.dashBoardJob(1);
//		jobService.dashBoardJob(2);
		return dashboardService.getAllSectorsAndIUS();
	}

	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getAllTimeperiods")
	@ResponseBody
	public Map<String, List<TimePeriodModel>> getAllTimeperiods() {
		return dashboardService.getAllTimePeriods();
	}

	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getMapData")
	@ResponseBody
	public Map<String, Object> getMapData(@RequestParam("iusNid") int iusnid,
			@RequestParam("timeperiodId") int timeperiodId) {
		return dashboardService.getMapData(iusnid, timeperiodId);
	}

	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getLineChartData")
	@ResponseBody
	public List<List<LineChart>> getLineChartData(
			@RequestParam("iusNid") int iusnid,
			@RequestParam("timeperiodId") int timeperiodId,
			@RequestParam("areaId") int areaId) {
		return dashboardService.getLineChartData(iusnid, timeperiodId, areaId);
	}

	
	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getCCILineChartData")
	@ResponseBody
	public List<List<LineChart>> getCCILineChartData(
			@RequestParam("iusNid") int iusnid,
			@RequestParam("timeperiodId") int timeperiodId,
			@RequestParam("areaId") int areaId) {
		return dashboardService.getCCILineChartData(iusnid, timeperiodId, areaId);
	}

	
	
	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getCCIData")
	@ResponseBody
	public List<Map<String,String>> getCCIData(
			@RequestParam("iusNid") int iusnid,
			@RequestParam("timeperiodId") int timeperiodId,
			@RequestParam("areaId") int areaId) {
		return dashboardService.getCCIData(iusnid, timeperiodId, areaId);
	}
	
	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("getComparisionData")
	@ResponseBody
	public List<DashboardIndicatorComparasionModel> getComparisionData(
			@RequestParam("sectorId") int sectorId) {
		return dashboardService.getIUSComparision(sectorId);
	}

	@Authorize(feature="report", permission="view")
	@RequestMapping("getAllIndicators")
	@ResponseBody
	public List<IndicatorUnitSubgroupModel> getAllIndicators()
			throws ParseException {
		return dashboardService.getIndicators();
	}

	@Authorize(feature="report", permission="view")
	@RequestMapping("getReportsForIndicator")
	@ResponseBody
	public Object getReportForIndicator(
			@RequestParam("indicatorName") String indicatorName,
			@RequestParam("iusNid") int iusnid,
			@RequestParam("startTimeperiod") int startTimeperiod,
			@RequestParam("endTimeperiod") int endTimeperiod) {
		return dashboardService.getReports(indicatorName, iusnid, startTimeperiod, endTimeperiod);
	}
	
	@Authorize(feature="report", permission="view")
	@RequestMapping("getReportForIndicatorForChildAbove18")
	@ResponseBody
	public Object getReportForIndicatorForChildAbove18(
			@RequestParam("indicatorName") String indicatorName,
			@RequestParam("iusNid") int iusnid,
			@RequestParam("startTimeperiod") int startTimeperiod,
			@RequestParam("endTimeperiod") int endTimeperiod) {
		return dashboardService.getReportsForIndicatorForChildAbove18(indicatorName, iusnid, startTimeperiod, endTimeperiod);
	}

	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("/api/exportToPdf")
	@ResponseBody
	public String exportToPdf(@RequestBody List<String> svgs,
			@RequestParam("iusnId") int iusnId,
			@RequestParam("timeperiodId") int timeperiodId,
			@RequestParam("areaId") int areaId) throws Exception {
		return dashboardService.exportToPdf(svgs.get(0), svgs.get(0),
				svgs.get(1), iusnId, timeperiodId, areaId);
	}
	
	
	
	@Authorize(feature="dashboard", permission="view")
	@RequestMapping("dashboard")
	public String dashboardPage()
	{
		return "dashboard";
	}
	
	@Authorize(feature="report", permission="view")
	@RequestMapping("report")
	public String reportPage()
	{
		return "report";
	}
	
	@Authorize(feature="report", permission="view")
	@RequestMapping("getChildDetailsForIUS")
	@ResponseBody
	public List<ReportModel> getChildDetailsForIUS(@RequestParam("childs") String childs,@RequestParam("indicator")String indicator) {
		return dashboardService.getChildDetailsForIUS(childs, indicator);
	}
	
	@Authorize(feature="report", permission="view")
	@RequestMapping("getFactsheet")
	public void getFactsheet(@RequestParam("startTimeperiod") int startTimeperiod,
			@RequestParam("endTimeperiod") int endTimeperiod,@RequestParam("divisionId") String divisionId,
			@RequestParam("districtId") String districtId,HttpServletResponse response) {
		
		File file=factsheetservice.getFactsheet(startTimeperiod, endTimeperiod,divisionId,districtId);
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",file.getName());
		response.setHeader(headerKey, headerValue);
		response.setContentType("application/octet-stream");
		try {
			ServletOutputStream os=response.getOutputStream();
			
			FileCopyUtils.copy(new FileInputStream(file), os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			file.delete();
		}
		
	}
	
	@Authorize(feature="report", permission="view")
	@RequestMapping("getAllAreaForLoginUser")
	@ResponseBody
	public List<AreaDetailsModel> getAllAreaForLoginUser() {
		List<AreaDetailsModel> areaModels = loginService.getAllAreaForLoginUser();
		return areaModels;
	}
	
	@Authorize(feature="report", permission="view")
	@RequestMapping("isFactsheetAvailable")
	@ResponseBody
	public Boolean isFactsheetAvailable(@RequestParam("startTimeperiod") int startTimeperiod,
			@RequestParam("endTimeperiod") int endTimeperiod,@RequestParam("divisionId") String divisionId,
			@RequestParam("districtId") String districtId,HttpServletResponse response) {
		File file=factsheetservice.getFactsheet(startTimeperiod, endTimeperiod,divisionId,districtId);
		if(file==null){
			return false;
		}
		return true;
	}
}
