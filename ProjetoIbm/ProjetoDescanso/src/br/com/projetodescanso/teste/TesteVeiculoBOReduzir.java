package br.com.projetodescanso.teste;

import javax.sql.rowset.spi.TransactionalWriter;

import br.com.projetodescanso.bo.VeiculoBO;
import br.com.projetodescanso.excecao.Excecao;

public class TesteVeiculoBOReduzir {

	public static void main(String[] args) {
	try {
		System.out.println(VeiculoBO.reduzirValorVenda());
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println(Excecao.tratarExcecao(e));
	}

	}

}
