/**
 * 
 */
package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.Sectors;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public interface SectorRepository {
	
	List<Sectors> findAll();

	Sectors findBySectorId(int sectorId);

	List<Sectors> findBySectorIdOrParentId(int sectorId, int sectorId2);

}
