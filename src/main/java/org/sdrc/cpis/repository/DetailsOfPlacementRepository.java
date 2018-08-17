package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.DetailsOfPlacement;
import org.springframework.transaction.annotation.Transactional;

public interface DetailsOfPlacementRepository {

	@Transactional
	Iterable<DetailsOfPlacement> save(Iterable<DetailsOfPlacement> detailsOfPlacement);
	
	List<DetailsOfPlacement> findDetailsByChildId(String childId);
}
