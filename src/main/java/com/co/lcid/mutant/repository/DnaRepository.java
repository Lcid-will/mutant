/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.lcid.mutant.db.model.Dna;


/**
 * The Interface DnaRepository.
 */
public interface DnaRepository extends CrudRepository<Dna, String>{
	
	
	/**
	 * Checks if is mutant.
	 *
	 * @param dna the dna
	 * @return true, if is mutant
	 */
	public boolean isMutant(String dna);

	
	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	@Query(value = "SELECT SUM(is_mutan=1) AS mutant, SUM(is_mutan=0) AS human, SUM(is_mutan = 1)/ COUNT(*) AS ratio FROM dna"
			, nativeQuery = true)
	 Object[]  getReport();


	

}
