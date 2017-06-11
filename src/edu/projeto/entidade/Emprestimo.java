package edu.projeto.entidade;


public class Emprestimo {

	private int id;
	private Cliente cliente;
	private Livro livro;
	private String dataEmprestimo;
	private String dataDevolucao;
	private String devolvido;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public String getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getDevolvido() {
		return devolvido;
	}
	public void setDevolvido(String devolvido) {
		this.devolvido = devolvido;
	}
}