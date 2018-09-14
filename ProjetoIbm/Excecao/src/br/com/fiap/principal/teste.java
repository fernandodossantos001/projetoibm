package br.com.fiap.principal;

import br.com.fiap.excecao.excecao;
import br.com.fiap.magica.Magica;

public class teste {
	public static void main(String[] args) {
		try {
			double num= Magica.decimal("Número");
			System.out.println(num);
			//trata excecoes separadamente
			//o ultimo exception nao pode vir primeiro por ser geral, e não usa os outros
		}
		/*catch (NumberFormatException e) {
		//	System.out.println("Digite um numero");
			//e.printStackTrace(); /mostra a excecao e coloca no catch
		}catch(ArithmeticException e) {
			System.out.println("Erro aritmetico");
		} */
		catch(Exception e) {
		System.out.println(excecao.tratarExcecao(e));	
		}
		finally {
			System.out.println("Fim");
		}

	}
}
