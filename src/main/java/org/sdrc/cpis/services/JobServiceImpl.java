package org.sdrc.cpis.services;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CICLCaseMoniteringSheet;
import org.sdrc.cpis.domains.CICLChildCareInstitutionPendingInquiry;
import org.sdrc.cpis.domains.CICLSocialBackgroundReport;
import org.sdrc.cpis.domains.CICLSocialInvestigationReport;
import org.sdrc.cpis.domains.CaseHistoryCCI;
import org.sdrc.cpis.domains.ChildEmploymentDetails;
import org.sdrc.cpis.domains.ChildPlacedInFitInstitution;
import org.sdrc.cpis.domains.ChildRegistrationDetails;
import org.sdrc.cpis.domains.DataValue;
import org.sdrc.cpis.domains.DataValueCciWise;
import org.sdrc.cpis.domains.FollowUpForm;
import org.sdrc.cpis.domains.FosterCareDetails;
import org.sdrc.cpis.domains.IndicatorUnitSubgroup;
import org.sdrc.cpis.domains.IndividualCarePlanA;
import org.sdrc.cpis.domains.IndividualCarePlanD;
import org.sdrc.cpis.domains.LegallyFreeForAdoption;
import org.sdrc.cpis.domains.RestorationDetails;
import org.sdrc.cpis.domains.SocialinvestigationReport;
import org.sdrc.cpis.domains.SponsorshipOrder;
import org.sdrc.cpis.domains.TimePeriod;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSChildRegistrationRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLCaseMonitoringSheetRepository;
import org.sdrc.cpis.repository.CICLChildCareInstitutionPendingInquiryRepository;
import org.sdrc.cpis.repository.CICLSocialBackgroundReportRepository;
import org.sdrc.cpis.repository.CICLSocialInvestigationReportRepository;
import org.sdrc.cpis.repository.CICLSupervisionOrderRepository;
import org.sdrc.cpis.repository.CaseHistoryRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.ChildEmploymentDetailsRepository;
import org.sdrc.cpis.repository.ChildInFitInstitutionRepository;
import org.sdrc.cpis.repository.DataValueCCIWiseRepository;
import org.sdrc.cpis.repository.DataValueRepository;
import org.sdrc.cpis.repository.FollowUpRepository;
import org.sdrc.cpis.repository.FosterCareDetailsRepository;
import org.sdrc.cpis.repository.IcpPersonalDetailsRepository;
import org.sdrc.cpis.repository.IndicatorUnitSubgroupRepository;
import org.sdrc.cpis.repository.IndividualCarePlanDRepository;
import org.sdrc.cpis.repository.LegallyFreeForAdoptionRepository;
import org.sdrc.cpis.repository.RestorationRepository;
import org.sdrc.cpis.repository.SocialInvestigationReportRepository;
import org.sdrc.cpis.repository.SponsorshipRepository;
import org.sdrc.cpis.repository.TimePeriodRepository;
import org.sdrc.cpis.springdatarepository.SpringDataCCIInfoRepository;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Abikananda(abikananda@sdrc.co.in)
 * @author Harsh Pratyush(harsh@sdrc.co.in)
 *
 */
public class JobServiceImpl implements JobService {

	@Autowired
	private TimePeriodRepository timePeriodRepository;

	@Autowired
	DataValueRepository dataValueRepository;

	@Autowired
	DataValueCCIWiseRepository dataValueCCIWiseRepository;

	@Autowired
	ChildDetailsRepository childDetailsRepository;

	@Autowired
	IndicatorUnitSubgroupRepository indicatorUnitSubgroupRepository;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	CCTSChildRegistrationRepository cctsChildRegistrationRepository;

	@Autowired
	CICLSocialBackgroundReportRepository backgroundReportRepository;

	@Autowired
	CaseHistoryRepository caseHistoryRepository;

	@Autowired
	ChildEmploymentDetailsRepository childEmploymentDetailsRepository;

	@Autowired
	RestorationRepository restorationRepository;

	@Autowired
	ChildInFitInstitutionRepository childInFitInstitutionRepository;

	@Autowired
	CICLSupervisionOrderRepository ciclSupervisionOrderRepository;

	@Autowired
	IndividualCarePlanDRepository individualCarePlanDRepository;

	@Autowired
	IcpPersonalDetailsRepository icpPersonalDetailsRepository;

	@Autowired
	SocialInvestigationReportRepository socialInvestigationReportRepository;

	@Autowired
	CICLSocialInvestigationReportRepository ciclSocialInvestigationReportRepository;

	@Autowired
	CICLCaseMonitoringSheetRepository caseMonitoringSheetRepository;

	@Autowired
	CICLChildCareInstitutionPendingInquiryRepository ciclChildCareInstitutionPendingInquiryRepository;

	@Autowired
	FosterCareDetailsRepository fosterCareDetailsRepository;

	@Autowired
	SponsorshipRepository sponsorshipRepository;

	@Autowired
	LegallyFreeForAdoptionRepository legeallyFreeForAdoptionRepository;

	@Autowired
	SpringDataCCIInfoRepository cciDetailsRepository;

	@Autowired
	CCTSTypeDetailsRepository cctsTypeDetailsRepository;

	@Autowired
	private FollowUpRepository followUpRepository;

	@Autowired
	ResourceBundleMessageSource notificationMessageSource;

	private static DecimalFormat df = new DecimalFormat(".#");

	private SimpleDateFormat simpleDateformater = new SimpleDateFormat(
			"yyyy-MM-dd");
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private Map<Integer, ValueObject> getTypeMap(
			List<CCTSTypeDetails> typeDetails) {

		Map<Integer, ValueObject> map = new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj = new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}

	List<String> employedChildList;

	@Override
	@Transactional
	public int createPreviousMonth() throws ParseException {

		Calendar startDateCalendar = Calendar.getInstance();
		startDateCalendar.add(Calendar.MONTH, -1);
		startDateCalendar.set(Calendar.DATE, 1);
		java.util.Date sDate = startDateCalendar.getTime();
		String startDateStr = simpleDateformater.format(sDate);
		java.util.Date startDate = (java.util.Date) formatter
				.parse(startDateStr + " 00:00:00.000");
		startDateCalendar.set(Calendar.DATE,
				startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		java.util.Date eDate = startDateCalendar.getTime();
		String endDateStr = simpleDateformater.format(eDate);
		java.util.Date endDate = (java.util.Date) formatter.parse(endDateStr
				+ " 00:00:00.000");
		TimePeriod time = timePeriodRepository.findByStartDateAndEndDate(
				new Date(startDate.getTime()), new Date(endDate.getTime()));

		if (time == null) {
			startDateCalendar.setTime(endDate);
			TimePeriod utTimePeriod = new TimePeriod();
			utTimePeriod.setStartDate((new Date(startDate.getTime())));
			utTimePeriod.setEndDate((new Date(endDate.getTime())));
			utTimePeriod.setPerodicity(1); // for monthly aggregation
											// periodicity is 1
			utTimePeriod.setTimeperiod(calendarMonth(startDateCalendar
					.get(Calendar.MONTH))
					+ " "
					+ startDateCalendar.get(Calendar.YEAR));
			utTimePeriod.setShortName(calendarMonth(
					startDateCalendar.get(Calendar.MONTH)).substring(0, 3)
					+ " " + startDateCalendar.get(Calendar.YEAR));
			utTimePeriod = timePeriodRepository.save(utTimePeriod);
			return utTimePeriod.getTimePeriodId();
		}
		// dashBoardJob(time.getTimePeriodId());
		return time.getTimePeriodId();

		// TODO Auto-generated method stub
	}

	private String calendarMonth(int month) {
		switch (month + 1) {
		case 1:
			return "January";
		case 2:
			return "Febuary";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";

		}
		return "";
	}

	@Override
	@Transactional
	public void dashBoardJob(int timeNid) {
		long startTime = System.currentTimeMillis();
		// System.out.println("Job Started at " + new Date(startTime));
		List<CCTSTypeDetails> typeDetails = cctsTypeDetailsRepository
				.getAllTypeDetails();
		Map<Integer, ValueObject> typeMap = getTypeMap(typeDetails);
		List<IndicatorUnitSubgroup> indicatorUnitSubgroups = indicatorUnitSubgroupRepository
				.findAll();
		TimePeriod timePeriod = timePeriodRepository
				.findByTimePeriodId(timeNid);
		List<AreaDetails> areaDetailslist = areaRepository.fetchAreaByLevel(4);

		List<ChildRegistrationDetails> childRegistrationDetails = cctsChildRegistrationRepository
				.findChildRegisteredWithinATimepriod(timePeriod.getStartDate(),
						timePeriod.getEndDate());
		List<CICLChildCareInstitutionPendingInquiry> ciclChildCareInstitutionPendingInquiry = ciclChildCareInstitutionPendingInquiryRepository
				.findCICLChildCareInstitutionPendingInquiryWithinTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<CCIDetails> cciDetailslist = cciDetailsRepository
				.getAllCCIDetails();
		List<ChildEmploymentDetails> childEmploymentDetails = childEmploymentDetailsRepository
				.findAll();
		List<CICLSocialBackgroundReport> backgroundReports = backgroundReportRepository
				.findWithinATimeperiod(timePeriod.getStartDate(),
						timePeriod.getEndDate());
		List<RestorationDetails> restorationDetails = restorationRepository
				.findWithinRestorationDetailsTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<ChildPlacedInFitInstitution> childPlacedInFitInstitutions = childInFitInstitutionRepository
				.findChildPlacedInFitInstitutionWithinTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		// List<CICLSupervisionOrder> ciclSupervisionOrders =
		// ciclSupervisionOrderRepository
		// .findCICLSupervisionOrderWithinTimePeriod(timePeriod.getStartDate(),
		// timePeriod.getEndDate());
		List<CaseHistoryCCI> caseHistoryCCIs = caseHistoryRepository
				.findCaseHistoryCCIWithinTimePeriod(timePeriod.getStartDate(),
						timePeriod.getEndDate());
		List<IndividualCarePlanA> individualCarePlanAs = icpPersonalDetailsRepository
				.findIndividualCarePlanAWithinTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<SocialinvestigationReport> socialinvestigationReports = socialInvestigationReportRepository
				.findSocialinvestigationReportWithinTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<CICLSocialInvestigationReport> ciclSocialInvestigationReports = ciclSocialInvestigationReportRepository
				.findCICLSocialInvestigationReportWithinTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<CICLCaseMoniteringSheet> caseMoniteringSheets = caseMonitoringSheetRepository
				.findCICLCaseMoniteringSheetWithinTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<FosterCareDetails> fosterCareDetails = fosterCareDetailsRepository
				.findFosterCareDetailsBetweenTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<SponsorshipOrder> sponsorshipOrders = sponsorshipRepository
				.findSponsorshipOrderBetweenTimePeriod(
						timePeriod.getStartDate(), timePeriod.getEndDate());
		List<IndividualCarePlanD> individualCarePlanD = individualCarePlanDRepository
				.findAdmissionDateBetweenTimePeriod(timePeriod.getStartDate(),
						timePeriod.getEndDate());

		List<LegallyFreeForAdoption> legallyFreeForAdoptionsCicl = legeallyFreeForAdoptionRepository
				.findByChildIdProgramType(1);
		List<LegallyFreeForAdoption> legallyFreeForAdoptionsCncp = legeallyFreeForAdoptionRepository
				.findByChildIdProgramType(0);
		List<LegallyFreeForAdoption> legallyFreeForAdoptions = legeallyFreeForAdoptionRepository
				.findByChildIdProgramType1(timePeriod.getStartDate(),
						timePeriod.getEndDate());
		List<RestorationDetails> restorationDetailsCncp = restorationRepository
				.findByChildIdProgramType(0);
		List<RestorationDetails> restorationDetailsCicl = restorationRepository
				.findByChildIdProgramType(1);
		List<ChildRegistrationDetails> childRegistrationDetailsTotal = cctsChildRegistrationRepository
				.findAll();
		List<ChildPlacedInFitInstitution> childPlacedInFitInstitutionsTotal = childInFitInstitutionRepository
				.findByPeriodForWhichSentToFitInstitutionGreaterThan(12);
		List<CICLSocialBackgroundReport> backgroundReportsTotal = backgroundReportRepository
				.findAll();

		List<FollowUpForm> followUpForms = followUpRepository
				.latestFollowUpForms(timePeriod.getStartDate(),
						timePeriod.getEndDate());
		// List<CICLSupervisionOrder> ciclSupervisionOrdersTotal =
		// ciclSupervisionOrderRepository
		// .findByChildPlacedPeriodGreaterThan(12);

		updateAgeOfChild(childRegistrationDetailsTotal, backgroundReportsTotal,
				typeMap);

		Map<String, SocialinvestigationReport> socialinvestigationReportsMap = new LinkedHashMap<>();
		for (SocialinvestigationReport socialinvestigationReport : socialinvestigationReports) {
			socialinvestigationReportsMap.put(socialinvestigationReport
					.getChildId().getChildId(), socialinvestigationReport);
		}

		Map<String, ChildPlacedInFitInstitution> childPlaced = new LinkedHashMap<>();
		for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutionsTotal) {
			childPlaced.put(childPlacedInFitInstitution.getChildId()
					.getChildId(), childPlacedInFitInstitution);
		}
		childPlacedInFitInstitutionsTotal = new ArrayList<ChildPlacedInFitInstitution>();
		for (Entry<String, ChildPlacedInFitInstitution> entry : childPlaced
				.entrySet()) {
			childPlacedInFitInstitutionsTotal.add(entry.getValue());
		}

		childPlaced = new LinkedHashMap<>();
		for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
			childPlaced.put(childPlacedInFitInstitution.getChildId()
					.getChildId(), childPlacedInFitInstitution);
		}
		childPlacedInFitInstitutions = new ArrayList<ChildPlacedInFitInstitution>();
		for (Entry<String, ChildPlacedInFitInstitution> entry : childPlaced
				.entrySet()) {
			childPlacedInFitInstitutions.add(entry.getValue());
		}
		employedChildList = new ArrayList<String>();
		for (ChildEmploymentDetails childEmploymentDetail : childEmploymentDetails) {
			employedChildList.add(childEmploymentDetail.getChildId()
					.getChildId());
		}

