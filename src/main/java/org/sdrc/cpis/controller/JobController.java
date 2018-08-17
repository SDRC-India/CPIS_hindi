package org.sdrc.cpis.controller;

import java.time.LocalDateTime;

import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.services.CCIInfoService;
import org.sdrc.cpis.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableScheduling
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private CCIInfoService cciInfoService;

	@Scheduled(cron="0 1 0 1 1/1 ? ")
	protected void executeInternal() {
		System.out.println("Aggregation started.");
		try {
			LocalDateTime currentDate = LocalDateTime.now();

			if ((int) currentDate.getDayOfMonth() == 1) {
				int timeNid = jobService.createPreviousMonth();
				jobService.dashBoardJob(timeNid);
//				jobService.icpTotalJob(timeNid);
				cciInfoService.updateCCITransactional();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Aggregation over.");
		}

	}
	
	/*Manual aggregation*/
	@Authorize(feature="role_management",permission="edit")
	@RequestMapping(value="/doAggregation")
	public void doAggregation(@RequestParam("fromTp") Integer fromTpNid, @RequestParam("toTp") Integer toTpNid) {
		for (int i = fromTpNid; i <= toTpNid; i++) {
			jobService.dashBoardJob(i);
			cciInfoService.updateCCITransactional();
		}
	System.out.println("success");
	}
	
	@Authorize(feature="role_management",permission="edit")
	@RequestMapping(value="/doAggregationSingleTp")
	public void doAggregationSingleTp(@RequestParam("tp") Integer tpNid) {
			jobService.dashBoardJob(tpNid);
			cciInfoService.updateCCITransactional();
	System.out.println("success");
	}
}
