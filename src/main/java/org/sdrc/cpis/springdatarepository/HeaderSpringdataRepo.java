package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.Header;
import org.sdrc.cpis.repository.HeaderRepo;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface HeaderSpringdataRepo extends HeaderRepo, Repository<Header, Integer> {

	@Override
	@Query("select header from Header header where header.itemId= :id")
	Header findByItemId(@Param("id")Integer id) throws DataAccessException;
}
