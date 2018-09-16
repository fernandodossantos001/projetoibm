package br.com.deschateie.teste;

import br.com.deschateie.beans.AvaliacaoVoluntario;
import br.com.deschateie.bo.AvaliacaoVoluntarioBO;
import br.com.deschateie.dao.AvaliacaoVoluntarioDAO;
import br.com.deschateie.excecao.Excecao;

public class TesteAvaliacaoVoluntario {

	public static void main(String[] args) {
		try {
		//	System.out.println(AvaliacaoVoluntarioBO.pesquisarAvaliacao(1).getAll());
		//	System.out.println(new AvaliacaoVoluntarioDAO().consultarAvaliacao(2).getAll());
		
			//System.out.println(AvaliacaoVoluntarioBO.novaAvaliacaoVoluntario(new AvaliacaoVoluntario(99, 11, 4, "32/01/19909", "em analise")));
		//	System.out.println(new AvaliacaoVoluntarioDAO().gravarDadosAvaliacao(123, 20, 1, "20/01/1990", "em analise"));
//			System.out.println(AvaliacaoVoluntarioBO.novaAvaliacaoVoluntario(1244, 20, 2, "12/01/1997","Aprovado"));
//			
//			System.out.println(AvaliacaoVoluntarioBO.pesquisarAvaliacao(1244).getAll());
//			System.out.println(AvaliacaoVoluntarioBO.excluirAvaliacaoVoluntario(1244));
//			
			System.out.println("---------------------------------");
			System.out.println(AvaliacaoVoluntarioBO.pesquisarAvaliacao(1244).getAll());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
		

	}

}
