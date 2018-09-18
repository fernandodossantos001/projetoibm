package br.com.deschateie.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBO {
	
	private static Pattern pattern ;
	private static Matcher matcher;
	
	public static String validarData(String data) {
		
		pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		matcher = pattern.matcher(data);
		
		if(!matcher.find()) {
			return "data inv�lido";
		}
		return data;
	}
	
	public static String validaDataHora(String dataHora) {
		String data = dataHora.substring(0, 10);
		
		String regexData = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
		Pattern pattern = Pattern.compile(regexData);
		Matcher matcher = pattern.matcher(data);
		
		if(!matcher.find()) {
			return "data inv�lido"; 
		}
		
		
		String hora = dataHora.substring(11, dataHora.length());
		String regexHora = "^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])(:[0-5][0-9])?$";
		pattern = Pattern.compile(regexHora);
		matcher = pattern.matcher(hora);
		
		if (!matcher.find()) {
			return "Horario Invalido";
		}
		
		
		return data + "-"+ hora;
		
	}

}
