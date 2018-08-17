package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.GridMenuItemDetails;

public interface GridMenuRepository {
List<GridMenuItemDetails> getGridMenuItemsByUserId(Integer designationId);
}
