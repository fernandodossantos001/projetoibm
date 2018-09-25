package br.com.deschateie.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.beans.Paciente;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.dao.ConsultaDAO;
import br.com.deschateie.dao.PacienteDAO;

public class PacienteBO {
	
	public static Paciente pesquisarPaciente(int codPaciente)throws Exception{
		
		if(codPaciente <0) {
			return new Paciente();
		}
		if(codPaciente> 99999) {
			return new Paciente();
		}
		
		PacienteDAO dao = new PacienteDAO();
		
		Paciente p = dao.consultarPaciente(codPaciente);
		dao.fechar();
		return p;
		
		
		
	}


	public static String novoPaciente(Paciente p,boolean ehvalido)throws Exception{
		
		
		
		if(p.getCodUsuario()!=p.getCodPaciente()) {
			return "os códigos do usuario e do paciente precisam ser iguais";
		}
		
		if(p.getCodPaciente()<1) {
			return "código inválido";
		}
		
		if (p.getCodPaciente()>99999) {
			return "código muito grande";
		}
		
		if(p.getCep().length()>99999999) {
			return "cep muito grande";
		}
		
		if(p.getCep().length()<1) {
			return "cep inválido";
		}
			
		if(p.getHistorico().length()>2000) {
			return "histórico muito grande";
		}
		
		if(p.getHistorico().length()<1) {
			p.setHistorico(" ");
		}
		
		if(p.getConsultasReazlizadas()>999) {
			return "consulta muito grande";
		}
		
		if(p.getConsultasReazlizadas()<1) {
			return "a quantidade de consultas não pode estar vazia";
		}
		
		p.setNivelPermissao(1);
		if(ehvalido==false) {
			String status = UsuarioBO.novoUsuario(p);
			
			if(!status.equals("Usuário cadastrado com Sucesso")) {
				
				return status;
			}
			
		
			
		}
		
		
		UsuarioBO.alterarNivelAcesso(p);	
		//Usuario user = UsuarioBO.pesquisarUsuarioPorCod(p.getCodUsuario());
		
		
		
//		Paciente paci = pesquisarPaciente(p.getCodPaciente());
//		if(paci.getCodPaciente()>0) {
//			return "paciente ja cadastrado";
//		}
		
		if(UsuarioBO.pesquisarUsuarioPorCod(p.getCodUsuario()).getCodUsuario()==0) {
			return "Usuario nao encontrado";
		}
		
		if(pesquisarPaciente(p.getCodPaciente()).getCodPaciente()>0) {
			return "O codigo do paciente ja existe";
		}
		
		PacienteDAO dao = new PacienteDAO();
		dao.gravarPaciente(p);
		dao.fechar();
		return "Cadastrado com suceso";
	}

	public static String excluirPaciente(int codPaciente)throws Exception{
		
		if(codPaciente<1) {
			return "código inválido";
		}
		
		if(codPaciente> 99999) {
			return "código muito grande";
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
		
		
		
		
		
		if(p.getCep().length()>99999999) {
			return "cep muito grande";
		}
		
		if(p.getCep().length()<1) {
			return "cep inválido";
		}
			
		if(p.getHistorico().length()>2000) {
			return "histórico muito grande";
		}
		
		PacienteDAO dao = new PacienteDAO();
		Paciente pa = new Paciente();
		
		if(dao.consultarPaciente(p.getCodPaciente()).getCodPaciente()<1) {
			return "nao foi possivel achar esse codigo no banco";
		}
		
		String status = UsuarioBO.AlterarDadosUsuario(p);
		
		if(!status.equals("Usuário alterado com Sucesso")) {
			System.out.println(dao.consultarPaciente(p.getCodPaciente()).getAll());
			return status;
		}
		
	
		
		if(p.getCpf() != pa.getCpf()) {
			if(p.getCpf() == dao.consultarPacienteCpf(pa.getCpf()).getCpf()) {
				dao.fechar();
				return "cpf já existe";			}
		}
		
		
		dao.AlteraDadosPaciente(p);
		dao.fechar();
		return "alterado com sucesso";
		
	}
	
	public static List<Paciente> pesquisarPacienteConsulta()throws Exception{
		
		boolean isTrueP = false;
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		List<Paciente> listaPacientes = new ArrayList<Paciente>();
		List<Consulta> listaConsultPaciente = new ArrayList<Consulta>();

		
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
		
	return listaPacientes;
	}

}
