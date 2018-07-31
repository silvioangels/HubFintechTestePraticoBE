package com.hubfintech.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Util {

	private static final String REGEX_CPF = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)";
	private static final String REGEX_CNPJ = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)";
	
	public Util() {
	}
	
	public static Boolean validarCpf(String cpf) {
		
		if(cpf == ""){
			return false;
		}
		
		Pattern padrao = Pattern.compile(REGEX_CPF);
		Matcher matcher = padrao.matcher(cpf);
		return matcher.matches();
		
	}
	
	public static Boolean validarCnpj(String cnpj) {
		
		if(cnpj == ""){
			return false;
		}
		
		Pattern padrao = Pattern.compile(REGEX_CNPJ);
		Matcher matcher = padrao.matcher(cnpj);
		return matcher.matches();
		
	}

}
