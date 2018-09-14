package br.com.projetodescanso.teste;

import javax.swing.JOptionPane;

import br.com.projetodescanso.beans.Veiculo;
import br.com.projetodescanso.dao.VeiculoDAO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteGravarVeiculoDAO {

	public static void main(String[] args) {
		try {
			String resp = "";
			do {
					System.out.println(new VeiculoDAO().gravarDados
					(new Veiculo(JOptionPane.showInputDialog("Digite a placa do carro")
							, JOptionPane.showInputDialog("Digite o modelo do carro")
							, JOptionPane.showInputDialog("Digite a cor do carro")
							, Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do veículo"))
							, Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de venda do veículo"))
							, Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de compra do veículo")))));
							resp = JOptionPane.showInputDialog("Deseja continuar  ?");
			}while(resp.equalsIgnoreCase("sim"));
	
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
