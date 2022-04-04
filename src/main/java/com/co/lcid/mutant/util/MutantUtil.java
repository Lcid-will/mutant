/*
 * Developed on 29/03/2022
 * by: William Rozo
 */
package com.co.lcid.mutant.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.co.lcid.mutant.dto.ResponseDto;



/**
 * The Class MutantUtil.
 */
public class MutantUtil {
	
	/**
	 * Checks if is mutant.
	 *
	 * @param dna the dna
	 * @return true, if is mutant
	 */
	public static boolean isMutant(String[] dna){
			
		if(!initValidation(dna)) {
			return false;
		}
		//valida primero horizontalmente
		List<String> horizontal = Arrays.asList(dna);
		ResponseDto response = new ResponseDto(false, 0);
		if(horizontal.get(0).length()>=Constant.REPETCIONES_VALIDAR) {
			 response=getRepeticiones(response, horizontal); if(response.isMutan());
			 if(response.isMutan()) return true;
		}
		//valida  verticalmente
		if(horizontal.size()>=Constant.REPETCIONES_VALIDAR) {
			List<String> vertical = getVertical(horizontal,0,new ArrayList<>());
			response=getRepeticiones(response,vertical); if(response.isMutan());
			if(response.isMutan()) return true;
		}
		// valida Diagonales normal e inversa
		if(horizontal.size()>=Constant.REPETCIONES_VALIDAR && horizontal.get(0).length()>=Constant.REPETCIONES_VALIDAR){
		String matrix[][] = getMatrix(horizontal);
		Integer alto = matrix.length, lado = matrix[0].length;
		response=getDiagonalPrincipal(response, matrix, alto, lado);
		 if(response.isMutan()) return true;
		 response=getDiagonaInvervesa(response, matrix, alto, lado);
		 if(response.isMutan()) return true;
		}
		return false;		
	}

	
	/**
	 * Gets the diagonal principal.
	 *
	 * @param response the response
	 * @param matrix the matrix
	 * @param alto the alto
	 * @param lado the lado
	 * @return the diagonal principal.
	 */
	public static ResponseDto getDiagonalPrincipal(ResponseDto response, String matrix[][], Integer alto,
			Integer lado) {
		StringBuilder diagonalArriba = new StringBuilder();

		for (int i = 0; i < lado; i++) {
			for (int j = i; j < alto; j++) {			
				diagonalArriba.append(matrix[i][j]);
			}
			diagonalArriba.append(",");
		}

		List<String> diagonalSuperior = getVertical(Arrays.asList(diagonalArriba.toString().split(",")), 0,
				new ArrayList<>()).stream().filter(e -> e.length() >= Constant.REPETCIONES_VALIDAR).collect(Collectors.toList());
		response = getRepeticiones(response, diagonalSuperior);
		if (response.isMutan())
			return response;
		else {
			response = getDiagonalInferiorPrincipal(response, matrix, alto, lado);
		}
		return response;

	}

	/**
	 * Gets the diagonal inferior principal.
	 *
	 * @param response the response
	 * @param matrix the matrix
	 * @param alto the alto
	 * @param lado the lado
	 * @return the diagonal inferior principal
	 */
	public static ResponseDto getDiagonalInferiorPrincipal(ResponseDto response, String matrix[][], Integer alto,
			Integer lado) {
		StringBuilder diagonalArriba = new StringBuilder();

		for (int i = 0; i < lado; i++) {
			for (int j = i; j < alto; j++) {
				diagonalArriba.append(matrix[j][i]);
			}
			diagonalArriba.append(",");
		
		}

		List<String> diagonalferior = getVertical(Arrays.asList(diagonalArriba.toString().split(",")), 0,
				new ArrayList<>()).stream().filter(e -> e.length() >= Constant.REPETCIONES_VALIDAR).collect(Collectors.toList());
		diagonalferior.remove(0);
		return getRepeticiones(response, diagonalferior);

	}

