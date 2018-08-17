package org.sdrc.cpis.repository;

import java.util.List;

public interface CMSRepository {
List<Object[]> getPageContent(String viewName, Integer language);
}
