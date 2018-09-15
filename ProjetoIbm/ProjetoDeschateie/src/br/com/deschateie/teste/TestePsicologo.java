package br.com.deschateie.teste;

import br.com.deschateie.bo.PsicologoBO;
import br.com.deschateie.dao.PsicologoDAO;
import br.com.deschateie.excecao.Excecao;

public class TestePsicologo {

	public static void main(String[] args) {
		try {
			
			System.out.println(new PsicologoDAO().consultarPsicologo(20).getAll());
			System.out.println("---------------------------------");
			System.out.println(PsicologoBO.pesquisarPsicologo(20).getAll());
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println(Excecao.tratarExcecao(e));
		}
	}

}