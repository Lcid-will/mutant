/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant;


import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.lcid.mutant.controller.ApiMutantController;
import com.co.lcid.mutant.db.model.Dna;
import com.co.lcid.mutant.dto.RequestMutantDto;
import com.co.lcid.mutant.dto.ResponseStatsDto;
import com.co.lcid.mutant.repository.DnaRepository;
import com.co.lcid.mutant.service.DnaService;
import com.co.lcid.mutant.util.Constant;


/**
 * The Class MutantApplicationTests.
 */
@SpringBootTest
class MutantApplicationTests {
	
	 private ApiMutantController controller;
	 private DnaService dnaService;

	 @Mock
	 private DnaRepository dnaRepository;	 

	 
	 private Logger logger;
	 @BeforeEach
	 void contextLoads() {
		 
			dnaService = new DnaService(dnaRepository);
			controller = new ApiMutantController(dnaService);
			logger=LogManager.getLogger(MutantApplicationTests.class);
			
		 
	 }
	 
	 
	/**
	 * Context loads.
	 */
	@Test
	void validateIsMutant_ReturnOk() {
		
		logger.info("validateIsMutant_ReturnOk-init");
		
		String[] dna= {"CTGCGA","CAGTGA","TTATGG","AGAAGG","CCCCTA","CCAAGA"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_ReturnOk-end");
		
	}
	
	/**
	 * Context loads.
	 */
	@Test
	void validateIsMutant_DiagonalUp_ReturnOk() {
		
		logger.info("validateIsMutant_DiagonalUp_ReturnOk-init");
		
		String[] dna= {"CGGCGAG","CCGAGAG","TTCGGGT","AACCGGT","AGCCCAA","CCAAGCC"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_DiagonalUp_ReturnOk-end");
		
	}
	
	
	/**
	 * Context loads.
	 */
	@Test
	void validateIsMutant_DiagonalDown_ReturnOk() {
		
		logger.info("validateIsMutant_DiagonalDown_ReturnOk-init");
		
		String[] dna= {"CAGCGAG","TAGAGAG","GTATGGT","AGTGGGT","AGGTCAA","CCAGTCC"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_DiagonalDown_ReturnOk-end");
		
	}
	
	/**
	 * Context loads.
	 */
	@Test
	void validateIsMutant_backslash_up_ReturnOk() {
		
		logger.info("validateIsMutant_backslash_up_ReturnOk-init");
		
		String[] dna= {"CAGG","AAGG","AGGG","GATG"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_backslash_up_ReturnOk-end");
		
	}
	
	@Test
	void validateIsMutant_backslash_down_Return() {
		
		logger.info("validateIsMutant_backslash_down_ReturnOk-init");
		
		String[] dna= {"CCCCCT","AAACTC","AGCTAG","GCTGAA"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_backslash_down_ReturnOk-end");
		
	}
	
	@Test
	void validateIsMutant_backslash_down_ReturnFordiben() {
		
		logger.info("validateIsMutant_backslash_down_ReturnFordiben-init");
		
		String[] dna= {"T","T","T","T"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_backslash_down_ReturnFordiben-end");
		
	}
	
	@Test
	void validateIsMutant_empty_ReturnFordiben() {
		
		logger.info("validateIsMutant_empty_ReturnFordiben-init");
		
		String[] dna= {};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_empty_ReturnFordiben-end");
		
	}
	
	@Test
	void validateIsMutant_diferentSize_ReturnFordiben() {
		
		logger.info("validateIsMutant_diferentSize_ReturnFordiben-init");
		
		String[] dna= {"AAAA","T"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_diferentSize_ReturnFordiben-end");
		
	}
	
	@Test
	void validateIsMutant_sizeLessFour_ReturnFordiben() {
		
		logger.info("validateIsMutant_sizeLessFour_ReturnFordiben-init");
		
		String[] dna= {"TT"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_sizeLessFour_ReturnFordiben-end");
		
	}
	
	
	
	
	
	@Test
	void validateIsMutant_ReturnOk_Big() {
		
		logger.info("validateIsMutant_ReturnOk_Big-init");
		
		String[] dna= {"CTGCGACACCAAGAGTGATTATGGGGGAAGGCTGCGACAGTGATTATGGAGAGTTATGGG","CTGCGACGTGATTATGGCTGCGACAGTGATTGAGAAGAGTGATTATGGAGAAGTTATGGG",
				"TCAGCTGCGACAGTGATTGGATAGCGTTAGCTGCGACAGTGATCGTGAATTATGCGAGAT","GAGTGATTATTATGTGATCGTGAAGAGACATGATCATAATACCAAGAGTGGATATAAAGG",
				"TACACCAATATGGATATGGAAAAGAGTGATACCAAGAGTGTGAGTTAATTACACCAATTA","CGTGATTATGGAAGTTAATTACACGTTATGCAGAGTGAGTTAAGAAGAACCAAGAGTGAT",};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_ReturnOk_B-end");
		
	}
	
	@Test
	void validateIsNotMutant_ReturnFordiben_diferentCharDna() {
		logger.info("validateIsNotMutant_ReturnFordiben_diferentCharDna-init");		
		String[] dna= {"FTGCGA","CAGTGA","TTATGG","AGAAGG","CCCCTA","CCAAGA"};
		RequestMutantDto request= new RequestMutantDto(dna);	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsNotMutant_ReturnFordiben_diferentCharDna-end");
		
	}
	
	
	
	@Test
	void validateIsNotMutant_ReturnFordiben_diferentLenghtColum() {
		logger.info("validateIsNotMutant_ReturnFordiben_diferentLenghtColum-init");		
		String[] dna= {"F","CAGTGA","TTATGG","AGAAGG","CCCCTA","CCAAGA"};
		RequestMutantDto request= new RequestMutantDto(dna);	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsNotMutant_ReturnFordiben_diferentLenghtColum-end");
		
	}
	
	@Test
	void validateIsNotMutant_ReturnFordiben_notMutant() {
		logger.info("validateIsNotMutant_ReturnFordiben_notMutant-init");
		
		String[] dna= {"TGGAA"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsNotMutant_ReturnFordiben_notMutantn-end");
		
	}
	
	
	@Test
	void validateStats_returnOk() {
		logger.info("validateStats_returnOk-init");	
		Object[] resObjtect= new Object[1];
		resObjtect[0]= new Object[] {new BigDecimal(40),new BigDecimal(100),new BigDecimal(0.4)};
		doReturn(resObjtect).when(dnaRepository).getReport();
		ResponseEntity<ResponseStatsDto> resp=controller.stats();
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateStats_returnOk-end");
		
	}
	
	@Test
	void validateStats_nullValue_returnOk() {
		logger.info("validateStats_Ok-init");	
		Object[] resObjtect= new Object[1];
		resObjtect[0]= new Object[] {null,new BigDecimal(100),null};
		doReturn(resObjtect).when(dnaRepository).getReport();
		ResponseEntity<ResponseStatsDto> resp=controller.stats();
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateStats_Ok-end");
		
	}
	
	@Test
	void validateStats_diffBigDecimal_returnOk() {
		logger.info("validateStats_Ok-init");	
		Object[] resObjtect= new Object[1];
		resObjtect[0]= new Object[] {new BigDecimal(0),new String("0"),new BigDecimal(0)};
		doReturn(resObjtect).when(dnaRepository).getReport();
		ResponseEntity<ResponseStatsDto> resp=controller.stats();
		Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatusCodeValue());
		logger.info("validateStats_Ok_-end");
		
	} 
	
	@Test
	void validateIsMutant_dnaExistTrue_ReturnFordiben() {
		logger.info("validateIsMutant_dnaExistTrue_ReturnFordiben-init");	
		String val="AAAA";
		doReturn(true).when(dnaRepository).existsById(val);
		String[] dna= {"AAAA"};
		RequestMutantDto request= new RequestMutantDto(dna);
	
		ResponseEntity<String> resp=controller.isMutant(request);
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), resp.getStatusCodeValue());
		logger.info("validateIsMutant_dnaExistTrue_ReturnFordiben-end");
		
	}
	


	
	@Test
	void validateIsMutant_valConstantes_ReturnOK() {
		logger.info("validateConstant_valConstantes_ReturnOK-init");
		Assertions.assertEquals(Constant.REPETCIONES_VALIDAR, 4);
		logger.info("validateConstant_valConstantes_ReturnOK-end");
		
	}
	
	@Test
	void validateIsMutant_valConstantes2_ReturnOK() {
		logger.info("validateConstant_valConstantes2_ReturnOK-init");
		Assertions.assertEquals(Constant.CARACTER_VALIDAR, "AAAA,TTTT,CCCC,GGGG");
		logger.info("validateConstant_valConstantes2_ReturnOK-end");
		
	}
	
	@Test
	void validateConstant_valConstante3_ReturnOK() {
		logger.info("validateConstant_valConstante3_ReturnOK-init");
		Assertions.assertEquals(Constant.EXPRESSION, "^(A|T|C|G)+");
		logger.info("validateConstant_valConstante3_ReturnOK-end");
		
	}
	
	@Test
	void validateModel_model_ReturnOK() {
		logger.info("validateModel_model_ReturnOK-init");
		Dna dna= new Dna("");
		dna.setDna("A");
		Assertions.assertEquals(dna, dna);
		logger.info("validateModel_model_ReturnOK-end");
		
	}


}
