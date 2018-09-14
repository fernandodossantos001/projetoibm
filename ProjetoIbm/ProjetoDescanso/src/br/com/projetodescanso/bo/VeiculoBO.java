package br.com.projetodescanso.bo;

import br.com.projetodescanso.beans.Veiculo;
import br.com.projetodescanso.dao.VeiculoDAO;

/**
 * classe para validar dados para tabela T_DD_VEICULO
 * possui m�todos para criar, reduzir valor de venda do ve�culo, excluir ve�culo, alterar e pesquisar ve�culos
 * @version 1.0
 * @since 1.0
 * @see Veiculo
 * @see VeiculoDAO
 * @author Fernando Dos Santos Ribeiro
 *
 */
public class VeiculoBO {
	
	public static String novoVeiculo(Veiculo veiculo)throws Exception {
		
		//regras de neg�cio

		
		if(veiculo.getAnoFab() < 2000) {
		return "data m�nima ultrapassada";
		}
		if(veiculo.getValorVenda() < veiculo.getValorCompra()) {
			return "o valor de venda deve ser maior que o de compra";
		}
				
		if(veiculo.getCor().trim().isEmpty()) {
			return "A cor n�o pode estar vazio";
		}
				
		
		//valida��es
		if(veiculo.getPlaca().length() > 8 ) {
			return "placa longa";
		}
		
		
		
		if(veiculo.getValorVenda() < 0) {
		
			return "valor inv�lido";
		}
		
		if(veiculo.getModelo().length() > 20) {
			
			return "modelo ultrapassa a quantidade de caracteres permitidas";
		}
		
		if(veiculo.getCor().length() > 20) {
			
			return "cor ultrapassa a quantidade de caracteres permitidas";
		}
		
		if(veiculo.getModelo().trim().isEmpty()) {
			
			return "O modelo n�o pode estar vazio";
		}
		
		if(veiculo.getPlaca().trim().isEmpty()) {
			
			return "A placa n�o pode estar vazia";
		}
		
		if(veiculo.getValorVenda() == 0){
		
			return "O valor de venda n�o pode estar vazio";
		}
		
		
	
		VeiculoDAO dao = new VeiculoDAO();
		// regra de negocio
		Veiculo placa = new VeiculoDAO().consultarPorPlaca(veiculo.getPlaca());
		if(placa.getValorVenda() > 0) {
			dao.fechar();
			return "placa j� existe";
		}
		
		
		
		//padroniza��o
		veiculo.setCor(veiculo.getCor().toUpperCase());
		veiculo.setModelo(veiculo.getModelo().toUpperCase());
		veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		
		
		
		String msg = dao.gravarDados(veiculo);
		dao.fechar();
		return msg;
		
	}

	
	public static String reduzirValorVenda() throws Exception{
		 VeiculoDAO veiculo = new VeiculoDAO();
		 int qtLinhas = veiculo.depreciar();
		 veiculo.fechar();
		 return qtLinhas + "foram alteradas";
	}
}
