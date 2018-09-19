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

	public static String novaAvaliacaoV(Avaliacao av, int nrPermissao)throws Exception{
		if (av.getCodAvaliacao()<0) {
			return "codigo invalido";
		}
		
		if (av.getCodAvaliacao()>99999) {
			return "codigo muito grande"; 
		}
		if (av.getResultado().length()<0) {
			return "O campo resultado n�o pode esta vazio";
		}
		
		if(av.getResultado().length()>80) {
			return "resultado muito grande";
		}
		
		String status = DataBO.validarData(av.getDataAvaliacao());
		if(!status.equals(av.getDataAvaliacao())) {
			return status;
		}
		
		Avaliacao avu = pesquisarAvaliacao(av.getCodAvaliacao());
		if(avu.getCodAvaliacao()== av.getCodAvaliacao()) {
			return "codigo ja existente";
		}
		
		

		
		Usuario usuario = UsuarioBO.pesquisarUsuarioPorCod(av.getUsuario().getCodUsuario());
		Psicologo psicologo = PsicologoBO.pesquisarPsicologo(av.getPsicologo().getCodPsicologo());
		if(usuario.getCodUsuario()!= av.getUsuario().getCodUsuario()) {
			return "o codigo do usuario nao foi encontrado";
		}
		
		if(psicologo.getCodPsicologo()!= av.getPsicologo().getCodPsicologo()) {
			return "o codigo do psicolo nao foi encontrado";
		}
		
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		
		if (dao.consultarAvaliacaoUsuario(av.getUsuario().getCodUsuario()).getUsuario().getCodUsuario() == av.getUsuario().getCodUsuario()) {
			return "Usuario j� existe";
		}
		
		avu.getUsuario().setNivelPermissao(nrPermissao);
		UsuarioBO.alterarNivelAcesso(avu.getUsuario());
		
		dao.gravarDadosAvaliacao(av);
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

	
	public static String alteradaDadosAvaliacao(Avaliacao av)throws Exception {
		
		String status = DataBO.validarData(av.getDataAvaliacao());
		if(!status.equals(av.getDataAvaliacao())) {
			return status;
		}
		
		if(av.getResultado().length()<0) {
			return "resultado nao pode estar vazio";
		}
		
		if (av.getResultado().length()>80) {
			return "resultado muito grande";
		}
		
		Avaliacao ava = pesquisarAvaliacao(av.getCodAvaliacao());
		if(ava.getCodAvaliacao()==0) {
			return "Avaliacao n�o encontradao";
		}
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		dao.alterarDadosAvaliacao(av);
		dao.fechar();
		return "Dados Alterados com suceso";
	}
	
	
}
