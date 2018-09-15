package br.com.deschateie.bo;

import br.com.deschateie.beans.Paciente;
import br.com.deschateie.dao.PacienteDAO;

public class PacienteBO {
	
	public static Paciente consutlarPaciente(int codPaciente)throws Exception{
		
		if(codPaciente <0) {
			return new Paciente();
		}
		if(codPaciente> 99999) {
			return new Paciente();
		}
		
		PacienteDAO dao = new PacienteDAO();
		
		Paciente p = dao.consultarPaciente(codPaciente);
		if(p.getCodUsuario()<0) {
			return new Paciente();
		}
		dao.fechar();
		
		return new Paciente(p.getCodPaciente(),
							p.getNomeUsuario(),
							p.getEmail(),
							p.getDataNascimento(),
							p.getLogin(),
							p.getSenha(),
							p.getNivelPermissao(),
							p.getFoto(),
							p.getGenero(),
							p.getCep(),
							p.getCpf(),
							p.getHistorico(),
							p.getConsultasReazlizadas());
		
		
		
	}


	public static String novoPaciente(Paciente p)throws Exception{
		
		
		
		if(p.getCodUsuario()!=p.getCodPaciente()) {
			return "os c�digos do usuario e do paciente precisam ser iguais";
		}
		
		if(p.getCodPaciente()<1) {
			return "c�digo inv�lido";
		}
		
		if (p.getCodPaciente()>99999) {
			return "c�digo muito grande";
		}
		
		if(p.getCep()>99999999) {
			return "cep muito grande";
		}
		
		if(p.getCep()<1) {
			return "cep inv�lido";
		}
			
		if(p.getHistorico().length()>2000) {
			return "hist�rico muito grande";
		}
		
		if(p.getConsultasReazlizadas()>999) {
			return "consulta muito grande";
		}
		
		if(p.getConsultasReazlizadas()<1) {
			return "a quantidade de consultas n�o pode estar vazia";
		}
		
		String status = UsuarioBO.novoUsuario(p);
		
		if(!status.equals("Usu�rio cadastrado com Sucesso")) {
			
			return status;
		}
		
		
		PacienteDAO dao = new PacienteDAO();
		dao.gravarPaciente(p);
		dao.fechar();
		return "cadastrado com sucesso";
	}

	public static String excluirPaciente(int codPaciente)throws Exception{
		
		if(codPaciente<1) {
			return "c�digo inv�lido";
		}
		
		if(codPaciente> 99999) {
			return "c�digo muito grande";
		}
		
		PacienteDAO dao = new PacienteDAO();
		Paciente p = dao.consultarPaciente(codPaciente);
		
		if(p.getCodPaciente()<1) {
			return "nao foi possivel encontrar o paciente";
		}
		
		dao.excluirPaciente(codPaciente);
		dao.fechar();
		return "excluido com sucesso";
	}

	public static String alterarDadosPaciente(Paciente p)throws Exception{
		
		
		
		
		
		if(p.getCep()>99999999) {
			return "cep muito grande";
		}
		
		if(p.getCep()<1) {
			return "cep inv�lido";
		}
			
		if(p.getHistorico().length()>2000) {
			return "hist�rico muito grande";
		}
		
	
		
		String status = UsuarioBO.AlterarDadosUsuario(p);
		
		if(!status.equals("Usu�rio alterado com Sucesso")) {
			
			return status;
		}
		
		
		PacienteDAO dao = new PacienteDAO();
		dao.AlteraDadosPaciente(p);
		dao.fechar();
		return "cadastrado com sucesso";
		
	}
}
