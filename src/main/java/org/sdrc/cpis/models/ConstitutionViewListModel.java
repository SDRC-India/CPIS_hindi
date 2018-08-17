package org.sdrc.cpis.models;

import java.util.List;

public class ConstitutionViewListModel {

	private String constitutionFormationDate;
	
	private List<ConstitutionViewModel> ConstitutionViewModel;

	public String getConstitutionFormationDate() {
		return constitutionFormationDate;
	}

	public void setConstitutionFormationDate(String constitutionFormationDate) {
		this.constitutionFormationDate = constitutionFormationDate;
	}

	public List<ConstitutionViewModel> getConstitutionViewModel() {
		return ConstitutionViewModel;
	}

	public void setConstitutionViewModel(
			List<ConstitutionViewModel> constitutionViewModel) {
		ConstitutionViewModel = constitutionViewModel;
	} 
	
}