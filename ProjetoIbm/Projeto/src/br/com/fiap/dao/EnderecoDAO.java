package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.beans.Endereco;
import br.com.fiap.conexao.Conexao;

public class EnderecoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public EnderecoDAO() throws Exception{
		con = new Conexao().conectar();
	}

	
	public String gravar(Endereco endereco) throws Exception {
		stmt= con.prepareStatement("INSERT INTO T_DDD_ENDERECO "
				+ "(CD_ENDERECO, DS_LOGRADOURO, NR_ENDERECO_NR_CEP)"
				+ " VALUES()?,?,?,?");
		stmt.setInt(1, endereco.getCodigo());
		stmt.setString(2, endereco.getLogradouro());
		stmt.setString(3, endereco.getNumero());
		stmt.setString(4, endereco.getCep());
		stmt.executeUpdate();
		
		return"ok";
	}


	public Endereco consultarPorCod(int cod)throws Exception {
		stmt = con.prepareStatement("SELECt * FROM T_DDD_ENDERECO WHERE CD_ENDERECO = ?");
		stmt.setInt(1, cod);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Endereco(
					rs.getInt("CD_ENDERECO"),
					rs.getString("DS_LOGADOURO"),
					rs.getString("NR_ENDERECO"),
					rs.getString("NR_CEP")
					);
		}else {
			return new Endereco();
		}
		
		
	}


	public void fechar() throws Exception {
		con.close();
		
	}
}
