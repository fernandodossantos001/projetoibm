package br.com.deschateie.teste;

import br.com.deschateie.beans.Conversa;
import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.bo.ConversaBO;
import br.com.deschateie.bo.DataBO;
import br.com.deschateie.excecao.Excecao;

public class TesteConversa {
	
	public static void main(String[] args) {
		try {
			
			
//		System.out.println(ConversaBO.pesquisarConversa(9).getAll());
//		System.out.println("--------------------------------");
//		//vai dar erro por ser chave primaria em outra tabela, o ideia � verificar se existe na outra tabela, exlcuir da outra tabela
//		// e depois excluir essa tabela
//		System.out.println(ConversaBO.exluirConversa(9));
//		System.out.println("--------------- S T A T U S -------------------");
//		System.out.println(ConversaBO.pesquisarConversa(9));
//		System.out.println(ConversaBO.novaConversa(new Conversa(123,"18/09/2018",12,"historico","horatermino",12)));
//		
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e)); 
		}
		
	}
}
