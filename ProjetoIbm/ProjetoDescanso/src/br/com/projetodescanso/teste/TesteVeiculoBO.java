package br.com.projetodescanso.teste;

import javax.swing.JOptionPane;

import br.com.projetodescanso.beans.Veiculo;
import br.com.projetodescanso.bo.VeiculoBO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteVeiculoBO {

	public static void main(String[] args) {
		try {
			System.out.println(VeiculoBO.novoVeiculo(
					new Veiculo(JOptionPane.showInputDialog("Informe a placa do veiulo"),
							JOptionPane.showInputDialog("Informe o Modelo do veiculo"),
							JOptionPane.showInputDialog("Informe a cor do veículo"),
							Integer.parseInt(JOptionPane.showInputDialog("Informe o ano")),
							Double.parseDouble(JOptionPane.showInputDialog("Informe o valor de venda")),
							Double.parseDouble(JOptionPane.showInputDialog("Informe o valor de compra")))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
