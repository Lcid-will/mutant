/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lcid.mutant.dto.RequestMutantDto;
import com.co.lcid.mutant.dto.ResponseStatsDto;
import com.co.lcid.mutant.service.DnaService;

/**
 * The Class ApiMutantController.
 */

@RestController
@RequestMapping("/api/")

public class ApiMutantController {
	
	public ApiMutantController(DnaService dnaService) {
		super();
		this.dnaService = dnaService;
	}

	/** The dna service. */
	
	private DnaService dnaService;
	
	/**
	 * Checks if is mutan.
	 *
	 * @param dna the dna
	 * @return the response entity
	 */

	@PostMapping("/mutant/")
	public ResponseEntity<String> isMutant(@Validated  @RequestBody RequestMutantDto dna) {
		
		return  dnaService.isMutant(dna);

	}
	
	/**
	 * Stats.
	 *
	 * @return the response entity
	 */
	
	@GetMapping("/stats/")
	public ResponseEntity<ResponseStatsDto> stats() {
		return ResponseEntity.ok(dnaService.stats());

	}

}
