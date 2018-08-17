package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.Header;
import org.springframework.dao.DataAccessException;

public interface HeaderRepo {
Header findByItemId(Integer id) throws DataAccessException;
}
