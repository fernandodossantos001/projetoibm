package br.com.deschateie.bo;

import br.com.deschateie.beans.Conversa;
import br.com.deschateie.dao.ConversaDAO;


/**
 * Classe para validar os dados da tabela T_SCP_CONVERSA
 * possui m�todos para consultar,excluir,criar e alterar uma consulta
 * 
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see ConversaDAO
 * @see Conversa
 *
 */
public class ConversaBO {
	
	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas a Conversa
	 * Regras de neg�cio avaliadas
	 * 1 Verifica se o c�digo � valido
	 * @param codConversa Recebe um c�digo da conversa que precisa ser um n�mero inteiro
	 * @return Retorna um Objeto do tipo Conversa
	 * @throws Exception chamada de exce��o do tipo checked
	 * @author Deschateie
	 */
	public static Conversa pesquisarConversa(int codConversa)throws Exception {
		if (codConversa<1) {
			return new Conversa();
		}
		
		if (codConversa>99999) {
			return new Conversa();
		}
		
		ConversaDAO dao = new ConversaDAO();
		Conversa conversa = dao.consultarConversa(codConversa);
		dao.fechar();
		return conversa;
	}
	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionada a Conversa
	 * Regras avaliadas
	 * 1 Verifica se o c�digo � valido
	 * 2 Verifica atrav�s do c�digo se a conversa existe
	 * @param codConversa Recebe um n�mero inteiro do codigo da conversa
	 * @return Retorna uma String informando um erro se alguma das regras acima
	 * n�o estiverem sendo cumpridas ou uma mensagem de sucesso
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String  exluirConversa(int codConversa)throws Exception {
		if (codConversa<1) {
			return "codigo invalido";
		}
		
		if (codConversa>99999) {
			return "codigo conversa muito grande";
		}
		
		if(pesquisarConversa(codConversa).getCodConversa()<1) {
			return "Codigo nao encontrado";
		}
		
		ConversaDAO dao = new ConversaDAO();
		dao.excluirConversa(codConversa);
		return "Conversa excluida com sucesso";
	}

	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionada a Conversa
	 * Regras avaliadas 
	 * 1 Verifica se o c�dgio � valido
	 * 2 Verifica se a data da convers � v�lida
	 * 3 Verifica se o c�dgio da conversa passado j� existe
	 * 4 Verifica se o voluntario existe 
	 * @param c Recebe um Objeto do tipo Conversa
	 * @return Retorna uma String informando um erro se alguma das regras acima 
	 * n�o estivem sendo cumpridas ou uma mensagem de sucesso
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String novaConversa(Conversa c)throws Exception {
		
		if (c.getCodConversa()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodConversa()>99999) {
			return "codigo muito grande";
		}
		
		String status = DataBO.validaDataHora(c.getHoraInicio());
		if(!status.equals(c.getHoraInicio())) {
			return status;
		}
		
		
		status = DataBO.validaDataHora(c.getHoraTermino());
		if(!status.equals(c.getHoraTermino())) {
			return status;
		}
		
		status = DataBO.comparaDatas(c.getHoraInicio(), c.getHoraTermino());
		if(!status.equals("ok")) {
			return status;
		}
		
		
		
		if(pesquisarConversa(c.getCodConversa())!=null) {
			return "codigo de conversa j� existente";
		}
		
		if (VoluntarioBO.pesquisarVoluntario(c.getVoluntario().getCodVoluntario()).getCodVoluntario()==0) {
			return "codigo voluntario nao existente";
		}
		
		ConversaDAO dao = new ConversaDAO();
		status = dao.gravarConversa(c);
		dao.fechar();
		
		return status;
	}

	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionada a Conversa
	 * Regras avaliadas 
	 * 1 Verifica se o c�dgio � valido
	 * 2 Verifica se a data da convers � v�lida
	 * 3 Verifica se o c�dgio da conversa passado j� existe
	 * 4 Verifica se o voluntario existe 
	 * @param Recebe um Objeto do tipo Conversa
	 * @return c Retorna uma String informando um erro se alguma das regras acima 
	 * n�o estivem sendo cumpridas ou uma mensagem de sucesso
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public static String alterarDadosConversa(Conversa c) throws Exception{
		if (c.getCodConversa()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodConversa()>99999) {
			return "codigo muito grande";
		}
		
		String status = DataBO.validaDataHora(c.getHoraInicio());
		if(!status.equals(c.getHoraInicio())) {
			return status;
		}
		
		
		status = DataBO.validaDataHora(c.getHoraTermino());
		if(!status.equals(c.getHoraTermino())) {
			return status;
		}
		
		status = DataBO.comparaDatas(c.getHoraInicio(), c.getHoraTermino());
		if(!status.equals("ok")) {
			return status;
		}
		
		Conversa conversa = pesquisarConversa(c.getCodConversa());
		
		if(conversa.getCodConversa()==0) {
			return "a conversa nao foi encontrada para ser alterada";
		}
		
		if (VoluntarioBO.pesquisarVoluntario(c.getVoluntario().getCodVoluntario()).getCodVoluntario()==0) {
			return "codigo voluntario nao existente";
		}
		
		
		
		
		
		ConversaDAO dao = new ConversaDAO();
		status = dao.alterarDadosConversa(c);
		dao.fechar();
		
		return "alterado com sucesso";
	}

}
