package br.com.projetodescanso.teste;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.projetodescanso.beans.Veiculo;
import br.com.projetodescanso.dao.VeiculoDAO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteConsultarPorModelo {

	public static void main(String[] args) {
		try {
			do {
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
				veiculos = new VeiculoDAO().consultarPormodelo
						(JOptionPane.showInputDialog("Informe o modelo do veículo"));
				
				for(Veiculo veiculo: veiculos) {
					System.out.println(veiculo.getAll());
					System.out.println();
				}
				
			}while(JOptionPane.showConfirmDialog(null,"Continuar","Pergunta",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == 0);	
				
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
