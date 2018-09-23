package br.com.deschateie.bo;

import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.dao.PsiOnlineDAO;

public class PsiOnlineBO {
	public static PsiOnline pesquisarPsicologoOnline(int codPsi)throws Exception {
		
		if(codPsi <1) {
			return new PsiOnline();
		}
		
		if(codPsi>99999) {
			return new PsiOnline();
		}
		
		PsiOnlineDAO dao = new PsiOnlineDAO();
		PsiOnline ps = dao.consultarPsiOnline(codPsi);
		dao.fechar();
		return ps;
	}
	
	
	public static String novoPsicologoOnline(PsiOnline psi, boolean ehValido)throws Exception {
		
		if (psi.getPeriodo().length()<0) {
			return "O periodo nao pode estar vazio";
		}
		
		if(psi.getPeriodo().length()>40) {
			return "periodo muito grande";
		}
		
		if (psi.getFormaAtendimento().length()<0) {
			return "A forma de atendimento nao pode estar vazia";
		}
		
		if (psi.getFormaAtendimento().length()>40) {
			return "forma de atendimento muito grande";
		}
		
		if (psi.getNotaAtendimento()<1) {
			return "nota invalida";
		}
		
		if (psi.getNotaAtendimento()>5) {
			return "nota atendimento deve estar entre 1 e 5";
		}
		
		if (psi.getQtdeAtendimentos()<0) {
			return "quantidade de atendimentos invalidos";
		}
		
		if (psi.getQtdeAtendimentos()>9999) {
			return "atendimento muito grande";
		}
		
		
		psi.setNivelPermissao(3);
		if (!ehValido) {
			String status = PsicologoBO.NovoPsicologo(psi,ehValido);
			 if (!status.equals("Psicologo cadastrado com Sucesso")) {
					return status;
				}
		}
		
		UsuarioBO.alterarNivelAcesso(psi);
		
		if(UsuarioBO.pesquisarUsuarioPorCod(psi.getCodUsuario()).getCodUsuario()==0) {
			return "Usuario nao encontrado";
		}
		
		 if(pesquisarPsicologoOnline(psi.getCodPsicologo()).getCodPsicologo()>0) {
				return "O codigo do psicologo ja existe";
			}
		
		 
		 PsiOnlineDAO dao = new PsiOnlineDAO();
		 dao.gravarPsiOnline(psi);
		 dao.fechar();
		 return "Psicologo online cadastrado com  sucesso";
	}

	public static String excluirPsicologoOnline(int codPsi)throws Exception{
		if(codPsi<1) {
			return "codigo invalido";
		}
		
		if (codPsi>99999) {
			return "codigo muito grande";
		}
		
		
		
		PsiOnlineDAO dao = new PsiOnlineDAO();
		String msg = dao.excluirPsiOnline(codPsi);
		PsicologoBO.excluirPsicologo(codPsi);
		return msg;
		
	}
	
	public static String AlterarDadosPsicologo(PsiOnline psi)throws Exception{
		if (psi.getPeriodo().length()<0) {
			return "O periodo nao pode estar vazio";
		}
		
		if(psi.getPeriodo().length()>40) {
			return "periodo muito grande";
		}
		
		if (psi.getFormaAtendimento().length()<0) {
			return "A forma de atendimento nao pode estar vazia";
		}
		
		if (psi.getFormaAtendimento().length()>40) {
			return "forma de atendimento muito grande";
		}
		
		if (psi.getNotaAtendimento()<1) {
			return "nota invalida";
		}
		
		if (psi.getNotaAtendimento()>5) {
			return "nota atendimento deve estar entre 1 e 5";
		}
		
		if (psi.getQtdeAtendimentos()<0) {
			return "quantidade de atendimentos invalidos";
		}
		
		if (psi.getQtdeAtendimentos()>9999) {
			return "atendimento muito grande";
		}

		String status = PsicologoBO.AlterarDadosPsicologo(psi);
		if(!status.equals("Psicologo atualizado com Sucesso")) {
			return status;
		}
		
		PsiOnlineDAO dao = new PsiOnlineDAO();
		dao.alterarDadosPsiOnline(psi);
		dao.fechar();
		return "Psicologo Online atualizado com Sucesso";
	}

}
