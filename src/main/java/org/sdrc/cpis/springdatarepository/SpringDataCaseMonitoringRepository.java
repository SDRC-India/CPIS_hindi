package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.CaseMonitoring;
import org.sdrc.cpis.repository.CaseMonitoringRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataCaseMonitoringRepository extends CaseMonitoringRepository,Repository<CaseMonitoring, Integer> {

}
