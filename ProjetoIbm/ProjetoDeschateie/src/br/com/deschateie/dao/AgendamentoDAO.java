package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Agendamento;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.conexao.Conexao;

public class AgendamentoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public AgendamentoDAO()throws Exception {
		con = new Conexao().conectar();
	}
	
	public Agendamento consultarAgendamento(int codAgendamento)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_AGENDAMENTO INNER JOIN T_SCP_USUARIO ON"
				+ "(T_SCP_AGENDAMENTO.CD_USUARIO = T_SCP_USUARIO.CD_USUARIO)"
				+ " INNER JOIN T_SCP_PSICOLOGO ON(T_SCP_AGENDAMENTO.CD_PSICOLOGO = T_SCP_PSICOLOGO.CD_PSICOLOGO)"
				+ " WHERE CD_AGENDAMENTO = ?");
		stmt.setInt(1, codAgendamento);
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new Agendamento(
					rs.getInt("CD_AGENDAMENTO"),
					new Psicologo(
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
							rs.getDouble("VL_CONSULTA")),
					new Usuario(rs.getInt("CD_USUARIO"),
							rs.getString("NM_USUARIO"),
							rs.getString("DS_EMAIL"),
							rs.getString("DT_NASCIMENTO"),
							rs.getString("DS_LOGIN"),
							rs.getString("DS_SENHA"),
							rs.getInt("NR_NIVEL_PERMISSAO"),
							rs.getString("DS_FOTO"),
							rs.getString("DS_GENERO")),
					rs.getString("DT_AGENDAMENTO"));
		}
		
		return new Agendamento();
	}

	
	
	
	public void fechar()throws Exception {
		con.close();
	}

	public String  excluirAgendamento(int codAgendamento)throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SCP_AGENDAMENTO WHERE CD_AGENDAMENTO = ?");
		stmt.setInt(1, codAgendamento);
		return stmt.executeUpdate() + "linha atualizada";
		
	}

	public String alterarDadosAgendamento(int codAgendamento,String data)throws Exception {
		stmt = con.prepareStatement("UPDATE T_SCP_AGENDAMENTO SET DT_AGENDAMENTO = TO_DATE(?,'DD/MM/YYYY HH24:MI') WHERE CD_AGENDAMENTO = ?");
		stmt.setString(1, data);
		stmt.setInt(2, codAgendamento);
		return stmt.executeUpdate() +  "linha alterada";
		
	}

	public String gravarAgendamento(int codAgendamento, int codPsicologo, int codUsuario,String data)throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SCP_AGENDAMENTO"
									+ "(CD_AGENDAMENTO,CD_PSICOLOGO,CD_USUARIO,DT_AGENDAMENTO)"
									+ " VALUES(?,?,?,TO_DATE(?,'DD/MM/YYYY'))");
		stmt.setInt(1, codAgendamento);
		stmt.setInt(2, codPsicologo);
		stmt.setInt(3, codUsuario);
		stmt.setString(4, data);
		stmt.executeUpdate();
		return "Agendamento cadastrado com sucesso";
		
	}

}