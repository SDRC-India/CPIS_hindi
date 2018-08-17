package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.CaseSummaryByCWC;
import org.sdrc.cpis.repository.CaseSummaryCWCRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataCaseSummaryCWCRepository extends CaseSummaryCWCRepository, Repository<CaseSummaryByCWC, Integer> {

}
