package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Endereco;
import br.com.fiap.beans.Produto;
import br.com.fiap.conexao.Conexao;

public class ProdutoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public ProdutoDAO()throws Exception {
		con = new Conexao().conectar();
	}

	
	public String gravarDados(Produto p)throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_DDD_PRODUTO (CD_DESCRICAO,VL_VALOR,DS_DESCRICAO)"
				+ " VALUES(?,?,?) ");
		stmt.setInt(1, p.getCodigo());
		stmt.setDouble(2, p.getValor());
		stmt.setString(3,p.getDescricao());
		stmt.executeUpdate();
		return "Cadastrado com sucesso";
	}

//	public List<Produto>  consultarPorNumero(int cod)throws Exception {
//		List<Produto> lista = new ArrayList<Produto>();
//		stmt = con.prepareStatement("SELECT * FROM T_DDD_CLIENTE WHERE CD_PRODUTO LIKE  ?");
//		stmt.setInt(1, cod);
//		rs = stmt.executeQuery();
//		
//		while(rs.next()){
//			lista.add( new Produto(
//						rs.getInt("CD_CODIGO"),
//						rs.getDouble("VL_PRODUTO"),
//						rs.getString("DS_DESCRICAO")));
//		}
//		return lista;
//	}
	
	
	public Cliente consularPorNumero(int numero)throws Exception {
		stmt = con.prepareStatement
		("SELECT * FROM T_DDD_PRODUTO WHERE CD_PRODUTO = ?");
		stmt.setInt(1, numero);
		rs = stmt.executeQuery();
		
		//eu poderia fazer isso com operador ternario ?
		if(rs.next()) {
			return new Cliente(
								rs.getString("NM_CLIENTE"),
								rs.getInt("NR_CLIENT"),
								rs.getInt("QT_ESTRELAS"),
								new Endereco(
										rs.getInt("CD_ENDERECO"),
										rs.getString("DS_LOGRADOURO"),
										rs.getString("NR_ENDERECO"),
										rs.getString("NR_CEP")
											));
		}else {
			return new Cliente();
		}
		
	}
	
}
