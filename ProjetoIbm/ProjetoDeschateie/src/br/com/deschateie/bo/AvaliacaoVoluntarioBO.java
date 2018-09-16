package br.com.deschateie.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.deschateie.beans.AvaliacaoVoluntario;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.dao.AvaliacaoVoluntarioDAO;

public class AvaliacaoVoluntarioBO {
	public static AvaliacaoVoluntario pesquisarAvaliacao(int codAvaliacao) throws Exception{
		
		if (codAvaliacao<1) {
			return new AvaliacaoVoluntario();
		}
		
		if (codAvaliacao>99999) {
			return new AvaliacaoVoluntario();
		}
		AvaliacaoVoluntarioDAO  dao = new AvaliacaoVoluntarioDAO(); 
		AvaliacaoVoluntario avaliacao = dao.consultarAvaliacao(codAvaliacao);
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
		
		AvaliacaoVoluntario avu = pesquisarAvaliacao(codAvaliacao);
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
		
		AvaliacaoVoluntarioDAO dao = new AvaliacaoVoluntarioDAO();
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
		
		AvaliacaoVoluntario av = pesquisarAvaliacao(codAvaliacao);
		
		if(av.getCodAvaliacao()<1) {
			return "codigo nao existe";
		}
		
		AvaliacaoVoluntarioDAO dao = new AvaliacaoVoluntarioDAO();
		dao.excluirAvaliacao(codAvaliacao);
		dao.fechar();
		return "Excluido com sucesso";
	}
}
