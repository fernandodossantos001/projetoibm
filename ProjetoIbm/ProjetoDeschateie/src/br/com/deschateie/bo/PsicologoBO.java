package br.com.deschateie.bo;

import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.dao.PsicologoDAO;

/**
 *  Classe para validar os dados para tebela T_SCP_PSICOLOGO
 * possui m�todos para criar,pesquisar,alterar e excluir um psicologo
 * @author Deschateie
 * @since 1.0
 * @version 1.0
 * @see Psicologo
 * @see PsicologoDAO
 */
public class PsicologoBO {
	
	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas Psicologo
	 * Regras avaliadas
	 * Verifica se o codigo do psicologo � valido
	 * @param Recebe um n�mero inteiro do codigo do psicologo
	 * @return Retorna um Objeto do tipo Psicologo
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static Psicologo pesquisarPsicologo(int codPsicologo)throws Exception {
		
		if(codPsicologo<1 || codPsicologo > 99999) {
			return new Psicologo();
		}
		
		PsicologoDAO dao = new PsicologoDAO();
		Psicologo p = dao.consultarPsicologo(codPsicologo);
		
		
		dao.fechar();
		return p ;
		
		
		
		
	}

	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas Psicologo
	 * Regras avaliadas
	 * Verifica se o codigo do psicologo � valido
	 * Verifica se o psicologo existe
	 * @param Recebe um n�mero inteiro do codigo do psicologo
	 * @return Retorna uma String informando um erro ou sucesso caso nenhuma das
	 * regras acima seja quebrada
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String excluirPsicologo(int codPsicologo)throws Exception {
	
		if (codPsicologo<0) {
			return "codigo invalido";
		}
		if(codPsicologo>99999) {
			return "c�digo muito grande";
		}
		
		
		Psicologo p = pesquisarPsicologo(codPsicologo);
		
		if(p.getCodPsicologo()<1) {
			return "Psicologo n�o encontrado";
		}
		
		PsicologoDAO dao = new PsicologoDAO();
		
		dao.excluirPsicologo(codPsicologo);
		UsuarioBO.excluirUsuario(codPsicologo);
		UsuarioBO.excluirUsuario(codPsicologo);
		dao.fechar();
		return "Psicologo excluido com sucesso ";
	}

	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas Psicologo
	 * Regras avaliadas
	 * 1 Verifica se o codigo do psicologo � valido
	 * 2 Verifica o tamnho da formacao
	 * 3 Verifica o tamanho da biografia
	 * 4 Verifica o tamanho do telefone
	 * 5 Verifica o tamanho a url da foto
	 * 6 Verifica o tamanho do valor da consulta
	 * 7 Verifica se o CRP � valido
	 * 8 Verifica se o CRP j� existe
	 * 9 Verifica se a usuario que est� se cadastrando como psicologo j� faz parte da plataforma ou n�o,
	 * se ele fizer ele vai apenas complementar os dados mas, se n�o fizer ser� necess�rio cadastrar todos os dados dele
	 * 10 Verifica se o usu�rio existe no banco de dados
	 * 11 Verifica se o c�digo do psicologo j� existe
	 * @param Recebe um Objeto do tipo Psicologo
	 * @return Retorna uma String informando um erro ou sucesso caso nenhuma das
	 * regras acima seja quebrada
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String  NovoPsicologo(Psicologo p, boolean ehValido) throws Exception{
		
		if(p.getCodUsuario()<0) {
			return "c�digo inv�lido";
		}
		
		if(p.getCodUsuario()>99999) {
			return "codigo muito grande";
		}
		 if(p.getFormacao().length()<0) {
			 return "formaco nao pode estar vazia";
		 }
		 
		 if (p.getFormacao().length()>120) {
			return"formacao muito grande";		}
		 
		 if (p.getBiografia().length()>120) {
			return"biografia muito grande";
		}
		 
		 if (p.getTelefone()<0) {
			return "telefone invalido";
		}
		 
		 if (p.getTelefone()>99999999999l) {
			return "telefone muito grande";
		}
		 
		 if (p.getFoto().length()>120) {
			return "url da foto muito grande";
		}
		 
		 if(p.getFoto().length()<1) {
			 return "url da foto n�o pode estar vazia";
		 }
		 
		 if (p.getValorConsulta()>99999999l) {
			return "valor da consulta muito grande";
		}
		if(p.getCrp()<0) {
			return "CRP invalido";
		}
		
		if(p.getCrp()>9999999) {
			return"CRP muito grande";
		}
		
		
		PsicologoDAO dao = new PsicologoDAO();
		Psicologo ps = dao.consultarPsicologoCrp(p.getCrp());
		 if(ps.getCrp()== p.getCrp()) {
			 dao.fechar();
			 return "CRP J� existente";
		 }
		 
		
		 p.setNivelPermissao(4);
		 if (!ehValido) {
			 String status = UsuarioBO.novoUsuario(p);
			 if (!status.equals("Usu�rio cadastrado com Sucesso")) {
				return status;
			}
		}
		 
		 
		 UsuarioBO.alterarNivelAcesso(p);
		 if(UsuarioBO.pesquisarUsuarioPorCod(p.getCodUsuario()).getCodUsuario()==0) {
				return "Usuario nao encontrado";
			}
			
		 if(pesquisarPsicologo(p.getCodPsicologo()).getCodPsicologo()>0) {
				return "O codigo do psicologo ja existe";
			}
		 
		 dao.GravarPsicologo(p);
		 dao.fechar();
		return "Psicologo cadastrado com Sucesso";
	}
	
	
	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas Psicologo
	 * Regras avaliadas
	 * 1 Verifica se o codigo do psicologo � valido
	 * 2 Verifica o tamnho da formacao
	 * 3 Verifica o tamanho da biografia
	 * 4 Verifica o tamanho do telefone
	 * 5 Verifica o tamanho a url da foto
	 * 6 Verifica o tamanho do valor da consulta
	 * 7 Verifica se o CRP � valido
	 * 8 Verifica se o CRP j� existe
	 * 10 Verifica se o usu�rio existe no banco de dados
	 * @param Recebe um Objeto do tipo Psicologo
	 * @return Retorna uma String informando um erro ou sucesso caso nenhuma das
	 * regras acima seja quebrada
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String  AlterarDadosPsicologo(Psicologo p) throws Exception{
		
		 if(p.getFormacao().length()<0) {
			 return "formaco nao pode estar vazia";
		 }
		 
		 if (p.getFormacao().length()>120) {
			return"formacao muito grande";		}
		 
		 if (p.getBiografia().length()>120) {
			return"biografia muito grande";
		}
		 
		 if (p.getTelefone()<0) {
			return "telefone invalido";
		}
		 
		 if (p.getTelefone()>99999999999l) {
			return "telefone muito grande";
		}
		 
		 if (p.getFoto().length()>120) {
			return "url da foto muito grande";
		}
		 
		 if(p.getFoto().length()<1) {
			 return "url da foto n�o pode estar vazia";
		 }
		 
		 if (p.getValorConsulta()>99999999d) {
			return "valor da consulta muito grande";
		}
		if(p.getCrp()<0) {
			return "CRP invalido";
		}
		
		if(p.getCrp()>9999999) {
			return"CRP muito grande";
		}
		
		
		PsicologoDAO dao = new PsicologoDAO();
		Psicologo ps = dao.consultarPsicologo(p.getCodUsuario());
		
		
		if(p.getCrp()!=ps.getCrp()) {
			
			 if(p.getCrp() == dao.consultarPsicologoCrp(p.getCrp()).getCrp()) {
				 dao.fechar();
				 return "CRP j� cadastrado";
			 }
		}
			
		
		 
		 String status = UsuarioBO.AlterarDadosUsuario(p);
		 if (!status.equals("Usu�rio alterado com Sucesso")) {
			return status;
		}
		 
		 
		 dao.alterarDadosPsicologo(p);
		 
		 dao.fechar();
		return "Psicologo atualizado com Sucesso";
	}


	
}