		List<DataValue> dataValues = new ArrayList<DataValue>();
		for (IndicatorUnitSubgroup indicatorUnitSubgroup : indicatorUnitSubgroups) {
			for (AreaDetails areaDetail : areaDetailslist) {
				DataValue dataValue = new DataValue();
				float value = 0;
				dataValue.setChildIds(null);
				dataValue.setAreaDetails(areaDetail);
				dataValue.setIndicatorUnitSubgroup(indicatorUnitSubgroup);
				dataValue.setTimePeriod(timePeriod);

				switch (indicatorUnitSubgroup.getIndicatorName()) {

				case "Children Need of Care and Protection cases registered":
					value = childRegistrationDetailsAggregation(
							indicatorUnitSubgroup, areaDetail,
							childRegistrationDetails, typeMap, dataValue);
					break;

				case "Children In Conflict With the Law cases registered":
					value = ciclSocialBackgroundReportAggregation(
							indicatorUnitSubgroup, areaDetail,
							backgroundReports, typeMap, dataValue);
					break;

				case "Children Need of Care and Protection cases disposed":
					value = restorationDetailsAggregation(
							indicatorUnitSubgroup, areaDetail,
							restorationDetails, legallyFreeForAdoptions,
							typeMap, dataValue);
					break;

				case "Children In Conflict With the Law cases disposed":
					value = restorationDetailsAggregation(
							indicatorUnitSubgroup, areaDetail,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue);
					break;

				case "Cases(CNCP) referred to CCI":
					value = childPlacedInFitInstitutionsAggregation(
							indicatorUnitSubgroup, areaDetail,
							childPlacedInFitInstitutions, typeMap, dataValue);
					break;

				case "Cases(CICL) referred to CCI":

					value = ciclSupervisionOrdersAggregation(
							indicatorUnitSubgroup, areaDetail,
							ciclChildCareInstitutionPendingInquiry, typeMap,
							dataValue);
					break;

				case "ICP developed for CNCP cases":
					value = individualCarePlanAsAggregation(
							indicatorUnitSubgroup, areaDetail,
							individualCarePlanAs, typeMap, dataValue);
					break;

				case "ICP developed for CICL cases":
					value = individualCarePlanAsAggregation(
							indicatorUnitSubgroup, areaDetail,
							individualCarePlanAs, typeMap, dataValue);
					break;

				case "SIR prepared for CNCP cases":
					value = socialinvestigationReportsAggregation(
							indicatorUnitSubgroup, areaDetail,
							socialinvestigationReports, typeMap, dataValue);
					break;

				case "SIR prepared for CICL cases":
					value = ciclSocialInvestigationReportsAggregation(
							indicatorUnitSubgroup, areaDetail,
							ciclSocialInvestigationReports, typeMap, dataValue);
					break;

				case "CNCP Cases released from CCI": // to decide
					value = restorationDetailsAggregation(
							indicatorUnitSubgroup, areaDetail,
							restorationDetails, legallyFreeForAdoptions,
							typeMap, dataValue);
					break;

				case "CICL Cases released from CCI": // to decide
					value = restorationDetailsAggregation(
							indicatorUnitSubgroup, areaDetail,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue);
					break;

				case "Pendency of Children Need of Care and Protection cases": // to
																				// decide
					List<ChildPlacedInFitInstitution> childPlacedInFitInstitutionsTotal1 = new ArrayList<ChildPlacedInFitInstitution>();
					childPlacedInFitInstitutionsTotal1
							.addAll(childPlacedInFitInstitutionsTotal);
					for (ChildPlacedInFitInstitution child : childPlacedInFitInstitutionsTotal) {
						for (RestorationDetails child2 : restorationDetailsCncp) {
							if (child.getChildId().getChildId()
									.equals(child2.getChildId().getChildId())) {
								childPlacedInFitInstitutionsTotal1
										.remove(child);
							}
						}
						for (LegallyFreeForAdoption child1 : legallyFreeForAdoptionsCncp) {
							if (child.getChildId().getChildId()
									.equals(child1.getChildId().getChildId())) {
								childPlacedInFitInstitutionsTotal1
										.remove(child);
							}
						}
					}
					value = cncpCasesPendencyAggregation(indicatorUnitSubgroup,
							areaDetail, childRegistrationDetailsTotal,
							childPlacedInFitInstitutionsTotal1,
							restorationDetailsCncp,
							legallyFreeForAdoptionsCncp, typeMap, dataValue);
					break;

				case "Pendency of Children In Conflict With the Law cases": // to
																			// decide
					Map<Integer, CICLSocialBackgroundReport> ciclSocialBackgroundMap = new HashMap<Integer, CICLSocialBackgroundReport>();
					for (CICLSocialBackgroundReport backgroundReport : backgroundReportsTotal) {
						ciclSocialBackgroundMap.put(backgroundReport
								.getChildId().getId(), backgroundReport);
					}

					List<CICLChildCareInstitutionPendingInquiry> ciclSupervisionOrdersTotal1 = new ArrayList<CICLChildCareInstitutionPendingInquiry>();
					ciclSupervisionOrdersTotal1
							.addAll(ciclChildCareInstitutionPendingInquiry);
					for (RestorationDetails ch : restorationDetailsCicl) {
						for (CICLChildCareInstitutionPendingInquiry ch1 : ciclChildCareInstitutionPendingInquiry) {
							if (ch.getChildId().getChildId()
									.equals(ch1.getChildId().getChildId())) {
								ciclSupervisionOrdersTotal1.remove(ch1);
							}
						}
					}

					List<CICLSocialBackgroundReport> backgroundReportsTotal1 = new ArrayList<CICLSocialBackgroundReport>();
					backgroundReportsTotal1.addAll(backgroundReportsTotal);
					for (CICLSocialBackgroundReport child : backgroundReportsTotal) {
						for (RestorationDetails child2 : restorationDetailsCicl) {
							if (child.getChildId().getChildId()
									.equals(child2.getChildId().getChildId())) {
								backgroundReportsTotal1.remove(child);
							}
						}
					}
					value = ciclCasesPendencyAggregation(indicatorUnitSubgroup,
							areaDetail, backgroundReportsTotal1,
							ciclSupervisionOrdersTotal1,
							restorationDetailsCicl,
							legallyFreeForAdoptionsCicl,
							ciclSocialBackgroundMap, typeMap, dataValue);
					break;

				case "Numbers of Children (CICL) differently able":
					value = ciclSocialBackgroundReportAggregation(
							indicatorUnitSubgroup, areaDetail,
							backgroundReports, typeMap, dataValue);
					break;

				case "Numbers of Children (CNCP) differently able":
					value = socialinvestigationReportsAggregation(
							indicatorUnitSubgroup, areaDetail,
							socialinvestigationReports, typeMap, dataValue);
					break;

				case "Education status of Children at the time of registration (CNCP)":
					value = educationStatusOfChildreeCNCP(
							indicatorUnitSubgroup, areaDetail,
							socialinvestigationReports, typeMap, dataValue);
					break;

				case "Education status of Children at the time of registration (CICL)":
					value = educationStatusOfChildreeCICL(
							indicatorUnitSubgroup, areaDetail,
							ciclSocialInvestigationReports, typeMap, dataValue);
					break;

				case "Children under Petty offense case(CICL)":
					value = caseMoniteringSheetsAggregation(
							indicatorUnitSubgroup, areaDetail,
							caseMoniteringSheets, typeMap, dataValue);
					break;

				case "Children under Serious Offence case(CICL)":
					value = caseMoniteringSheetsAggregation(
							indicatorUnitSubgroup, areaDetail,
							caseMoniteringSheets, typeMap, dataValue);
					break;

				case "Children under Henious offence case(CICL)":
					value = caseMoniteringSheetsAggregation(
							indicatorUnitSubgroup, areaDetail,
							caseMoniteringSheets, typeMap, dataValue);
					break;

				case "Children (CNCP) Sexual  Abused":
					value = socialinvestigationReportsAggregation(
							indicatorUnitSubgroup, areaDetail,
							socialinvestigationReports, typeMap, dataValue);
					break;

				case "Children (CICL) Abused":
					value = ciclSocialInvestigationReportsAggregation(
							indicatorUnitSubgroup, areaDetail,
							ciclSocialInvestigationReports, typeMap, dataValue);
					break;

				case "Numbers of CNCP children placed in Foster Care":
					value = fosterCareDetailsAggregation(indicatorUnitSubgroup,
							areaDetail, fosterCareDetails, typeMap, dataValue);
					break;

				case "Numbers of Children Placed under Sponsorship":
					value = sponsorshipOrdersAggregation(indicatorUnitSubgroup,
							areaDetail, sponsorshipOrders, typeMap, dataValue);
					break;

				case "Follow up of CNCP distributed by time period":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
				
				case "Follow up of CNCP with the type of restoration":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Age wise break up of CNCP having follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
				case "Gender wise break up of CNCP having follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
				
				case "Health status of CNCP as per follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Education status of CNCP having follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
				
				case "Availability of learning items with CNCP having follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Vocational training status of CNCP according to follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Health benefits provided to the CNCP as per the followup":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Intellective status of CNCP according ot followup":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Behaviour of the parents towards the child according to the followup":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "CNCP abused according to the followup":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Behaviour of the CNCP towards the parents according to follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "Compliances to the CNCP according to follow up":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);
					
				case "CNCP sharing their problems with parent during the time":
					value = followUpCncpAggregation(indicatorUnitSubgroup,
							areaDetail, followUpForms, typeMap, dataValue);

				}

				try {
					dataValue.setValue(Float.parseFloat(df.format(value)));

				} catch (Exception e) {
					dataValue.setValue(0);
					dataValue.setChildIds(null);
				}
				dataValues.add(dataValue);
			}
		}
		dataValueRepository.save(dataValues);

		// TODO Auto-generated method stub
		List<DataValueCciWise> dataValuesCciWise = new ArrayList<DataValueCciWise>();
		indicatorUnitSubgroups = indicatorUnitSubgroups
				.stream()
				.filter(p -> {
					return p.getIndicatorName().equals(
							"Child above age 18 years living in CCIs(CNCP)")
							|| p.getIndicatorName()
									.equals("Child above age 18 years living in CCIs(CICL)")
							|| p.getIndicatorName().equals(
									"CNCP Cases released from CCI")
							|| p.getIndicatorName().equals(
									"CICL Cases released from CCI")
							|| p.getIndicatorName().equals(
									"ICP developed for CNCP cases")
							|| p.getIndicatorName().equals(
									"ICP developed for CICL cases")
							|| p.getIndicatorName()
									.equals("CCI Wise Education status of Children at the time of registration (CNCP)")
							|| p.getIndicatorName()
									.equals("CCI Wise Education status of Children at the time of registration (CICL)")

							|| p.getIndicatorName()
									.equals("Children suffering from the respiratory disorder in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from hearing impairment in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from eye diseases in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from dental diseases in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from cardiac diseases in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from skin disease in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from sexually transmitted diseases in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from neurological disorders in CCI(CNCP)")
							|| p.getIndicatorName().equals(
									"Children mental handicap in CCI(CNCP)")
							|| p.getIndicatorName().equals(
									"Children physical handicap in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from urinary tract infections in CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Children suffering from any other disease(CNCP)")

							|| p.getIndicatorName()
									.equals("Children suffering from the respiratory disorder in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from hearing impairment in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from eye diseases in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from dental diseases in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from cardiac diseases in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from skin disease in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from sexually transmitted diseases in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from neurological disorders in CCI(CICL)")
							|| p.getIndicatorName().equals(
									"Children mental handicap in CCI(CICL)")
							|| p.getIndicatorName().equals(
									"Children physical handicap in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from urinary tract infections in CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Children suffering from any other disease(CICL)")

							|| p.getIndicatorName()
									.equals("Availability of birth certificate with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of school certificate with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of caste certificate with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of BPL card with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of Disability certificate with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of Immunization card with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of Ration card with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("Availability of Adhaar card with the child after release from CCI(CNCP)")
							|| p.getIndicatorName()
									.equals("The child received compensation from Govt. after release from CCI(CNCP)")

							|| p.getIndicatorName()
									.equals("Availability of birth certificate with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of school certificate with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of caste certificate with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of BPL card with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of Disability certificate with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of Immunization card with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of Ration card with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("Availability of Adhaar card with the child after release from CCI(CICL)")
							|| p.getIndicatorName()
									.equals("The child received compensation from Govt. after release from CCI(CICL)")

					;
				}).collect(Collectors.toList());
		for (IndicatorUnitSubgroup indicatorUnitSubgroup : indicatorUnitSubgroups) {
			for (CCIDetails cciDetails : cciDetailslist) {
				DataValueCciWise dataValueCciWise = new DataValueCciWise();
				float value = 0;
				dataValueCciWise.setChildIds(null);
				dataValueCciWise.setCciDetails(cciDetails);
				dataValueCciWise
						.setIndicatorUnitSubgroup(indicatorUnitSubgroup);
				dataValueCciWise.setTimePeriod(timePeriod);
				dataValueCciWise.setCciName(cciDetails.getCciName());
				AreaDetails areaDetails = new AreaDetails();
				areaDetails.setAreaId(cciDetails.getAreaDetails());
				dataValueCciWise.setAreaDetails(areaDetails);

				switch (indicatorUnitSubgroup.getIndicatorName()) {

				case "Child above age 18 years living in CCIs(CNCP)":
					value = childAbove18InCCIAggregationCNCP(
							indicatorUnitSubgroup, cciDetails,
							childRegistrationDetails, backgroundReports,
							typeMap, socialinvestigationReportsMap,
							dataValueCciWise);
					break;
				case "Child above age 18 years living in CCIs(CICL)":
					value = childAbove18InCCIAggregationCICL(
							indicatorUnitSubgroup, cciDetails,
							childRegistrationDetails, backgroundReports,
							typeMap, socialinvestigationReportsMap,
							dataValueCciWise);
					break;
				case "CNCP Cases released from CCI":
					value = restorationDetailsAggregationCCIWise(
							indicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptions,
							typeMap, dataValueCciWise);
					break;

				case "CICL Cases released from CCI":
					value = restorationDetailsAggregationCCIWise(
							indicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise);
					break;
				case "ICP developed for CNCP cases":
					value = individualCarePlanAsAggregationCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise);
					break;
				case "ICP developed for CICL cases":
					value = individualCarePlanAsAggregationCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise);
					break;
				case "CCI Wise Education status of Children at the time of registration (CNCP)":
					value = educationStatusOfChildreeCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;

				case "CCI Wise Education status of Children at the time of registration (CICL)":
					value = educationStatusOfChildreeCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							ciclSocialInvestigationReports, typeMap,
							dataValueCciWise);
					break;

				case "Children suffering from the respiratory disorder in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from hearing impairment in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from eye diseases in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from dental diseases in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from cardiac diseases in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from skin disease in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from sexually transmitted diseases in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from neurological disorders in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children mental handicap in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children physical handicap in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from urinary tract infections in CCI(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;
				case "Children suffering from any other disease(CNCP)":
					value = childHealthStatusAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							socialinvestigationReports, typeMap,
							dataValueCciWise);
					break;

				case "Children suffering from the respiratory disorder in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from hearing impairment in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from eye diseases in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from dental diseases in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from cardiac diseases in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from skin disease in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from sexually transmitted diseases in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from neurological disorders in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children mental handicap in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children physical handicap in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from urinary tract infections in CCI(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;
				case "Children suffering from any other disease(CICL)":
					value = childHealthStatusAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails, caseHistoryCCIs,
							typeMap, dataValueCciWise);
					break;

				case "Availability of birth certificate with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of school certificate with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of caste certificate with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of BPL card with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Disability certificate with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Immunization card with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Ration card with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Adhaar card with the child after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "The child received compensation from Govt. after release from CCI(CNCP)":
					value = childCertificationAggregationCNCPCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;

				case "Availability of birth certificate with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of school certificate with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of caste certificate with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of BPL card with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Disability certificate with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Immunization card with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Ration card with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "Availability of Adhaar card with the child after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;
				case "The child received compensation from Govt. after release from CCI(CICL)":
					value = childCertificationAggregationCICLCCIWise(
							indicatorUnitSubgroup, cciDetails,
							individualCarePlanD, typeMap, dataValueCciWise);
					break;

				}

				try {
					dataValueCciWise
							.setValue(Float.parseFloat(df.format(value)));

				} catch (Exception e) {
					dataValueCciWise.setValue(0);
					dataValueCciWise.setChildIds(null);
				}
				dataValuesCciWise.add(dataValueCciWise);
			}
		}
		dataValueCCIWiseRepository.save(dataValuesCciWise);
		System.out.println("Job Completed and it takes "
				+ TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()
						- startTime) + " minute");

	}

	private float followUpCncpAggregation(
			IndicatorUnitSubgroup indicatorUnitSubgroup,
			AreaDetails areaDetail, List<FollowUpForm> followUpForms,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// TODO Auto-generated method stub

		float i;
		String childIds = null;
		StringJoiner joiner = null;

		switch (indicatorUnitSubgroup.getIndicatorName()) {
		case "Follow up of CNCP distributed by time period":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Half yearly follow up":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getPeriodicFollowUp())
							.getName()
							.equalsIgnoreCase("Half yearly")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}

				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Quarterly follow up":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getPeriodicFollowUp())
							.getName().equalsIgnoreCase("Quarterly")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			

			/*case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getChildId().getChildSex())
							.getName().equalsIgnoreCase("Boy")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getChildId().getChildSex())
							.getName().equalsIgnoreCase("Girl")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getChildId().getChildSex())
							.getName().equalsIgnoreCase("Third gender")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;*/
			}
			
		case "Follow up of CNCP with the type of restoration":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			
			case "Foster care":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getTypeOfRestoration()).getName().equalsIgnoreCase("Foster Care")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Sponsorship care":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getTypeOfRestoration()).getName().equalsIgnoreCase("Sponsorship")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Normal care":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (typeMap.get(followUpForm.getTypeOfRestoration()).getName().equalsIgnoreCase("Normal")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			/*case "Boys":
				return setGender(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Boy");
				
			case "Girls":
				return setGender(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Girl");
			
			case "Third gender":
				return setGender(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Third gender");*/
			}
			
		case "Age wise break up of CNCP having follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			
			case "Less than 6 years":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getCurrentAge()<6
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "6-12 years":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getCurrentAge()>=6 &&
							(Integer.parseInt(typeMap.get(followUpForm.getChildId().getAge()).getName())<12
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId())) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "12-18 years":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getAge()>=12 && followUpForm.getChildId().getCurrentAge()<18
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Above 18 years":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getChildId().getCurrentAge()>18
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
			
		case "Gender wise break up of CNCP having follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Boys":
				return setGender(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Boy");
				
			case "Girls":
				return setGender(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Girl");
			
			case "Third gender":
				return setGender(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Third gender");
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
		case "Health status of CNCP as per follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Healthy":
				return setHealthStatus(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Healthy");
				
			case "Average":
				return setHealthStatus(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Average");
				
			case "Below average":
				return setHealthStatus(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Below average");
				
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			
			}
			
		case "Education status of CNCP having follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Not attended school":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getSchoolAttended()==false
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "<V":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if(followUpForm.getClassOfStudy()!=null) {
					if (Integer.parseInt(followUpForm.getClassOfStudy())<5
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "V-VII":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if(followUpForm.getClassOfStudy()!=null) {
					if (Integer.parseInt(followUpForm.getClassOfStudy())>=5 && Integer.parseInt(followUpForm.getClassOfStudy())<=8
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "IX-X":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if(followUpForm.getClassOfStudy()!=null) {
					if (Integer.parseInt(followUpForm.getClassOfStudy())==9 || Integer.parseInt(followUpForm.getClassOfStudy())==10
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				  }
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case ">X":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if(followUpForm.getClassOfStudy()!=null) {
					if (Integer.parseInt(followUpForm.getClassOfStudy())>=10
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Government school":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if(followUpForm.getSchoolAttended()==true) {
					if (typeMap.get(followUpForm.getSchoolType()).getName().equalsIgnoreCase("Government")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Private school":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if(followUpForm.getSchoolAttended()==true) {
					if (typeMap.get(followUpForm.getSchoolType()).getName().equalsIgnoreCase("Private")
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "Availability of learning items with CNCP having follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "School dress":
				return setAvailableItems(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "400");
				
			case "Bag":
				return setAvailableItems(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "401");
				
			case "Books":
				return setAvailableItems(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "402");
				
			case "Notebooks":
				return setAvailableItems(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "403");
				
			case "Other stationary":
				return setAvailableItems(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "404");
				
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "Health benefits provided to the CNCP as per the followup":
			switch (indicatorUnitSubgroup.getSubGroup()) {
				case "Health card provided":
				i = 0;
				joiner = new StringJoiner(",");
				for (FollowUpForm followUpForm : followUpForms) {
					if (followUpForm.getHealthCardProvided()==true
							&& followUpForm.getChildId().getChildDistrict()
									.getAreaId() == areaDetail.getAreaId()) {
						i++;
						joiner.add(followUpForm.getChildId().getChildId());
					}
				}
				if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup
							.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
					iusTotalIndicatorUnitSubgroup
							.setIndicatorName(indicatorUnitSubgroup
									.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / followUpCncpAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetail,
							followUpForms, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
				
				case "Routine check up":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getRoutineCheckUp()==true
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Illness since last visit":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getIllness()==true
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Hospitalized":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getHospitalized()==true
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Total":
					return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "Intellective status of CNCP according ot followup":
			switch (indicatorUnitSubgroup.getSubGroup()) {
				case "Happy & well adjusted":
					return setIntellectualStatus(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Happy and well-adjusted");
					
				case "In process of adjusting":
					return setIntellectualStatus(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "In process of adjusting");
					
				case "Maladjusted":
					return setIntellectualStatus(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "Maladjusted");
					
				case "Total":
					return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "Vocational training status of CNCP according to follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
				case "Not enrolled for Vocational training":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getSkillDeveloped()==false
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Vocational training ongoing":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getCourseStatus()==408
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Completed":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getCourseStatus()==409
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Passed":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getCourseStatus()==410
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Failed":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getCourseStatus()==411
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Total":
					return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "Behaviour of the parents towards the child according to the followup":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Supportive":
				return setParentsBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, 412);
			case "Neglectful":
				return setParentsBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, 413);
			case "Permissive":
				return setParentsBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, 414);
			case "Authoritian":
				return setParentsBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, 415);
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "CNCP abused according to the followup":
			switch (indicatorUnitSubgroup.getSubGroup()) {
				case "Sexually abused":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getSexuallyAbused()==true
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Beaten by the parents":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getBeatenByParents()==true
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Made to do household chores":
					i = 0;
					joiner = new StringJoiner(",");
					for (FollowUpForm followUpForm : followUpForms) {
						if (followUpForm.getChildDoHouseholdChores()==true
								&& followUpForm.getChildId().getChildDistrict()
										.getAreaId() == areaDetail.getAreaId()) {
							i++;
							joiner.add(followUpForm.getChildId().getChildId());
						}
					}
					if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup
								.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
						iusTotalIndicatorUnitSubgroup
								.setIndicatorName(indicatorUnitSubgroup
										.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / followUpCncpAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetail,
								followUpForms, typeMap, dataValue)) * 100;

					}
					childIds = joiner.toString();
					dataValue.setChildIds(childIds.equals("") ? null : childIds);
					return i;
					
				case "Total":
					return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
			
		case "Behaviour of the CNCP towards the parents according to follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Good Behaviour":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "416");
				
			case "Bullying behavior":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "417");
				
			case "Violent outbursts":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "418");
				
			case "Destructions":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "419");
				
			case "Self-harm":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "420");
				
			case "Lying":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "421");
				
			case "Defiance":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "422");
				
			case "Impulsiveness":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "423");
				
			case "Lack of empathy":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "424");
				
			case "Sexually deviant actions":
				return setChildBehaviour(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "425");
				
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);	
			}
			
		case "Compliances to the CNCP according to follow up":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Birth Certificate":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "426");
			case "School Certificate":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "427");
			case "Caste certificate":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "428");
			case "Disability Certificate":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "429");
			case "BPL Card":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "430");
			case "Ration Card":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "431");
			case "Health Card":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "432");
			case "Others":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "433");
			case "Nothing at all":
				return setCompliances(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, null);
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
				
			}
			
		case "CNCP sharing their problems with parent during the time":
			switch (indicatorUnitSubgroup.getSubGroup()) {
			case "Dining":
				return setProblemShringTime(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "434");
			case "Playing":
				return setProblemShringTime(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "435");
			case "Watching TV":
				return setProblemShringTime(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "436");
			case "Going to school":
				return setProblemShringTime(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "437");
			case "Doing homework together":
				return setProblemShringTime(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "438");
			case "Others":
				return setProblemShringTime(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue, "439");
			case "Total":
				return setTotal(indicatorUnitSubgroup, typeMap, followUpForms, areaDetail, dataValue);
			}
		}
		return 0;
	}

	/*
	 * This method returns a list of numbers 
	 * from a string of numbers separated by commas
	 * 
	 */
	private List<String> getItemList(String availableItems) {
		String arr[]=availableItems.split(",");
		List<String> list=new ArrayList<>();
		list.addAll(Arrays.asList(arr));
		
		return list;
	}
	
	private float setParentsBehaviour(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, Integer typeId) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			if (followUpForm.getParentsBehaviour()==typeId
					&& followUpForm.getChildId().getChildDistrict()
					.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	/*
	 * This method returns the value for follow up form indicators 
	 * having gender subgroups ie. boys, girls and third gender
	 */
	private float setGender(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			if (typeMap.get(followUpForm.getChildId().getChildSex()).getName().equalsIgnoreCase(typeName)
					&& followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
		
	}
	
	
	/*
	 * This method returns the value for follow up form indicators 
	 * having health status subgroups ie. healthy, average, below average
	 */
	
	private float setHealthStatus(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			if (typeMap.get(followUpForm.getChildLook()).getName().equalsIgnoreCase(typeName)
					&& followUpForm.getChildId().getChildDistrict()
					.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	
/*	
 * This method sets the value for all the 
 * IUS of indicator "Available items with the child"
 */	
	private float setAvailableItems(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			List<String> itemList=getItemList(followUpForm.getAvailableItems());
			if (itemList.contains(typeName)
					&& followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	private float setChildBehaviour(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			List<String> itemList=getItemList(followUpForm.getChildsBehaviour());
			if (itemList.contains(typeName)
					&& followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	private float setCompliances(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			List<String> itemList=getItemList(followUpForm.getComplianceByGovt());
			if (typeName!=null && itemList.contains(typeName)
					&& followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
			else if(typeName==null && followUpForm.getComplianceByGovt()==null) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	private float setProblemShringTime(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			List<String> itemList=getItemList(followUpForm.getProblemShareTime());
			if (typeName!=null && itemList.contains(typeName)
					&& followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
			else if(typeName==null && followUpForm.getComplianceByGovt()==null) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	private float setIntellectualStatus(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue, String typeName) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			if (typeMap.get(followUpForm.getIntellectiveStatus()).getName().equalsIgnoreCase(typeName)
					&& followUpForm.getChildId().getChildDistrict()
							.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		if (indicatorUnitSubgroup.getUnit().equalsIgnoreCase("percent")) {
			IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
			iusTotalIndicatorUnitSubgroup
					.setHighIsGood(indicatorUnitSubgroup.isHighIsGood());
			iusTotalIndicatorUnitSubgroup
					.setIndicatorName(indicatorUnitSubgroup
							.getIndicatorName());
			iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
			i = (i / followUpCncpAggregation(
					iusTotalIndicatorUnitSubgroup, areaDetail,
					followUpForms, typeMap, dataValue)) * 100;

		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
		
	}
	
	private float setTotal(IndicatorUnitSubgroup indicatorUnitSubgroup, Map<Integer, ValueObject> typeMap, List<FollowUpForm> followUpForms, 
			AreaDetails areaDetail, DataValue dataValue) {
		float i = 0;
		StringJoiner joiner = new StringJoiner(",");
		String childIds="";
		for (FollowUpForm followUpForm : followUpForms) {
			if (followUpForm.getChildId().getChildDistrict()
					.getAreaId() == areaDetail.getAreaId()) {
				i++;
				joiner.add(followUpForm.getChildId().getChildId());
			}
		}
		childIds = joiner.toString();
		dataValue.setChildIds(childIds.equals("") ? null : childIds);
		return i;
	}
	
	
	private float childCertificationAggregationCNCPCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails areaDetails,
			List<IndividualCarePlanD> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValue) {

		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "Availability of birth certificate with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of school certificate with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of caste certificate with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of BPL card with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Disability certificate with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Immunization card with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Ration card with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Adhaar card with the child after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "The child received compensation from Govt. after release from CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		}
		return 0;
	}

	private float childCertificationAggregationCICLCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails areaDetails,
			List<IndividualCarePlanD> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValue) {

		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "Availability of birth certificate with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBirthCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of school certificate with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdSchoolCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of caste certificate with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdCasteCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of BPL card with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdBplCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Disability certificate with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.isIdDisabiltyCertificateProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Immunization card with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdImmunizationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Ration card with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdRationCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Availability of Adhaar card with the child after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isIdAdhaarCardProduced()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "The child received compensation from Govt. after release from CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanD backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.isRecievedCompensation()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childCertificationAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		}
		return 0;
	}

	private float educationStatusOfChildreeCICLCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails cciDetails,
			List<CICLSocialInvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValueCciWise) {
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {

		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Third Gender":

			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third Gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Illiterate":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap.get(registrationDetail.getEducation())
								.getName().equalsIgnoreCase("Illiterate")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "<V":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied up to V Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "V-VIII":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above V Standard but below VIII Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "VIII-X":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above VIII Standard but below X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">X":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied above X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICLCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}
		return 0;
	}

	private float educationStatusOfChildreeCNCPCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails cciDetails,
			List<SocialinvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValueCciWise) {
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {

		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Third Gender":

			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third Gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Illiterate":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap.get(registrationDetail.getEducation())
								.getName().equalsIgnoreCase("Illiterate")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "<V":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied up to V Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "V-VIII":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above V Standard but below VIII Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "VIII-X":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above VIII Standard but below X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">X":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getCciDetails() != null
						&& registrationDetail.getChildId().getCciDetails()
								.getCciId().intValue() == cciDetails.getCciId()
								.intValue()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied above X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCPCCIWise(
						iusTotalIndicatorUnitSubgroup, cciDetails,
						socialinvestigationReports, typeMap, dataValueCciWise)) * 100;

			}
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}
		return 0;
	}

	private float individualCarePlanAsAggregationCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails cciDetails,
			List<IndividualCarePlanA> individualCarePlanAs,
			List<LegallyFreeForAdoption> legallyFreeForAdoptionsCncp,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValueCciWise) {
		// typeMap = getTypeMap();
		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "ICP developed for CNCP cases":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;
			}

		case "ICP developed for CICL cases":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							individualCarePlanAs, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;
			}

		}
		return 0;
	}

	private float restorationDetailsAggregationCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails cciDetails,
			List<RestorationDetails> restorationDetails,
			List<LegallyFreeForAdoption> legallyFreeForAdoptionsCncp,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValueCciWise) {
		// typeMap = getTypeMap();
		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "CNCP Cases released from CCI":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;
			}
			break;

		case "CICL Cases released from CCI":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getCciDetails() != null
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == cciDetails
									.getCciId().intValue()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregationCCIWise(
							iusTotalIndicatorUnitSubgroup, cciDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValueCciWise)) * 100;

				}
				childIds = joiner.toString();
				dataValueCciWise.setChildIds(childIds.equals("") ? null
						: childIds);
				return i;
			}

		}
		return 0;
	}

	private float educationStatusOfChildreeCICL(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<CICLSocialInvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {

		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Third Gender":

			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third Gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Illiterate":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetail.getEducation())
								.getName().equalsIgnoreCase("Illiterate")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "<V":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied up to V Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "V-VIII":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above V Standard but below VIII Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "VIII-X":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above VIII Standard but below X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">X":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialInvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied above X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCICL(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}
		return 0;
	}

	private float educationStatusOfChildreeCNCP(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<SocialinvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {

		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Third Gender":

			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third Gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Illiterate":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetail.getEducation())
								.getName().equalsIgnoreCase("Illiterate")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "<V":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied up to V Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "V-VIII":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above V Standard but below VIII Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "VIII-X":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase(
										"Studied above VIII Standard but below X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">X":
			i = 0;
			joiner = new StringJoiner(",");
			for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetail.getEducation())
								.getName()
								.equalsIgnoreCase("Studied above X Standard")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / educationStatusOfChildreeCNCP(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						socialinvestigationReports, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		return 0;
	}

	private float childAbove18InCCIAggregationCNCP(
			IndicatorUnitSubgroup ius,
			CCIDetails cciDetails,
			List<ChildRegistrationDetails> childRegistrationDetails,
			List<CICLSocialBackgroundReport> backgroundReports,
			Map<Integer, ValueObject> typeMap,
			Map<String, SocialinvestigationReport> socialinvestigationReportsMap,
			DataValueCciWise dataValueCciWise) {

		backgroundReports = backgroundReports
				.stream()
				.filter(a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getEntryDate().getTime() / (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);

					return (a.getChildId().getCciDetails() != null)
							&& (a.getChildId().getCciDetails().getCciId()
									.intValue() == cciDetails.getCciId()
									.intValue())
							&& (diffInDays + Integer.parseInt(typeMap.get(
									a.getChildId().getAge()).getName())) > 18;
				}).collect(Collectors.toList());

		childRegistrationDetails = childRegistrationDetails
				.stream()
				.filter(a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getDateOfProduction().getTime()
							/ (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);

					return (a.getChildId().getCciDetails() != null)
							&& (a.getChildId().getCciDetails().getCciId()
									.intValue() == cciDetails.getCciId()
									.intValue())
							&& (diffInDays + Integer.parseInt(typeMap.get(
									a.getChildAge()).getName())) > 18;
				}).collect(Collectors.toList());

		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = childRegistrationDetails.size();
			childIds = childRegistrationDetails.stream()
					.map(a -> a.getChildId().getChildId())
					.collect(Collectors.joining(","));
			if (!childIds.equals("")) {
				dataValueCciWise.setChildIds(childIds);
			}
			return i;
		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap.get(registrationDetail.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}

			/*
			 * if (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup.setIndicatorName(ius.
			 * getIndicatorName());
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap,dataValueCciWise)) * 100; }
			 */
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap.get(registrationDetail.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			/*
			 * if (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup.setIndicatorName(ius.
			 * getIndicatorName());
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap,dataValueCciWise)) * 100; }
			 */
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap.get(registrationDetail.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}

			/*
			 * if (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup.setIndicatorName(ius.
			 * getIndicatorName());
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap,dataValueCciWise)) * 100; }
			 */
			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

			/*
			 * case "SC": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("SC")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("SC")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i;
			 * 
			 * case "ST": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("ST")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("ST")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i;
			 * 
			 * case "OBC": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("OBC")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("OBC")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i; case
			 * "EBC": i = 0; for (ChildRegistrationDetails registrationDetail :
			 * childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("EBC")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("EBC")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i; case
			 * "General": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("General")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("General")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i; case
			 * "not specified": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()).getName().
			 * equalsIgnoreCase("not specified")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("not specified")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i;
			 */
		}
		return 0;
	}

	private float childAbove18InCCIAggregationCICL(
			IndicatorUnitSubgroup ius,
			CCIDetails cciDetails,
			List<ChildRegistrationDetails> childRegistrationDetails,
			List<CICLSocialBackgroundReport> backgroundReports,
			Map<Integer, ValueObject> typeMap,
			Map<String, SocialinvestigationReport> socialinvestigationReportsMap,
			DataValueCciWise dataValueCciWise) {

		backgroundReports = backgroundReports
				.stream()
				.filter(a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getEntryDate().getTime() / (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);

					return (a.getChildId().getCciDetails() != null)
							&& (a.getChildId().getCciDetails().getCciId()
									.intValue() == cciDetails.getCciId()
									.intValue())
							&& (diffInDays + Integer.parseInt(typeMap.get(
									a.getChildId().getAge()).getName())) > 18;
				}).collect(Collectors.toList());

		childRegistrationDetails = childRegistrationDetails
				.stream()
				.filter(a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getDateOfProduction().getTime()
							/ (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);

					return (a.getChildId().getCciDetails() != null)
							&& (a.getChildId().getCciDetails().getCciId()
									.intValue() == cciDetails.getCciId()
									.intValue())
							&& (diffInDays + Integer.parseInt(typeMap.get(
									a.getChildAge()).getName())) > 18;
				}).collect(Collectors.toList());

		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = backgroundReports.size();
			childIds = backgroundReports.stream()
					.map(a -> a.getChildId().getChildId())
					.collect(Collectors.joining(","));
			if (!childIds.equals("")) {
				dataValueCciWise.setChildIds(childIds);
			}
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}

			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}

			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}

			childIds = joiner.toString();
			dataValueCciWise.setChildIds(childIds.equals("") ? null : childIds);
			return i;

			/*
			 * case "SC": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("SC")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("SC")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i;
			 * 
			 * case "ST": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("ST")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("ST")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i;
			 * 
			 * case "OBC": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("OBC")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("OBC")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i; case
			 * "EBC": i = 0; for (ChildRegistrationDetails registrationDetail :
			 * childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("EBC")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("EBC")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i; case
			 * "General": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()
			 * ).getName().equalsIgnoreCase ("General")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("General")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i; case
			 * "not specified": i = 0; for (ChildRegistrationDetails
			 * registrationDetail : childRegistrationDetails) { if
			 * (typeMap.get(socialinvestigationReportsMap
			 * .get(registrationDetail.
			 * getChildId().getChildId()).getChildCast()).getName().
			 * equalsIgnoreCase("not specified")) { i++; } } for
			 * (CICLSocialBackgroundReport backgroundReport : backgroundReports)
			 * { if
			 * (typeMap.get(backgroundReport.getCasteHinduType()).getName().
			 * equalsIgnoreCase("not specified")) { i++; } } if
			 * (ius.getUnit().equalsIgnoreCase("percent")) {
			 * IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new
			 * IndicatorUnitSubgroup();
			 * iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
			 * iusTotalIndicatorUnitSubgroup
			 * .setIndicatorName(ius.getIndicatorName() );
			 * iusTotalIndicatorUnitSubgroup.setSubGroup("Total"); i = (i /
			 * childAbove18InCCIAggregation(iusTotalIndicatorUnitSubgroup,
			 * cciDetails, childRegistrationDetails,backgroundReports,
			 * typeMap,socialinvestigationReportsMap)) * 100; } return i;
			 */
		}
		return 0;
	}

	private float childAbove18InCCIAggregation(
			IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<ChildRegistrationDetails> childRegistrationDetails,
			List<CICLSocialBackgroundReport> backgroundReports,
			Map<Integer, ValueObject> typeMap,
			Map<String, SocialinvestigationReport> socialinvestigationReportsMap,
			DataValue dataValue) {

		childRegistrationDetails = childRegistrationDetails
				.stream()
				.filter(a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getDateOfProduction().getTime()
							/ (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);
					System.out.println("backgroundReports " + diffInDays + " "
							+ a.getChildId().getAge() + " "
							+ a.getChildId().getChildDistrict().getAreaId()
							+ " " + areaDetails.getAreaId());
					return a.getChildId().getChildDistrict().getAreaId()
							.intValue() == areaDetails.getAreaId().intValue()
							&& (diffInDays + a.getChildId().getAge()) > 18;
				}).collect(Collectors.toList());
		backgroundReports = backgroundReports
				.stream()
				.filter(a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getEntryDate().getTime() / (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);
					System.out.println("backgroundReports " + diffInDays);
					return a.getChildId().getChildDistrict().getAreaId() == areaDetails
							.getAreaId()
							&& diffInDays + a.getChildId().getAge() > 18;
				}).collect(Collectors.toList());

		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = childRegistrationDetails.size() + backgroundReports.size();
			return i;
		case "Total CNCP Childs":
			i = backgroundReports.size();
			return i;
		case "Total CICL Childs":
			i = childRegistrationDetails.size();
			return i;
		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap.get(registrationDetail.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap.get(registrationDetail.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap.get(registrationDetail.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getChildId().getChildSex())
						.getName().equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "SC":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap
						.get(socialinvestigationReportsMap.get(
								registrationDetail.getChildId().getChildId())
								.getChildCast()).getName()
						.equalsIgnoreCase("SC")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getCasteHinduType()).getName()
						.equalsIgnoreCase("SC")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "ST":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap
						.get(socialinvestigationReportsMap.get(
								registrationDetail.getChildId().getChildId())
								.getChildCast()).getName()
						.equalsIgnoreCase("ST")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getCasteHinduType()).getName()
						.equalsIgnoreCase("ST")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "OBC":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap
						.get(socialinvestigationReportsMap.get(
								registrationDetail.getChildId().getChildId())
								.getChildCast()).getName()
						.equalsIgnoreCase("OBC")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getCasteHinduType()).getName()
						.equalsIgnoreCase("OBC")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "EBC":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap
						.get(socialinvestigationReportsMap.get(
								registrationDetail.getChildId().getChildId())
								.getChildCast()).getName()
						.equalsIgnoreCase("EBC")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getCasteHinduType()).getName()
						.equalsIgnoreCase("EBC")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "General":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap
						.get(socialinvestigationReportsMap.get(
								registrationDetail.getChildId().getChildId())
								.getChildCast()).getName()
						.equalsIgnoreCase("General")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getCasteHinduType()).getName()
						.equalsIgnoreCase("General")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		case "not specified":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (typeMap
						.get(socialinvestigationReportsMap.get(
								registrationDetail.getChildId().getChildId())
								.getChildCast()).getName()
						.equalsIgnoreCase("not specified")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
				if (typeMap.get(backgroundReport.getCasteHinduType()).getName()
						.equalsIgnoreCase("not specified")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childAbove18InCCIAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, backgroundReports, typeMap,
						socialinvestigationReportsMap, dataValue)) * 100;
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}
		return 0;
	}

	private float childRegistrationDetailsAggregation(
			IndicatorUnitSubgroup ius, AreaDetails areaDetails,
			List<ChildRegistrationDetails> childRegistrationDetails,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// Total, Boys, Girls, Third Gender, <6 yrs, 6-12,12-16, >16, Hindu,
		// Muslim, Christian, Other, SC, ST, OBC, EBC, General, not specified
		// typeMap = getTypeMap();
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "<6 yrs":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 6) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "6-12":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 12) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "12-16":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) <= 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">16":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildRegistrationDetails registrationDetail : childRegistrationDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) > 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childRegistrationDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		return (float) 0.0;
	}

	private float ciclSocialBackgroundReportAggregation(
			IndicatorUnitSubgroup ius, AreaDetails areaDetails,
			List<CICLSocialBackgroundReport> backgroundReports,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {

		// typeMap = getTypeMap();
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {
		case "Children In Conflict With the Law cases registered":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport backgroundReport : backgroundReports)
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "<6 yrs":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) < 6) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "6-12":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) >= 6
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) < 12) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "12-16":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) >= 12
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) <= 16) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case ">16":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) > 16) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Hindu":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getReligion())
									.getName().equalsIgnoreCase("Hindu")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Muslim":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getReligion())
									.getName().equalsIgnoreCase("Muslim")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Christian":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getReligion())
									.getName().equalsIgnoreCase("Christian")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Other":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getReligion())
									.getName().equalsIgnoreCase("Other")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "SC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail
									.getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail.getCasteHinduType())
									.getName().equalsIgnoreCase("SC")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "ST":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail
									.getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail.getCasteHinduType())
									.getName().equalsIgnoreCase("ST")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "OBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail
									.getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail.getCasteHinduType())
									.getName().equalsIgnoreCase("OBC")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "EBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail
									.getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail.getCasteHinduType())
									.getName().equalsIgnoreCase("EBC")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "General":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail
									.getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail.getCasteHinduType())
									.getName().equalsIgnoreCase("General")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "not specified":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail
									.getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail.getCasteHinduType())
									.getName()
									.equalsIgnoreCase("Not Specified")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
			break;
		case "Numbers of Children (CICL) differently able":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.isDifferentlyAbled()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport backgroundReport : backgroundReports)
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.isDifferentlyAbled()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& registrationDetail.isDifferentlyAbled()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& registrationDetail.isDifferentlyAbled()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Mentally retarded":
				i = 0;
				joiner = new StringJoiner(",");
				backgroundReports = backgroundReports
						.stream()
						.filter(p -> p.getDifferentlyAbledType() != null
								&& !p.getDifferentlyAbledType().equals(""))
						.collect(Collectors.toList());
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					String arr[] = registrationDetail.getDifferentlyAbledType()
							.split(",");
					for (String differentlyAbledTypeId : arr) {
						if (registrationDetail.getChildId().getChildDistrict()
								.getAreaId() == areaDetails.getAreaId()
								&& Integer.parseInt(differentlyAbledTypeId) == 384) {
							i++;
							joiner.add(registrationDetail.getChildId()
									.getChildId());
						}
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Mentally ill":
				i = 0;
				joiner = new StringJoiner(",");
				backgroundReports = backgroundReports
						.stream()
						.filter(p -> p.getDifferentlyAbledType() != null
								&& !p.getDifferentlyAbledType().equals(""))
						.collect(Collectors.toList());
				for (CICLSocialBackgroundReport registrationDetail : backgroundReports) {
					String arr[] = registrationDetail.getDifferentlyAbledType()
							.split(",");
					for (String differentlyAbledTypeId : arr) {
						if (registrationDetail.getChildId().getChildDistrict()
								.getAreaId() == areaDetails.getAreaId()
								&& Integer.parseInt(differentlyAbledTypeId) == 45) {
							i++;
							joiner.add(registrationDetail.getChildId()
									.getChildId());
						}
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialBackgroundReportAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							backgroundReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		}
		return 0;
	}

	private float restorationDetailsAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<RestorationDetails> restorationDetails,
			List<LegallyFreeForAdoption> legallyFreeForAdoptionsCncp,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// typeMap = getTypeMap();
		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "Children Need of Care and Protection cases disposed":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());

					}

				}
				for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptionsCncp) {
					if (legallyFreeForAdoption.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& legallyFreeForAdoption.getChildId()
									.getProgramType() == 0) {
						i++;
						joiner.add(legallyFreeForAdoption.getChildId()
								.getChildId());

					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		case "Children In Conflict With the Law cases disposed":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		case "CNCP Cases released from CCI":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				for (LegallyFreeForAdoption backgroundReport : legallyFreeForAdoptionsCncp) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
			break;

		case "CICL Cases released from CCI":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}

				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (RestorationDetails backgroundReport : restorationDetails) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.getChildId().getCciDetails() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / restorationDetailsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							restorationDetails, legallyFreeForAdoptionsCncp,
							typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		}
		return 0;
	}

	private float childPlacedInFitInstitutionsAggregation(
			IndicatorUnitSubgroup ius, AreaDetails areaDetails,
			List<ChildPlacedInFitInstitution> childPlacedInFitInstitutions,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// Total, Boys, Girls, Third Gender, <6 yrs, 6-12,12-16, >16, Hindu,
		// Muslim, Christian, Other, SC, ST, OBC, EBC, General, not specified
		// typeMap = getTypeMap();
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "<6 yrs":

			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 6) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "6-12":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 12) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "12-16":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) <= 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">16":
			i = 0;
			joiner = new StringJoiner(",");
			for (ChildPlacedInFitInstitution registrationDetail : childPlacedInFitInstitutions) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) > 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / childPlacedInFitInstitutionsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childPlacedInFitInstitutions, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		return 0;

	}

	private float ciclSupervisionOrdersAggregation(
			IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<CICLChildCareInstitutionPendingInquiry> ciclChildCareInstitutionPendingInquiry,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {

		Map<String, CICLSocialBackgroundReport> backgroundReportsMaps = new HashMap<String, CICLSocialBackgroundReport>();
		List<CICLSocialBackgroundReport> backgroundReports = backgroundReportRepository
				.findAll();
		for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
			backgroundReportsMaps.put(backgroundReport.getChildId()
					.getChildId(), backgroundReport);
		}
		Map<String, CICLChildCareInstitutionPendingInquiry> ciclChildCareInstitutionPendingInquiryMap = new HashMap<>();
		List<CICLChildCareInstitutionPendingInquiry> li = new ArrayList<>();
		for (CICLChildCareInstitutionPendingInquiry socialinvestigationReport : ciclChildCareInstitutionPendingInquiry) {
			if (ciclChildCareInstitutionPendingInquiryMap
					.get(socialinvestigationReport.getChildId().getChildId()) == null) {
				ciclChildCareInstitutionPendingInquiryMap.put(
						socialinvestigationReport.getChildId().getChildId(),
						socialinvestigationReport);
				li.add(socialinvestigationReport);
			}
		}
		ciclChildCareInstitutionPendingInquiry = li;
		// typeMap = getTypeMap();
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry backgroundReport : ciclChildCareInstitutionPendingInquiry) {
				if (backgroundReport.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry backgroundReport : ciclChildCareInstitutionPendingInquiry) {
				if (backgroundReport.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReport.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "<6 yrs":

			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 6) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "6-12":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 12) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "12-16":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) <= 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">16":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) > 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Hindu":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId()).getReligion())
								.getName().equalsIgnoreCase("Hindu")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Muslim":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId()).getReligion())
								.getName().equalsIgnoreCase("Muslim")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Christian":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId()).getReligion())
								.getName().equalsIgnoreCase("Christian")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Other":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId()).getReligion())
								.getName().equalsIgnoreCase("Other")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "SC":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(backgroundReportsMaps.get(
								registrationDetail.getChildId().getChildId())
								.getCasteHinduType()) != null
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId())
										.getCasteHinduType()).getName()
								.equalsIgnoreCase("SC")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "ST":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(backgroundReportsMaps.get(
								registrationDetail.getChildId().getChildId())
								.getCasteHinduType()) != null
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId())
										.getCasteHinduType()).getName()
								.equalsIgnoreCase("ST")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "OBC":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(backgroundReportsMaps.get(
								registrationDetail.getChildId().getChildId())
								.getCasteHinduType()) != null
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId())
										.getCasteHinduType()).getName()
								.equalsIgnoreCase("OBC")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "EBC":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(backgroundReportsMaps.get(
								registrationDetail.getChildId().getChildId())
								.getCasteHinduType()) != null
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId())
										.getCasteHinduType()).getName()
								.equalsIgnoreCase("EBC")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "General":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(backgroundReportsMaps.get(
								registrationDetail.getChildId().getChildId())
								.getCasteHinduType()) != null
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId())
										.getCasteHinduType()).getName()
								.equalsIgnoreCase("General")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "not specified":
			i = 0;
			joiner = new StringJoiner(",");
			for (CICLChildCareInstitutionPendingInquiry registrationDetail : ciclChildCareInstitutionPendingInquiry) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(backgroundReportsMaps.get(
								registrationDetail.getChildId().getChildId())
								.getCasteHinduType()) != null
						&& typeMap
								.get(backgroundReportsMaps.get(
										registrationDetail.getChildId()
												.getChildId())
										.getCasteHinduType()).getName()
								.equalsIgnoreCase("Not Specified")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclSupervisionOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclChildCareInstitutionPendingInquiry, typeMap,
						dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}
		return 0;
	}

	private float individualCarePlanAsAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<IndividualCarePlanA> individualCarePlanAs,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// typeMap = getTypeMap();
		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "ICP developed for CNCP cases":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							individualCarePlanAs, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							individualCarePlanAs, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							individualCarePlanAs, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		case "ICP developed for CICL cases":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							individualCarePlanAs, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							individualCarePlanAs, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (IndividualCarePlanA backgroundReport : individualCarePlanAs) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 1
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / individualCarePlanAsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							individualCarePlanAs, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		}
		return 0;
	}

	private float socialinvestigationReportsAggregation(
			IndicatorUnitSubgroup ius, AreaDetails areaDetails,
			List<SocialinvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// typeMap = getTypeMap();
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {
		case "SIR prepared for CNCP cases":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
			}

		case "Numbers of Children (CNCP) differently able":

			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.isChildDifferentlyAbled()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.isChildDifferentlyAbled()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");

				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& backgroundReport.isChildDifferentlyAbled()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& backgroundReport.isChildDifferentlyAbled()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Mentally retarded":
				i = 0;
				joiner = new StringJoiner(",");
				socialinvestigationReports = socialinvestigationReports
						.stream()
						.filter(p -> p.getDifferentlyAbledType() != null
								&& !p.getDifferentlyAbledType().equals(""))
						.collect(Collectors.toList());
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					String arr[] = backgroundReport.getDifferentlyAbledType()
							.split(",");
					for (String differentlyAbledTypeId : arr) {
						if (backgroundReport.getChildId().getChildDistrict()
								.getAreaId() == areaDetails.getAreaId()
								&& Integer.parseInt(differentlyAbledTypeId) == 384
								&& backgroundReport.isChildDifferentlyAbled()) {
							i++;
							joiner.add(backgroundReport.getChildId()
									.getChildId());
						}
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Mentally ill":
				i = 0;
				joiner = new StringJoiner(",");
				socialinvestigationReports = socialinvestigationReports
						.stream()
						.filter(p -> p.getDifferentlyAbledType() != null
								&& !p.getDifferentlyAbledType().equals(""))
						.collect(Collectors.toList());
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					String arr[] = backgroundReport.getDifferentlyAbledType()
							.split(",");
					for (String differentlyAbledTypeId : arr) {
						if (backgroundReport.getChildId().getChildDistrict()
								.getAreaId() == areaDetails.getAreaId()
								&& Integer.parseInt(differentlyAbledTypeId) == 45
								&& backgroundReport.isChildDifferentlyAbled()) {
							i++;
							joiner.add(backgroundReport.getChildId()
									.getChildId());
						}
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		case "Education status of Children at the time of registration (CNCP)":

		case "Children (CNCP) Sexual  Abused":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getSexualAbuse() != null
							&& backgroundReport.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.getSexualAbuse() != null
							&& backgroundReport.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "<6 yrs":

				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) < 6
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "6-12":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) >= 6
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) < 12
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "12-16":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) >= 12
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) <= 16
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case ">16":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) > 16
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Hindu":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Hindu")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Muslim":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Muslim")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Christian":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Christian")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Other":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Other")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "SC":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("SC")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "ST":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("ST")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "OBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("OBC")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "EBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("EBC")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "General":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("General")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "not specified":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport registrationDetail : socialinvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName()
									.equalsIgnoreCase("Not Specified")
							&& registrationDetail.getSexualAbuse() != null
							&& registrationDetail.getSexualAbuse().trim() != "") {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / socialinvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		}

		return 0;
	}

	private float ciclSocialInvestigationReportsAggregation(
			IndicatorUnitSubgroup ius, AreaDetails areaDetails,
			List<CICLSocialInvestigationReport> ciclSocialInvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {

		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {
		case "SIR prepared for CICL cases":

			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport backgroundReport : ciclSocialInvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport backgroundReport : ciclSocialInvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport backgroundReport : ciclSocialInvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

					if (ius.getUnit().equalsIgnoreCase("percent")) {
						IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
						iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
								.isHighIsGood());
						iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
								.getIndicatorName());
						iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
						i = (i / ciclSocialInvestigationReportsAggregation(
								iusTotalIndicatorUnitSubgroup, areaDetails,
								ciclSocialInvestigationReports, typeMap,
								dataValue)) * 100;

					}
				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport backgroundReport : ciclSocialInvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		case "Children (CICL) Abused":
			switch (ius.getSubGroup()) {

			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport backgroundReport : ciclSocialInvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport backgroundReport : ciclSocialInvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& backgroundReport.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "<6 yrs":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) < 6
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "6-12":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) >= 6
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) < 12
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "12-16":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) >= 12
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) <= 16
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case ">16":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& Integer.parseInt(typeMap.get(
									registrationDetail.getChildId().getAge())
									.getName()) > 16
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Hindu":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Hindu")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Muslim":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Muslim")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Christian":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Christian")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Other":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildReligion())
									.getName().equalsIgnoreCase("Other")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "SC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("SC")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "ST":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("ST")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "OBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("OBC")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "EBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("EBC")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "General":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName().equalsIgnoreCase("General")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "not specified":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLSocialInvestigationReport registrationDetail : ciclSocialInvestigationReports) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap.get(registrationDetail.getChildCast()) != null
							&& typeMap.get(registrationDetail.getChildCast())
									.getName()
									.equalsIgnoreCase("Not Specified")
							&& registrationDetail.isChildSubjectedOfAbuse()) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / ciclSocialInvestigationReportsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							ciclSocialInvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		}
		return 0;
	}

	private float caseMoniteringSheetsAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<CICLCaseMoniteringSheet> caseMoniteringSheets,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		Map<String, CICLSocialBackgroundReport> backgroundReportsMaps = new HashMap<String, CICLSocialBackgroundReport>();
		List<CICLSocialBackgroundReport> backgroundReports = backgroundReportRepository
				.findAll();
		for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
			backgroundReportsMaps.put(backgroundReport.getChildId()
					.getChildId(), backgroundReport);
		}

		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {
		case "Children under Petty offense case(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							/*
							 * && typeMap .get(registrationDetail.getChildId()
							 * .getChildSex()).getName()
							 * .equalsIgnoreCase("Total")
							 */
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Third Gender":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third Gender")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Illiterate":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Illiterate")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "<V":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Upto primary ( I - V)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "V-VIII":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails())
									.getName()
									.equalsIgnoreCase("Middle Level (VI - VII)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "VIII-X":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails())
									.getName()
									.equalsIgnoreCase(
											"Upper Primary ( VIII - X)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case ">X":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Higher Secondary")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Hindu":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Hindu")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Muslim":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Muslim")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Christian":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Christian")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Other":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Other")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "SC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("sc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "ST":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("st")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "OBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("obc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "EBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("ebc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "General":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("General")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "not specified":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("Not Specified")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Employed details":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& employedChildList.contains(registrationDetail
									.getChildId().getChildId())) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			}
		case "Children under Serious Offence case(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Third Gender":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third Gender")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Illiterate":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Illiterate")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "<V":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()

							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Upto primary ( I - V)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "V-VIII":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails())
									.getName()
									.equalsIgnoreCase("Middle Level (VI - VII)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "VIII-X":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails())
									.getName()
									.equalsIgnoreCase(
											"Upper Primary ( VIII - X)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case ">X":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Higher Secondary")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Hindu":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Hindu")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Muslim":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Muslim")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Christian":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Christian")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Other":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Other")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "SC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("sc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "ST":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("st")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "OBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("obc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "EBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("ebc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "General":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("General")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "not specified":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("Not Specified")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Employed details":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& employedChildList.contains(registrationDetail
									.getChildId().getChildId())) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			}

		case "Children under Henious offence case(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Third Gender":

				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third Gender")
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Illiterate":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Illiterate")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "<V":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Upto primary ( I - V)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "V-VIII":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Serious")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails())
									.getName()
									.equalsIgnoreCase("Middle Level (VI - VII)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "VIII-X":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails())
									.getName()
									.equalsIgnoreCase(
											"Upper Primary ( VIII - X)")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case ">X":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getEducationDetails()).getName()
									.equalsIgnoreCase("Higher Secondary")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Hindu":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Hindu")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Muslim":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Muslim")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Christian":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Christian")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Other":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getReligion()).getName()
									.equalsIgnoreCase("Other")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "SC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("sc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "ST":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("st")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "OBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("obc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "EBC":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("ebc")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "General":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("General")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "not specified":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Petty")
							&& typeMap.get(backgroundReportsMaps.get(
									registrationDetail.getChildId()
											.getChildId()).getCasteHinduType()) != null
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& typeMap
									.get(backgroundReportsMaps.get(
											registrationDetail.getChildId()
													.getChildId())
											.getCasteHinduType()).getName()
									.equalsIgnoreCase("Not Specified")) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			case "Employed details":
				i = 0;
				joiner = new StringJoiner(",");
				for (CICLCaseMoniteringSheet registrationDetail : caseMoniteringSheets) {
					if (registrationDetail.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& typeMap
									.get(registrationDetail
											.getNatureOfOffence()).getName()
									.equalsIgnoreCase("Heinous")
							&& employedChildList.contains(registrationDetail
									.getChildId().getChildId())) {
						i++;
						joiner.add(registrationDetail.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / caseMoniteringSheetsAggregation(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							caseMoniteringSheets, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			}

		}
		return 0;
	}

	private float fosterCareDetailsAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails, List<FosterCareDetails> fosterCareDetails,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails backgroundReport : fosterCareDetails) {
				if (backgroundReport.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& backgroundReport.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails backgroundReport : fosterCareDetails) {
				if (backgroundReport.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReport.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")
						&& backgroundReport.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails registrationDetail : fosterCareDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")
						&& registrationDetail.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails registrationDetail : fosterCareDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")
						&& registrationDetail.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "<6 yrs":

			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails registrationDetail : fosterCareDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 6
						&& registrationDetail.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "6-12":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails registrationDetail : fosterCareDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 12
						&& registrationDetail.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "12-16":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails registrationDetail : fosterCareDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) <= 16
						&& registrationDetail.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">16":
			i = 0;
			joiner = new StringJoiner(",");
			for (FosterCareDetails registrationDetail : fosterCareDetails) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) > 16
						&& registrationDetail.getChildId().getProgramType() == 0) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / fosterCareDetailsAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						fosterCareDetails, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}
		return 0;
	}

	private float sponsorshipOrdersAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails, List<SponsorshipOrder> sponsorshipOrders,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// typeMap = getTypeMap();
		float i = 0;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getSubGroup()) {
		case "Total":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder backgroundReport : sponsorshipOrders) {
				if (backgroundReport.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder backgroundReport : sponsorshipOrders) {
				if (backgroundReport.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(backgroundReport.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					joiner.add(backgroundReport.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Girls":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder registrationDetail : sponsorshipOrders) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Girl")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder registrationDetail : sponsorshipOrders) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap
								.get(registrationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "<6 yrs":

			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder registrationDetail : sponsorshipOrders) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 6) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "6-12":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder registrationDetail : sponsorshipOrders) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) < 12) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "12-16":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder registrationDetail : sponsorshipOrders) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) <= 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case ">16":
			i = 0;
			joiner = new StringJoiner(",");
			for (SponsorshipOrder registrationDetail : sponsorshipOrders) {
				if (registrationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& Integer.parseInt(typeMap.get(
								registrationDetail.getChildId().getAge())
								.getName()) > 16) {
					i++;
					joiner.add(registrationDetail.getChildId().getChildId());
				}

			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / sponsorshipOrdersAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						sponsorshipOrders, typeMap, dataValue)) * 100;

			}
			childIds = joiner.toString();
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}
		return 0;
	}

	private float cncpCasesPendencyAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<ChildRegistrationDetails> childRegistrationDetails,
			List<ChildPlacedInFitInstitution> childPlacedInFitInstitutions,
			List<RestorationDetails> restorationDetails,
			List<LegallyFreeForAdoption> legallyFreeForAdoptions,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {

		float i = 0;
		String childIds = null;
		List<String> ids = null;
		int childRegistrationDetailsCount, childPlacedInFitInstitutionsCount, restorationDetailsCount, legallyFreeForAdoptionsCounts;
		switch (ius.getSubGroup()) {
		case "Total": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "Boys": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildSex()) != null
						&& typeMap.get(registrationDetails.getChildSex())
								.getName().equalsIgnoreCase("Boy")) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(childPlacedInFitInstitution.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(restorationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(legallyFreeForAdoption.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Boy")) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / cncpCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, childPlacedInFitInstitutions,
						restorationDetails, legallyFreeForAdoptions, typeMap,
						dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "Girls": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildSex()) != null
						&& typeMap.get(registrationDetails.getChildSex())
								.getName().equalsIgnoreCase("girl")) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(childPlacedInFitInstitution.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("girl")) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(restorationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("girl")) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(legallyFreeForAdoption.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("girl")) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / cncpCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, childPlacedInFitInstitutions,
						restorationDetails, legallyFreeForAdoptions, typeMap,
						dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "Third Gender": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildSex()) != null
						&& typeMap.get(registrationDetails.getChildSex())
								.getName().equalsIgnoreCase("Third gender")) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(childPlacedInFitInstitution.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(restorationDetail.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getChildSex()) != null
						&& typeMap
								.get(legallyFreeForAdoption.getChildId()
										.getChildSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / cncpCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, childPlacedInFitInstitutions,
						restorationDetails, legallyFreeForAdoptions, typeMap,
						dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "<6 yrs": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getChildAge()).getName()) < 6) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								childPlacedInFitInstitution.getChildId()
										.getAge()).getName()) < 6) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId().getAge()) != null
						&& Integer.parseInt(typeMap.get(
								restorationDetail.getChildId().getAge())
								.getName()) < 6) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								legallyFreeForAdoption.getChildId().getAge())
								.getName()) < 6) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / cncpCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, childPlacedInFitInstitutions,
						restorationDetails, legallyFreeForAdoptions, typeMap,
						dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "6-12": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getChildAge()).getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getChildAge()).getName()) < 12) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								childPlacedInFitInstitution.getChildId()
										.getAge()).getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								childPlacedInFitInstitution.getChildId()
										.getAge()).getName()) < 12) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId().getAge()) != null
						&& Integer.parseInt(typeMap.get(
								restorationDetail.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								restorationDetail.getChildId().getAge())
								.getName()) < 12) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								legallyFreeForAdoption.getChildId().getAge())
								.getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								legallyFreeForAdoption.getChildId().getAge())
								.getName()) < 12) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / cncpCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, childPlacedInFitInstitutions,
						restorationDetails, legallyFreeForAdoptions, typeMap,
						dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "12-16": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getChildAge()).getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getChildAge()).getName()) <= 16) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								childPlacedInFitInstitution.getChildId()
										.getAge()).getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								childPlacedInFitInstitution.getChildId()
										.getAge()).getName()) <= 16) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId().getAge()) != null
						&& Integer.parseInt(typeMap.get(
								restorationDetail.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								restorationDetail.getChildId().getAge())
								.getName()) <= 16) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								legallyFreeForAdoption.getChildId().getAge())
								.getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								legallyFreeForAdoption.getChildId().getAge())
								.getName()) <= 16) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case ">16": {
			ids = new ArrayList<String>();
			childRegistrationDetailsCount = 0;
			childPlacedInFitInstitutionsCount = 0;
			restorationDetailsCount = 0;
			legallyFreeForAdoptionsCounts = 0;

			for (ChildRegistrationDetails registrationDetails : childRegistrationDetails) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getChildAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getChildAge()).getName()) > 16) {
					childRegistrationDetailsCount++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			for (ChildPlacedInFitInstitution childPlacedInFitInstitution : childPlacedInFitInstitutions) {
				if (childPlacedInFitInstitution.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(childPlacedInFitInstitution.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								childPlacedInFitInstitution.getChildId()
										.getAge()).getName()) > 16) {
					childPlacedInFitInstitutionsCount++;
					ids.remove(childPlacedInFitInstitution.getChildId()
							.getChildId());
				}
			}

			for (RestorationDetails restorationDetail : restorationDetails) {
				if (restorationDetail.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(restorationDetail.getChildId().getAge()) != null
						&& Integer.parseInt(typeMap.get(
								restorationDetail.getChildId().getAge())
								.getName()) > 16) {
					restorationDetailsCount++;
					ids.remove(restorationDetail.getChildId().getChildId());

				}
			}

			for (LegallyFreeForAdoption legallyFreeForAdoption : legallyFreeForAdoptions) {
				if (legallyFreeForAdoption.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(legallyFreeForAdoption.getChildId()
								.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								legallyFreeForAdoption.getChildId().getAge())
								.getName()) > 16) {
					legallyFreeForAdoptionsCounts++;
					ids.remove(legallyFreeForAdoption.getChildId().getChildId());
				}

			}

			i = childRegistrationDetailsCount
					- (childPlacedInFitInstitutionsCount
							+ restorationDetailsCount + legallyFreeForAdoptionsCounts);
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / cncpCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						childRegistrationDetails, childPlacedInFitInstitutions,
						restorationDetails, legallyFreeForAdoptions, typeMap,
						dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}
		}
		return i;

	}

	private float ciclCasesPendencyAggregation(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<CICLSocialBackgroundReport> ciclSocialBackgroundReport,
			List<CICLChildCareInstitutionPendingInquiry> ciclSupervisionOrder,
			List<RestorationDetails> restorationDetails,
			List<LegallyFreeForAdoption> legallyFreeForAdoptions,
			Map<Integer, CICLSocialBackgroundReport> ciclSocialBackgroundMap,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {

		float i = 0;
		String childIds = null;
		List<String> ids = null;

		switch (ius.getSubGroup()) {
		case "Total":
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Boys": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getSex()) != null
						&& typeMap.get(registrationDetails.getSex()).getName()
								.equalsIgnoreCase("Boy")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "Girls":
			ids = new ArrayList<String>();
			i = 0;
			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getSex()) != null
						&& typeMap.get(registrationDetails.getSex()).getName()
								.equalsIgnoreCase("girl")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "Third Gender": {
			ids = new ArrayList<String>();
			i = 0;
			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getSex()) != null
						&& typeMap.get(registrationDetails.getSex()).getName()
								.equalsIgnoreCase("Third gender")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "<6 yrs":
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getAge()).getName()) < 6) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		case "6-12": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getAge()).getName()) >= 6
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getAge()).getName()) < 12) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "12-16": {
			ids = new ArrayList<String>();
			i = 0;
			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getAge()).getName()) >= 12
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getAge()).getName()) <= 16) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case ">16": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getAge()) != null
						&& Integer.parseInt(typeMap.get(
								registrationDetails.getAge()).getName()) > 16) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;
		}

		case "Hindu": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getReligion()) != null
						&& typeMap.get(registrationDetails.getReligion())
								.getName().equalsIgnoreCase("Hindu")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "Muslim": {
			ids = new ArrayList<String>();
			i = 0;
			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getReligion()) != null
						&& typeMap.get(registrationDetails.getReligion())
								.getName().equalsIgnoreCase("Muslim")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "Christian": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getReligion()) != null
						&& typeMap.get(registrationDetails.getReligion())
								.getName().equalsIgnoreCase("Christian")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "Other": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getReligion()) != null
						&& typeMap.get(registrationDetails.getReligion())
								.getName().equalsIgnoreCase("Other")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "SC": {
			ids = new ArrayList<String>();
			i = 0;
			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getCasteHinduType()) != null
						&& typeMap.get(registrationDetails.getCasteHinduType())
								.getName().equalsIgnoreCase("SC")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "ST": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getCasteHinduType()) != null
						&& typeMap.get(registrationDetails.getCasteHinduType())
								.getName().equalsIgnoreCase("ST")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}
			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "OBC": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getCasteHinduType()) != null
						&& typeMap.get(registrationDetails.getCasteHinduType())
								.getName().equalsIgnoreCase("OBC")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "EBC": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getCasteHinduType()) != null
						&& typeMap.get(registrationDetails.getCasteHinduType())
								.getName().equalsIgnoreCase("EBC")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "General": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getCasteHinduType()) != null
						&& typeMap.get(registrationDetails.getCasteHinduType())
								.getName().equalsIgnoreCase("General")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		case "not specified": {
			ids = new ArrayList<String>();
			i = 0;

			for (CICLSocialBackgroundReport registrationDetails : ciclSocialBackgroundReport) {
				if (registrationDetails.getChildId().getChildDistrict()
						.getAreaId() == areaDetails.getAreaId()
						&& typeMap.get(registrationDetails.getCasteHinduType()) != null
						&& typeMap.get(registrationDetails.getCasteHinduType())
								.getName().equalsIgnoreCase("Not Specified")) {
					i++;
					ids.add(registrationDetails.getChildId().getChildId());
				}
			}

			if (ius.getUnit().equalsIgnoreCase("percent")) {
				IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
				iusTotalIndicatorUnitSubgroup.setHighIsGood(ius.isHighIsGood());
				iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
						.getIndicatorName());
				iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
				i = (i / ciclCasesPendencyAggregation(
						iusTotalIndicatorUnitSubgroup, areaDetails,
						ciclSocialBackgroundReport, ciclSupervisionOrder,
						restorationDetails, legallyFreeForAdoptions,
						ciclSocialBackgroundMap, typeMap, dataValue)) * 100;

			}
			childIds = ids.stream().collect(Collectors.joining(","));
			dataValue.setChildIds(childIds.equals("") ? null : childIds);
			return i;

		}

		}
		return i;
	}

	private float childHealthStatusAggregationCNCP(IndicatorUnitSubgroup ius,
			AreaDetails areaDetails,
			List<SocialinvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValue dataValue) {
		// typeMap = getTypeMap();
		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "Children suffering from the respiratory disorder in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsRespiratoryDisorders() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from hearing impairment in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsHearingImpairment() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from eye diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsEyeDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from dental diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsDentalDisease() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from cardiac diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsCardiacDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from skin disease in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSkinDisease() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from sexually transmitted diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSTD() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSTD() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSTD() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsSTD() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from neurological disorders in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsNeurologicalDisorders() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children mental handicap in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsMentalHandicap() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children physical handicap in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsPhysicalHandicap() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from urinary tract infections in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsUrinaryTractInfections() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from any other disease(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsotherHealthStatusName() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsotherHealthStatusName() != null
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsotherHealthStatusName() != null
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getChildDistrict()
							.getAreaId() == areaDetails.getAreaId()
							&& backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getHsotherHealthStatusName() != null
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCP(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		}
		return 0;
	}

	private float childHealthStatusAggregationCNCPCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails areaDetails,
			List<SocialinvestigationReport> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValue) {

		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "Children suffering from the respiratory disorder in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsRespiratoryDisorders() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					System.out.println(backgroundReport.getChildId().getChildId());
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from hearing impairment in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsHearingImpairment() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from eye diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsEyeDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from dental diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsDentalDisease() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from cardiac diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsCardiacDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from skin disease in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSkinDisease() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from sexually transmitted diseases in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSTD() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSTD() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSTD() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsSTD() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from neurological disorders in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsNeurologicalDisorders() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children mental handicap in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsMentalHandicap() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children physical handicap in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsPhysicalHandicap() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from urinary tract infections in CCI(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsUrinaryTractInfections() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from any other disease(CNCP)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsotherHealthStatusName() != null) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsotherHealthStatusName() != null
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsotherHealthStatusName() != null
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (SocialinvestigationReport backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getCciDetails() != null && backgroundReport.getChildId().getProgramType() == 0
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHsotherHealthStatusName() != null
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCNCPCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		}
		return 0;
	}

	private float childHealthStatusAggregationCICLCCIWise(
			IndicatorUnitSubgroup ius, CCIDetails areaDetails,
			List<CaseHistoryCCI> socialinvestigationReports,
			Map<Integer, ValueObject> typeMap, DataValueCciWise dataValue) {

		float i;
		String childIds = null;
		StringJoiner joiner = null;
		switch (ius.getIndicatorName()) {

		case "Children suffering from the respiratory disorder in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getRespiratoryDisorders() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getRespiratoryDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from hearing impairment in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHearingImpairment() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getHearingImpairment() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from eye diseases in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getEyeDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getEyeDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from dental diseases in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getDentalDisease() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getDentalDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from cardiac diseases in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getCardiacDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getCardiacDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from skin disease in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getSkinDisease() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getSkinDisease() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from sexually transmitted diseases in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.getSexuallyTransmittedDiseases() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.getSexuallyTransmittedDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.getSexuallyTransmittedDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport
									.getSexuallyTransmittedDiseases() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from neurological disorders in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getNeurologicalDisorders() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getNeurologicalDisorders() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children mental handicap in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getMentalHandicap() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getMentalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children physical handicap in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getPhysicalHandicap() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getPhysicalHandicap() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from urinary tract infections in CCI(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getUrinaryTractInfections() == 95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getUrinaryTractInfections() == 95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}
		case "Children suffering from any other disease(CICL)":
			switch (ius.getSubGroup()) {
			case "Total":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getOtherHealthIssuesName() != null
							&& backgroundReport.getOtherHealthIssues()==95) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Boys":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getOtherHealthIssuesName() != null
									&& backgroundReport.getOtherHealthIssues()==95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Boy")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}
				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Girls":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getOtherHealthIssuesName() != null
							&& backgroundReport.getOtherHealthIssues()==95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Girl")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;

			case "Third Gender":
				i = 0;
				joiner = new StringJoiner(",");
				for (CaseHistoryCCI backgroundReport : socialinvestigationReports) {
					if (backgroundReport.getChildId().getProgramType() == 1
							&& backgroundReport.getChildId().getCciDetails()
									.getCciId().intValue() == areaDetails
									.getCciId().intValue()
							&& backgroundReport.getOtherHealthIssuesName() != null
							&& backgroundReport.getOtherHealthIssues()==95
							&& typeMap
									.get(backgroundReport.getChildId()
											.getChildSex()).getName()
									.equalsIgnoreCase("Third gender")) {
						i++;
						joiner.add(backgroundReport.getChildId().getChildId());
					}

				}
				if (ius.getUnit().equalsIgnoreCase("percent")) {
					IndicatorUnitSubgroup iusTotalIndicatorUnitSubgroup = new IndicatorUnitSubgroup();
					iusTotalIndicatorUnitSubgroup.setHighIsGood(ius
							.isHighIsGood());
					iusTotalIndicatorUnitSubgroup.setIndicatorName(ius
							.getIndicatorName());
					iusTotalIndicatorUnitSubgroup.setSubGroup("Total");
					i = (i / childHealthStatusAggregationCICLCCIWise(
							iusTotalIndicatorUnitSubgroup, areaDetails,
							socialinvestigationReports, typeMap, dataValue)) * 100;

				}
				childIds = joiner.toString();
				dataValue.setChildIds(childIds.equals("") ? null : childIds);
				return i;
			}

		}
		return 0;
	}

	/*
	 * @Override public void icpTotalJob(int timeNid) { IndicatorUnitSubgroup
	 * ius=indicatorUnitSubgroupRepository.findByIndicatorId(Integer.parseInt(
	 * notificationMessageSource.getMessage("icp.total.indicatorid",
	 * null,null))); TimePeriod timePeriod =
	 * timePeriodRepository.findByTimePeriodId(timeNid); List<TimePeriod>
	 * timePeriods=timePeriodRepository.findAll(); ///List<AreaDetails>
	 * areaDetailslist = areaRepository.fetchAreaByLevel(4); List<DataValue>
	 * dataValueList=new ArrayList<DataValue>(); List<Integer> icpIusIds=new
	 * ArrayList<Integer>();
	 * 
	 * List<String> iusIds=Arrays.asList(notificationMessageSource.getMessage(
	 * "icp.cicl.indicatorid",
	 * null,null),notificationMessageSource.getMessage("icp.cncp.indicatorid",
	 * null,null));
	 * 
	 * for(String iusId:iusIds) { icpIusIds.add(Integer.parseInt(iusId)); }
	 * 
	 * ///List<Object []> dataValues=dataValueRepository.
	 * findAllIndicatorUnitSubgroupIndicatorIdInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc
	 * (icpIusIds, timePeriod.getTimePeriodId()); List<DataValue>
	 * dataValuesArea=dataValueRepository.
	 * findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc
	 * (icpIusIds, timePeriod.getTimePeriodId());
	 * 
	 * Map<Integer,List<DataValue>> dataValueMap=new HashMap<Integer,
	 * List<DataValue>>();
	 * 
	 * for(DataValue dataValue:dataValuesArea) {
	 * if(dataValueMap.containsKey(dataValue.getAreaDetails().getAreaId())) {
	 * dataValueMap.get(dataValue.getAreaDetails().getAreaId()).add(dataValue);
	 * } else { List<DataValue> dataValues=new ArrayList<DataValue>();
	 * dataValues.add(dataValue);
	 * dataValueMap.put(dataValue.getAreaDetails().getAreaId(),dataValues); } }
	 * 
	 * 
	 * for(Integer key: dataValueMap.keySet()) { DataValue dataValue = new
	 * DataValue();
	 * 
	 * float
	 * value=dataValueMap.get(key).get(0).getValue()+dataValueMap.get(key).
	 * get(1).getValue();
	 * 
	 * dataValue.setValue(value); dataValue.setTimePeriod(timePeriod);
	 * dataValue.setIndicatorUnitSubgroup(ius);
	 * dataValue.setAreaDetails(dataValueMap.get(key).get(0).getAreaDetails());
	 * dataValue
	 * .setChildIds(dataValueMap.get(key).get(0).getChildIds()+","+dataValueMap
	 * .get(key).get(1).getChildIds());
	 * 
	 * dataValueList.add(dataValue); }
	 * 
	 * dataValueRepository.save(dataValueList);
	 * 
	 * }
	 */
	private void updateAgeOfChild(
			List<ChildRegistrationDetails> childRegistrationDetails,
			List<CICLSocialBackgroundReport> backgroundReports,
			Map<Integer, ValueObject> typeMap) {
		backgroundReports.stream().forEach(
				a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getEntryDate().getTime() / (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);
					a.getChildId()
							.setCurrentAge(
									(int) diffInDays
											+ Integer.parseInt(typeMap.get(
													a.getChildId().getAge())
													.getName()));
				});

		childRegistrationDetails.stream().forEach(
				a -> {
					LocalDate dateOfProduction = LocalDate.ofEpochDay(a
							.getDateOfProduction().getTime()
							/ (1000 * 60 * 60 * 24));
					LocalDate currentDate = LocalDate.now();
					long diffInDays = ChronoUnit.YEARS.between(
							dateOfProduction, currentDate);
					a.getChildId().setCurrentAge(
							(int) diffInDays
									+ Integer.parseInt(typeMap.get(
											a.getChildAge()).getName()));
				});

		for (CICLSocialBackgroundReport backgroundReport : backgroundReports) {
			childDetailsRepository.updateCurrentAgeByChildId(backgroundReport
					.getChildId().getCurrentAge(), backgroundReport
					.getChildId().getChildId());
		}
		for (ChildRegistrationDetails childRegistrationDetail : childRegistrationDetails) {
			childDetailsRepository.updateCurrentAgeByChildId(
					childRegistrationDetail.getChildId().getCurrentAge(),
					childRegistrationDetail.getChildId().getChildId());
		}
	}

}
