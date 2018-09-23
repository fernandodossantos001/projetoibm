package br.com.deschateie.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Endereco;
import br.com.deschateie.dao.EnderecoDAO;

public class EnderecoBO {
	
	
	public static String novoEnderecoPsicologo(Endereco endereco)throws Exception {
		
		
		//padronizacao
		endereco.setAll(endereco.getCdEndereco(),
						endereco.getCdAtendente(),
						endereco.getTipo().toUpperCase(),
						endereco.getLogradouro().toUpperCase(),
						endereco.getNumero(),
						endereco.getComplemento().toUpperCase(),
						endereco.getCep(),
						endereco.getBairro().toUpperCase(),
						endereco.getCidade().toUpperCase(),
						endereco.getUf().toUpperCase().trim().substring(0, 1),
						endereco.getPais().toUpperCase()
						);
		
		if(endereco.getTipo().length()>20) {
			return "Quantidade de caracterdes de  Tipo muito grande";
		}
		
		if (endereco.getLogradouro().length()>60) {
			return "Quantidade de caracterdes de  Logradouro muito grande";
		}
		
		if(endereco.getNumero().length()>20) {
			return "N�mero muito grande";
		}
		
		if (endereco.getComplemento().length()>10) {
			return "Quantidade de caracterdes de  Complemento muito grande";
		}
		
		if (endereco.getCep().length()>99999999) {
			return "N�mero de CEP muito grande";
		}
		
		if (endereco.getBairro().length()>30) {
			return "Quantidade de caracterdes de Bairro muito grande";
		}
		
		if (endereco.getCidade().length()>30) {
			return "Quantidade de caracterdes de Cidade muito grande";
			
		}
		
		if (endereco.getUf().length()>2) {
			return "Quantidade de caracterdes de  UF muito grande";
			
		}
		
		if (endereco.getCdAtendente()>99999) {
			return "C�digo do Atendente inv�lido, excede a quantidade de caracteres permitida";
		}
		
		if (endereco.getCdAtendente()<1) {
			return "Atendente n�o existe";
		}
		
		if(endereco.getCdEndereco()<1) {
			return "c�digo inv�lido";
		}
		
		if(endereco.getCdEndereco()>99999) {
			return "C�digo do endereco inv�lido, excede a quantidade de caracteres permitida";
		}
		
		
		if(endereco.getTipo().equals(null)) {
			return "O campo tipo n�o pode estar vazio";
		}
		
		if (endereco.getLogradouro().equals(null)) {
			return "O campo logradouro n�o pode estar vazio";
		}
		
		if(endereco.getNumero().equals(null)) {
			return "O campo numero n�o pode estar vazio";
		}
		
		
		
		if (endereco.getCep().length()== 0) {
			return "O campo Cep n�o pode estar vazio ";
		}
		
		if (endereco.getBairro().equals(null)) {
			return "O campo bairro n�o pode estar vazio";
		}
		
		if (endereco.getCidade().equals(null)) {
			return "O campo cidade n�o pode estar vazio";
			
		}
		
		if (endereco.getUf().equals(null)) {
			return "O campo uf n�o pode estar vazio";
		}	
		
		

		EnderecoDAO dao = new EnderecoDAO();
		Endereco ende = dao.consultarEnderecoPsicologo(endereco.getCdEndereco());
		String msg = null;
		if(ende.getCdEndereco()==0) {
			msg = dao.gravarEnderecoPsicologo(endereco);
			dao.fechar();
			return msg;
		}
		dao.fechar();
		return "Endereco J� existente";
	}

