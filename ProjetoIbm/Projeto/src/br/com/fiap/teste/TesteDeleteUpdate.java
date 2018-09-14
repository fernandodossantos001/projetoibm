package br.com.fiap.teste;


import javax.swing.JOptionPane;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.excecao.Excecao;

public class TesteDeleteUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			ClienteDAO dao = new ClienteDAO();
			int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do cliente"));
			char resposta = JOptionPane.showInputDialog("Digite : \n" + "<A> para apagar \n" + "<B> para promover").toUpperCase().charAt(0);
			 if(resposta == 'A') {
				 dao.apagar(numero);
			 }else if(resposta == 'B') {
				 dao.promover(numero);
			 }else {
				 System.out.println("Opção inválida");
			 }
			 
			 for(Cliente cli : dao.consultarPorNome("")) {
				 System.out.println(cli.getAll());
			 }
			 
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}
