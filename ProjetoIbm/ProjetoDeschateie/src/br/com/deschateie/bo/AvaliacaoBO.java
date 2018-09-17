package br.com.deschateie.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.deschateie.beans.Avaliacao;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.dao.AvaliacaoDAO;

public class AvaliacaoBO {
	public static Avaliacao pesquisarAvaliacao(int codAvaliacao) throws Exception{
		
		if (codAvaliacao<1) {
			return new Avaliacao();
		}
		
		if (codAvaliacao>99999) {
			return new Avaliacao();
		}
		
		
		AvaliacaoDAO  dao = new AvaliacaoDAO(); 	 
		Avaliacao avaliacao = dao.consultarAvaliacao(codAvaliacao);		
		dao.fechar();
		return avaliacao;
	}

	public static String novaAvaliacaoVoluntario(int codAvaliacao, int codPsicologo, int codUsuario, String dataAvaliacao,String resultado)throws Exception{
		if (codAvaliacao<0) {
			return "codigo invalido";
		}
		
		if (codAvaliacao>99999) {
			return "codigo muito grande"; 
		}
		if (resultado.length()<0) {
			return "O campo resultado n�o pode esta vazio";
		}
		
		if(resultado.length()>80) {
			return "resultado muito grande";
		}
		
		Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		Matcher matcher = pattern.matcher(dataAvaliacao);
		
		if(!matcher.find()) {
			return "data inv�lido";
			
		}
		
		Avaliacao avu = pesquisarAvaliacao(codAvaliacao);
		if(avu.getCodAvaliacao()== codAvaliacao) {
			return "codigo ja existente";
		}
		
		

		
		Usuario usuario = UsuarioBO.pesquisarUsuarioPorCod(codUsuario);
		Psicologo psicologo = PsicologoBO.pesquisarPsicologo(codPsicologo);
		if(usuario.getCodUsuario()!= codUsuario) {
			return "o codigo do usuario nao foi encontrado";
		}
		
		if(psicologo.getCodPsicologo()!= codPsicologo) {
			return "o codigo do psicolo nao foi encontrado";
		}
		
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		
		if (dao.consultarAvaliacaoUsuario(codUsuario).getUsuario().getCodUsuario() == codUsuario) {
			return "Usuario j� existe";
		}
		
		dao.gravarDadosAvaliacao(codAvaliacao, codPsicologo, codUsuario,dataAvaliacao,resultado);
		dao.fechar();
		return "Avaliacao cadastrada com sucesso";
	}

	public static String excluirAvaliacaoVoluntario(int codAvaliacao)throws Exception{
		if(codAvaliacao<0) {
			return "codigo invalido";
		}
		
		if (codAvaliacao>99999) {
			return "codigo muito grande";
		}
		
		Avaliacao av = pesquisarAvaliacao(codAvaliacao);
		
		if(av.getCodAvaliacao()<1) {
			return "codigo nao existe";
		}
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		dao.excluirAvaliacao(codAvaliacao);
		dao.fechar();
		return "Excluido com sucesso";
	}

	
	public static String alteradaDadosAvaliacao(int codAvaliacao, String dataAvaliacao, String resultado)throws Exception {
		
		Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		Matcher matcher = pattern.matcher(dataAvaliacao);
		
		if(!matcher.find()) {
			return "data inv�lido";
			
		}
		
		if(resultado.length()<0) {
			return "resultado nao pode estar vazio";
		}
		
		if (resultado.length()>80) {
			return "resultado muito grande";
		}
		
		Avaliacao av = pesquisarAvaliacao(codAvaliacao);
		if(av.getCodAvaliacao()==0) {
			return "Avaliacao n�o encontradao";
		}
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		dao.alterarDadosAvaliacao(codAvaliacao, dataAvaliacao, resultado);
		dao.fechar();
		return "Dados Alterados com suceso";
	}
	
	
}
