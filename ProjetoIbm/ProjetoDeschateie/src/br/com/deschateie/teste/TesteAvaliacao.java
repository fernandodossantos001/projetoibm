package br.com.deschateie.teste;

import br.com.deschateie.beans.Avaliacao;
import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.bo.AvaliacaoBO;
import br.com.deschateie.bo.PsicologoBO;
import br.com.deschateie.dao.AvaliacaoDAO;
import br.com.deschateie.excecao.Excecao;

public class TesteAvaliacao {

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
			System.out.println(AvaliacaoBO.pesquisarAvaliacao(1).getAll());
			System.out.println("-------------------------------------");
			
			
			//System.out.println(AvaliacaoVoluntarioBO.pesquisarAvaliacao(0).getAll());
			
			//System.out.println(new AvaliacaoVoluntarioDAO().consultarAvaliacaoUsuario(1).getAll());
			
			
//			System.out.println(AvaliacaoVoluntarioBO.pesquisarAvaliacao(1).getAll());
//			System.out.println("----------------------- S T A T U S ------------------------------");
//			System.out.println(AvaliacaoVoluntarioBO.alteradaDadosAvaliacao(1,"15/09/2018","EM ANALISE"));
//			System.out.println("---------------------------------------------------");
//			System.out.println(AvaliacaoVoluntarioBO.pesquisarAvaliacao(1).getAll());
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
		

	}

}
