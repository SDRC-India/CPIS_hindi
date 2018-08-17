package org.sdrc.cpis.job;

/**
 * @author Harsh Pratyush(harsh@sdrc.co.in)
 */
import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.sdrc.cpis.services.CCIInfoService;
import org.sdrc.cpis.services.JobService;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MonthlyJob extends QuartzJobBean {
	
	private JobService jobService;
	
	private CCIInfoService cciInfoService;



	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}



	public void setCciInfoService(CCIInfoService cciInfoService) {
		this.cciInfoService = cciInfoService;
	}



	//call every month on 1st -- 12 A.M
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

//		try {
//			LocalDateTime currentDate = LocalDateTime.now();
//
//			if ((int) currentDate.getDayOfMonth() == 1) {
//				int timeNid = jobService.createPreviousMonth();
//				jobService.dashBoardJob(timeNid);
////				jobService.icpTotalJob(timeNid);
//				cciInfoService.updateCCITransactional();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
