package br.com.projetodescanso.beans;

public class Veiculo {

		private String placa;
		private String modelo;
		private String cor;
		private int anoFab;
		private double valorVenda;
		private double valorCompra;
		
		
		public Veiculo() {
			
		}
		
		public Veiculo(String placa, String modelo, String cor, int anoFab, double valorVenda, double valorCompra) {
			setPlaca(placa);
			setModelo(modelo);
			setCor(cor);
			setAnoFab(anoFab);
			setValorVenda(valorVenda);
			setValorCompra(valorCompra);
		}
		
		
		public void setAll(String placa, String modelo, String cor, int anoFab, double valorVenda, double valorCompra) {
			setPlaca(placa);
			setModelo(modelo);
			setCor(cor);
			setAnoFab(anoFab);
			setValorVenda(valorVenda);
			setValorCompra(valorCompra);
		}
		
		
	
		public String getAll() {
			return "Placa :" + placa + "\n" +
					"Modelo :" + modelo  + "\n" +
					"Cor " + cor + "\n" +
					"Ano de Fabricacao " + anoFab + "\n" +
					"Valor de Venda " + valorVenda + "\n" +
					"Valor Compra " + valorCompra;
		}


		public String getPlaca() {
			return placa;
		}
		public void setPlaca(String placa) {
			this.placa = placa;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public String getCor() {
			return cor;
		}
		public void setCor(String cor) {
			this.cor = cor;
		}
		public int getAnoFab() {
			return anoFab;
		}
		public void setAnoFab(int anoFab) {
			this.anoFab = anoFab;
		}
		public double getValorVenda() {
			return valorVenda;
		}
		public void setValorVenda(double valorVenda) {
			this.valorVenda = valorVenda;
		}
		public double getValorCompra() {
			return valorCompra;
		}
		public void setValorCompra(double valorCompra) {
			this.valorCompra = valorCompra;
		}
		
		
}
