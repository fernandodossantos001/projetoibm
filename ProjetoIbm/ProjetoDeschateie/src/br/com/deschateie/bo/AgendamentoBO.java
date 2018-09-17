package br.com.deschateie.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static String AlterarDadosAgendamento(int codAgendamento,String data) throws Exception{

		Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		
		Matcher matcher = pattern.matcher(data);
		
		if(!matcher.find()) {
			
			return "data inv�lido";
			
		}
		
		if (codAgendamento<1 ) {
			return "codigo agendamento invalido";
		}
		
		if (codAgendamento>99999) {
			return "codigo agendamento muito grande";
		}
		
		
		Agendamento agendamento = pesquisarAgendamento(codAgendamento);
		if (agendamento.getCodAgendamento()<1) {
			return "codigo nao encontrado";
		}
		AgendamentoDAO dao = new AgendamentoDAO();
		dao.alterarDadosAgendamento(codAgendamento,data);
		dao.fechar();
		return "Data Alterada com sucesso";
	}

	public static String novoAgendamento(int codAgendamento, int codPsicologo, int codUsuario, String data)throws Exception {
		if (codAgendamento<1 ) {
			return "codigo agendamento invalido";
		}
		
		if (codAgendamento>99999) {
			return "codigo agendamento muito grande";
		}
		
		if(codPsicologo<1) {
			return "codigo psicologo invalido";
		}
		if (codPsicologo>99999) {
			return "codigo psicologo muito grande";
		}
		
		Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		Matcher matcher = pattern.matcher(data);
		if(!matcher.find()) {
			return "data inv�lido";
		}
		
		Usuario usuario = new Usuario();
		usuario = UsuarioBO.pesquisarUsuarioPorCod(codUsuario);
		if (usuario.getCodUsuario()<1) {
			return "codigo de usuario nao existe";
		}
		
		Psicologo psicologo = new Psicologo();
		psicologo = PsicologoBO.pesquisarPsicologo(codPsicologo);
		if (psicologo.getCodPsicologo()<1) {
			return "codigo do psicologo nao existe";
		}
		
		Agendamento agendamento = pesquisarAgendamento(codAgendamento);
		if(agendamento.getCodAgendamento()>0) {
			return "codigo do agendamento ja existe";
		}
		AgendamentoDAO dao = new AgendamentoDAO();
		
		dao.gravarAgendamento(codAgendamento,codPsicologo,codUsuario,data);
		dao.fechar();
		
		return "Agendamento cadastrado com sucesso";
		
	}
}