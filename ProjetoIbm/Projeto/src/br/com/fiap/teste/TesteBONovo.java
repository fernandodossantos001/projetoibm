package br.com.fiap.teste;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Endereco;
import br.com.fiap.bo.ClienteBO;
import br.com.fiap.excecao.Excecao;

public class TesteBONovo {

	public static void main(String[] args) {
	try {
		System.out.println(ClienteBO.novoCliente
				(new Cliente(JOptionPane.showInputDialog("Digite seu nome")
						,Integer.parseInt(JOptionPane.showInputDialog("Informe o número do cliente"))
						,Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de estrelas")),
						new Endereco(
								Integer.parseInt(JOptionPane.showInputDialog("Código")),
								JOptionPane.showInputDialog("Logradouro"),
								JOptionPane.showInputDialog("Número "),
								JOptionPane.showInputDialog("CEP")
								))));
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println(Excecao.tratarExcecao(e));
	}
		
	}

}
