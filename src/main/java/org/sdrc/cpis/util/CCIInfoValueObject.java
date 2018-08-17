package org.sdrc.cpis.util;

import java.util.List;

import org.sdrc.cpis.domains.CCITypeDetails;
import org.sdrc.cpis.models.CCIInfoMapModel;

public class CCIInfoValueObject {
private List<CCITypeDetails> cciTypes;
private List<CCIInfoMapModel> cciDetails;
private List<GeoLocationObject> googleMapData;
public List<CCITypeDetails> getCciTypes() {
	return cciTypes;
}
public void setCciTypes(List<CCITypeDetails> cciTypes) {
	this.cciTypes = cciTypes;
}
public List<GeoLocationObject> getGoogleMapData() {
	return googleMapData;
}
public void setGoogleMapData(List<GeoLocationObject> googleMapData) {
	this.googleMapData = googleMapData;
}
public List<CCIInfoMapModel> getCciDetails() {
	return cciDetails;
}
public void setCciDetails(List<CCIInfoMapModel> cciDetails) {
	this.cciDetails = cciDetails;
}


}
