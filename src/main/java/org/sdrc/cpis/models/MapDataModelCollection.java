package org.sdrc.cpis.models;

import java.util.List;



/**
 * 
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public class MapDataModelCollection 
{

	private List<List<MapDataModel>> dataCollection;

	public List<List<MapDataModel>> getDataCollection() {
		return dataCollection;
	}

	public void setDataCollection(List<List<MapDataModel>> dataCollection) {
		this.dataCollection = dataCollection;
	}
}
