/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ResponseStatsDto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatsDto {
	
	/** The count mutant dna. */
	private BigDecimal count_mutant_dna;
	
	/** The count human dna. */
	private BigDecimal count_human_dna;	
	
	/** The ratio. */
	private BigDecimal ratio;
	
	

}
