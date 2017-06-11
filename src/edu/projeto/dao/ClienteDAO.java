package edu.projeto.dao;

import java.util.List;
import edu.projeto.entidade.Cliente;

public interface ClienteDAO {

	public void adicionar(Cliente c);
	public void excluir(Cliente c);
	public void alterar(Cliente c);
	public List<Cliente> pesquisarPorNome(String nome);
	public List<Cliente> pesquisarTodos();
}