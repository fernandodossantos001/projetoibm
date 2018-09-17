package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.conexao.Conexao;

public class PsiOnlineDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
		
	public PsiOnlineDAO()throws Exception{
		con = new Conexao().conectar();
	}
	
	
	public PsiOnline consultarPsicologoOnline(int codPsi)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO INNER JOIN T_SCP_PSICOLOGO"
				+ " ON CD_USUARIO = CD_PSICOLOGO INNER JOIN T_SCP_PSI_ONLINE"
				+ " ON CD_PSICOLOGO = CD_PSICOLOGO_ON WHERE CD_PSICOLOGO_ON = ?");
		stmt.setInt(1, codPsi);
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new PsiOnline(
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
					rs.getDouble("VL_CONSULTA"),
					rs.getString("DS_PERIODO"),
					rs.getString("DS_FORMA_ATENDIMENTO"),
					rs.getInt("VL_NOTA_ATENDIMENTO"),
					rs.getInt("QT_ATENDIMENTO"));
		}else {
			return new PsiOnline();
		}
	}

	public String gravarPsiOnline(PsiOnline psi)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_SCP_PSI_ONLINE"
									+ "(CD_PSICOLOGO_ON,DS_PERIODO,"
									+ "DS_FORMA_ATENDIMENTO,VL_NOTA_ATENDIMENTO,QT_ATENDIMENTO)"
									+ "VALUES(?,?,?,?,?)");
		stmt.setInt(1, psi.getCodPsiOnline());
		stmt.setString(2, psi.getPeriodo());
		stmt.setString(3, psi.getFormaAtendimento());
		stmt.setInt(4, psi.getNotaAtendimento());
		stmt.setInt(5, psi.getQtdeAtendimentos());
		stmt.executeUpdate();
		return "Cadastrado com sucesso";
	}

	public String excluirPsicologoOnline(int codPsi)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_PSI_ONLINE WHERE CD_PSICOLOGO_ON = ?");
		stmt.setInt(1, codPsi);
		return "foi excluida " + stmt.executeUpdate() + "linha";
		
	}

	public String alterarDadosPsicologoOnline(PsiOnline psi)throws Exception{
		stmt = con.prepareStatement("UPDATE T_SCP_PSI_ONLINE "
									+ "SET DS_PERIODO = ?,"
									+ "DS_FORMA_ATENDIMENTO = ?,"
									+ "VL_NOTA_ATENDIMENTO = ?,"
									+ "QT_ATENDIMENTO = ?");
		stmt.setString(1, psi.getPeriodo());
		stmt.setString(2, psi.getFormaAtendimento());
		stmt.setInt(3, psi.getNotaAtendimento());
		stmt.setInt(4, psi.getQtdeAtendimentos());
		return stmt.executeUpdate()+"atualiada";
	}
	public void fechar()throws Exception {
		con.close();
	}
}