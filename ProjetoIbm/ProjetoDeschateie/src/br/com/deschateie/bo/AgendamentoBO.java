package br.com.deschateie.bo;


import br.com.deschateie.beans.Agendamento ;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.dao.AgendamentoDAO;

/**
 * Classe para validar dados para a tabela T_SCP_AGENDAMENTO
 * possui m�todos para  criar,exluir,alterar e pesquisar agendamentos
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see AgendamentoDAO 
 * @see Agendamento
 */

public class AgendamentoBO {
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas a Agendamento
	 * Regras de neg�cio avaliadas
	 * 1� O tamanho do c�digo de agendamento
	 * @param recebe um codigo de agendamento que precisa ser um n�mero inteiro
	 * @return Retorna um Objeto do tipo Agendamento, que pode estar nulo se o codigo n�o for existente
	 *  ou se o c�digo passado n�o estiver dentro dos padr�es
	 * @author Deschateie
	 * @throws  Exce��o do tipo checkd
	 */
	public static Agendamento pesquisarAgendamento(int codAgendamento)throws Exception{
		if (codAgendamento<0) {
			return new Agendamento();
		}
		
		if(codAgendamento>99999) {
			return new Agendamento();
		}
		
		Agendamento agendamento = new Agendamento();
		AgendamentoDAO dao = new AgendamentoDAO();
		agendamento = dao.consultarAgendamento(codAgendamento);
		dao.fechar();
		return agendamento;
	}

	
	
	/** M�todo respons�vel por manipular as regras de neg�cio relacionadas a Agendamento
	 * Regras de neg�cio avaliadas
	 * 1 O tamanha do c�digo de agendamento
	 * 2 Se o agendamento existe
	 * @param recebe um c�digo de agendamento, que precisa ser um n�mero inteiro
	 * @return retorna uma String  informando uma mensagem de erro se o c�digo n�o for v�lido,
	 * o agendamento n�o for encontrado ou uma mensagem de sucesso se o agendamento for excluido
	 * @author Deschateie
	 * @throws Exception Exce��o do tipo checkd
	 */
	public static String exluirAgendamento(int codAgendamento)throws Exception {
		if (codAgendamento<0) {
			return "C�digo inv�lido";
		}
		
		if (codAgendamento>99999) {
			return "codigo muito grande";
		}
		
		
		if(pesquisarAgendamento(codAgendamento).getCodAgendamento() == 0) {
			return "n�o foi pssivel excluir o agendamento, verifique o codigo de agendamento";
		}
		
		AgendamentoDAO dao = new AgendamentoDAO();
		dao.excluirAgendamento( codAgendamento);
		dao.fechar();
		return "Exluido com sucesso";
	}

	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas a Agendamento
	 * Regras de neg�cio avaliadas
	 * 1 O tamanha do c�digo de agendamento
	 * 2 Verifica se o agendamento existe atrav�s do c�digo
	 * 3 Verificar se a data � v�lida
	 * @param Recebe um Objeto do tipo Agendamento
	 * @return Retorna uma Stirng, informando uma mensagem de erro ou de sucesso
	 * @author Deschateie
	 * @throws Exception Exce��o do tipo checkd
	 */
	
	

	public static String AlterarDadosAgendamento(Agendamento ag) throws Exception{

		
		String status = DataBO.validarData(ag.getDataAgendamento());
		if(!status.equals(ag.getDataAgendamento())) {
			return status;
		}
		
		if (ag.getCodAgendamento()<1 ) {
			return "codigo agendamento invalido";
		}
		
		if (ag.getCodAgendamento()>99999) {
			return "codigo agendamento muito grande";
		}
		
		
		
		if (pesquisarAgendamento(ag.getCodAgendamento()).getCodAgendamento() <1) {
			return "codigo de agendamento nao encontrado";
		}
		AgendamentoDAO dao = new AgendamentoDAO();
		dao.alterarDadosAgendamento(ag);
		dao.fechar();
		return "Data Alterada com sucesso";
	}

	
	/**
	 * M�todo respons�vel por manipular as regras de neg�cio relacionadas a  Agendamento
	 * Regras de neg�cio avaliadas
	 * 1 O tamanha do c�digo de agendamento
	 * 2 Verifica se o agendamento existe atrav�s do c�digo
	 * 3 Verificar se a data � v�lida
	 * 4 Verificar se o Paciente Existe
	 * 5 Verificar se o Psicologo existe
	 * @param Recebe um Objeto do tipo Agendamento
	 * @return retorna uma String, informando uma mensagem de erro caso 
	 * alguma dessas regras n�o sejam cumpridas ou mensagem de sucesso caso o cadastro seja
	 * efetuado com sucesso
	 * @throws Exception Exce��o do tipo checkd
	 * @author Deschateie
	 */ 
	
	
	public static String novoAgendamento(Agendamento ag)throws Exception  {
		
		if (ag.getCodAgendamento()<1 ) {
			return "codigo agendamento invalido";
		}
		
		if (ag.getCodAgendamento()>99999) {
			return "codigo agendamento muito grande";
		}
		
		if(ag.getPsicologo().getCodPsicologo()<1) {
			return "codigo psicologo invalido";
		}
		if (ag.getPsicologo().getCodPsicologo()>99999) {
			return "codigo psicologo muito grande";
		}
		
		String status = DataBO.validarData(ag.getDataAgendamento());
		if (!status.equals(ag.getDataAgendamento())) {
			return status;
		}
		
		Usuario usuario = new Usuario();
		usuario = UsuarioBO.pesquisarUsuarioPorCod(ag.getUsuario().getCodUsuario());
		if (usuario.getCodUsuario()<1) {
			return "codigo de usuario nao existe";
		}
		
		Psicologo psicologo = new Psicologo();
		psicologo = PsicologoBO.pesquisarPsicologo(ag.getPsicologo().getCodPsicologo());
		if (psicologo.getCodPsicologo()<1) {
			return "codigo do psicologo nao existe";
		}
		
		
		AgendamentoDAO dao = new AgendamentoDAO();
		
		if (dao.consultarAgendamento(ag.getCodAgendamento()).getCodAgendamento()>1) {
			return "C�digo de Agendamento j� existente,  informe outro c�digo";
		}
		
		dao.gravarAgendamento(ag);
		dao.fechar();
		
		return "Agendamento cadastrado com sucesso";
		
	}
}
