package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
	// CREATE
	public void adicionarCliente(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente (nome, cpf, email) VALUES (?, ?, ?)";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			
			stmt.executeUpdate();
		}
	}

	// READ (listar todos)
	public List<Cliente> listarClientes() throws SQLException {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM cliente";

		try (Connection conn = Conexao.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdcliente(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));				
				clientes.add(cliente);
			}
		}
		return clientes;
	}
	
    public Cliente buscarPorId(int idcliente) throws SQLException {  // ✅ MÉTODO FALTANTE ADICIONADO
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idcliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdcliente(rs.getInt("idcliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    return cliente;
                }
            }
        }
        return null;
    }

	// UPDATE
	public void atualizarCliente(Cliente cliente) throws SQLException {
		String sql = "UPDATE cliente SET nome = ?, cpf = ?, email = ? WHERE idcliente = ?";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());			
			stmt.setInt(4, cliente.getIdcliente());
			stmt.executeUpdate();
		}
	}

	// DELETE
	public void deletarCliente(int id) throws SQLException {
		String sql = "DELETE FROM cliente WHERE idcliente = ?";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
}