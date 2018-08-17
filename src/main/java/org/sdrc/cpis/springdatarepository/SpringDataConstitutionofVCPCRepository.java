package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.ConstitutionOfVCPC;
import org.sdrc.cpis.repository.ConstitutionofVCPCRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataConstitutionofVCPCRepository extends ConstitutionofVCPCRepository, Repository<ConstitutionOfVCPC, Integer> {

}
