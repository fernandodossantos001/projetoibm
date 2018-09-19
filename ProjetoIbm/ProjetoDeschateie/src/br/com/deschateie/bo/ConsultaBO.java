package br.com.deschateie.bo;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.dao.ConsultaDAO;

public class ConsultaBO {
	
	public static Consulta pesquisarConsulta(int codConsulta)throws Exception{
		Consulta c = new ConsultaDAO().pesquisarConsulta(codConsulta);
		return c;
	}

}
