package edu.projeto.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.projeto.dao.LivroDAO;
import edu.projeto.dao.LivroDAOImpl;
import edu.projeto.entidade.Livro;

@ManagedBean
@SessionScoped
public class LivroMB {
	private LivroDAO livroDao = new LivroDAOImpl();
	private Livro livroAtual = new Livro();
	private List<Livro> lista = new ArrayList<Livro>();

	public String adicionar() {
		livroDao.adicionar(livroAtual);
		livroAtual = new Livro();
		return "";
	}

	public String alterar() {
		livroDao.alterar(livroAtual);
		return "";
	}

	public String excluir() {
		livroDao.excluir(livroAtual.getNome());
		return "";
	}

	public String pesquisar() {
		lista = livroDao.pesquisarPorNome(livroAtual.getNome());
		if (lista != null && lista.size() > 0) {
			livroAtual = lista.get(0);
		}
		return "";
	}
	
	public String todos() {
		lista = livroDao.pesquisarTodos();
		if (lista != null && lista.size() > 0) {
			livroAtual = lista.get(0);
		}
		return "";
	}

	public Livro getLivroAtual() {
		return livroAtual;
	}

	public void setLivroAtual(Livro LivroAtual) {
		this.livroAtual = LivroAtual;
	}

	public List<Livro> getLista() {
		return lista;
	}

	public void setLista(List<Livro> lista) {
		this.lista = lista;
	}
}