	/**
	 * Gets the diagona invervesa.
	 *
	 * @param response the response
	 * @param matrix the matrix
	 * @param alto the alto
	 * @param lado the lado
	 * @return the diagona invervesa
	 */
	public static ResponseDto getDiagonaInvervesa(ResponseDto response, String matrix[][], Integer alto, Integer lado) {
		StringBuilder diagonalArriba = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = matrix.length - 1; j >= 0; j--) {
				diagonalArriba.append(matrix[i][j]);
			}
			diagonalArriba.append(",");
		}
		
		List<String> diagonalSuperior = getVertical(Arrays.asList(diagonalArriba.toString().split(",")), 0,
				new ArrayList<>()).stream().filter(e -> e.length() >= Constant.REPETCIONES_VALIDAR).collect(Collectors.toList());
		response = getRepeticiones(response, diagonalSuperior);
		if (response.isMutan())
			return response;
		else {
			response = getDiagonalInferiorInversal(response, matrix, alto, lado);
		}
		return response;

	}

	/**
	 * Gets the diagonal inferior inversal.
	 *
	 * @param response the response
	 * @param matrix the matrix
	 * @param alto the alto
	 * @param lado the lado
	 * @return the diagonal inferior inversal
	 */
	public static ResponseDto getDiagonalInferiorInversal(ResponseDto response, String matrix[][], Integer alto,
			Integer lado) {
		StringBuilder diagonalArriba = new StringBuilder();
		List<String> lista = new ArrayList<String>();

		for (int i = 0; i < alto; i++) {
			for (int j = alto - 1; j >= 0; j--) {
				diagonalArriba.append(matrix[j][i]);
				matrix[j][i] = matrix[j][i];
			}
			lista.add(diagonalArriba.toString());
			diagonalArriba = new StringBuilder();

		}
		matrix = getMatrix(lista);
		return getDiagonalPrincipal(response, matrix, alto, lado);

	}

	/**
	 * Gets the matrix.
	 *
	 * @param listaInicial the lista inicial
	 * @return the matrix
	 */
	public static String[][] getMatrix(List<String> listaInicial) {
		String matrix[][] = new String[listaInicial.size()][listaInicial.get(0).toString().length()];

		for (int i = 0; i < listaInicial.size(); i++) {
			for (int n = 0; n < listaInicial.get(i).length(); n++) {
				matrix[i][n] = listaInicial.get(i).charAt(n) + "";
			}
		}

		return matrix;
	}




	/**
	 * Gets the vertical.
	 *
	 * @param list the list
	 * @param pocision the pocision
	 * @param listaFinal the lista final
	 * @return the vertical
	 */
	public static List<String> getVertical(List<String> list, int pocision, List<String> listaFinal) {

		StringBuilder linea = new StringBuilder();
		for (String dnaTemp : list) {
			if (dnaTemp.length() > pocision) {
				char letra = dnaTemp.charAt(pocision);
				linea.append(letra);
			}

		}
		listaFinal.add(linea.toString());
		pocision++;

		if (pocision <= linea.toString().length()) {

			getVertical(list, pocision, listaFinal);
		}

		return listaFinal;
	}

	/**
	 * Inits the validation.
	 *
	 * @param dna the dna
	 * @return true, if successful
	 */
	public static boolean initValidation(String[] dna) {
		if (ArrayUtils.isEmpty(dna)) {
			return false;
		}
		List<String> dnaLis = Arrays.asList(dna);
		boolean result = dnaLis.stream().anyMatch(obj -> !Pattern.matches(Constant.EXPRESSION, obj));
		if (result) {
			return false;
		}

		if (dnaLis.stream().anyMatch(e -> e.length() != dnaLis.get(0).length())) {
			return false;
		}
		if(dna.length==1 && dna[0].length()<Constant.REPETCIONES_VALIDAR) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the repeticiones.
	 *
	 * @param response the response
	 * @param validar the validar
	 * @return the repeticiones
	 */
	public static ResponseDto getRepeticiones(ResponseDto response, List<String> validar) {
		for (String horizontal : validar) {
			for (String expre : Constant.CARACTER_VALIDAR.split(",")) {
				if (horizontal.contains(expre)) {
					response.setCount(response.getCount() + 1);
					if (response.getCount() > 1) {
						response.setMutan(true);
						return response;
					}

				}
			}
		}

		return response;
	}
	
	
	 /**
 	 * Gets the big decimal.
 	 *
 	 * @param value the value
 	 * @return the big decimal
 	 */
 	public static BigDecimal getBigDecimal( Object value ) {
	        BigDecimal ret = null;
	        if( value != null ) {
	            if( value instanceof BigDecimal ) {
	                ret = (BigDecimal) value;
	            } else {
	               ret= new BigDecimal(new BigInteger("0"));
	            }
	        }else {
	        	ret= new BigDecimal(new BigInteger("0"));
	        }
	        return ret;
	    }

}
