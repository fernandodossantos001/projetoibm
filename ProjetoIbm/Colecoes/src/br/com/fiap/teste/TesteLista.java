package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.fiap.beans.Cargo;

public class TesteLista {

	public static void main(String[] args) {
		
		
		//em herenca : crio o objeto pela super e instancio pela sub
		// cria pela super classe e instancia pela subclasse
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(new Cargo("DBA",5000,"PLENO"));
		cargos.add(new Cargo("ESTAGIARIO",500,"PLENO"));
		cargos.add(new Cargo("ESTAGIARIA",100,"JUNIOR"));
		cargos.add(new Cargo("Fer",3000,"JUNIOR"));
		cargos.add(new Cargo("Developer",898,"junior"));
	
		

		Collections.sort(cargos);
		
//		System.out.println(cargos);
		
		 
		for (Cargo cargo : cargos) {
			System.out.println(cargo.getNome());
//			System.out.println(cargo.getSalario());
//			System.out.println(cargo.getNivel());
		}
	}

}
