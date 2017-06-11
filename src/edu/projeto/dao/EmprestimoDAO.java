package edu.projeto.dao;

import java.text.ParseException;
import java.util.List;

import edu.projeto.entidade.Cliente;
import edu.projeto.entidade.Emprestimo;
import edu.projeto.entidade.Livro;

public interface EmprestimoDAO {

	public void emprestar(Emprestimo em) throws Exception;
	
	public void devolver(Emprestimo em);
	
	public void excluir(Emprestimo em);

	public void alterar(Emprestimo em) throws ParseException, Exception;

	public List<Emprestimo> pesquisarPorCliente(Cliente c) throws Exception;
	
	public List<Emprestimo> pesquisarPorLivro(Livro l) throws Exception;	

	public List<Emprestimo> pesquisarTodos() throws Exception;
}