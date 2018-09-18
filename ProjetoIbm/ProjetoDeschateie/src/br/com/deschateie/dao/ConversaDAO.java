package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Conversa;
import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.conexao.Conexao;

public class ConversaDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public ConversaDAO()throws Exception{
		con = new Conexao().conectar();
	}
	
	public Conversa consultarConversa(int codConversa)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_CONVERSA INNER JOIN T_SCP_VOLUNTARIO ON "
									+ "(T_SCP_CONVERSA.CD_VOLUNTARIO = T_SCP_VOLUNTARIO.CD_VOLUNTARIO)"
									+ " INNER JOIN T_SCP_USUARIO ON (T_SCP_VOLUNTARIO.CD_VOLUNTARIO = T_SCP_USUARIO.CD_USUARIO)"
									+ " WHERE T_SCP_CONVERSA.CD_CONVERSA = ?");
		stmt.setInt(1, codConversa);
		rs = stmt.executeQuery();
		if (rs.next()) {
			return new Conversa(
					rs.getInt("CD_CONVERSA"),
					rs.getString("HR_INICIO"),
					rs.getInt("QT_RESPOSTAS"),
					rs.getString("DS_HISTORICO"),
					rs.getString("HR_TERMINO"),
					new  Voluntario(
							rs.getInt("CD_USUARIO"),
							rs.getString("NM_USUARIO"),
							rs.getString("DS_EMAIL"),
							rs.getString("DT_NASCIMENTO"),
							rs.getString("DS_LOGIN"),
							rs.getString("DS_SENHA"),
							rs.getInt("NR_NIVEL_PERMISSAO"), 
							rs.getString("DS_FOTO"), 
							rs.getString("DS_GENERO"),
							rs.getString("NR_RG"),
							rs.getLong("NR_CPF"),
							rs.getString("DS_FORMACAO"), 
							rs.getString("DS_PERIODO"), 
							rs.getString("DS_COMENTARIO"), 
							rs.getLong("DS_TELEFONE")));
		}
		
		return new Conversa();
	}

	

	public String excluirConversa(int codConversa)throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SCP_CONVERSA WHERE CD_CONVERSA = ?");
		stmt.setInt(1, codConversa);
		return stmt.executeUpdate() + "linha excluida";
	}
	
	public void fechar()throws Exception{
		con.close();
	}
	
}
