package br.com.deschateie.bo;

import br.com.deschateie.beans.FormaPagamento;
import br.com.deschateie.dao.FormaPagamentoDAO;

public class FormaPagamentoBO {
	public static FormaPagamento pesquisarFormaPagamento(FormaPagamento fp)throws Exception{
		if(fp.getCodFormaPagamento()<0) {
			return new FormaPagamento();
		}
		
		if (fp.getCodFormaPagamento()>99) {
			return new FormaPagamento();
		}
		
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		FormaPagamento forma = dao.consultarFormaPagamento(fp);
		dao.fechar();
		return forma;
	}

	public static String novaFormaPagamento(FormaPagamento fp)throws Exception {
		if(fp.getCodFormaPagamento()<0) {
			return "codigo invalido";
		}
		
		if (fp.getCodFormaPagamento()>99) {
			return  "codigo muito grande";
		}
		
		if(fp.getFormaPagamento().length()<1) {
			return "A forma de pagamento nao pode estar vazia";
					
		}
		
		if(fp.getFormaPagamento().length()>40) {
			return "Forma de pagamento muito grande";
		}
		
		if(pesquisarFormaPagamento(fp).getCodFormaPagamento()!=0) {
			return "codigo de forma de pagamento ja existente";
		}
		
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		String status = dao.gravarFormaPagamento(fp);
		dao.fechar();
		return status;
	}

	public static String alterarDadosFormaPagamento(FormaPagamento fp) throws Exception {

		if(fp.getCodFormaPagamento()<0) {
			return "codigo invalido";
		}
		
		if (fp.getCodFormaPagamento()>99) {
			return  "codigo muito grande";
		}
		
		if(fp.getFormaPagamento().length()<1) {
			return "A forma de pagamento nao pode estar vazia";
					
		}
		
		if(fp.getFormaPagamento().length()>40) {
			return "Forma de pagamento muito grande";
		}
		
		if(pesquisarFormaPagamento(fp).getCodFormaPagamento()==0) {
			return "codigo de forma de pagamento nao existente";
		}
		
		
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		String status = dao.alterarDadosFormaPagamento(fp);
		dao.fechar();
		return status;
	}

	public static String exluirFormaPagamento(FormaPagamento fp)throws Exception{
		if(fp.getCodFormaPagamento()<0) {
			return "codigo invalido";
		}
		
		if (fp.getCodFormaPagamento()>99) {
			return  "codigo muito grande";
		}
		
		if(fp.getFormaPagamento().length()<1) {
			return "A forma de pagamento nao pode estar vazia";
					
		}
		
		if(fp.getFormaPagamento().length()>40) {
			return "Forma de pagamento muito grande";
		}
		
		if(pesquisarFormaPagamento(fp).getCodFormaPagamento()==0) {
			return "codigo de forma de pagamento nao existente";
		}
		
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		String status = dao.exluirFormaPagamento(fp);
		dao.fechar();
		return status;
	}
}