	public static String novoEnderecoVoluntario(Endereco endereco)throws Exception {

		
		
		//padronizacao
		endereco.setAll(endereco.getCdEndereco(),
						endereco.getCdAtendente(),
						endereco.getTipo().toUpperCase(),
						endereco.getLogradouro().toUpperCase(),
						endereco.getNumero(),
						endereco.getComplemento().toUpperCase(),
						endereco.getCep(),
						endereco.getBairro().toUpperCase(),
						endereco.getCidade().toUpperCase(),
						endereco.getUf().toUpperCase().trim().substring(0, 1),
						endereco.getPais().toUpperCase()
						);
		
		if(endereco.getCdEndereco()<1) {
			return "c�digo inv�lido";
		}
		
		if(endereco.getCdEndereco()>99999) {
			return "C�digo do endereco inv�lido, excede a quantidade de caracteres permitida";
		}
		
		if(endereco.getTipo().length()>20) {
			return "Quantidade de caracterdes de  Tipo muito grande";
		}
		
		if (endereco.getLogradouro().length()>60) {
			return "Quantidade de caracterdes de  Logradouro muito grande";
		}
		
		if(endereco.getNumero().length()>20) {
			return "N�mero muito grande";
		}
		
		if (endereco.getComplemento().length()>10) {
			return "Quantidade de caracterdes de  Complemento muito grande";
		}
		
		if (endereco.getCep().length()>99999999) {
			return "N�mero de CEP muito grande";
		}
		
		if (endereco.getBairro().length()>30) {
			return "Quantidade de caracterdes de Bairro muito grande";
		}
		
		if (endereco.getCidade().length()>30) {
			return "Quantidade de caracterdes de Cidade muito grande";
			
		}
		
		if (endereco.getUf().length()>2) {
			return "Quantidade de caracterdes de  UF muito grande";
			
		}
		
		if (endereco.getCdAtendente()>99999) {
			return "C�digo do Atendente inv�lido";
		}
		
		if (endereco.getCdAtendente()<1) {
			return "Atendente n�o existe";
		}
		
		
		if(endereco.getTipo().equals(null)) {
			return "O campo tipo n�o pode estar vazio";
		}
		
		if (endereco.getLogradouro().equals(null)) {
			return "O campo logradouro n�o pode estar vazio";
		}
		
		if(endereco.getNumero().equals(null)) {
			return "O campo numero n�o pode estar vazio";
		}
		
		
		
		if (endereco.getCep().length()== 0) {
			return "O campo Cep n�o pode estar vazio n�o pode estar vazio";
		}
		
		if (endereco.getBairro().equals(null)) {
			return "O campo bairro n�o pode estar vazio";
		}
		
		if (endereco.getCidade().equals(null)) {
			return "O campo cidade n�o pode estar vazio";
			
		}
		
		if (endereco.getUf().equals(null)) {
			return "O campo uf n�o pode estar vazio";
		}	
		
		

		EnderecoDAO dao = new EnderecoDAO();
		Endereco ende = dao.consultarEnderecoVoluntario(endereco.getCdEndereco());
		String msg = null;
		if(ende.getCdEndereco()==0) {
			msg = dao.gravarEnderecoVoluntario(endereco);
			dao.fechar();
			return msg;
		}
		dao.fechar();
		return "Endereco J� existente";
	}

	
	public static List<Endereco> pesquisarEnderecoPsicologo(String logradouro)throws Exception{
		
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		
		EnderecoDAO dao = new EnderecoDAO();
		listaEndereco = dao.consultarPorLogradouroEnderecoPsicologo(logradouro);
		dao.fechar();
		return listaEndereco;
		
	
	}

	public static List<Endereco> pesquisarEnderecoVoluntario(String logradouro)throws Exception{
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		
		EnderecoDAO dao = new EnderecoDAO();
		listaEndereco = dao.consultarPorLogradouroEnderecoVoluntario(logradouro);
		dao.fechar();
		return listaEndereco;
		
	
	}

	
	public static String excluirEnderecoPsicologo(int cdEndereco)throws Exception{
		if(cdEndereco < 0) {
			return "codigo endereco inv�lido";
		}
		
		if(cdEndereco > 99999999) {
			return "quantidade de caracteres ultrapassa o permitido ";
		}
		
		EnderecoDAO dao = new EnderecoDAO();
		Endereco endereco = dao.consultarEnderecoPsicologo(cdEndereco);
		
		if(endereco.getCdEndereco()==0) {
			dao.fechar();
			return "n�o foi possivel localizar um endereco com esse cep para ser apagado";
		}
		
		dao.apagarEndereco(cdEndereco);
		dao.fechar(); 
		return "O endereco foi apagado com sucesso" ;
	}

