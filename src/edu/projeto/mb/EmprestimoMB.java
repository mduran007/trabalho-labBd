package edu.projeto.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.projeto.dao.ClienteDAO;
import edu.projeto.dao.ClienteDAOImpl;
import edu.projeto.dao.EmprestimoDAO;
import edu.projeto.dao.EmprestimoDAOImpl;
import edu.projeto.dao.LivroDAO;
import edu.projeto.dao.LivroDAOImpl;
import edu.projeto.entidade.Cliente;
import edu.projeto.entidade.Emprestimo;
import edu.projeto.entidade.Livro;

@ManagedBean
@SessionScoped

public class EmprestimoMB {
	
	private EmprestimoDAO emprestimoDao = new EmprestimoDAOImpl();
	private ClienteDAO clienteDao = new ClienteDAOImpl();
	private LivroDAO livroDao = new LivroDAOImpl();
	
	private Emprestimo emprestimoAtual  = new Emprestimo();
	private Livro livroAtual = new Livro();
	private Cliente clienteAtual = new Cliente();
	
	private List<Emprestimo> lista = new ArrayList<Emprestimo>();
	private List<Cliente> listaCliente = new ArrayList<Cliente>();
	private List<Livro> listaLivro = new ArrayList<Livro>();


	public String emprestar() {
		
		emprestimoAtual.setCliente( clienteAtual );
		emprestimoAtual.setLivro( livroAtual );
		try {
			emprestimoDao.emprestar( emprestimoAtual );
		} catch (Exception e) {
			e.printStackTrace();
		}
		emprestimoAtual = new Emprestimo();
		return "";
	}
	
	public String alterar() {
		try {
			emprestimoDao.alterar(emprestimoAtual);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String excluir() {
		emprestimoDao.excluir(emprestimoAtual);
		return "";
	}
	
	public String todos() {
		try {
			lista = emprestimoDao.pesquisarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lista != null && lista.size() > 0) {
			emprestimoAtual = lista.get(0);
		}
		return "";
	}

	public String pesquisarCliente() {
		listaCliente = clienteDao.pesquisarPorNome( clienteAtual.getNome());
		if (listaCliente != null && listaCliente.size() > 0) {
			clienteAtual = listaCliente.get(0);
		}
		return "";
	}

	public String pesquisarLivro() {
		listaLivro = livroDao.pesquisarPorNome(livroAtual.getNome());
		if (listaLivro != null && listaLivro.size() > 0) {
			livroAtual = listaLivro.get(0);
		}
		return "";
	}

	public Emprestimo getEmprestimoAtual() {
		return emprestimoAtual;
	}

	public void setEmprestimoAtual(Emprestimo EmprestimoAtual) {
		this.emprestimoAtual = EmprestimoAtual;
	}

	public List<Emprestimo> getLista() {
		return lista;
	}

	public void setLista(List<Emprestimo> lista) {
		this.lista = lista;
	}
	
	public Livro getLivroAtual() {
		return livroAtual;
	}

	public void setLivroAtual(Livro LivroAtual) {
		this.livroAtual = LivroAtual;
	}

	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	public void setClienteAtual(Cliente clienteAtual) {
		this.clienteAtual = clienteAtual;
	}
}