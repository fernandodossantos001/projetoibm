package br.com.deschateie.bo;


import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.dao.VoluntarioDAO;

/**
 *  Classe para validar os dados para tebela T_SCP_VOLUNTARIO
 * possui m�todos para criar,pesquisar,alterar e excluir um Voluntario
 * @author Deschateie
 * @since 1.0
 * @version 1.0
 * @see Voluntario
 * @see VoluntarioDAO
 */
public class VoluntarioBO {

	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas Voluntario
	 * Regras avaliadas
	 * Verifica se o codigo do volunt�rio � valido
	 * @param codVoluntario Recebe um n�mero inteiro do codigo do Voluntario
	 * @return Retorna um Objeto do tipo Voluntario
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static Voluntario pesquisarVoluntario(int codVoluntario)throws Exception {
		if(codVoluntario <1) {
			return new Voluntario();
		}
		
		if(codVoluntario>99999) {
			return new Voluntario();
		}
		
		VoluntarioDAO dao = new VoluntarioDAO();
		Voluntario vo = dao.consultarVoluntario(codVoluntario);
		dao.fechar();
		return vo;
		 
		
	}

	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas Voluntario
	 * Regras avaliadas
	 * 1 Verifica se o c�digo do Voluntario � valido
	 * 2 Verifica o tamanho da formacao
	 * 3 Verifica o tamanha do per�odo
	 * 4 Verifica o tamanha do comentario
	 * 5 Verifica o tamanho do campo telefone
	 * 6 Verifica se o RG j� existe
	 * 7 Verifica se o CPF j� existe
	 * 8 Verifica se o usuario que foi passado existe
	 * 9  Verifica se o usuario que est� se cadastrando como PsiOnline j� faz parte da plataforma ou n�o,
	 * se ele fizer ele vai apenas complementar os dados mas, se n�o fizer ser� necess�rio cadastrar todos os dados dele
	 * 10 Verifica se o usuario que foi passado existe
	 * @param v Recebe um Objeto do tipo Voluntario
	 * @param ehValido Recebe um boolean para validar uma regra de neg�cio
	 * @return Retorna uma Stirng informando uma mensagem de erro ou sucesso se nenhuma das regras 
	 * acima for quebrada
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String novoVoluntario(Voluntario v,boolean ehValido)throws Exception{
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
		
		if (volu.getRg().length()<1) {
			return "RG n�o pode estar vazio";
		}
		
		if(volu.getCpf()== v.getCpf()) {
			dao.fechar();
			return "CPF j� existente";
		}
		
		
		v.setNivelPermissao(7);
		
		if (!ehValido) {
			String status = UsuarioBO.novoUsuario(v);		
			if (!status.equals( "Usu�rio cadastrado com sucesso")) {
				
				return status;
			}
		}
		
		if(UsuarioBO.pesquisarUsuarioPorCod(volu.getCodUsuario()).getCodUsuario()<1) {
			return "Usuario n�o existe";
		}
		
		UsuarioBO.alterarNivelAcesso(v);
		
		dao.gravarVoluntario(v);
		return "Cadastrado com sucesso";
	}

	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas Voluntario
	 * Regras Avaliada
	 * 1 Verifica se o codigo do Voluntario � valido
	 * 2 Verifica se o Voluntarioe existe
	 * @param codVoluntario Recebe um n�mero inteiro do codigo do Voluntario
	 * @return Retorna uma String informando um erro ou sucesso caso nenhuma das regras acima 
	 * sejam quebradas
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
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

	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas Voluntario
	 * Regras avaliadas
	 * 1 Verifica se o c�digo do Voluntario � valido
	 * 2 Verifica o tamanho da formacao
	 * 3 Verifica o tamanha do per�odo
	 * 4 Verifica o tamanha do comentario
	 * 5 Verifica o tamanho do campo telefone
	 * 6 Verifica se o RG j� existe
	 * 7 Verifica se o CPF j� existe
	 * 8 Verifica se o usuario que foi passado existe
	 * @param v Recebe um Objeto do tipo Voluntario
	 * @return Retorna uma Stirng informando uma mensagem de erro ou sucesso se nenhuma das regras 
	 * acima for quebrada
	 * @throws Exception chamada da exce��o checked SQLException
	 */
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
		
		if (v.getRg().length()<1) {
			return "RG n�o pode estar vazio";
		}
		VoluntarioDAO dao = new VoluntarioDAO();

		Voluntario volu = new Voluntario();
		volu = dao.consultarVoluntario(v.getCodVoluntario());
		
		if(v.getCpf() != volu.getCpf()) {
			if(v.getCpf() == dao.consultarVoluntarioCpf(v.getCpf()).getCpf()) {
				dao.fechar();
				return "cpf j� existe";			}
		}
		
		if(v.getRg() != volu.getRg()) {
			if(v.getRg() == dao.consultarVoluntarioRg(v.getRg()).getRg()) {
				dao.fechar();
				return "cpf j� existe";			}
		}
		
		
		String status = UsuarioBO.AlterarDadosUsuario(v);
		
		if (!status.equals( "Usu�rio alterado com Sucesso")) {
			return status;
		}
		
		
		UsuarioBO.alterarNivelAcesso(v);
		
		dao.alterarDadosVoluntario(v);
		dao.fechar();
		return "Alterado com sucesso";
	}

	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas Voluntario
	 * @return Retorna um ArrayList do tipo PsiOnline apenas com os Voluntario tempor�rio
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static List<Voluntario> pesquisarVoluntarioTemporarios()throws Exception{
	List<Voluntario> listaVoluntario = new ArrayList<Voluntario>();
	VoluntarioDAO dao = new VoluntarioDAO();
	listaVoluntario = dao.consultarVoluntario();
	dao.fechar();
	return listaVoluntario;
	}
}
