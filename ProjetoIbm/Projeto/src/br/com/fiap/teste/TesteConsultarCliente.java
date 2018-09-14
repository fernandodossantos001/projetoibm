package br.com.fiap.teste;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.excecao.Excecao;

public class TesteConsultarCliente {

	public static void main(String[] args) {
		try {
			Cliente cli = new Cliente();
			ClienteDAO dao = new ClienteDAO();
			
			do {
				cli = dao.consularPorNumero(Integer.parseInt(JOptionPane.showInputDialog("Digite um número")));
				System.out.println( cli.getAll());
			}while(JOptionPane.showConfirmDialog(null,"Continuar","Pergunta",
														JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
		

	}

}