	public static Endereco pesquisarEnderecoCodPsicologo(int codPsicologo)throws Exception{
		if (codPsicologo<1) {
			return new Endereco();
		}
		
		if (codPsicologo>99999) {
			return new Endereco();
		}
		
		EnderecoDAO dao = new EnderecoDAO();
		Endereco e = new Endereco();
		e = dao.consultarEnderecoCodPsicologo(codPsicologo);
		dao.fechar();
		return e;
	}
	
	
	public static Endereco pesquisarEnderecoCodVoluntario(int codVoluntario)throws Exception{
		if (codVoluntario<1) {
			return new Endereco();
		}
		
		if (codVoluntario>99999) {
			return new Endereco();
		}
		
		EnderecoDAO dao = new EnderecoDAO();
		Endereco e = new Endereco();
		e = dao.consultarEnderecoCodVoluntario(codVoluntario);
		dao.fechar();
		return e;
	}
	

	public static String excluirEnderecoVoluntario(int cdEndereco)throws Exception{
		if(cdEndereco < 0) {
			return "c�digo endereco inv�lido";
		}
		
		if(cdEndereco > 999999) {
			return "quantidade de caracteres ultrapassa o permitido ";
		}
		
		EnderecoDAO dao = new EnderecoDAO();
		Endereco endereco = dao.consultarEnderecoVoluntario(cdEndereco);
		
		if(endereco.getCdEndereco() == 0) {
			dao.fechar();
			return "n�o foi possivel localizar um endereco com esse cep para ser apagado";
		}
		
		dao.apagarEndereco(cdEndereco);
		dao.fechar(); 
		return "O endereco foi apagado com sucesso" ;
	}

