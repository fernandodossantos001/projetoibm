package br.com.deschateie.bo;


import br.com.deschateie.beans.Agendamento;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.dao.AgendamentoDAO;

public class AgendamentoBO {
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

	public static String exluirAgendamento(int codAgendamento)throws Exception {
		if (codAgendamento<0) {
			return "C�digo inv�lido";
		}
		
		if (codAgendamento>99999) {
			return "codigo muito grande";
		}
		
		
		if(pesquisarAgendamento(codAgendamento)== null) {
			return "n�o foi pssivel excluir o agendamento, verifique o codigo de agendamento";
		}
		
		AgendamentoDAO dao = new AgendamentoDAO();
		dao.excluirAgendamento( codAgendamento);
		dao.fechar();
		return "Exluido com sucesso";
	}

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
		
		
		Agendamento agendamento = pesquisarAgendamento(ag.getCodAgendamento());
		if (agendamento.getCodAgendamento()<1) {
			return "codigo nao encontrado";
		}
		AgendamentoDAO dao = new AgendamentoDAO();
		dao.alterarDadosAgendamento(ag);
		dao.fechar();
		return "Data Alterada com sucesso";
	}

	public static String novoAgendamento(Agendamento ag)throws Exception {
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
		
		Agendamento agendamento = pesquisarAgendamento(ag.getCodAgendamento());
		if(agendamento.getCodAgendamento()>0) {
			return "codigo do agendamento ja existe";
		}
		AgendamentoDAO dao = new AgendamentoDAO();
		
		dao.gravarAgendamento(ag);
		dao.fechar();
		
		return "Agendamento cadastrado com sucesso";
		
	}
}
