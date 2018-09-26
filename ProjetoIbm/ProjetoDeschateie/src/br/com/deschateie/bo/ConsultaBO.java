package br.com.deschateie.bo;

import java.util.ArrayList; 
import java.util.List;
import br.com.deschateie.beans.Consulta;
import br.com.deschateie.beans.Paciente;
import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.dao.ConsultaDAO;
/**
 * Classe para validar os dados para tebela T_SCP_CONSULTA
 * possui m�todos para criar,pesquisar,alterar e excluir a consulta
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see Consulta
 * @see ConsultaDAO
 * @see Paciente
 * @see PsiOnline
 *
 */
public class ConsultaBO {
	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * Regras avaliadas
	 * 1 Verifica se o codigo � valido
	 * @param codConsulta Recebe um n�mero inteiro do c�digo da consutla
	 * @return Retorna um Objeto do tipo Consulta
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static Consulta pesquisarConsulta(int codConsulta)throws Exception{
		
		if (codConsulta<1) {
			return new Consulta();
		}
		if (codConsulta>99999) {
			return new Consulta();
		}
		
		ConsultaDAO dao = new ConsultaDAO();
		Consulta c = new Consulta();
		c = dao.pesquisarConsultaPorCod(codConsulta);
		dao.fechar();
		return c;
	
		
	}

	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * @return Retorna um ArrayList do tipo Consulta
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static List<Consulta> pesquisarConsulta()throws Exception{
		List<Consulta> lista = new ArrayList<Consulta>();
		ConsultaDAO dao = new ConsultaDAO();
		lista = dao.pesquisarListaConsulta();
		dao.fechar();
		return lista;
	}
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * Regras avaliadas
	 * 1 Verifica se o codigo � valido
	 * 2 Verifica se a consulta existe
	 * 3 Verifica se a consulta j� foi paga,
	 * se ela foi paga n�o ter� como apagar
	 * @param codConsulta Recebe um n�mero inteiro do c�digo da consulta
	 * @return Retorna uma String informando um erro caso as regras acima n�o sejam seguidas
	 * ou uma mensagem de sucesso
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String excluirConsulta(int codConsulta)throws Exception{
		if(codConsulta<1) {
			return "codigo invalido";
		}
		
		if (codConsulta>99999) {
			return "codigo muito grande";
		}
		
		Consulta c = pesquisarConsulta(codConsulta);
		if (c.getCodConsulta()<1) {
			return "consulta nao encontrada, verificar o codigo";
		}
		
		if(PagamentoBO.pesquisarPagamentoCodConsulta(codConsulta).getCodConsulta()>1) {
			return "N�o � possivel apagar essa consulta pois ela j� foi paga";
		}
		ConsultaDAO dao = new ConsultaDAO();
		dao.excluirConsulta(codConsulta);
		dao.fechar();
		
		return "Excluido com sucesso";
	}

	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * Regras avaliadas
	 * 1 Verifica se o Psicologo Online j� est� na lista
	 * caso ele esteja o mesmo n�o ser� adicionado
	 * @return Retorna um ArrayList do tipo List com dois Objeto diferentes
	 * o primero Objeto � do tipo Consulta e estar� na primeira posi��o do ArrayList.
	 * O segundo Objeto � do tipo PsiOnline e estar� na �ltima posicao do ArrayList
	 * @throws Exception chamada da exce��o checked SQLException
	 * @author Deschateie
	 */
	public static List<List> pesquisarPsiOnline()throws Exception{
		boolean isTruePsi = false;
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		List<PsiOnline> listaPsiOnlines = new ArrayList<PsiOnline>();
		List<Consulta> listaConsultPsiOn = new ArrayList<Consulta>();
		List<List> listaConsultaPsiOnlines = new ArrayList<List>();
		
		
		ConsultaDAO dao = new ConsultaDAO();
		listaConsultas = dao.pesquisarListaConsulta();
		PsiOnline psiOnline;
		
		for (Consulta consultas: listaConsultas) {
			psiOnline = PsiOnlineBO.pesquisarPsicologoOnline(consultas.getCodPsiOnline());
			
			for(Consulta x : listaConsultPsiOn) {
				if(x.getCodPsiOnline() == psiOnline.getCodPsicologo()) {
					isTruePsi = true;
					break;
				}
			}
			
			if(!isTruePsi) {
				listaConsultPsiOn.add(consultas);
			}
			isTruePsi = false;
			
		}
		
		
		
		for (Consulta consulta : listaConsultPsiOn) {
			listaPsiOnlines.add(PsiOnlineBO.pesquisarPsicologoOnline(consulta.getCodPsiOnline()));
		}
		
		listaConsultaPsiOnlines.add(listaConsultPsiOn);
		listaConsultaPsiOnlines.add(listaPsiOnlines);
		
		return listaConsultaPsiOnlines;
				
		
	}
	
