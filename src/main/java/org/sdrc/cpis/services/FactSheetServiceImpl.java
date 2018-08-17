package org.sdrc.cpis.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.DataValueRepository;
import org.sdrc.cpis.repository.TimePeriodRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class FactSheetServiceImpl implements Factsheetservice {
	@Autowired
	DataValueRepository dataValueRepository;
	
	@Autowired
	TimePeriodRepository timePeriodRepository;

	@Autowired
	private StateManager stateManager;
	
	@Autowired 
	private AreaRepository areaRepository;

	@Autowired
	private ServletContext context;

	@Autowired
	private ResourceBundleMessageSource notificationMessageSource;

	@Override
	@Transactional
	public File getFactsheet(int startTimeperiod, int endTimeperiod,String divisionId,String districtId) {
		
		String factsheetTitle = null;
		StringJoiner joiner = null;
		joiner = new StringJoiner(" ");
		StringJoiner outputFileNameBuilder = null;
		outputFileNameBuilder = new StringJoiner("_");
		joiner.add("CPIS Report from");
		joiner.add(timePeriodRepository.findByTimePeriodId(startTimeperiod).getTimeperiod());
		outputFileNameBuilder.add(timePeriodRepository.findByTimePeriodId(startTimeperiod).getTimeperiod());
		joiner.add("to");
		joiner.add(timePeriodRepository.findByTimePeriodId(endTimeperiod).getTimeperiod());
		outputFileNameBuilder.add(timePeriodRepository.findByTimePeriodId(endTimeperiod).getTimeperiod());
		joiner.add("for");
		
		Map<Integer, String> iusCellMap = new HashMap<Integer, String>();
		Map<String, Double> cellValueMap = new HashMap<String, Double>();
		FileOutputStream ops = null;
		File file = null;
		List<Object[]> factsheetData=new ArrayList<Object[]>();
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		int userAreaId = userDetailModel.getAreaId();
		List<Integer> tlist = new ArrayList<Integer>();
		do {
			tlist.add(startTimeperiod);
			startTimeperiod++;
		} 
		while (startTimeperiod <= endTimeperiod);
		
		try {
			iusCellMap = readExcelToGetIUSCellMap();
			System.out.println(iusCellMap.toString());
			List<Integer> iusIds = new ArrayList<Integer>(iusCellMap.keySet());

			if (userDetailModel.getAreaLevelId() == 2 || userDetailModel.getAreaLevelId() == 1) {
				if((divisionId == null || divisionId.equals("All")) && (districtId == null || districtId.equals("All"))){
					joiner.add(areaRepository.findByAreaId(userAreaId).getAreaName());
					outputFileNameBuilder.add(areaRepository.findByAreaId(userAreaId).getAreaName());
					factsheetData = dataValueRepository
							.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
									iusIds, tlist);
				}else if(districtId == null || districtId.equals("All")){
					joiner.add(areaRepository.findByAreaId(Integer.parseInt(divisionId)).getAreaName());
					outputFileNameBuilder.add(areaRepository.findByAreaId(Integer.parseInt(divisionId)).getAreaName());
					factsheetData = dataValueRepository
							.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
									iusIds, tlist, Integer.parseInt(divisionId));
				}else{
					joiner.add(areaRepository.findByAreaId(Integer.parseInt(districtId)).getAreaName());
					outputFileNameBuilder.add(areaRepository.findByAreaId(Integer.parseInt(districtId)).getAreaName());
					factsheetData = dataValueRepository
							.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg1(
									iusIds, tlist, Integer.parseInt(districtId));
				}
				
				cellValueMap = mapValueToCell(factsheetData, iusCellMap);
				System.out.println(cellValueMap.toString());

			} else if (userDetailModel.getAreaLevelId() == 3) {
				if(districtId == null || districtId.equals("All")){
					joiner.add(areaRepository.findByAreaId(userAreaId).getAreaName());
					outputFileNameBuilder.add(areaRepository.findByAreaId(userAreaId).getAreaName());
					factsheetData = dataValueRepository
							.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
									iusIds, tlist, userAreaId);
				}else{
					joiner.add(areaRepository.findByAreaId(Integer.parseInt(districtId)).getAreaName());
					outputFileNameBuilder.add(areaRepository.findByAreaId(Integer.parseInt(districtId)).getAreaName());
					factsheetData = dataValueRepository
							.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg1(
									iusIds, tlist, Integer.parseInt(districtId));
				}
				
				cellValueMap = mapValueToCell(factsheetData, iusCellMap);
				System.out.println(cellValueMap.toString());

			} else {
				joiner.add(areaRepository.findByAreaId(userAreaId).getAreaName());
				outputFileNameBuilder.add(areaRepository.findByAreaId(userAreaId).getAreaName());
				factsheetData = dataValueRepository
						.findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg1(
								iusIds, tlist, userAreaId);
				cellValueMap = mapValueToCell(factsheetData, iusCellMap);
				System.out.println(cellValueMap.toString());

			}
			factsheetTitle=joiner.toString();
			String outputPath = context
					.getRealPath(notificationMessageSource.getMessage("excel.outputfilepath", null, null));
			outputPath=outputPath+"_"+outputFileNameBuilder.toString()+".xlsx";
			if(factsheetData==null || factsheetData.isEmpty()){
				file=null;
			}else{
				file = writeToFactsheet(cellValueMap, factsheetTitle,outputPath);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	private File writeToFactsheet(Map<String, Double> cellValueMap, String factsheetTitle,String outputPath) throws IOException {
		String excelFilePath = context
				.getRealPath(notificationMessageSource.getMessage("excel.inputfilepath", null, null));
		
		File file = new File(excelFilePath);
		File outputfile = new File(outputPath);
		FileInputStream inputStream = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet secondSheet = workbook.getSheetAt(0);
		for (Entry<String, Double> entry : cellValueMap.entrySet()) {
			String reference=entry.getKey().trim();
			String isPieChart = reference.substring(reference.indexOf(" ")+1).trim();
			if(isPieChart.equalsIgnoreCase("TRUE")){
				
				if(entry.getValue()!=0){
					CellReference cr = new CellReference(reference.substring(0, reference.indexOf(" ")));
					int r = cr.getRow();
					int c = cr.getCol();

					Row row = secondSheet.getRow(r);

					Cell cell = row.getCell(c);
					
					cell.setCellValue(entry.getValue());
				}
			}else{
				CellReference cr = new CellReference(reference.substring(0, reference.indexOf(" ")));
				int r = cr.getRow();
				int c = cr.getCol();

				Row row = secondSheet.getRow(r);

				Cell cell = row.getCell(c);
				
				cell.setCellValue(entry.getValue());
			}
			
		}
		CellReference cr = new CellReference("C112");
		int r = cr.getRow();
		int c = cr.getCol();
		Row row = secondSheet.getRow(r);

		Cell cell = row.getCell(c);
		cell.setCellValue(factsheetTitle);
		
		secondSheet.enableLocking();

		FileOutputStream fileOutputStream = new FileOutputStream(outputfile);

		workbook.setForceFormulaRecalculation(true);

		workbook.write(fileOutputStream);

		workbook.close();

		return outputfile;
	}

	private Map<String, Double> mapValueToCell(List<Object[]> factsheetData, Map<Integer, String> iusCellMap) {
		Map<String, Double> cellValueMap = new HashMap<String, Double>();
		for (Object object[] : factsheetData) {
			cellValueMap.put(iusCellMap.get(Integer.parseInt(object[2].toString())),
					Double.parseDouble(object[0].toString()));
		}
		return cellValueMap;
	}

	private Map<Integer, String> readExcelToGetIUSCellMap() throws IOException {
		String excelFilePath = context
				.getRealPath(notificationMessageSource.getMessage("excel.inputfilepath", null, null));
		Map<Integer, String> iusCellMap = new HashMap<Integer, String>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		for (int i = 148; i <= 189; i++) {
			Row row = firstSheet.getRow(i);
			iusCellMap.put((int) row.getCell(3).getNumericCellValue(), row.getCell(4).toString()+" "+row.getCell(5).toString());
		}
		workbook.close();
		inputStream.close();
		return iusCellMap;
	}

}
