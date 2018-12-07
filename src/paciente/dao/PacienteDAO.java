package paciente.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import dao.DAO;
import paciente.Paciente;

public class PacienteDAO extends DAO {

	public void salvar(Paciente p) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "INSERT INTO Paciente (RG, Nome, Telefone) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, p.getRG());
		ps.setString(2, p.getNome());
		ps.setString(3, p.getTelefone());

		ps.executeUpdate();

		conn.close();
	}

	public Paciente[] getAllPacientes() throws SQLException {
		conn = Conexao.getConnection();

		String sql = "SELECT * FROM Paciente";
		PreparedStatement ps = conn.prepareStatement(sql);

		List<Paciente> pacientes = new ArrayList<>();

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Paciente p = new Paciente();

			p.setNome(rs.getString("Nome"));
			p.setRG(rs.getString("RG"));
			p.setTelefone(rs.getString("Telefone"));

			pacientes.add(p);
		}

		return pacientes.toArray(new Paciente[pacientes.size()]);
	}

	public Paciente getPacientePorRG(String RG) throws SQLException {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM Paciente where RG='" + RG + "'";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		conn.close();

		rs.next();

		Paciente p = new Paciente();

		p.setNome(rs.getString("Nome"));
		p.setRG(rs.getString("RG"));
		p.setTelefone(rs.getString("Telefone"));

		return p;
	}
}
