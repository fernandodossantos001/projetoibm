package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.excecao.Excecao;

public class TesteConsularClienteNome {
	
	public static void main(String[] args) {
		try {
			List<Cliente> cliente = new ArrayList<Cliente>();
			ClienteDAO dao = new ClienteDAO();
			do {
			cliente = dao.consultarPorNome(JOptionPane.showInputDialog("Digite um nome"));
			for (Cliente cli : cliente) {
				System.out.println(cli.getAll());
			}
			}while(JOptionPane.showConfirmDialog(null,"Continuar","Pergunta",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
		
	}
}
	
