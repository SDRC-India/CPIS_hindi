package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.Designation;

public interface DesignationRepository {
	List<Designation> fetchAllDesignations();
}
