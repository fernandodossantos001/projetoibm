package br.com.deschateie.teste;

import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.bo.PsiOnlineBO;
import br.com.deschateie.excecao.Excecao;

public class TestePsiOnline {

	public static void main(String[] args) {

		try {
			System.out.println(PsiOnlineBO.pesquisarPsicologoOnline(11).getAll());
			
			
			System.out.println(PsiOnlineBO.novoPsicologoOnline
					(new PsiOnline(844, "Emanuel",
					"manubolao@man22u.com.br","01/01/2004",
					"manuBola22o", "manuu", 2,"c:", "masculino",
					1291234, "psicologia","ahahahahah",
					11976311305l, 395.89, "manha", "Video",
					3, 45)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}

}
