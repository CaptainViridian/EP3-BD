package consulta.diagnostico.doenca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import consulta.diagnostico.doenca.Doenca;
import dao.DAO;
import medico.Medico;

public class DoencaDAO extends DAO {
	public void salvar(Doenca d) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "INSERT INTO Doenca (Nome) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, d.getNome());

		ps.executeUpdate();

		conn.close();
	}

	public Doenca getDoencaPorId(int id) throws SQLException {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM Doenca where id='" + id + "'";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		conn.close();

		Doenca d = null;
		if (rs.next()) {

			d = new Doenca();

			d.setId(rs.getInt("id"));
			d.setNome(rs.getString("Nome"));
		}
		return d;
	}

	public Doenca[] getAllDoencas() throws SQLException {
		conn = Conexao.getConnection();

		String sql = "SELECT * FROM Doenca";
		PreparedStatement ps = conn.prepareStatement(sql);

		List<Doenca> doencas = new ArrayList<>();

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Doenca d = new Doenca();

			d.setNome(rs.getString("Nome"));
			d.setId(rs.getInt("id"));
			// setar pagamentos, disponibilidades, especialidades

			doencas.add(d);
		}

		return doencas.toArray(new Doenca[doencas.size()]);
	}
}
