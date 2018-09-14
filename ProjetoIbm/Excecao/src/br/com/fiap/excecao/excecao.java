package br.com.fiap.excecao;

public class excecao extends Exception {

		public static String tratarExcecao(Exception e){
			
			
			if(e.getClass().getName().equals("java.lang.NumberFormatException")) {
				return "Numero invalido";
			}else {
				return "Mensagem do arquiteto";
			}
		}
		public static String tratarExcecao(){
	
			return "Mensagem do arquiteto";
		}
}
