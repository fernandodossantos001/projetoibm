package br.com.deschateie.bo;


import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.dao.VoluntarioDAO;

public class VoluntarioBO {

	public static Voluntario pesquisarVoluntario(int codVoluntario)throws Exception {
		if(codVoluntario <1) {
			return new Voluntario();
		}
		
		if(codVoluntario>99999) {
			return new Voluntario();
		}
		
		VoluntarioDAO dao = new VoluntarioDAO();
		Voluntario v = dao.consultarVoluntario(codVoluntario);
		 if(v==null) {
			 return new Voluntario();
		 }
		 
		 return new Voluntario(
				 				v.getCodUsuario(),
				 				v.getNomeUsuario(),
				 				v.getEmail(),
				 				v.getDataNascimento(),
				 				v.getLogin(),
				 				v.getSenha(),
				 				v.getNivelPermissao(),
				 				v.getFoto(),
				 				v.getGenero(),
				 				v.getRg(),
				 				v.getCpf(),
				 				v.getFormacao(),
				 				v.getPeriodo(), 
				 				v.getComentario(),
				 				v.getTelefone());
		
	}

	public static String novoVoluntario(Voluntario v)throws Exception{
		if (v.getCodVoluntario()<0) {
			return "Codigo voluntario invalido";
		}
		
		if(v.getCodVoluntario()>99999) {
			return "Codigo muito grande";
		}
		
		if (v.getFormacao().length() < 1) {
			return "o campo formacao nao pode estar vazio";
		}
		
		if (v.getFormacao().length()>60) {
			return "formacao muito grande";
		}
		
		
		if (v.getPeriodo().length()<0) {
			return "o campo perido nao pode estar vazio";
		}
		
		if(v.getPeriodo().length()> 40) {
			return "periodo muito grande ";
		}
		
		if (v.getComentario().length()<0) {
			return "o campo comentario nao pode estar vazio";
		}
		
		if (v.getComentario().length()>80) {
			return "comentario muito grande";
		}
		
		if(v.getTelefone()<0) {
			return "o campo telefone n�o pode estar vazio";
		}
		if(v.getTelefone()>99999999999l) {
			return "telefone muito grande";
		}
		
		VoluntarioDAO dao = new VoluntarioDAO();
		Voluntario volu = dao.consultarVoluntario(v.getCodVoluntario());

		if(volu.getRg().equalsIgnoreCase(v.getRg())) {
			dao.fechar();
			return "RG j� existente";
		}
		
		if(volu.getCpf()== v.getCpf()) {
			dao.fechar();
			return "CPF j� existente";
		}
		
		
		
		String status = UsuarioBO.novoUsuario(v);
		
		if (!status.equals( "Usu�rio cadastrado com sucesso")) {
			
			return status;
		}
		
		
		dao.gravarVoluntario(v);
		return "Cadastrado com sucesso";
	}

	public static String  excluirVoluntario(int codVoluntario)throws Exception {
		if(codVoluntario <0 ) {
			return "codigo invalido";
		}
		
		if (codVoluntario >99999) {
			return "codigo muito grande";
		}
		
		
		Voluntario v = VoluntarioBO.pesquisarVoluntario(codVoluntario);
		
		if(v.getCodVoluntario()<1) {
			return "Voluntario nao encontrado";
		}
		 
		VoluntarioDAO dao  = new VoluntarioDAO();
		String status = dao.excluirVoluntario(codVoluntario);
		
		dao.fechar();
		return status;
	}

	public static String alterarDadosVoluntario(Voluntario v)throws Exception{
		
		if (v.getCodVoluntario()<0) {
			return "Codigo voluntario invalido";
		}
		
		if(v.getCodVoluntario()>99999) {
			return "Codigo muito grande";
		}
		
		if (v.getFormacao().length() < 1) {
			return "o campo formacao nao pode estar vazio";
		}
		
		if (v.getFormacao().length()>60) {
			return "formacao muito grande";
		}
		
		
		if (v.getPeriodo().length()<0) {
			return "o campo perido nao pode estar vazio";
		}
		
		if(v.getPeriodo().length()> 40) {
			return "periodo muito grande ";
		}
		
		if (v.getComentario().length()<0) {
			return "o campo comentario nao pode estar vazio";
		}
		
		if (v.getComentario().length()>80) {
			return "comentario muito grande";
		}
		
		if(v.getTelefone()<0) {
			return "o campo telefone n�o pode estar vazio";
		}
		if(v.getTelefone()>99999999999l) {
			return "telefone muito grande";
		}
		
		VoluntarioDAO dao = new VoluntarioDAO();

		Voluntario volu = new Voluntario();
		volu = dao.consultarVoluntario(v.getCodVoluntario());
		
		if(v.getCpf() != volu.getCpf()) {
			if(v.getCpf() == dao.consultarVoluntarioCpf(v.getCpf()).getCpf()) {
				dao.fechar();
				return "cpf j� existe";			}
		}
		
		
		
		String status = UsuarioBO.AlterarDadosUsuario(v);
		
		if (!status.equals( "Usu�rio alterado com Sucesso")) {
			return status;
		}
		
		
		dao.alterarDadosVoluntario(v);
		dao.fechar();
		return "Alterado com sucesso";
	}

}
