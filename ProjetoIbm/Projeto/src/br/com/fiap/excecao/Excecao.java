package br.com.fiap.excecao;

public class Excecao extends Exception {

	public static String tratarExcecao(Exception e){
		if(e.getClass().getName().equals("java.lang.NumberFormatException")) {
			return "Numero invalido";
		}else if(e.getClass().getName().equals("java.sql.SQLException")){
			return "Não foi possível conectar no banco de dados!";
		}else {
			return "Mensagem do arquiteto";
		}
	}
	public static String tratarExcecao(){
		return "Mensagem do arquiteto";
	}
}
