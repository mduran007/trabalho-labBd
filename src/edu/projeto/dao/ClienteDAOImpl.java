package edu.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.projeto.entidade.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	private Connection con;

	public ClienteDAOImpl() {
		con = DBUtil.getInstance().getConn();
	}

	@Override
	public void adicionar(Cliente c) {
		String sql = "INSERT INTO cliente (nome, telefone, endereco) VALUES (?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, c.getNome());
			psmt.setString(2, c.getTelefone());
			psmt.setString(3, c.getEndereco());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Cliente c) {
		String sql = "DELETE FROM cliente WHERE id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, c.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Cliente c) {
		String sql = "UPDATE cliente SET nome = ?, telefone = ?, endereco = ? WHERE id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getNome());
			st.setString(2, c.getTelefone());
			st.setString(3, c.getEndereco());
			st.setInt(4, c.getId());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Alterar no iCliente");
	}

	@Override
	public List<Cliente> pesquisarPorNome(String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "SELECT id, nome, telefone, endereco FROM cliente " + "WHERE nome like ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + nome + "%");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				c.setEndereco(rs.getString("endereco"));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	@Override
	public List<Cliente> pesquisarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM cliente";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				c.setEndereco(rs.getString("endereco"));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
}