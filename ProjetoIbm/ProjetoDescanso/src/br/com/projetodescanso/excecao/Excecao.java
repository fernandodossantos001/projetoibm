package br.com.projetodescanso.excecao;

public class Excecao extends Exception {

	public static String tratarExcecao(Exception e){
		if(e.getClass().getName().equals("java.lang.NumberFormatException")) {
			return "Numero invalido";
		}else if(e.getClass().getName().equals("java.sql.SQLException")){
			return "N�o foi poss�vel conectar no banco de dados!";
		}else {
			return "Mensagem do arquiteto";
		}
	}
	public static String tratarExcecao(){
		return "Mensagem do arquiteto";
	}
}
