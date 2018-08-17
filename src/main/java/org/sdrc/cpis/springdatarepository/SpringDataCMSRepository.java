package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.PageContentDetails;
import org.sdrc.cpis.repository.CMSRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCMSRepository extends Repository<PageContentDetails, Integer>,
		CMSRepository {
	
	@Override
	@Query(value="select pcd.id,pcd.Content_Title,pcd.Content,pcd.Parent_Content_Id,pcd.languagetype,pcd.image_path,pcd.href from page_content_details pcd where pcd.view_name= :viewName and pcd.languagetype= :language",nativeQuery=true)
	List<Object[]> getPageContent(@Param("viewName") String viewName,
									  @Param("language") Integer language);
}
