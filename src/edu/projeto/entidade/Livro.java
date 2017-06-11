package edu.projeto.entidade;

public class Livro {

	private int id;
	private String nome;
	private String tipoColecao;
	private int numEdicao;
	private int ano;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoColecao() {
		return tipoColecao;
	}

	public void setTipoColecao(String tipoColecao) {
		this.tipoColecao = tipoColecao;
	}

	public int getNumEdicao() {
		return numEdicao;
	}

	public void setNumEdicao(int numEdicao) {
		this.numEdicao = numEdicao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
