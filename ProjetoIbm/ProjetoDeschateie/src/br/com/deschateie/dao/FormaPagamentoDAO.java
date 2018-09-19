package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.FormaPagamento;
import br.com.deschateie.conexao.Conexao;

public class FormaPagamentoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public FormaPagamentoDAO()throws Exception {
		this.con = new  Conexao().conectar();
	}
	
	public FormaPagamento consultarFormaPagamento(FormaPagamento fp)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_FORMA_PAGAMENTO WHERE CD_FORMA_PGTO = ?");
		stmt.setInt(1, fp.getCodFormaPagamento());
		rs = stmt.executeQuery();
		if (rs.next()) {
			return new FormaPagamento(
					rs.getInt("CD_FORMA_PGTO"),
					rs.getString("DS_FORMA_PGTO"));
		}
		
		return new FormaPagamento();
	}

	public void fechar()throws Exception{
		con.close();
	}

	public String gravarFormaPagamento(FormaPagamento fp)throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SCP_FORMA_PAGAMENTO(CD_FORMA_PGTO,DS_FORMA_PGTO) VALUES(?,?)");
		stmt.setInt(1, fp.getCodFormaPagamento());
		stmt.setString(2, fp.getFormaPagamento());
		stmt.executeUpdate();
		return "Forma de pagamento cadastrada com suceso";
		
	}

	public String alterarDadosFormaPagamento(FormaPagamento fp)throws Exception {
		stmt = con.prepareStatement("UPDATE T_SCP_FORMA_PAGAMENTO SET DS_FORMA_PGTO = ? WHERE CD_FORMA_PGTO = ?");
		stmt.setString(1, fp.getFormaPagamento());
		stmt.setInt(2, fp.getCodFormaPagamento());
		return stmt.executeUpdate()+"linha alterada";
	}
	
	public String exluirFormaPagamento(FormaPagamento fp)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_FORMA_PAGAMENTO WHERE CD_FORMA_PGTO = ?");
		stmt.setInt(1,fp.getCodFormaPagamento());
		return stmt.executeUpdate() + "linha excluida";
	}
	
}
