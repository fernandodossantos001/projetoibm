package br.com.fiap.teste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.fiap.conexao.Conexao;
import br.com.fiap.excecao.Excecao;

public class Conexao1 {

	public static void main(String[] args) {	
		Connection con = null;
		PreparedStatement stmt = null ;
		ResultSet rs = null;
		
		
		
		
		//modo correto de fazer a conex�o
		try {
			
			con = new Conexao().conectar();
			stmt= con.prepareStatement("");
			
			con = DriverManager.getConnection
			//@localhost para usar em casa, ORCL = xe, no lugar do rm colocar "SYSTEM"
			("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL","RM80577","120197");
			System.out.println("Abriu");
			stmt = con.prepareStatement
					("SELECT * FROM T_DDD_CLIENTE WHERE NR_CLIENTE = ? AND NM_CLIENTE = ?");
			String strNome = JOptionPane.showInputDialog("Digite um nome").toUpperCase();
			int intNumero = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero do cliente"));
			
			stmt.setInt(1, intNumero);
			stmt.setString(2, strNome);
			rs = stmt.executeQuery();
			if(rs.next()) {
				System.out.println("Autorizado ");
				System.out.print("N�mero :" + rs.getInt("NR_CLIENTE") + " ");
				System.out.print("Nome : "+rs.getString("NM_CLIENTE")+ " ");
				System.out.print("Estrelas : " + rs.getInt("QT_ESTRELAS")+ " ");
			}else {
				System.out.println("N�o autorizado");
			}
			
//			buscando todos os dados da tabela
//			while(rs.next()) {
//				System.out.print("N�mero :" + rs.getInt("NR_CLIENTE") + " ");
//				System.out.print("Nome : "+rs.getString("NM_CLIENTE")+ " ");
//				System.out.print("Estrelas : " + rs.getInt("QT_ESTRELAS")+ " ");
//				System.out.println();
//			}
//			
			
		}catch(Exception e) {
			//1� Momento: o sistema est� em teste
			e.printStackTrace();
			//2� Momento: o sistema est� homologado
			System.out.println(Excecao.tratarExcecao(e));
		}finally {
			// � sempre executado para encerrar conex�o
			try {
				con.close();
				System.out.println("fechou");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(Excecao.tratarExcecao(e));
			}
		}

	}

}
