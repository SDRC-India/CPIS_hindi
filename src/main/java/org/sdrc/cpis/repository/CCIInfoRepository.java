package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCITypeDetails;
import org.springframework.transaction.annotation.Transactional;

public interface CCIInfoRepository {

@Transactional
void save(CCIDetails cciDetails);
	
List<Object[]> fetchCCIMstData();

List<CCITypeDetails> getCCITypes();

List<CCIDetails> fetchAllCCIs();

List<CCIDetails> getAreaWiseCciList(Integer areaId);

CCIDetails getCciById(Integer cciId);

List<CCIDetails> getAllCCIDetails();

List<Object[]> getCciWiseChildCount();
}
