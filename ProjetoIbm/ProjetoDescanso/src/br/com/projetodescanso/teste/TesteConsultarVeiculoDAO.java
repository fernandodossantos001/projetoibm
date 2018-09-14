package br.com.projetodescanso.teste;

import javax.swing.JOptionPane;

import br.com.projetodescanso.dao.VeiculoDAO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteConsultarVeiculoDAO {

	public static void main(String[] args) {
		try {
			String resp = "";
			do {
				System.out.println(new VeiculoDAO().consultarPorPlaca
						((JOptionPane.showInputDialog
								("Digite o número da placa do veiculo"))).getAll());
				resp = JOptionPane.showInputDialog("Desejar fazer uma nova consulta ?");
				System.out.println();
				
			
			}while(resp.equalsIgnoreCase("sim"));
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
