package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCTSType;
import org.sdrc.cpis.repository.CCTSTypeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataCCTSTypeRepository extends Repository<CCTSType, Integer>, CCTSTypeRepository {
	@Override
	@Query("select cctsTypes from CCTSType cctsTypes")
	List<CCTSType> getAllTypes();
}
