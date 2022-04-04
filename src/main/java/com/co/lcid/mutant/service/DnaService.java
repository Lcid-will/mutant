/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.lcid.mutant.db.model.Dna;
import com.co.lcid.mutant.dto.RequestMutantDto;
import com.co.lcid.mutant.dto.ResponseStatsDto;
import com.co.lcid.mutant.repository.DnaRepository;
import com.co.lcid.mutant.util.MutantUtil;

/**
 * The Class DnaService.
 */
@Service
public class DnaService {

	/** The dna respository. */

	private DnaRepository dnaRespository;

	public DnaService(DnaRepository dnaRespository) {
		super();
		this.dnaRespository = dnaRespository;
	}

	/**
	 * Checks if is mutant.
	 *
	 * @param dna the dna
	 * @return the response entity
	 */
	public ResponseEntity<String> isMutant(RequestMutantDto dna) {
	
		String dnaLinea = String.join("", dna.getDna());
		if (dnaRespository.existsById(dnaLinea)) {
			return getValidate(dnaRespository.isMutant(dnaLinea));
		} else {
			boolean isMutant = MutantUtil.isMutant(dna.getDna());
			dnaRespository.save(new Dna(dnaLinea, isMutant));
			return getValidate(isMutant);
		}
	}

	/**
	 * Stats.
	 *
	 * @return the response stats dto
	 */
	@Transactional(readOnly = true)
	public ResponseStatsDto stats() {
		ResponseStatsDto response = new ResponseStatsDto();
		Object[] re = (Object[]) dnaRespository.getReport()[0];
		response = new ResponseStatsDto(MutantUtil.getBigDecimal(re[0]), MutantUtil.getBigDecimal(re[1]),
				MutantUtil.getBigDecimal(re[2]));
		System.out.println();
		return response;

	}

	/**
	 * Gets the validate.
	 *
	 * @param val the val
	 * @return the validate
	 */
	public ResponseEntity<String> getValidate(boolean val) {
		if (val)
			return ResponseEntity.ok("");
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
	}

}
