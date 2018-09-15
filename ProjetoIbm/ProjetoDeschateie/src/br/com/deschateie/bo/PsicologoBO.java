package br.com.deschateie.bo;

import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.dao.PsicologoDAO;

public class PsicologoBO {

	public static Psicologo pesquisarPsicologo(int codPsicologo)throws Exception {
		
		if(codPsicologo<1 || codPsicologo > 99999) {
			return new Psicologo();
		}
		
		PsicologoDAO dao = new PsicologoDAO();
		Psicologo p = dao.consultarPsicologo(codPsicologo);
		 if(p.getCodPsicologo()<1) {
			 return new Psicologo();
		 }
		
		dao.fechar();
		return new Psicologo(p);
		
		
		
		
		
	}

}