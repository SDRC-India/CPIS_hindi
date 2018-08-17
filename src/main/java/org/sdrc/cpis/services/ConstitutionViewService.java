package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.ConstitutionViewListModel;

public interface ConstitutionViewService {

	public List<ConstitutionViewListModel> getConstitutionView(Integer district,Integer constitutionType, Integer blockId);
}
