package br.com.fiap.bo;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;

/**
 * Classe para validar dados para a tabela t_DDD_Cliente
 * possui m�todos para  criar um novo cliente, aumentar a quantidade de estrelas
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see ClienteDAO
 */
public class ClienteBO {
	
	
	/**
	 * M�todo respons�vel por manipular as regras relacionadas ao Cliente
	 * Regra de Neg�cio avaliadas:
	 * 1� Estrelas deve estar entre 1 e 5
	 * 2� Nome deve possuir no m�ximo 40 caracteres
	 * @param cli Recebe um objeto Cliente
	 * @return retorna a mesma String que recebe do ClienteDAO
	 * @throws Exception Excecao Checkd
	 * @author Deschateie
	 */
	public static String novoCliente(Cliente cli) throws Exception {
		// Regra de Neg�cio
		if(cli.getQtdeEstrelas() < 1 || cli.getQtdeEstrelas() > 5) {
			return "Quantidade de Estralas inv�lida !";
		}
		
		//Valida��o
		if(cli.getNome().length()>40) {
			return "Nome longo";
		}
		
		//Padroniza��o
		cli.setNome(cli.getNome().toUpperCase());
		
		
		
		ClienteDAO dao = new ClienteDAO();
		
		if(dao.consularPorNumero(cli.getNumero()).getNumero() > 0) {
			dao.fechar();
			return "O Cliente j� existe";
		}
		
		String y = EnderecoBO.novoEndereco(cli.getEndereco());
		if(y.equalsIgnoreCase("ok") || y == null) {
		String msg = dao.gravar(cli);
		dao.fechar();
		return  msg;
		}
		
		return "usu�rio n�o cadastrado";
	}
	
	
	public static String aumentarEstrela(int numeroCliente)throws Exception{
		if(numeroCliente < 1) {
			return "N�mero inv�lido";
		}
		
		
		ClienteDAO dao = new ClienteDAO();
		if(dao.consularPorNumero(numeroCliente).getQtdeEstrelas() == 5) {
			dao.fechar();
			return "N�o � poss�vel aumentar a quantidade de estrelas";
		}
		
		int x = dao.promover(numeroCliente);
		dao.fechar();
		
		return x + "Atualizado(s) com sucesso";
	}
}
