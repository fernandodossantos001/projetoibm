package br.com.deschateie.bo;

import br.com.deschateie.beans.Usuario;

public class TesteUsuario {

	public static void main(String[] args) {

		try {
			System.out.println(UsuarioBO.AlterarDadosUsuario(new Usuario(1, "PAKI", "Cras.eu.tellus@Suspendi.net", "22/08/1974", "teste123321", "teste94238", 1, "c", "feminino")));
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

}
