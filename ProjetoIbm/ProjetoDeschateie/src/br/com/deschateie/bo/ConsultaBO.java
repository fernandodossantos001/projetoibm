package br.com.deschateie.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.dao.ConsultaDAO;

public class ConsultaBO {
	
	public static List<Consulta> pesquisarConsulta()throws Exception{
		List<Consulta> listaConsulta = new ArrayList<Consulta>();
		
		
		
		listaConsulta = new ConsultaDAO().pesquisarConsulta();
		return listaConsulta;
	}

	public static Consulta pesquisarConsultaPaciente(int codPaciente)throws Exception{
		
		if(codPaciente< 1) {
			return new Consulta();
		}
		
		if(codPaciente>99999) {
			return new Consulta();
		}
		
		Consulta c = new ConsultaDAO().pesquisarConsultaPaciente(codPaciente);
		return c;
	}
}
