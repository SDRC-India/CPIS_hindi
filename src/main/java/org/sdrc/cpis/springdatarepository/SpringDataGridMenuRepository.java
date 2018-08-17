package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.GridMenuItemDetails;
import org.sdrc.cpis.repository.GridMenuRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataGridMenuRepository extends 
				 Repository<GridMenuItemDetails, Integer>, GridMenuRepository {
	
	@Override
	@Query("select menuItem from GridMenuItemDetails menuItem,GridMenuDesignationMapping menuRole "
			+ "where menuItem.itemId=menuRole.gridMenu.itemId AND menuRole.designation.designationId= :designationId"
			+ " ORDER BY menuItem.order")
	List<GridMenuItemDetails> getGridMenuItemsByUserId(@Param("designationId")Integer designationId);
}
