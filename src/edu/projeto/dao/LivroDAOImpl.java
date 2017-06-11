package edu.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.projeto.entidade.Livro;

public class LivroDAOImpl implements LivroDAO{
	
	private Connection con;

	public LivroDAOImpl() {
		con = DBUtil.getInstance().getConn();
	}


	@Override
	public void adicionar(Livro l) {
		String sql = "INSERT INTO livro (nome, tipoColecao, numEdicao, ano) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, l.getNome());
			psmt.setString(2, l.getTipoColecao());
			psmt.setInt(3, l.getNumEdicao());
			psmt.setInt(4, l.getAno());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(String nome) {
		String sql = "DELETE FROM livro WHERE nome = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nome);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void alterar(Livro l) {
		String sql = "UPDATE livro SET nome = ?, tipoColecao = ?, numEdicao = ?, ano = ? WHERE id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, l.getNome());
			st.setString(2, l.getTipoColecao());
			st.setInt(3, l.getNumEdicao());
			st.setInt(4, l.getAno());
			st.setInt(5, l.getId());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Alterar no iLivro");		
	}

	@Override
	public List<Livro> pesquisarPorNome(String nome) {
		List<Livro> livros = new ArrayList<Livro>();
		String sql = "SELECT id, nome, tipoColecao, numEdicao, ano FROM livro " + "WHERE nome like ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + nome + "%");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Livro l = new Livro();
				l.setId(rs.getInt("id"));
				l.setNome(rs.getString("nome"));
				l.setTipoColecao(rs.getString("tipoColecao"));
				l.setNumEdicao(rs.getInt("numEdicao"));
				l.setAno(rs.getInt("ano"));
				livros.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
	
	@Override
	public List<Livro>  pesquisarTodos() {
		List<Livro> livros = new ArrayList<Livro>();
		String sql = "SELECT * FROM livro";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Livro l = new Livro();
				l.setId(rs.getInt("id"));
				l.setNome(rs.getString("nome"));
				l.setTipoColecao(rs.getString("tipoColecao"));
				l.setNumEdicao(rs.getInt("numEdicao"));
				l.setAno(rs.getInt("ano"));
				livros.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
}