package br.com.deschateie.teste;

import br.com.deschateie.bo.ConsultaBO;
import br.com.deschateie.bo.ConversaBO;
import br.com.deschateie.excecao.Excecao;

public class TesteConsulta {

	public static void main(String[] args) {
		try {
			System.out.println(ConsultaBO.pesquisarConsulta(1).getAll());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
