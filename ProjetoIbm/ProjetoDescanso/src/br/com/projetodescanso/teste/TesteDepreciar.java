package br.com.projetodescanso.teste;

import br.com.projetodescanso.dao.VeiculoDAO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteDepreciar {

	public static void main(String[] args) {
		try {
			System.out.println("A quantidade de veículos atualizados foi de : " + new VeiculoDAO().depreciar());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