	/**
	  * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * Regras avaliadas
	 * 1 Verifica se o Paciente j� est� na lista
	 * caso ele esteja o mesmo n�o ser� adicionado
	 * @return Retorna um ArrayList do tipo List com dois Objeto diferentes
	 * o primero Objeto � do tipo Consulta e estar� na primeira posi��o do ArrayList.
	 * O segundo Objeto � do tipo Paciente e estar� na �ltima posicao do ArrayList
	 * @throws Exception chamada da exce��o checked SQLException
	 * @author Deschateie
	 */
	public static List<List> pesquisarPaciente()throws Exception{
		
		boolean isTrueP = false;
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		List<Paciente> listaPacientes = new ArrayList<Paciente>();
		List<Consulta> listaConsultPaciente = new ArrayList<Consulta>();
		List<List> listaConsultaPacientes = new ArrayList<List>();

		
		ConsultaDAO dao = new ConsultaDAO();
		listaConsultas = dao.pesquisarListaConsulta();
		Paciente paciente;
		for (Consulta consultas: listaConsultas) {
			paciente = PacienteBO.pesquisarPaciente(consultas.getCodPaciente());
			for(Consulta x : listaConsultPaciente) {
				if(x.getCodPaciente() == paciente.getCodPaciente()) {
					isTrueP = true;
					break;
				}
			}
			if(!isTrueP) {
				listaConsultPaciente.add(consultas);
			}
			isTrueP = false;
		}
		
		for (Consulta consulta : listaConsultPaciente) {
			listaPacientes.add(PacienteBO.pesquisarPaciente(consulta.getCodPaciente()));
		}
		
		listaConsultaPacientes.add(listaConsultPaciente);
		listaConsultaPacientes.add(listaPacientes);
		
		return listaConsultaPacientes;
	}
	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * Regras avaliadas
	 * 1 Verifica se o c�digo da consulta � valido
	 * 2 Verifica se o c�digo  da conversa � valido
	 * 3 Verifica se o c�digo do paciente � valido
	 * 4 Verifica se o c�digo do psicologo online � valido
	 * 5 Verifica se a data � valida
	 * 6 Verifica se a nota de atendimento est� entre 1 e 5
	 * 7 Verifica se o tamanho do campo coment�rio 
	 * 8 Verifica se a consulta j� foi paga, se a consulta
	 * tiver sido paga ent�o as altera��es n�o poder�o ser feitas
	 * @param c Recebe um Objeto do tipo Consulta
	 * @return Retorna uma Stirng informando um erro caso
	 * as regras acima n�o sejam seguidas ou uma mensagem de sucesso
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String alterarDadosConsulta(Consulta c)throws Exception{
		
		if (c.getCodConsulta()<1) {
			return "codigo invalido";
		}
		if (c.getCodConsulta()>99999) {
			return "codigo muito grande";
		}
		
		if (c.getCodConversa()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodConversa()>99999) {
			return "codigo muito grande";
		}
		
		if (c.getCodPaciente()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodPaciente()>99999) {
			return "codigo muito grande";
		}
		
		if (c.getCodPsiOnline()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodPsiOnline()>99999) {
			return "codigo muito grande";
		}
		
		String status = DataBO.validarData(c.getDataConsulta());
		if (status != c.getDataConsulta()) {
			return status;
		}
		
		if (c.getNotaAtendimento()<1 || c.getNotaAtendimento()>5) {
			return "nota inv�lida, a nota precisa estar entre 1 e 5";
		}
		
		if (c.getComentario().length()>120) {
			return "Comentario muito grande";
		}
		
		if(PagamentoBO.pesquisarPagamentoCodConsulta(c.getCodConsulta()).getCodPagamento()>0) {
			return "N�o ser� poss�vel efetuar altere��es pois a consulta j� foi paga";
		}
		
		ConsultaDAO dao = new ConsultaDAO();
		dao.alterarDadosConsulta(c);
		dao.fechar();
		return "Dados alterado com sucesso";
		
	}
	
	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas FormaPagamento
	 * Regras avaliadas
	 * 1 Verifica se o c�digo da consulta � valido
	 * 2 Verifica se o c�digo  da conversa � valido
	 * 3 Verifica se o c�digo do paciente � valido
	 * 4 Verifica se o c�digo do psicologo online � valido
	 * 5 Verifica se a data � valida
	 * 6 Verifica se a nota de atendimento est� entre 1 e 5
	 * 7 Verifica se o tamanho do campo coment�rio 
	 * 8 Verifica se a consulta j� foi paga, se a consulta
	 * tiver sido paga ent�o as altera��es n�o poder�o ser feitas
	 * @param Recebe um Objeto do tipo Consulta
	 * @return c Retorna uma Stirng informando um erro caso
	 * as regras acima n�o sejam seguidas ou uma mensagem de sucesso
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String novaConsulta(Consulta c)throws Exception{
		
		if (c.getCodConsulta()<1) {
			return "codigo invalido";
		}
		if (c.getCodConsulta()>99999) {
			return "codigo muito grande";
		}
		
		if (c.getCodConversa()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodConversa()>99999) {
			return "codigo muito grande";
		}
		
		if (c.getCodPaciente()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodPaciente()>99999) {
			return "codigo muito grande";
		}
		
		if (c.getCodPsiOnline()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodPsiOnline()>99999) {
			return "codigo muito grande";
		}
		
		String status = DataBO.validarData(c.getDataConsulta());
		if (status != c.getDataConsulta()) {
			return status;
		}
		
		if (c.getNotaAtendimento()<1 || c.getNotaAtendimento()>5) {
			return "nota inv�lida, a nota precisa estar entre 1 e 5";
		}
		
		if (c.getComentario().length()>120) {
			return "Comentario muito grande";
		}
		
		
		ConsultaDAO dao = new ConsultaDAO();
		dao.gravarConsulta(c);
		dao.fechar();
		return "Cadastrad com sucesso";
		
	}
	
	 
}