	public static String alterarDadosEnderecoPsicologo(Endereco endereco)throws Exception{
		
		if(endereco.getCdEndereco()<1) {
			return "c�digo inv�lido";
		}
		
		if(endereco.getCdEndereco()>99999) {
			return "C�digo do endereco inv�lido, excede a quantidade de caracteres permitida";
		}
		if(endereco.getTipo().length()>20) {
			return "Quantidade de caracterdes de  Tipo muito grande";
		}
		
		if (endereco.getLogradouro().length()>60) {
			return "Quantidade de caracterdes de  Logradouro muito grande";
		}
		
		if(endereco.getNumero().length()>20) {
			return "N�mero muito grande";
		}
		
		if (endereco.getComplemento().length()>10) {
			return "Quantidade de caracterdes de  Complemento muito grande";
		}
		
		if (endereco.getCep().length()>99999999) {
			return "N�mero de CEP muito grande";
		}
		
		if (endereco.getBairro().length()>30) {
			return "Quantidade de caracterdes de Bairro muito grande";
		}
		
		if (endereco.getCidade().length()>30) {
			return "Quantidade de caracterdes de Cidade muito grande";
			
		}
		
		if (endereco.getUf().length()>2) {
			return "Quantidade de caracterdes de  UF muito grande";
			
		}
		
		if (endereco.getCdAtendente()>99999) {
			return "C�digo do Atendente inv�lido";
		}
		
		if (endereco.getCdAtendente()<1) {
			return "Atendente n�o existe";
		}
		
		
		if(endereco.getTipo().equals(null)) {
			return "O campo tipo n�o pode estar vazio";
		}
		
		if (endereco.getLogradouro().equals(null)) {
			return "O campo logradouro n�o pode estar vazio";
		}
		
		if(endereco.getNumero().equals(null)) {
			return "O campo numero n�o pode estar vazio";
		}
		
		
		
		if (endereco.getCep().length()== 0) {
			return "O campo Cep n�o pode estar vazio n�o pode estar vazio";
		}
		
		if (endereco.getBairro().equals(null)) {
			return "O campo bairro n�o pode estar vazio";
		}
		
		if (endereco.getCidade().equals(null)) {
			return "O campo cidade n�o pode estar vazio";
			
		}
		
		if (endereco.getUf().equals(null)) {
			return "O campo uf n�o pode estar vazio";
		}
		
		EnderecoDAO dao = new EnderecoDAO();
		Endereco ende = new Endereco();
		
		ende = dao.consultarEnderecoPsicologo(endereco.getCdEndereco());
		
		if(ende.getCdEndereco() != 0) {
			
			endereco.setAll(endereco.getCdEndereco(),
					endereco.getCdAtendente(),
					endereco.getTipo().toUpperCase(),
					endereco.getLogradouro().toUpperCase(),
					endereco.getNumero(),
					endereco.getComplemento().toUpperCase(),
					endereco.getCep(),
					endereco.getBairro().toUpperCase(),
					endereco.getCidade().toUpperCase(),
					endereco.getUf().toUpperCase().trim().substring(0, 1),
					endereco.getPais().toUpperCase()
					);
			
			String resp = dao.alterarEnderecoPsicologo(endereco);

			

			dao.fechar();
			return resp;
		}
		
		
		
		dao.fechar();
		return "c�digo de endereco n�o encontrado";
		
		
		
		
		
	}
	
	
	public static String alterarDadosEnderecoVoluntario(Endereco endereco)throws Exception{
		
		
		if(endereco.getCdEndereco()<1) {
			return "c�digo inv�lido";
		}
		
		if(endereco.getCdEndereco()>99999) {
			return "C�digo do endereco inv�lido, excede a quantidade de caracteres permitida";
		}
		
		if(endereco.getTipo().length()>20) {
			return "Quantidade de caracterdes de  Tipo muito grande";
		}
		
		if (endereco.getLogradouro().length()>60) {
			return "Quantidade de caracterdes de  Logradouro muito grande";
		}
		
		if(endereco.getNumero().length()>20) {
			return "N�mero muito grande";
		}
		
		if (endereco.getComplemento().length()>10) {
			return "Quantidade de caracterdes de  Complemento muito grande";
		}
		
		if (endereco.getCep().length()>99999999) {
			return "N�mero de CEP muito grande";
		}
		
		if (endereco.getBairro().length()>30) {
			return "Quantidade de caracterdes de Bairro muito grande";
		}
		
		if (endereco.getCidade().length()>30) {
			return "Quantidade de caracterdes de Cidade muito grande";
			
		}
		
		if (endereco.getUf().length()>2) {
			return "Quantidade de caracterdes de  UF muito grande";
			
		}
		
		if (endereco.getCdAtendente()>99999) {
			return "C�digo do Atendente inv�lido";
		}
		
		if (endereco.getCdAtendente()<1) {
			return "Atendente n�o existe";
		}
		
		
		if(endereco.getTipo().equals(null)) {
			return "O campo tipo n�o pode estar vazio";
		}
		
		if (endereco.getLogradouro().equals(null)) {
			return "O campo logradouro n�o pode estar vazio";
		}
		
		if(endereco.getNumero().equals(null)) {
			return "O campo numero n�o pode estar vazio";
		}
		
		
		
		if (endereco.getCep().length()== 0) {
			return "O campo Cep n�o pode estar vazio n�o pode estar vazio";
		}
		
		if (endereco.getBairro().equals(null)) {
			return "O campo bairro n�o pode estar vazio";
		}
		
		if (endereco.getCidade().equals(null)) {
			return "O campo cidade n�o pode estar vazio";
			
		}
		
		if (endereco.getUf().equals(null)) {
			return "O campo uf n�o pode estar vazio";
		}

		EnderecoDAO dao = new EnderecoDAO();
		Endereco ende = new Endereco();
		
		ende = dao.consultarEnderecoVoluntario(endereco.getCdEndereco());
		
		if(ende.getCdEndereco() != 0) {
			
			endereco.setAll(endereco.getCdEndereco(),
					endereco.getCdAtendente(),
					endereco.getTipo().toUpperCase(),
					endereco.getLogradouro().toUpperCase(),
					endereco.getNumero(),
					endereco.getComplemento().toUpperCase(),
					endereco.getCep(),
					endereco.getBairro().toUpperCase(),
					endereco.getCidade().toUpperCase(),
					endereco.getUf().toUpperCase().trim().substring(0, 1),
					endereco.getPais().toUpperCase()
					);
			
			String resp = dao.alterarEnderecoVoluntario(endereco);

			

			dao.fechar();
			return resp;
		}
		
		
		
		dao.fechar();
		return "c�digo de endereco n�o encontrado";
		
		
		
	}
	
	
}
