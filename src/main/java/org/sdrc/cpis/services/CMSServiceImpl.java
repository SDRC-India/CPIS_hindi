package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.List;

import org.sdrc.cpis.domains.Header;
import org.sdrc.cpis.models.ContentObject;
import org.sdrc.cpis.repository.CMSRepository;
import org.sdrc.cpis.repository.HeaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CMSServiceImpl implements CMSService {

	@Autowired
	private HeaderRepo hederRepo;
	
	@Autowired
	private CMSRepository cmsRepository;
	
	@Override
	public Header getHeader(Integer id) {
		// TODO Auto-generated method stub
		return hederRepo.findByItemId(id);
	}
	@Transactional
	@Override
	public List<ContentObject> getPageContent(String viewName, Integer language) {
		List<Object[]> pageConentDetails = cmsRepository.getPageContent(viewName, language);
		
		List<ContentObject> contentObjects = new ArrayList<ContentObject>();
		for (Object[] objects : pageConentDetails) {
			ContentObject contentObject=new ContentObject();
			contentObject.setContentId(Integer.parseInt(String.valueOf(objects[0])));
			contentObject.setTitle(null==objects[1]?null:String.valueOf(objects[1]));
			contentObject.setContent(null==objects[2]?null:String.valueOf(objects[2]));
			contentObject.setParentId(null==objects[3]?null:Integer.parseInt(String.valueOf(objects[3])));
			contentObject.setLanguageType(null==objects[4]?null:Integer.parseInt(String.valueOf(objects[4])));
			contentObject.setImagePath(null==objects[5]?null:String.valueOf(objects[5]));
			contentObject.setHref(null==objects[6]?null:String.valueOf(objects[6]));
			
			contentObjects.add(contentObject);
		}
		
		return contentObjects;
	}

}
