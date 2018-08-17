package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.domains.Header;
import org.sdrc.cpis.models.ContentObject;

public interface CMSService {
	Header getHeader(Integer id);

	List<ContentObject> getPageContent(String viewName, Integer language);
}
