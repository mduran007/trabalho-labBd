package edu.projeto.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.projeto.dao.ClienteDAO;
import edu.projeto.dao.ClienteDAOImpl;
import edu.projeto.entidade.Cliente;

@ManagedBean
@SessionScoped
public class ClienteMB {
	
	private ClienteDAO clienteDao = new ClienteDAOImpl();
	private Cliente clienteAtual = new Cliente();
	private List<Cliente> lista = new ArrayList<Cliente>();

	public String adicionar() {
		clienteDao.adicionar(clienteAtual);
		clienteAtual = new Cliente();
		return "";
	}

	public String alterar() {
		clienteDao.alterar(clienteAtual);
		return "";
	}

	public String excluir() {
		clienteDao.excluir(clienteAtual);
		return "";
	}

	public String pesquisar() {
		lista = clienteDao.pesquisarPorNome(clienteAtual.getNome());
		if (lista != null && lista.size() > 0) {
			clienteAtual = lista.get(0);
		}
		return "";
	}
	
	public String todos() {
		lista = clienteDao.pesquisarTodos();
		if (lista != null && lista.size() > 0) {
			clienteAtual = lista.get(0);
		}
		return "";
	}

	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	public void setClienteAtual(Cliente ClienteAtual) {
		this.clienteAtual = ClienteAtual;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
}
