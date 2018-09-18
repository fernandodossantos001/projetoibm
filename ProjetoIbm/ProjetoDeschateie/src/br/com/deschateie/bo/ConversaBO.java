package br.com.deschateie.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.deschateie.beans.Conversa;
import br.com.deschateie.dao.ConversaDAO;

public class ConversaBO {
	
	public static Conversa pesquisarConversa(int codConversa)throws Exception {
		if (codConversa<1) {
			return new Conversa();
		}
		
		if (codConversa>99999) {
			return new Conversa();
		}
		
		ConversaDAO dao = new ConversaDAO();
		Conversa conversa = dao.consultarConversa(codConversa);
		dao.fechar();
		return conversa;
	}

	public static String  exluirConversa(int codConversa)throws Exception {
		if (codConversa<1) {
			return "codigo invalido";
		}
		
		if (codConversa>99999) {
			return "codigo conversa muito grande";
		}
		
		if(pesquisarConversa(codConversa).getCodConversa()<1) {
			return "Codigo nao encontrado";
		}
		
		ConversaDAO dao = new ConversaDAO();
		dao.excluirConversa(codConversa);
		return null;
	}

	public static String novaConversa(Conversa c) {
		
		if (c.getCodConversa()<1) {
			return "codigo invalido";
		}
		
		if (c.getCodConversa()>99999) {
			return "codigo muito grande";
		}
		
		String status = DataBO.validaDataHora(c.getHoraInicio());
		if(!status.equals(c.getHoraInicio())) {
			return status;
		}
		
		
		status = DataBO.validaDataHora(c.getHoraTermino());
		if(!status.equals(c.getHoraTermino())) {
			return status;
		}
		
		
	
		return null;
	}

}
