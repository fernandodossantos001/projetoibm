package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TesteArrayIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> teste = new ArrayList<Integer>();
		teste.add(12);
		teste.add(13);
		teste.add(14);
		teste.add(15);
		teste.add(16);
		Iterator <Integer> it = teste.iterator();
		
		
		while (it.hasNext()) {
			Integer integer = (Integer) it.next();
			it.remove();
			System.out.println(integer);
			
			
		}
		
	}

}
