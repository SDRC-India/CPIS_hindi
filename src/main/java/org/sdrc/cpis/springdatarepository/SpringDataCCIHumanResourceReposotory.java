package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCIHumanResource;
import org.sdrc.cpis.repository.CCIHumanResourceReposotory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCCIHumanResourceReposotory extends CCIHumanResourceReposotory, Repository<CCIHumanResource, Integer> {
	
	@Override
	@Query("select hr from CCIHumanResource hr")
	List<CCIHumanResource> findAll();
	
	@Override
	@Query("select hr from CCIHumanResource hr where hr.nameOfCCI=:ciiId")
	CCIHumanResource findByNameofCII(@Param("ciiId")Integer ciiId);

}
