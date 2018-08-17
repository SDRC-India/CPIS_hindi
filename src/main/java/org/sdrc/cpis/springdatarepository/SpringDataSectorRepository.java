/**
 * 
 */
package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.Sectors;
import org.sdrc.cpis.repository.SectorRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public interface SpringDataSectorRepository extends SectorRepository,
		JpaRepository<Sectors, Integer> {

}
