package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.Designation;
import org.sdrc.cpis.repository.DesignationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataDesignationRepository extends DesignationRepository, Repository<Designation, Integer> {
	@Override
	@Query("select designation from Designation designation ORDER BY designation.designationId")
	List<Designation> fetchAllDesignations();
}
