package br.com.projetodescanso.teste;

import javax.swing.JOptionPane;

import br.com.projetodescanso.dao.VeiculoDAO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteDeletarPorAno {

	public static void main(String[] args) {
		try {
			System.out.println(new VeiculoDAO().apagarPorAno
					(Integer.parseInt(JOptionPane.showInputDialog
							("Digite o ano de fabricação do carro"))));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
