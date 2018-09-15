package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.conexao.Conexao;

public class PsicologoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	public PsicologoDAO() throws Exception{
		con = new Conexao().conectar();
	}
	
	
	public Psicologo consultarPsicologo(int codPsicologo)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO NATURAL JOIN T_SCP_PSICOLOGO  WHERE CD_USUARIO = ? AND CD_PSICOLOGO = ?");
		stmt.setInt(1, codPsicologo);
		stmt.setInt(2, codPsicologo);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Psicologo(
					rs.getInt("CD_USUARIO"),
					rs.getString("NM_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					rs.getString("DS_LOGIN"),
					rs.getString("DS_SENHA"),
					rs.getInt("NR_NIVEL_PERMISSAO"),
					rs.getString("DS_FOTO"),
					rs.getString("DS_GENERO"),
					rs.getInt("NR_CRP"),
					rs.getString("DS_FORMACAO"),
					rs.getString("DS_BIOGRAFIA"),
					rs.getLong("DS_TELEFONE"),
					rs.getDouble("VL_CONSULTA"));
					}else {
						return new Psicologo();
					}
	}


	public void fechar()throws Exception {
		con.close();
		
	}
		
}
