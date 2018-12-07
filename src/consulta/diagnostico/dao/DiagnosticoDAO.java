package consulta.diagnostico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import consulta.diagnostico.Diagnostico;
import consulta.diagnostico.doenca.dao.DoencaDAO;
import dao.DAO;

public class DiagnosticoDAO extends DAO {
	public void salvar(Diagnostico d) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "INSERT INTO Diagnostico (Tratamento, id_doenca) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, d.getTratamento());
		ps.setInt(2, d.getDoenca().getId());

		ps.executeUpdate();

		conn.close();
	}

	public Diagnostico getDiagnosticoPorId(int id) throws SQLException {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM Diagnostico where id='" + id + "'";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		conn.close();

		Diagnostico d = null;
		if (rs.next()) {

			d = new Diagnostico();

			d.setId(rs.getInt("id"));
			d.setTratamento(rs.getString("Tratamento"));
			d.setDoenca(new DoencaDAO().getDoencaPorId(rs.getInt("id_doenca")));
		}
		return d;
	}

	public Diagnostico getRecemCriado() throws SQLException {
		conn = Conexao.getConnection();
		String sql = "select * from diagnostico order by id desc";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Diagnostico d = new Diagnostico();

		d.setDoenca(new DoencaDAO().getDoencaPorId(rs.getInt("id_doenca")));
		d.setId(rs.getInt("id"));
		d.setTratamento(rs.getString("tratamento"));
		
		return d;
	}
}
