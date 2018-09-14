package br.com.projetodescanso.teste;

import javax.swing.JOptionPane;

import br.com.projetodescanso.beans.Veiculo;
import br.com.projetodescanso.dao.VeiculoDAO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteAtualizarVeiculo {

	public static void main(String[] args) {
		try {
			
			//passando por parametros
			System.out.println(new VeiculoDAO().atualizar
														(Integer.parseInt(JOptionPane.showInputDialog("Digite a placa do Veículo"))
														, JOptionPane.showInputDialog("Digite o modelo")
														, JOptionPane.showInputDialog("Digite a cor do Veículo")
														, Integer.parseInt(JOptionPane.showInputDialog("Digite o anor de fabricação do Veículo"))
														, Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de venda do Veículo"))
														, Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de compra do Veículo"))));
			
			
		//passando objeto	
		System.out.println(new VeiculoDAO().atualizar(new Veiculo((JOptionPane.showInputDialog("Digite a placa do Veículo"))
														, JOptionPane.showInputDialog("Digite o modelo")
														, JOptionPane.showInputDialog("Digite a cor do Veículo")
														, Integer.parseInt(JOptionPane.showInputDialog("Digite o anor de fabricação do Veículo"))
														, Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de venda do Veículo"))
														, Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de compra do Veículo")))));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
