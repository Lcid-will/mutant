/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class RequestMutantDto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMutantDto {
	
	/** The dna. */
	@NotNull(message="object can't be null")
	@Size(min=1,message="object must have one item")
	private String[] dna;
	
	
	

}
