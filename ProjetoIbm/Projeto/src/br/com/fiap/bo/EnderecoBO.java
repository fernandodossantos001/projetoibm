package br.com.fiap.bo;

import br.com.fiap.beans.Endereco;
import br.com.fiap.dao.EnderecoDAO;

public class EnderecoBO {

	public static String novoEndereco(Endereco e) throws Exception{
		//regras de negócio
		if(e.getLogradouro().equals(null)) {
			return "Logradouo vazio !";
		}
		
		if(e.getCodigo()<1) {
			return "Código não existe";
		}
		
		e.setAll(e.getCodigo(), e.getLogradouro().toUpperCase(), e.getNumero(), e.getCep());
		
		EnderecoDAO dao = new EnderecoDAO();
		
		Endereco ende = dao.consultarPorCod(e.getCodigo()); 
		String resp = null;
		
		
		if(ende.getCodigo() == 0) {
			
			resp = dao.gravar(ende); 
			dao.fechar();
			return resp;
		}
		
		dao.fechar();
		
		return "Já existe";
	}

	
}
