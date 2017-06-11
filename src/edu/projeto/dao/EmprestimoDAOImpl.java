package edu.projeto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.projeto.entidade.Cliente;
import edu.projeto.entidade.Emprestimo;
import edu.projeto.entidade.Livro;

public class EmprestimoDAOImpl implements EmprestimoDAO {

	private Connection con;

	public EmprestimoDAOImpl() {
		con = DBUtil.getInstance().getConn();
	}

	@Override
	public void emprestar(Emprestimo em) throws Exception {

		String sql = "INSERT INTO emprestimo (idCliente, idLivro, dataEmprestimo, dataDevolucao, devolvido) "
				+ "VALUES ( ?, ?, ?, ?,?)";
		
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, em.getCliente().getId() );
			psmt.setInt(2, em.getLivro().getId() );
			psmt.setDate(3, formataStringData(em.getDataEmprestimo()) );
			psmt.setDate(4, formataStringData(em.getDataDevolucao()) );
			psmt.setString(5, em.getDevolvido() );
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void devolver(Emprestimo em) {
		
		String sql = "UPDATE emprestimo "
				+ "SET devolvido = ? "
				+ "WHERE id = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, em.getDevolvido() );
			st.setInt(2, em.getId());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void excluir(Emprestimo em) {
		String sql = "DELETE FROM emprestimo WHERE id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, em.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void alterar(Emprestimo em) throws Exception {
		String sql = "UPDATE emprestimo "
				+ "SET idCliente = ?, idLivro = ?, dataEmprestimo = ?, dataDevolucao = ?, devolvido = ? "
				+ "WHERE id = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, em.getCliente().getId() );
			st.setInt(2, em.getLivro().getId() );
			st.setDate(3, formataStringData(em.getDataEmprestimo()) );
			st.setDate(4, formataStringData(em.getDataDevolucao()) );
			st.setString(5, em.getDevolvido() );
			st.setInt(6, em.getId());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public List<Emprestimo> pesquisarPorCliente(Cliente c) throws Exception {

		String sql = "SELECT e.id, idCliente, idLivro, e.dataEmprestimo, e.dataDevolucao, e.devolvido "
				+ "FROM emprestimo AS e " 
				+ "INNER JOIN livro AS l " 
				+ "ON e.idLivro = l.id "
				+ "INNER JOIN cliente AS c "
				+ "ON e.idCliente = c.id "
				+ "WHERE c.id = ?";
		
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, c.getId());
			ResultSet rs = psmt.executeQuery();
			
			while (rs.next()) {
				Emprestimo em = new Emprestimo();
				em.setId(rs.getInt("id"));
				em.setCliente( cliente( (rs.getInt("idCliente"))));
				em.setLivro( livro( (rs.getInt("idLivro"))));
				em.setDataEmprestimo(formataDataString(rs.getDate("dataEmprestimo")));
				em.setDataDevolucao(formataDataString(rs.getDate("dataDevolucao")));
				emprestimos.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprestimos;
	}

	
	@Override
	public List<Emprestimo> pesquisarPorLivro(Livro l) throws Exception {

		String sql = "SELECT e.id, idCliente, idLivro, e.dataEmprestimo, e.dataDevolucao, e.devolvido  "
				+ "FROM emprestimo AS e " 
				+ "INNER JOIN livro AS l " 
				+ "ON e.idLivro = l.id " 
				+ "WHERE l.id = ?";

		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, l.getId());
			ResultSet rs = psmt.executeQuery();
			
			while (rs.next()) {
				Emprestimo em = new Emprestimo();
				em.setId(rs.getInt("id"));
				em.setCliente( cliente( (rs.getInt("idCliente"))));
				em.setLivro( livro( (rs.getInt("idLivro"))));
				em.setDataEmprestimo(formataDataString(rs.getDate("dataEmprestimo")));
				em.setDataDevolucao(formataDataString(rs.getDate("dataDevolucao")));
				emprestimos.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprestimos;
	}
	
	
	@Override
	public List<Emprestimo> pesquisarTodos() throws Exception {

		String sql = "SELECT e.id, idCliente, idLivro, e.dataEmprestimo, e.dataDevolucao, e.devolvido "
				+ "FROM emprestimo AS e " 
				+ "INNER JOIN livro AS l " 
				+ "ON e.idLivro = l.id "
				+ "INNER JOIN cliente AS c "
				+ "ON e.idCliente = c.id ";

		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while (rs.next()) {
				Emprestimo em = new Emprestimo();
				em.setId(rs.getInt("id"));
				em.setCliente( cliente( (rs.getInt("idCliente"))));
				em.setLivro( livro( (rs.getInt("idLivro"))));
				em.setDataEmprestimo(formataDataString(rs.getDate("dataEmprestimo")));
				em.setDataDevolucao(formataDataString(rs.getDate("dataDevolucao")));
				em.setDevolvido( rs.getString("devolvido"));
				emprestimos.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprestimos;
	}

	
	public Cliente cliente(Integer id) {

		String sql = "SELECT id, nome, telefone, endereco "
				+ "FROM cliente " 
				+ "WHERE id = ?";
		Cliente c = new Cliente();

		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				c.setEndereco(rs.getString("endereco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	
	public Livro livro(Integer id) {

		String sql = "SELECT id, nome, tipoColecao, numEdicao, ano "
				+ "FROM livro " 
				+ "WHERE id = ?";

		Livro l = new Livro();

		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				l.setId(rs.getInt("id"));
				l.setNome(rs.getString("nome"));
				l.setTipoColecao(rs.getString("tipoColecao"));
				l.setNumEdicao(rs.getInt("numEdicao"));
				l.setAno(rs.getInt("ano"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}	
	
	
	public String formataDataString(java.sql.Date data) throws Exception {   
        if (data == null || data.equals("")){
            return null;   
        }  
        Date sqlDate = data;   
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(sqlDate.getTime())); 
        return date;   
    }
	
	
	public Date formataStringData(String data) throws Exception {   
        if (data == null || data.equals("")){
            return null;   
        }          
        String stringDate = data;
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(fmt.parse(stringDate).getTime());
        return date;   
    }
}