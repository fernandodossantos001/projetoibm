package br.com.fiap.bo;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;

/**
 * Classe para validar dados para a tabela t_DDD_Cliente
 * possui métodos para  criar um novo cliente, aumentar a quantidade de estrelas
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see ClienteDAO
 */
public class ClienteBO {
	
	
	/**
	 * Método responsável por manipular as regras relacionadas ao Cliente
	 * Regra de Negócio avaliadas:
	 * 1º Estrelas deve estar entre 1 e 5
	 * 2º Nome deve possuir no máximo 40 caracteres
	 * @param cli Recebe um objeto Cliente
	 * @return retorna a mesma String que recebe do ClienteDAO
	 * @throws Exception Excecao Checkd
	 * @author Deschateie
	 */
	public static String novoCliente(Cliente cli) throws Exception {
		// Regra de Negócio
		if(cli.getQtdeEstrelas() < 1 || cli.getQtdeEstrelas() > 5) {
			return "Quantidade de Estralas inválida !";
		}
		
		//Validação
		if(cli.getNome().length()>40) {
			return "Nome longo";
		}
		
		//Padronização
		cli.setNome(cli.getNome().toUpperCase());
		
		
		
		ClienteDAO dao = new ClienteDAO();
		
		if(dao.consularPorNumero(cli.getNumero()).getNumero() > 0) {
			dao.fechar();
			return "O Cliente já existe";
		}
		
		String y = EnderecoBO.novoEndereco(cli.getEndereco());
		if(y.equalsIgnoreCase("ok") || y == null) {
		String msg = dao.gravar(cli);
		dao.fechar();
		return  msg;
		}
		
		return "usuário não cadastrado";
	}
	
	
	public static String aumentarEstrela(int numeroCliente)throws Exception{
		if(numeroCliente < 1) {
			return "Número inválido";
		}
		
		
		ClienteDAO dao = new ClienteDAO();
		if(dao.consularPorNumero(numeroCliente).getQtdeEstrelas() == 5) {
			dao.fechar();
			return "Não é possível aumentar a quantidade de estrelas";
		}
		
		int x = dao.promover(numeroCliente);
		dao.fechar();
		
		return x + "Atualizado(s) com sucesso";
	}
}
