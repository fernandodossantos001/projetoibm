package br.com.fiap.teste;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Endereco;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.excecao.Excecao;

public class TesteGravarCliente {

	public static void main(String[] args) {
		try {
//			Cliente obj = new Cliente(JOptionPane.showInputDialog
//					("Digite seu nome"), Integer.parseInt
//					(JOptionPane.showInputDialog("Digite o número")),Integer.parseInt
//					(JOptionPane.showInputDialog("Digite a quantidade de estrlas")));			
			
			System.out.println(new ClienteDAO().gravar(new Cliente(JOptionPane.showInputDialog
					("Digite seu nome"), Integer.parseInt
					(JOptionPane.showInputDialog("Digite o número")),
					Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de estrlas")),
						new	Endereco(
									Integer.parseInt(JOptionPane.showInputDialog("Código")),
									JOptionPane.showInputDialog("Logradouro"),
									JOptionPane.showInputDialog("Número "),
									JOptionPane.showInputDialog("CEP")
									))));
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao());
		}
		
	}

}
