package br.com.deschateie.teste;

import br.com.deschateie.dao.ConsultaDAO;
import br.com.deschateie.excecao.Excecao;

public class TesteConsulta {

	public static void main(String[] args) {
		try {
			//System.out.println(ConsultaBO.pesquisarConsulta(1).getAll());
			
			System.out.println(new ConsultaDAO().pesquisarConsultaCodPaciente(1).getAll());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
