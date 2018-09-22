package br.com.deschateie.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.beans.Conversa;
import br.com.deschateie.beans.Paciente;
import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.dao.ConsultaDAO;

public class ConsultaBO {
	
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

	public static List<List> pesquisarConsultas()throws Exception{
		List<Consulta> listaConsultas = new ArrayList<Consulta>();
		List<Paciente> listaPacientes = new ArrayList<Paciente>();
		List<PsiOnline> listaPsiOnlines = new ArrayList<PsiOnline>();
		List<List> consulta = new ArrayList<List>();
		
		
		ConsultaDAO dao = new ConsultaDAO();
		listaConsultas = dao.pesquisarListaConsulta();
		
		
		
		for (Consulta consultas: listaConsultas) {
			Paciente paciente;
			PsiOnline psiOnline;
			
			paciente = PacienteBO.pesquisarPaciente(consultas.getCodPaciente());
			psiOnline = PsiOnlineBO.pesquisarPsicologoOnline(consultas.getCodPsiOnline());
			
			listaPacientes.add(paciente);
			listaPsiOnlines.add(psiOnline);
		}
		
		consulta.add(listaConsultas);
		consulta.add(listaPacientes);
		consulta.add(listaPsiOnlines);
		return consulta;
	}

	public static List<Consulta> pesquisarConsultaCodPsiOnline(int codPsiOnline)throws Exception{
		List<Consulta> listaConsulta = new ArrayList<Consulta>();
		
		if(codPsiOnline< 1) {
			return listaConsulta;
		}
		
		if(codPsiOnline>99999) {
			return listaConsulta;
		}
		
		listaConsulta = new ConsultaDAO().pesquisarConsultaPsiOnline(codPsiOnline);
		return listaConsulta;
	}

	public static List<Consulta> pesquisarConsultaCodPaciente(int codPaciente)throws Exception{
		List<Consulta> listaConsulta = new ArrayList<Consulta>();
		
		if(codPaciente< 1) {
			return listaConsulta;
		}
		
		if(codPaciente>99999) {
			return listaConsulta;
		}
		
		listaConsulta = new ConsultaDAO().pesquisarConsultaPaciente(codPaciente);
		return listaConsulta;
	}

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
		
		PsiOnline psi = PsiOnlineBO.pesquisarPsicologoOnline(c.getCodPsiOnline());
		PsiOnlineBO.excluirPsicologoOnline(psi.getCodPsiOnline());
		Paciente p = PacienteBO.pesquisarPaciente(c.getCodPaciente());
		PacienteBO.excluirPaciente(p.getCodPaciente());
		Conversa cv = ConversaBO.pesquisarConversa(c.getCodConversa());
		ConversaBO.exluirConversa(cv.getCodConversa());
		ConsultaDAO dao = new ConsultaDAO();
		dao.excluirConsulta(codConsulta);
		dao.fechar();
		
		return "Excluiso com sucesso";
	}
}
