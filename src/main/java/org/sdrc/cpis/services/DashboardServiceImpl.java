package org.sdrc.cpis.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.FileUtils;
import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.DataValue;
import org.sdrc.cpis.domains.DataValueCciWise;
import org.sdrc.cpis.domains.IndicatorUnitSubgroup;
import org.sdrc.cpis.domains.Sectors;
import org.sdrc.cpis.domains.TimePeriod;
import org.sdrc.cpis.models.DashboardIndicatorComparasionModel;
import org.sdrc.cpis.models.IndicatorUnitSubgroupModel;
import org.sdrc.cpis.models.LineChart;
import org.sdrc.cpis.models.MapDataModel;
import org.sdrc.cpis.models.ReportModel;
import org.sdrc.cpis.models.SectorModel;
import org.sdrc.cpis.models.TimePeriodModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.models.ValueModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.DataValueCCIWiseRepository;
import org.sdrc.cpis.repository.DataValueRepository;
import org.sdrc.cpis.repository.FollowUpRepository;
import org.sdrc.cpis.repository.IndicatorUnitSubgroupRepository;
import org.sdrc.cpis.repository.IndividualCarePlanBRepository;
import org.sdrc.cpis.repository.SectorRepository;
import org.sdrc.cpis.repository.TimePeriodRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.HeaderFooter;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private SectorRepository sectorRepository;

	@Autowired
	private TimePeriodRepository timePeriodRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;

	@Autowired
	DataValueRepository dataValueRepository;
	
	@Autowired
	DataValueCCIWiseRepository dataValueCCIWiseRepository;

	@Autowired
	IndicatorUnitSubgroupRepository indicatorUnitSubgroupRepository;

	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	private IndividualCarePlanBRepository individualCarePlanBRepository;

	@Autowired
	ResourceBundleMessageSource applicationMessageSource;
	@Autowired
	private StateManager stateManager;

	@Autowired
	private ServletContext context;
	
	@Autowired
	private ResourceBundleMessageSource notificationMessageSource;
	
	@Autowired
	private FollowUpRepository followUpRepository;

	private static DecimalFormat df = new DecimalFormat(".#");

	@Override
	public List<List<LineChart>> getLineChartData(int iusid, int timeperiodid,
			int areaId) {
		TimePeriod period = timePeriodRepository
				.findByTimePeriodId(timeperiodid);

		List<TimePeriod> timePeriod=timePeriodRepository.findTop12ByOrderByTimePeriodIdDesc();
		
		Date startDate = null;
		Date endDate = period.getEndDate();
		
		if(timePeriod.size()>=12)
		{
			startDate=timePeriod.get(11).getStartDate();
		}
		
		else
		{
			startDate=timePeriod.get(timePeriod.size()-1).getStartDate();
		}
/*
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		 //setting start date to the previous year date
		calendar.add(Calendar.YEAR, -1);
		startDate = new Date(calendar.getTime().getTime());*/

		List<Object[]> dataValues = new ArrayList<Object[]>();
		if (areaId == 0) {
			dataValues = dataValueRepository.findStateAvgForIUSNId(iusid,
					startDate, endDate);
		} else {
			dataValues = dataValueRepository.findDistrictValueForIUSNId(iusid,
					startDate, endDate, areaId);
		}
		List<LineChart> lineCharts = new ArrayList<LineChart>();

		for (Object[] obj : dataValues) {
			LineChart lineChart = new LineChart();

			lineChart
					.setValue(df.format(Double.parseDouble(obj[0].toString())));
			if (Double.parseDouble(obj[0].toString()) == 0) {
				lineChart.setValue(0.0);
			}
			lineChart.setDate(obj[1].toString());

			
			lineCharts.add(lineChart);
		}

		List<List<LineChart>> lineChartsList = new ArrayList<List<LineChart>>();

		lineChartsList.add(lineCharts);
		// Collections.reverse(lineChartsList);

		// TODO Auto-generated method stub
		return lineChartsList;
	}

	@Override
	public Map<String, List<TimePeriodModel>> getAllTimePeriods() {
		Map<String, List<TimePeriodModel>> timePeriodModelMap = new LinkedHashMap<String,List<TimePeriodModel>>();
		List<TimePeriodModel> timePeriodModels = new ArrayList<TimePeriodModel>();

		for (TimePeriod timePeriod : timePeriodRepository.findByOrderByStartDateAsc()) {

			TimePeriodModel timePeriodModel = new TimePeriodModel();

			timePeriodModel.setStartDate(new String(timePeriod.getStartDate()
					.toString()));
			timePeriodModel.setEndDate(new String(timePeriod.getEndDate()
					.toString()));						
			timePeriodModel.setTimeperiod(timePeriod.getTimeperiod());
			
			timePeriodModel.setPerodicity(timePeriod.getPerodicity());
			timePeriodModel.setTimePeriodId(timePeriod.getTimePeriodId());
			String tPeriod=timePeriod.getTimeperiod();
			
			if(timePeriodModelMap.containsKey(tPeriod.substring(tPeriod.indexOf(" ")+1))){
				timePeriodModelMap.get(tPeriod.substring(tPeriod.indexOf(" ")+1)).add(timePeriodModel);				
			}else{
				List<TimePeriodModel> li=new ArrayList<TimePeriodModel>();
				li.add(timePeriodModel);
				timePeriodModelMap.put(tPeriod.substring(tPeriod.indexOf(" ")+1), li);
			}

			timePeriodModels.add(timePeriodModel);
		}
		// TODO Auto-generated method stub
		return timePeriodModelMap;
	}

	@Override
	@Transactional
	public List<SectorModel> getAllSectorsAndIUS() {

		UserDetailModel userDetailModel=(UserDetailModel)stateManager.getValue(Constants.USER_PRINCIPAL);
		List<SectorModel> sectorModels = new ArrayList<SectorModel>();
		int sectorId=0;
		List<Sectors> sectors=new ArrayList<Sectors>();
		//cci.role=10
		if(userDetailModel.getDesignationId()!=Integer.parseInt(notificationMessageSource.getMessage("cci.role", null,null))&&userDetailModel.getDesignationId()!=Integer.parseInt(notificationMessageSource.getMessage("cwc.role", null,null)) && userDetailModel.getDesignationId()!=Integer.parseInt(notificationMessageSource.getMessage("jjb.role", null,null)))
		{
			sectors=sectorRepository.findAll();	
		}
		else
		{
			sectors=new ArrayList<Sectors>();
		if(userDetailModel.getDesignationId()==Integer.parseInt(notificationMessageSource.getMessage("cwc.role", null,null)))	
		{
			sectors=new ArrayList<Sectors>();
			sectorId=Integer.parseInt(notificationMessageSource.getMessage("cwc.sector.id", null,null));
			sectors.add(sectorRepository.findBySectorId(sectorId));
		}
		
		else if(userDetailModel.getDesignationId()==Integer.parseInt(notificationMessageSource.getMessage("cci.role", null,null)))
		{
			sectors=new ArrayList<Sectors>();
			sectorId=Integer.parseInt(notificationMessageSource.getMessage("cci.sector.id", null,null));
			sectors.addAll(sectorRepository.findBySectorIdOrParentId(sectorId,sectorId));
		}
		else
		{	
			sectorId=Integer.parseInt(notificationMessageSource.getMessage("jjb.sector.id", null,null));
			sectors=new ArrayList<Sectors>();
			sectors.add(sectorRepository.findBySectorId(sectorId));
		}
		}
		
		for (Sectors sector : sectors) {
			SectorModel sectorModel = new SectorModel();

			sectorModel.setSectorId(sector.getSectorId());
			sectorModel.setSectorName(sector.getSectorName());
			sectorModel.setParentId(sector.getParentId());

			List<IndicatorUnitSubgroupModel> indicatorUnitSubgroupModels = new ArrayList<IndicatorUnitSubgroupModel>();
			for (IndicatorUnitSubgroup indicatorUnitSubgroup : sector
					.getIndicatorUnitSubgroup()) {
				if (indicatorUnitSubgroup.getModule().equalsIgnoreCase("D")) {
					IndicatorUnitSubgroupModel indicatorUnitSubgroupModel = new IndicatorUnitSubgroupModel();

					// indicator name will be in format of
					// -indicatorName-subgroup(Unit)
					indicatorUnitSubgroupModel
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName()
									+ "-"
									+ indicatorUnitSubgroup.getSubGroup()
									+ " ("
									+ indicatorUnitSubgroup.getUnit()
									+ ")");
					indicatorUnitSubgroupModel
							.setIndicatorUnitSubgroupId(indicatorUnitSubgroup
									.getIndicatorId());
					indicatorUnitSubgroupModel.setModule(indicatorUnitSubgroup
							.getModule());
					indicatorUnitSubgroupModel
							.setSubgroupName(indicatorUnitSubgroup
									.getSubGroup());
					indicatorUnitSubgroupModel
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					indicatorUnitSubgroupModel.setUnit(indicatorUnitSubgroup
							.getUnit());

					indicatorUnitSubgroupModels.add(indicatorUnitSubgroupModel);
				}
			}

			sectorModel.setIusModel(indicatorUnitSubgroupModels);
			sectorModels.add(sectorModel);

		}
		// TODO Auto-generated method stub
		return sectorModels;
	}

	@Override
	public Map<String, Object> getMapData(int iusid, int timeperiodid) {
		List<DataValue> dataValues = dataValueRepository
				.findByIndicatorUnitSubgroupIndicatorIdAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc(
						iusid, timeperiodid);
		List<Object[]> minAndMaxValue = dataValueRepository.findMaxAndMinValue(
				iusid, timeperiodid);

		List<MapDataModel> mapDataModels = new ArrayList<MapDataModel>();
		Map<String, Object> mapDatasMap = new HashMap<String, Object>();

		// populating the legend
		if (dataValues.size() > 0) {
			List<ValueObject> legends = populateLegends(
					Double.parseDouble(minAndMaxValue.get(0)[1].toString()),
					Double.parseDouble(minAndMaxValue.get(0)[0].toString()),
					dataValues.get(0).getIndicatorUnitSubgroup().isHighIsGood(),dataValues.get(0).getIndicatorUnitSubgroup().getUnit());

			for (DataValue dataValue : dataValues) {
				MapDataModel dataModel = new MapDataModel();

				dataModel.setAreaCode(dataValue.getAreaDetails().getAreaCode());
				dataModel.setAreaName(dataValue.getAreaDetails().getAreaName());
				dataModel.setValue(dataValue.getIndicatorUnitSubgroup().getUnit().equalsIgnoreCase("percent")?String.valueOf(dataValue.getValue()):Integer.toString((int)(dataValue.getValue())));
				dataModel.setHighIsGood(dataValue.getIndicatorUnitSubgroup()
						.isHighIsGood());
				dataModel.setAreaId(dataValue.getAreaDetails().getAreaId());
				for (ValueObject legend : legends) {
					String[] legendArray = legend.getDesc().split("-");

					// setting the css class of each area according to its value
					// in
					// reference with the legend populated
					if (Double.parseDouble(dataModel.getValue()) <= Double
							.parseDouble(legendArray[1])
							&& Double.parseDouble(dataModel.getValue()) >= Double
									.parseDouble(legendArray[0]))

					{
						dataModel.setCssClass(legend.getValue());
						//dataModel.setValue(String.valueOf());
						break;
					}

				}
				// Css is still to be filled

				mapDataModels.add(dataModel);
			}
			mapDatasMap.put("dataCollection", mapDataModels);
			mapDatasMap.put("legends", legends);
		}

		else {
			mapDatasMap.put("dataCollection", mapDataModels);
			mapDatasMap.put("legends", null);
		}
		// TODO Auto-generated method stub
		return mapDatasMap;
	}

	private List<ValueObject> populateLegends(Double minDataValue,
			Double maxDataValue, boolean highIsGood,String unit) {
		// TO DO: we need three different value legends so we have set it to 3.
		int range = 3;
		String firstslices = Constants.Slices.FIRST_SLICE;
		String secondslices = Constants.Slices.SECOND_SLICE;
		String thirdslices = Constants.Slices.THIRD_SLICE;
		String fifthslices = Constants.Slices.FIFTHSLICES;

		String firstSliceValue, secondSliceValue, thirdSliceValue;

		List<ValueObject> list = new ArrayList<ValueObject>();

		Double difference = (maxDataValue - minDataValue) / range;
		// if difference is 0 between the max and min vakue only one slice will
		// be there
		if (difference == 0) {
			if(unit.equalsIgnoreCase("number"))
				firstSliceValue = String.valueOf(Math.round(minDataValue)) + " - "
						+ String.valueOf(Math.round(maxDataValue));
			else	
			firstSliceValue = Double.toString(Math.round(minDataValue)) + " - "
					+ Double.toString(Math.round(maxDataValue));
		
			
			list.add(highIsGood ? new ValueObject(firstSliceValue, firstslices)
					: new ValueObject(firstSliceValue, thirdslices));

		}
		
		else if(unit.equalsIgnoreCase("number") && (maxDataValue - minDataValue)<3)
		{
			firstSliceValue = String.valueOf(Math.round(minDataValue)) + " - "
					+ String.valueOf(Math.round(maxDataValue));
			list.add(highIsGood ? new ValueObject(firstSliceValue, firstslices)
					: new ValueObject(firstSliceValue, thirdslices));
		}

		// else we will have three diffrent slices
		else {
			Double maxValue = Double.valueOf(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(minDataValue)):df.format(minDataValue))
					+ Double.valueOf(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(difference)):df.format(difference));
			Double maxValue1 = Double.valueOf(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(minDataValue)):df.format(minDataValue)) + 2
					* Double.valueOf(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(difference)):df.format(difference));
			// Double maxValue2=Double.valueOf(df.format(minDataValue)) + 3 *
			// Double.valueOf(df.format(difference));
			firstSliceValue = String.valueOf(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(minDataValue)):df.format(minDataValue)) + " - "
					+ (unit.equalsIgnoreCase("number")?String.valueOf(Math.round(maxValue)):df.format(maxValue));
			secondSliceValue =(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(Double.valueOf(df.format(minDataValue))
					+ Double.valueOf(df.format(difference)) + 0.1 )):df
					.format(Double.valueOf(df.format(minDataValue))
							+ Double.valueOf(df.format(difference)) + 0.1))
					+ " - " +(unit.equalsIgnoreCase("number")?Math.round(maxValue1) :df.format(maxValue1));
			thirdSliceValue = 
					(unit.equalsIgnoreCase("number")?String.valueOf(Math.round(Double.valueOf(df.format(minDataValue))
					+ 2 * Double.valueOf(df.format(difference)) + 0.1)) :
					df.format(Double.valueOf(df.format(minDataValue))
					+ 2 * Double.valueOf(df.format(difference)) + 0.1))
					+ " - " + (unit.equalsIgnoreCase("number")?Math.round(maxDataValue):df.format(maxDataValue));

			// if high is good (means increasing value show us the good
			// indication) then we will set value to each slices as it is
			if (!highIsGood) {
				list.add(new ValueObject(firstSliceValue, firstslices));
				list.add(new ValueObject(secondSliceValue, secondslices));
				list.add(new ValueObject(thirdSliceValue, thirdslices));
				list.add(new ValueObject("Not Available", fifthslices));
			}

			// else we will set it in reverse order
			else {
				list.add(new ValueObject(firstSliceValue, thirdslices));
				list.add(new ValueObject(secondSliceValue, secondslices));
				list.add(new ValueObject(thirdSliceValue, firstslices));
				list.add(new ValueObject("Not Available", fifthslices));
			}
		}

		return list != null && !list.isEmpty() ? list : null;

	}

	@Override
	public List<DashboardIndicatorComparasionModel> getIUSComparision(
			int sectorId)

	{

		LocalDate now = LocalDate.now();
		LocalDate lastMonth = now.minusMonths(1).withDayOfMonth(1);
		//Calendar lastMonthCalendar=Calendar.getInstance()
		// to be
																	// changed
																	// to
																	// lastMonth
		// LocalDate lastMonth = now.withDayOfMonth(1);
		//startDateCalendar.get(Calendar.MONTH)) + " " + startDateCalendar.get(Calendar.YEAR)

		LocalDate secondLastMonth = now.minusMonths(2).withDayOfMonth(1);
		List<String> iusList = new ArrayList<String>();
		List<Integer> iusId = new ArrayList<Integer>();
		
		List<Object[]> datavalueForLastMonth=new ArrayList<Object[]>();
		List<Object[]> datavalueForSecondLastMonth=new ArrayList<Object[]>();
		if(sectorId==1 || sectorId==4){
			iusList = Arrays.asList(applicationMessageSource
					.getMessage("indicator.unit.subgroup.ID.cwc", null, null)
					.split(","));
			iusId = new ArrayList<Integer>();
			for (String ius : iusList) {
				iusId.add(Integer.parseInt(ius));
			}
			datavalueForLastMonth = dataValueRepository.findAllTheIndicatorValueForSectorAndTime(
							Date.valueOf(lastMonth), iusId);
			datavalueForSecondLastMonth = dataValueRepository.findAllTheIndicatorValueForSectorAndTime(
							Date.valueOf(secondLastMonth), iusId);
		}else if(sectorId==2 || sectorId==5){
			iusList = Arrays.asList(applicationMessageSource
					.getMessage("indicator.unit.subgroup.ID.jjb", null, null)
					.split(","));
			iusId = new ArrayList<Integer>();
			for (String ius : iusList) {
				iusId.add(Integer.parseInt(ius));
			}
			datavalueForLastMonth = dataValueRepository.findAllTheIndicatorValueForSectorAndTime(
					Date.valueOf(lastMonth), iusId);
			datavalueForSecondLastMonth = dataValueRepository.findAllTheIndicatorValueForSectorAndTime(
					Date.valueOf(secondLastMonth), iusId);
		}else{
			iusList = Arrays.asList(applicationMessageSource
					.getMessage("indicator.unit.subgroup.ID.all", null, null)
					.split(","));
			iusId = new ArrayList<Integer>();
			for (String ius : iusList) {
				iusId.add(Integer.parseInt(ius));
			}
			datavalueForLastMonth = dataValueRepository.findAllTheIndicatorValueForSectorAndTime(
					Date.valueOf(lastMonth), iusId);
			datavalueForSecondLastMonth = dataValueRepository.findAllTheIndicatorValueForSectorAndTime(
					Date.valueOf(secondLastMonth), iusId);
		}
		

		List<DashboardIndicatorComparasionModel> comparasionModels = new ArrayList<DashboardIndicatorComparasionModel>();
		for (Object[] dataValue : datavalueForLastMonth) {
			{
				DashboardIndicatorComparasionModel comparasionModel = new DashboardIndicatorComparasionModel();
				comparasionModel.setIndicatorName(dataValue[1].toString());
				comparasionModel.setNewValue(String.valueOf(Math
						.round((Double) dataValue[0])));

				for (Object[] value : datavalueForSecondLastMonth) {
					if (value[1].toString().equalsIgnoreCase(
							dataValue[1].toString())) {
						comparasionModel.setOldValue(String.valueOf(Math
								.round((Double) value[0])));
						break;
					}
				}

				if (comparasionModel.getOldValue() == null) {
					comparasionModel.setOldValue("N/A");
					comparasionModel.setDiffrencePercent("N/A");
					// css classs to be set neutral
					comparasionModel.setCssClass("");
				} else {
					Double diffrence;
					if (Float.parseFloat(comparasionModel.getOldValue()) == 0 && Float.parseFloat(comparasionModel.getNewValue()) == 0) {
						diffrence = 0.0;
					}
					else if(Float.parseFloat(comparasionModel.getOldValue()) == 0 && Float.parseFloat(comparasionModel.getNewValue()) != 0)
					{
						diffrence=100.0;
					}
					else {
						diffrence = (((Double.valueOf(comparasionModel
								.getNewValue()) - Double
								.valueOf(comparasionModel.getOldValue())) / Double
								.valueOf(comparasionModel.getOldValue())) * 100);
					}
					if (diffrence != 0) {
						comparasionModel.setDiffrencePercent(df
								.format(diffrence));
					} else {
						comparasionModel.setDiffrencePercent("0.0");
					}
					if(diffrence!=0.0)
					{
					if (dataValue[2].toString().equalsIgnoreCase("true")) {
						if (diffrence >= 0) {
							// css classs to be set high
							comparasionModel.setCssClass("good");
						} else {
							// css classs to be set low
							comparasionModel.setCssClass("bad");
						}
					} else {
						if (diffrence >= 0) {
							// css classs to be set high
							comparasionModel.setCssClass("bad");
						} else {
							// css classs to be set low
							comparasionModel.setCssClass("good");
						}
					}
					}
					else
					{
						comparasionModel.setCssClass("");
					}
					comparasionModel.setTimePeriod(secondLastMonth.getMonth().name()+" "+secondLastMonth.getYear()+" v/s "+lastMonth.getMonth().name()+" "+lastMonth.getYear());
				}
				comparasionModels.add(comparasionModel);
			}

		}
		// Collections.reverse(comparasionModels);

		// TODO Auto-generated method stub
		return comparasionModels;
	}

	@Override
	public List<IndicatorUnitSubgroupModel> getIndicators() {

		List<IndicatorUnitSubgroup> indicatorUnitSubgroups = new ArrayList<IndicatorUnitSubgroup>();
		
		UserDetailModel userDetailModel=(UserDetailModel)stateManager.getValue(Constants.USER_PRINCIPAL);
		int sectorId=0;
		if(userDetailModel.getDesignationId()!=Integer.parseInt(notificationMessageSource.getMessage("cwc.role", null,null)) && userDetailModel.getDesignationId()!=Integer.parseInt(notificationMessageSource.getMessage("jjb.role", null,null)))
			indicatorUnitSubgroups = indicatorUnitSubgroupRepository
			.findBySubGroup("Total");
		else
		{
			 indicatorUnitSubgroups = new ArrayList<IndicatorUnitSubgroup>();
			if(userDetailModel.getDesignationId()==Integer.parseInt(notificationMessageSource.getMessage("cwc.role", null,null)))	
		{
				indicatorUnitSubgroups = new ArrayList<IndicatorUnitSubgroup>();
			sectorId=Integer.parseInt(notificationMessageSource.getMessage("cwc.sector.id", null,null));
			indicatorUnitSubgroups=indicatorUnitSubgroupRepository
					.findBySubGroupAndSectorsSectorId("Total",sectorId);
		}
		else
		{	
			sectorId=Integer.parseInt(notificationMessageSource.getMessage("jjb.sector.id", null,null));
			 indicatorUnitSubgroups = new ArrayList<IndicatorUnitSubgroup>();
			 indicatorUnitSubgroups =indicatorUnitSubgroupRepository
						.findBySubGroupAndSectorsSectorId("Total",sectorId);
		}
		}
		List<IndicatorUnitSubgroupModel> indicatorUnitSubgroupModels = new ArrayList<IndicatorUnitSubgroupModel>();
		for (IndicatorUnitSubgroup indicatorUnitSubgroup : indicatorUnitSubgroups) {
			IndicatorUnitSubgroupModel indicatorUnitSubgroupModel = new IndicatorUnitSubgroupModel();

			// indicator name will be in format of
			// -indicatorName-subgroup(Unit)
			indicatorUnitSubgroupModel.setIndicatorName(indicatorUnitSubgroup
					.getIndicatorName());
			indicatorUnitSubgroupModel
					.setIndicatorUnitSubgroupId(indicatorUnitSubgroup
							.getIndicatorId());
			indicatorUnitSubgroupModel.setModule(indicatorUnitSubgroup
					.getModule());
			indicatorUnitSubgroupModel.setSubgroupName(indicatorUnitSubgroup
					.getSubGroup());
			indicatorUnitSubgroupModel.setHighIsGood(indicatorUnitSubgroup
					.isHighIsGood());
			indicatorUnitSubgroupModel.setUnit(indicatorUnitSubgroup.getUnit());
			indicatorUnitSubgroupModel.setSectorId(indicatorUnitSubgroup.getSectors().getSectorId());

			indicatorUnitSubgroupModels.add(indicatorUnitSubgroupModel);
		}
		// TODO Auto-generated method stub
		return indicatorUnitSubgroupModels;
	}

	@Override
	@Transactional
	public Object getReports(String indicatorName, int indicatorId,
			 int startTimeperiod, int endTimeperiod) {
		IndicatorUnitSubgroup ius = indicatorUnitSubgroupRepository
				.findByIndicatorId(indicatorId);
		List<IndicatorUnitSubgroup> iusList = indicatorUnitSubgroupRepository
				.findByIndicatorNameOrderByIndicatorIdAsc(ius.getIndicatorName());
		List<Integer> iusIds = new ArrayList<Integer>();
		for (IndicatorUnitSubgroup indicatorUnitSubgroup : iusList) {
			iusIds.add(indicatorUnitSubgroup.getIndicatorId());
		}

		List<DataValue> dataValues=new ArrayList<>();
		UserDetailModel userDetailModel=(UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		int userAreaId=userDetailModel.getAreaId();
		if(indicatorName.equals("Pendency of Children Need of Care and Protection cases") || indicatorName.equals("Pendency of Children In Conflict With the Law cases")){
			startTimeperiod=endTimeperiod;
		}
		List<Integer> tlist=new ArrayList<Integer>();
		do{
			tlist.add(startTimeperiod);
			startTimeperiod++;
		}
		while(startTimeperiod <= endTimeperiod);
				
		if(userDetailModel.getAreaLevelId()==2 || userDetailModel.getAreaLevelId()==1)
		{
			List<Object[]> objects = dataValueRepository
				.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
						iusIds, tlist);
			for (Object object[] : objects) {
				AreaDetails area= areaRepository.findByAreaId(Integer.parseInt(object[2].toString()));
				
				IndicatorUnitSubgroup indicator=
						indicatorUnitSubgroupRepository.
						findByIndicatorId(Integer.parseInt(object[3].toString()));
				DataValue dv=new DataValue();
				dv.setAreaDetails(area);
				dv.setIndicatorUnitSubgroup(indicator);
				dv.setChildIds(object[1]==null?null:object[1].toString());
				dv.setValue(Float.parseFloat(object[0].toString()));
				dataValues.add(dv);
				
			}
		}
		else if(userDetailModel.getAreaLevelId()==3)
		{
			List<Object[]> objects = dataValueRepository
				.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
						iusIds, tlist, userAreaId);
			for (Object object[] : objects) {
				AreaDetails area= areaRepository.findByAreaId(Integer.parseInt(object[2].toString()));
				
				IndicatorUnitSubgroup indicator=
						indicatorUnitSubgroupRepository.
						findByIndicatorId(Integer.parseInt(object[3].toString()));
				DataValue dv=new DataValue();
				dv.setAreaDetails(area);
				dv.setIndicatorUnitSubgroup(indicator);
				dv.setChildIds(object[1]==null?null:object[1].toString());
				dv.setValue(Float.parseFloat(object[0].toString()));
				dataValues.add(dv);
				
			}
		}
		else
		{		
		List<Object[]> objects = dataValueRepository
			.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg(
					iusIds, tlist,userAreaId);
		for (Object object[] : objects) {
			AreaDetails area= areaRepository.findByAreaId(Integer.parseInt(object[2].toString()));
			
			IndicatorUnitSubgroup indicator=
					indicatorUnitSubgroupRepository.
					findByIndicatorId(Integer.parseInt(object[3].toString()));
			DataValue dv=new DataValue();
			dv.setAreaDetails(area);
			dv.setIndicatorUnitSubgroup(indicator);
			dv.setChildIds(object[1]==null?null:object[1].toString());
			dv.setValue(Float.parseFloat(object[0].toString()));
			dataValues.add(dv);
			
			}
		}
		List<Map<String, String>> iusValuesMapList = new ArrayList<Map<String, String>>();
		Map<String, String> iusValuesMap = new LinkedHashMap<String, String>();
		for (DataValue dataValue : dataValues) {

			if (iusValuesMap.isEmpty()
					|| !iusValuesMap.get("District").equalsIgnoreCase(
							dataValue.getAreaDetails().getAreaName())) {
				if (iusValuesMap.size() > 0) {
					iusValuesMapList.add(iusValuesMap);
				}
				iusValuesMap = new LinkedHashMap<String, String>();
				iusValuesMap.put("District", dataValue.getAreaDetails()
						.getAreaName());
			}
		if(	dataValue.getIndicatorUnitSubgroup().getUnit().equalsIgnoreCase("Percent"))
			{
			iusValuesMap.put(dataValue.getIndicatorUnitSubgroup().getSubGroup()+ " ("
					+ dataValue.getIndicatorUnitSubgroup().getUnit()
					+ ")", Float
					.toString(dataValue.getValue()));
			}
		else
		{
			iusValuesMap.put(dataValue.getIndicatorUnitSubgroup().getSubGroup()+ " ("
					+ dataValue.getIndicatorUnitSubgroup().getUnit()
					+ ")"
					,
					String.valueOf((int)(dataValue.getValue())));
			iusValuesMap.put(dataValue.getIndicatorUnitSubgroup().getSubGroup()+ " ("
					+ dataValue.getIndicatorUnitSubgroup().getUnit()
					+ ")-childs",
					String.valueOf(dataValue.getChildIds()));			
		}
			

		}
		iusValuesMapList.add(iusValuesMap);
		/*if(userDetailModel.getAreaLevelId()!=4){
		iusValuesMap = new LinkedHashMap<String, String>();

		iusValuesMap.put("District", userDetailModel.getAreaName());
		List<Object[]> dataValuesOfstate =new ArrayList<>();
		if(userDetailModel.getAreaLevelId()==1||userDetailModel.getAreaLevelId()==2)
		{
			iusValuesMap.put("District", "Uttar Pradesh");
		dataValuesOfstate = dataValueRepository
				.findTotalStateValueOfIusForAtimePeriod(iusIds, startTimeperiod);
		}
		else
		{
		dataValuesOfstate = dataValueRepository
				.findTotalDivisionalValueOfIusForAtimePeriod(iusIds, startTimeperiod,userAreaId);
		}
		for (Object[] dataValueOfstate : dataValuesOfstate) {
			iusValuesMap.put(dataValueOfstate[1].toString() + " (Number" + ") "
					+ dataValueOfstate[2].toString(),
					String.valueOf((int)Double.parseDouble(dataValueOfstate[0].toString())));
		}

		for (IndicatorUnitSubgroup iuss : iusList) {
			if (iuss.getUnit().equalsIgnoreCase("Percent")) {
				float i = 0;
				if (Float.parseFloat(iusValuesMap.get(iuss.getIndicatorName()
						+ " (Number" + ") Total")) > 0
						&& Float.parseFloat(iusValuesMap.get(iuss
								.getIndicatorName()
								+ " (Number"
								+ ") "
								+ iuss.getSubGroup())) > 0) {
					i = Float.parseFloat(iusValuesMap.get(iuss
							.getIndicatorName()
							+ " (Number"
							+ ") "
							+ iuss.getSubGroup()))
							/ Float.parseFloat(iusValuesMap.get(iuss
									.getIndicatorName()
									+ " (Number"
									+ ") Total")) * 100;
					i = Float.parseFloat(df.format(Double.parseDouble(String
							.valueOf(i))));
				}
				iusValuesMap.put(
						iuss.getIndicatorName() + " (" + iuss.getUnit() + ") "
								+ iuss.getSubGroup(), String.valueOf(i));

			}

		}
		iusValuesMapList.add(iusValuesMap);*/
		//}
		
		Map<String,ValueModel> valueObjectMap = new LinkedHashMap<String, ValueModel>();
		
		for(IndicatorUnitSubgroup iuss:iusList)
		{
			ValueModel valueObject = new ValueModel();
			if(iuss.getUnit().equalsIgnoreCase("number"))
			{
			if(iuss.getSubGroupType()==null)
			{
				valueObject = new ValueModel();
				valueObject.setId(iuss.getIndicatorId());
				valueObject.setName(iuss.getSubGroup()+ " ("
					+ iuss.getUnit()
					+ ")");
				valueObjectMap.put(valueObject.getName(), valueObject);	
			}
			else 
			{
				if(valueObjectMap.containsKey(iuss.getSubGroupType()))
				{
					valueObject = new ValueModel();
					valueObject.setId(iuss.getIndicatorId());
					valueObject.setName(iuss.getSubGroup()+ " ("
						+ iuss.getUnit()
						+ ")");
					valueObjectMap.get(iuss.getSubGroupType()).getChilds().add(valueObject);
				}
				
				else 
				{
					valueObject = new ValueModel();
					ValueModel valueModel =new ValueModel();
					
					valueModel.setName(iuss.getSubGroupType());
					
					List<ValueModel> vModels=new ArrayList<ValueModel>();
					valueObject.setId(iuss.getIndicatorId());
					valueObject.setName(iuss.getSubGroup()+ " ("
						+ iuss.getUnit()
						+ ")");
					
					vModels.add(valueObject);
					valueModel.setChilds(vModels);
					
					valueObjectMap.put(iuss.getSubGroupType(), valueModel);
				}
				
			}
			}
		}
		
		List<ValueModel> valueModel=new ArrayList<ValueModel>();
		
		for(String key:valueObjectMap.keySet())
		{
			valueModel.add(valueObjectMap.get(key));
		}
		
		Map<String,Object> tableData=new HashMap<String, Object>();
		
		tableData.put("table", iusValuesMapList);
		tableData.put("header", valueModel);
		
		return tableData;
	}
	
	@Override
	@Transactional
	public Object getReportsForIndicatorForChildAbove18(String indicatorName, int indicatorId,
			 int startTimeperiod, int endTimeperiod) {
		IndicatorUnitSubgroup ius = indicatorUnitSubgroupRepository
				.findByIndicatorId(indicatorId);
		List<IndicatorUnitSubgroup> iusList = indicatorUnitSubgroupRepository
				.findByIndicatorNameOrderByIndicatorIdAsc(ius.getIndicatorName());
		List<Integer> iusIds = new ArrayList<Integer>();
		for (IndicatorUnitSubgroup indicatorUnitSubgroup : iusList) {
			iusIds.add(indicatorUnitSubgroup.getIndicatorId());
		}

		List<DataValueCciWise> dataValues=new ArrayList<>();
		UserDetailModel userDetailModel=(UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		int userAreaId=userDetailModel.getAreaId();
		List<Integer> tlist=new ArrayList<Integer>();
		do{
			tlist.add(startTimeperiod);
			startTimeperiod++;
		}
		while(startTimeperiod <= endTimeperiod);
		List<Object[]> objects= dataValueCCIWiseRepository
			.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInOrderByCciDetailsCciNameAscAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
					iusIds, tlist);
		for (Object object[] : objects) {
			AreaDetails area= areaRepository.findByAreaId(Integer.parseInt(object[2].toString()));
			
			IndicatorUnitSubgroup indicator=
					indicatorUnitSubgroupRepository.
					findByIndicatorId(Integer.parseInt(object[3].toString()));
			DataValueCciWise dv=new DataValueCciWise();
			dv.setAreaDetails(area);
			dv.setIndicatorUnitSubgroup(indicator);
			dv.setChildIds(object[1]==null?null:object[1].toString());
			dv.setValue(Float.parseFloat(object[0].toString()));
			dv.setCciName(object[4].toString());
			dataValues.add(dv);			
		}
		
		List<Map<String, String>> iusValuesMapList = new ArrayList<Map<String, String>>();
		Map<String, String> iusValuesMap = new LinkedHashMap<String, String>();
		for (DataValueCciWise dataValue : dataValues) {

			if (iusValuesMap.isEmpty()
					||  !iusValuesMap.get("CCI").equalsIgnoreCase(
									dataValue.getCciName())) {
				if (iusValuesMap.size() > 0) {
					iusValuesMapList.add(iusValuesMap);
				}
				iusValuesMap = new LinkedHashMap<String, String>();
				iusValuesMap.put("District", dataValue.getAreaDetails()
						.getAreaName());
				iusValuesMap.put("CCI", dataValue.getCciName());
			}
		if(	dataValue.getIndicatorUnitSubgroup().getUnit().equalsIgnoreCase("Percent"))
			{
			iusValuesMap.put(dataValue.getIndicatorUnitSubgroup().getSubGroup()+ " ("
					+ dataValue.getIndicatorUnitSubgroup().getUnit()
					+ ")", Float
					.toString(dataValue.getValue()));
			}
		else
		{
			iusValuesMap.put(dataValue.getIndicatorUnitSubgroup().getSubGroup()+ " ("
					+ dataValue.getIndicatorUnitSubgroup().getUnit()
					+ ")",
					String.valueOf((int)(dataValue.getValue())));
			iusValuesMap.put(dataValue.getIndicatorUnitSubgroup().getSubGroup()+ " ("
					+ dataValue.getIndicatorUnitSubgroup().getUnit()
					+ ")-childs",
					String.valueOf(dataValue.getChildIds()));	
		}

		}
		iusValuesMapList.add(iusValuesMap);
		/*if(userDetailModel.getAreaLevelId()!=4){
		iusValuesMap = new LinkedHashMap<String, String>();

		iusValuesMap.put("District", userDetailModel.getAreaName());
		List<Object[]> dataValuesOfstate =new ArrayList<>();
		if(userDetailModel.getAreaLevelId()==1||userDetailModel.getAreaLevelId()==2)
		{
			iusValuesMap.put("District", "Uttar Pradesh");
		dataValuesOfstate = dataValueRepository
				.findTotalStateValueOfIusForAtimePeriod(iusIds, timeperiodId);
		}
		else
		{
		dataValuesOfstate = dataValueRepository
				.findTotalDivisionalValueOfIusForAtimePeriod(iusIds, timeperiodId,userAreaId);
		}
		for (Object[] dataValueOfstate : dataValuesOfstate) {
			iusValuesMap.put(dataValueOfstate[1].toString() + " (Number" + ") "
					+ dataValueOfstate[2].toString(),
					String.valueOf((int)Double.parseDouble(dataValueOfstate[0].toString())));
		}

		for (IndicatorUnitSubgroup iuss : iusList) {
			if (iuss.getUnit().equalsIgnoreCase("Percent")) {
				float i = 0;
				if (Float.parseFloat(iusValuesMap.get(iuss.getIndicatorName()
						+ " (Number" + ") Total")) > 0
						&& Float.parseFloat(iusValuesMap.get(iuss
								.getIndicatorName()
								+ " (Number"
								+ ") "
								+ iuss.getSubGroup())) > 0) {
					i = Float.parseFloat(iusValuesMap.get(iuss
							.getIndicatorName()
							+ " (Number"
							+ ") "
							+ iuss.getSubGroup()))
							/ Float.parseFloat(iusValuesMap.get(iuss
									.getIndicatorName()
									+ " (Number"
									+ ") Total")) * 100;
					i = Float.parseFloat(df.format(Double.parseDouble(String
							.valueOf(i))));
				}
				iusValuesMap.put(
						iuss.getIndicatorName() + " (" + iuss.getUnit() + ") "
								+ iuss.getSubGroup(), String.valueOf(i));

			}

		}
		iusValuesMapList.add(iusValuesMap);
		}*/
		Map<String,ValueModel> valueObjectMap = new LinkedHashMap<String, ValueModel>();
		
		for(IndicatorUnitSubgroup iuss:iusList)
		{
			ValueModel valueObject = new ValueModel();
			if(iuss.getUnit().equalsIgnoreCase("number"))
			{
			if(iuss.getSubGroupType()==null)
			{
				valueObject = new ValueModel();
				valueObject.setId(iuss.getIndicatorId());
				valueObject.setName(iuss.getSubGroup()+ " ("
					+ iuss.getUnit()
					+ ")");
				valueObjectMap.put(valueObject.getName(), valueObject);	
			}
			else 
			{
				if(valueObjectMap.containsKey(iuss.getSubGroupType()))
				{
					valueObject = new ValueModel();
					valueObject.setId(iuss.getIndicatorId());
					valueObject.setName(iuss.getSubGroup()+ " ("
						+ iuss.getUnit()
						+ ")");
					valueObjectMap.get(iuss.getSubGroupType()).getChilds().add(valueObject);
				}
				
				else 
				{
					valueObject = new ValueModel();
					ValueModel valueModel =new ValueModel();
					
					valueModel.setName(iuss.getSubGroupType());
					
					List<ValueModel> vModels=new ArrayList<ValueModel>();
					valueObject.setId(iuss.getIndicatorId());
					valueObject.setName(iuss.getSubGroup()+ " ("
						+ iuss.getUnit()
						+ ")");
					
					vModels.add(valueObject);
					valueModel.setChilds(vModels);
					
					valueObjectMap.put(iuss.getSubGroupType(), valueModel);
				}
				
			}
			}
		}
		
		List<ValueModel> valueModel=new ArrayList<ValueModel>();
		
		for(String key:valueObjectMap.keySet())
		{
			valueModel.add(valueObjectMap.get(key));
		}
		
		Map<String,Object> tableData=new HashMap<String, Object>();
		
		tableData.put("table", iusValuesMapList);
		tableData.put("header", valueModel);
		
		return tableData;
	}

	@Override
	public String exportToPdf(String mapImageSvg, String legendImage,
			String lineChartImageSvg, int iusnId, int timeperiodId, int areaId)
			throws Exception {
		Map<String, Object> getMapData = getMapData(iusnId, timeperiodId);
		List<List<LineChart>> getLineChartData = getLineChartData(iusnId,
				timeperiodId, areaId);

		new FileOutputStream(new File(context.getRealPath("")
				+ "\\resources\\mapImageSvg.svg"))
				.write(mapImageSvg.getBytes());

		new FileOutputStream(new File(context.getRealPath("")
				+ "\\resources\\lineChartImageSvg.svg"))
				.write(lineChartImageSvg.getBytes());

		// If area is clicked it we will set district name in area
		String area;
		// for the district filter
		if (areaId != 0) {
			area = areaRepository.fetchAreaById(areaId).getAreaName();
		} else
			area = "Uttar Pradesh";
		TimePeriod period = timePeriodRepository
				.findByTimePeriodId(timeperiodId);
		IndicatorUnitSubgroup ius = indicatorUnitSubgroupRepository
				.findByIndicatorId(iusnId);

		Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font dataFont = new Font(Font.FontFamily.HELVETICA, 10);

		Document document = new Document(PageSize.A4.rotate());
		String outputPath = applicationMessageSource.getMessage("outputPath",
				null, null)
				+ ius.getIndicatorName()
				+ "_"
				+ period.getTimeperiod() + "_Score_Card.pdf";
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(outputPath));

		// setting Header Footer.PLS Refer to org.sdrc.cpis.util.HeaderFooter
		HeaderFooter headerFooter = new HeaderFooter(context,
				applicationMessageSource.getMessage("domainName", null, null));
		writer.setPageEvent(headerFooter);
		document.addAuthor(applicationMessageSource.getMessage("pdf.author",
				null, null));
		document.open();

		BaseColor cellColor = WebColors.getRGBColor("#E8E3E2");

		Paragraph dashboardTitle = new Paragraph();
		dashboardTitle.setAlignment(Element.ALIGN_CENTER);
		dashboardTitle.setSpacingAfter(10);
		Chunk dashboardChunk = new Chunk("Score Card");
		dashboardTitle.add(dashboardChunk);

		Paragraph blankSpace = new Paragraph();
		blankSpace.setAlignment(Element.ALIGN_CENTER);
		blankSpace.setSpacingAfter(10);
		Chunk blankSpaceChunk = new Chunk("          ");
		blankSpace.add(blankSpaceChunk);

		Paragraph spiderDataParagraph = new Paragraph();
		spiderDataParagraph.setAlignment(Element.ALIGN_CENTER);
		spiderDataParagraph.setSpacingAfter(10);
		Chunk spiderChunk = new Chunk("Indicator: " + ius.getUnit() + " of "
				+ ius.getIndicatorName() + " ( " + ius.getSubGroup() + " )"
				+ "\t  \t  Timeperiod: " + period.getTimeperiod());
		Font headerFont = new Font();
		headerFont.setStyle(Font.BOLD);
		headerFont.setSize(12);
		spiderChunk.setFont(headerFont);
		spiderDataParagraph.add(spiderChunk);

		// for Image

		String css = "svg {" + "shape-rendering: geometricPrecision;"
				+ "text-rendering:  geometricPrecision;"
				+ "color-rendering: optimizeQuality;"
				+ "image-rendering: optimizeQuality;" + "}";
		File cssFile = File.createTempFile("batik-default-override-", ".css");
		FileUtils.writeStringToFile(cssFile, css);

		String svg_URI_input = Paths
				.get(new File(context.getRealPath("")
						+ "\\resources\\mapImageSvg.svg").getPath()).toUri()
				.toURL().toString();

		TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);
		// Step-2: Define OutputStream to PNG Image and attach to
		// TranscoderOutput
		ByteArrayOutputStream png_ostream = new ByteArrayOutputStream();
		TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
		// Step-3: Create PNGTranscoder and define hints if required
		PNGTranscoder my_converter = new PNGTranscoder();
		// Step-4: Convert and Write output

		my_converter.transcode(input_svg_image, output_png_image);

		Image image1 = Image.getInstance(png_ostream.toByteArray());
		//image1.setBorder(com.itextpdf.text.Rectangle.BOX);
	//	image1.setBorderWidth(Float.parseFloat(String.valueOf(0.5)));
		//image1.setUseVariableBorders(true);
		//image1.setBorderColor(BaseColor.DARK_GRAY);
		image1.setAlignment(Element.ALIGN_CENTER);
		png_ostream.flush();
		image1.setAbsolutePosition(170, 140);

		// int indentation2 = 0;

		svg_URI_input = Paths
				.get(new File(context.getRealPath("")
						+ "\\resources\\lineChartImageSvg.svg").getPath())
				.toUri().toURL().toString();
		input_svg_image = new TranscoderInput(svg_URI_input);
		// Step-2: Define OutputStream to PNG Image and attach to
		// TranscoderOutput
		png_ostream = new ByteArrayOutputStream();
		output_png_image = new TranscoderOutput(png_ostream);
		// Step-3: Create PNGTranscoder and define hints if required
		my_converter = new PNGTranscoder();
		// Step-4: Convert and Write output
		my_converter.transcode(input_svg_image, output_png_image);
		png_ostream.flush();
		//

		Image lineChartImage = Image.getInstance(png_ostream.toByteArray());
		lineChartImage.setAbsolutePosition(140, 120);
		// End of image

		PdfPTable mapDataTable = new PdfPTable(3);

		float[] mapDatacolumnWidths = new float[] { 8f, 30f, 30f };
		mapDataTable.setWidths(mapDatacolumnWidths);

		PdfPCell mapDataCell0 = new PdfPCell(new Paragraph("Sl.No.", smallBold));
		PdfPCell mapDataCell1 = new PdfPCell(new Paragraph("District",
				smallBold));

		PdfPCell mapDataCell3 = new PdfPCell(new Paragraph("SCORE", smallBold));

		mapDataCell0.setBackgroundColor(BaseColor.LIGHT_GRAY);
		mapDataCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

		mapDataCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

		mapDataCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		mapDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
		mapDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		mapDataTable.addCell(mapDataCell0);
		mapDataTable.addCell(mapDataCell1);

		mapDataTable.addCell(mapDataCell3);

		@SuppressWarnings("unchecked")
		List<MapDataModel> mapDatas = (List<MapDataModel>) getMapData
				.get("dataCollection");
		int i = 1;
		for (MapDataModel mapData : mapDatas) {

			PdfPCell data0 = new PdfPCell(new Paragraph(Integer.toString(i),
					dataFont));
			data0.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell data1 = new PdfPCell(new Paragraph(mapData.getAreaName(),
					dataFont));
			data1.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell data3 = new PdfPCell(new Paragraph(mapData.getValue(),
					dataFont));
			data3.setHorizontalAlignment(Element.ALIGN_CENTER);

			data0.setBackgroundColor(cellColor);
			data1.setBackgroundColor(cellColor);

			data3.setBackgroundColor(cellColor);

			mapDataTable.addCell(data0);
			mapDataTable.addCell(data1);

			mapDataTable.addCell(data3);

			i++;

		}

		Paragraph chartDataTitle = new Paragraph();
		chartDataTitle.setAlignment(Element.ALIGN_CENTER);
		chartDataTitle.setSpacingAfter(10);
		Chunk chartDataChunk = new Chunk("Indicator: " + ius.getUnit() + " of "
				+ ius.getIndicatorName() + " ( " + ius.getSubGroup() + " )"
				+ "\t  \t  Area: " + area);
		chartDataChunk.setFont(headerFont);
		chartDataTitle.add(chartDataChunk);

		PdfPTable chartDataTable = new PdfPTable(3);

		float[] chartDatacolumnWidths = new float[] { 8f, 30f, 30f };
		mapDataTable.setWidths(mapDatacolumnWidths);

		PdfPCell chartDataCell0 = new PdfPCell(new Paragraph("Sl.No.",
				smallBold));
		PdfPCell chartDataCell1 = new PdfPCell(new Paragraph("Time Period",
				smallBold));

		PdfPCell chartDataCell3 = new PdfPCell(
				new Paragraph("SCORE", smallBold));

		chartDataCell0.setBackgroundColor(BaseColor.LIGHT_GRAY);
		chartDataCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

		chartDataCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

		chartDataCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		chartDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
		chartDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		chartDataTable.addCell(chartDataCell0);
		chartDataTable.addCell(chartDataCell1);

		chartDataTable.addCell(chartDataCell3);

		i = 1;
		for (LineChart lineChartModel : getLineChartData.get(0)) {

			PdfPCell data0 = new PdfPCell(new Paragraph(Integer.toString(i),
					dataFont));
			data0.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell data1 = new PdfPCell(new Paragraph(
					lineChartModel.getDate(), dataFont));
			data1.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell data3 = new PdfPCell(new Paragraph(lineChartModel
					.getValue().toString(), dataFont));
			data3.setHorizontalAlignment(Element.ALIGN_CENTER);

			data0.setBackgroundColor(cellColor);
			data1.setBackgroundColor(cellColor);
			data3.setBackgroundColor(cellColor);

			chartDataTable.addCell(data0);
			chartDataTable.addCell(data1);

			chartDataTable.addCell(data3);

			i++;

		}

		document.add(blankSpace);

		document.add(spiderDataParagraph);

		document.add(image1);

		document.newPage();
		document.add(mapDataTable);

		document.newPage();
		document.add(chartDataTitle);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(lineChartImage);

		document.newPage();
		document.add(chartDataTable);

		document.close();

		// //////////////////
		return outputPath;

	}

	@Override
	public List<DashboardIndicatorComparasionModel> getIusDataForCpisHome(Integer selectedLang) {
		List<Object[]> dataValuesOfIus;
		List<String> iusList = Arrays.asList(applicationMessageSource
				.getMessage("indicator.unit.subgroup.ID", null, null)
				.split(","));
		List<Integer> iusId = new ArrayList<Integer>();
		for (String ius : iusList) {
			iusId.add(Integer.parseInt(ius));
		}
		List<DashboardIndicatorComparasionModel> dashboardIndicatorComparasionModels = new ArrayList<DashboardIndicatorComparasionModel>();
		dataValuesOfIus = dataValueRepository.findTotalDataValueOfIus(iusId);

		for (Object[] dataValueOfIus : dataValuesOfIus) {
			DashboardIndicatorComparasionModel comparasionModel = new DashboardIndicatorComparasionModel();
			if(selectedLang==1)
			comparasionModel.setIndicatorName(dataValueOfIus[1].toString());
			else
			comparasionModel.setIndicatorName(null==dataValueOfIus[2]?null:dataValueOfIus[2].toString());
			
			long i=Math.round(Double.valueOf(dataValueOfIus[0].toString()));
			comparasionModel.setNewValue((String.valueOf(i)));

			dashboardIndicatorComparasionModels.add(comparasionModel);
		}
		// TODO Auto-generated method stub
		return dashboardIndicatorComparasionModels;
	}
	
	@Override
	public List<ReportModel> getChildDetailsForIUS(String childs,String indicator) {
		List<String> childIds=new ArrayList<String>();
		List<ReportModel> childDetailsModels=new ArrayList<ReportModel>();
		if(childs != null){
			childIds = Arrays.asList(childs.split(","));
		}
		List<ChildDetails> childList=childDetailsRepository.findByChildIdIsIn(childIds);
		if(childList!=null && !childList.isEmpty()){
			for(ChildDetails child:childList){
				ReportModel childDetailsModel=new ReportModel();
				childDetailsModel.setChildName(child.getChildName());
				childDetailsModel.setChildId(child.getChildId());
				if(indicator.equals("ICP developed for CNCP cases") || indicator.equals("ICP developed for CICL cases")) {
					Long count=individualCarePlanBRepository.countByChildId(child.getChildId());
					childDetailsModel.setCount(count);
					}
				if(indicator.equals("Follow up of CNCP distributed by time period")) {
					Long count=followUpRepository.countByChildId(child.getChildId());
					childDetailsModel.setCount(count);
				}
				childDetailsModel.setSirFatherName(child.getSirFatherName()!=null?child.getSirFatherName():"NA");
				childDetailsModel.setSirMotherName(child.getSirMotherName()!=null?child.getSirMotherName():"NA");
				childDetailsModel.setAddress(child.getSirChildAddress()!=null?child.getSirChildAddress():"NA");
				
				childDetailsModels.add(childDetailsModel);
			}
		}		
		return childDetailsModels;
	}

	@Override
	public List<Map<String, String>> getCCIData(int iusid, int timeperiodid,
			int areaId) {
		List<Map<String,String>> tableDatas=new ArrayList<Map<String,String>>();
		
		
		Map<String,String> tableDataMap=new LinkedHashMap<String,String>();
		
		List<DataValueCciWise> cciDatas=dataValueCCIWiseRepository.findByIndicatorUnitSubgroupIndicatorIdAndTimePeriodTimePeriodIdAndCciDetailsAreaDetailsOrderByCciDetailsCciNameAsc(iusid, timeperiodid,areaId);
		
		for(DataValueCciWise cciData:cciDatas)
		{
			tableDataMap=new LinkedHashMap<String,String>();
			
			IndicatorUnitSubgroup indicatorUnitSubgroup=cciData.getIndicatorUnitSubgroup();
			
			tableDataMap.put("cciId",String.valueOf(cciData.getCciDetails().getCciId()));
			tableDataMap.put("CCI Name", cciData.getCciName());
			tableDataMap.put(indicatorUnitSubgroup
					.getIndicatorName()
					+ "-"
					+ indicatorUnitSubgroup.getSubGroup()
					+ " ("
					+ indicatorUnitSubgroup.getUnit()
					+ ")", indicatorUnitSubgroup.getUnit().equalsIgnoreCase("number")?String.valueOf(Math.round(cciData.getValue())):String.valueOf(cciData.getValue()));
			
			tableDatas.add(tableDataMap);
		}
		return tableDatas;
	}

	@Override
	public List<List<LineChart>> getCCILineChartData(int iusid,
			int timeperiodid, int areaId) {
		TimePeriod period = timePeriodRepository
				.findByTimePeriodId(timeperiodid);

		List<TimePeriod> timePeriod=timePeriodRepository.findTop12ByOrderByTimePeriodIdDesc();
		
		Date startDate = null;
		Date endDate = period.getEndDate();
		
		if(timePeriod.size()>=12)
		{
			startDate=timePeriod.get(11).getStartDate();
		}
		
		else
		{
			startDate=timePeriod.get(timePeriod.size()-1).getStartDate();
		}
/*
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		 //setting start date to the previous year date
		calendar.add(Calendar.YEAR, -1);
		startDate = new Date(calendar.getTime().getTime());*/

		List<Object[]> dataValues = new ArrayList<Object[]>();
		
			 
			
		dataValues=dataValueCCIWiseRepository.findCCIValueForIUSNId(iusid,
					startDate, endDate, areaId);
		
		List<LineChart> lineCharts = new ArrayList<LineChart>();

		for (Object[] obj : dataValues) {
			LineChart lineChart = new LineChart();

			lineChart
					.setValue(df.format(Double.parseDouble(obj[0].toString())));
			if (Double.parseDouble(obj[0].toString()) == 0) {
				lineChart.setValue(0.0);
			}
			lineChart.setDate(obj[1].toString());

			
			lineCharts.add(lineChart);
		}

		List<List<LineChart>> lineChartsList = new ArrayList<List<LineChart>>();

		lineChartsList.add(lineCharts);
		// Collections.reverse(lineChartsList);

		// TODO Auto-generated method stub
		return lineChartsList;
	}
}
