package org.sdrc.cpis.repository;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.DCPUHRDetails;

public interface DCPUHRDetailsRepository {

	@Transactional
	void save(DCPUHRDetails dcpuHRDetails);

	DCPUHRDetails findByDcpuAreaId(Integer areaId);
	
}
