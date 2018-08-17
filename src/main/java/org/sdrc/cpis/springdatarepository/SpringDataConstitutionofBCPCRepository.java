package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.ConstitutionOfBCPC;
import org.sdrc.cpis.repository.ConstitutionofBCPCRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataConstitutionofBCPCRepository extends ConstitutionofBCPCRepository, Repository<ConstitutionOfBCPC, Integer>{

}
