package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.conexao.Conexao;

public class VoluntarioDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public VoluntarioDAO()throws Exception {
		con = new Conexao().conectar();
	}
	
	public Voluntario consultarVoluntario(int codVoluntario)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO,T_SCP_VOLUNTARIO WHERE CD_USUARIO = ?");
		stmt.setInt(1, codVoluntario);
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new  Voluntario(
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
									rs.getInt("DS_TELEFONE"));
		}else {
			return new Voluntario();
		}
	}
	
	
}
