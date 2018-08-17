package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.CICLPeriodicReport;
import org.sdrc.cpis.repository.CICLPeriodicReportRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataCICLPeriodicReportRepository extends CICLPeriodicReportRepository , Repository<CICLPeriodicReport, Integer>{

}
