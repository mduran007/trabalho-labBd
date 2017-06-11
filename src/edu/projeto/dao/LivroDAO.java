package edu.projeto.dao;

import java.util.List;

import edu.projeto.entidade.Livro;


public interface LivroDAO {

	public void adicionar(Livro l);
	public void excluir(String nome);
	public void alterar(Livro l);
	public List<Livro> pesquisarPorNome(String nome);
	public List<Livro> pesquisarTodos();
}