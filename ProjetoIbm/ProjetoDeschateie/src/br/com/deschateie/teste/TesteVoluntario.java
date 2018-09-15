package br.com.deschateie.teste;

import br.com.deschateie.bo.UsuarioBO;
import br.com.deschateie.excecao.Excecao;

public class TesteVoluntario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			System.out.println(UsuarioBO.pesquisarEmailUsuario("libero@euismodest.net").getAll());
			System.out.println("-----------------------------------------------------------");
			System.out.println(UsuarioBO.autenticarUsuario("Erat", "UIJ82AHO8PM").getAll());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
