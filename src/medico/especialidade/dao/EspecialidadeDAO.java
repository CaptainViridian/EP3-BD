package medico.especialidade.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import dao.DAO;
import medico.especialidade.Especialidade;

public class EspecialidadeDAO extends DAO {
	public void salvar(Especialidade e) throws SQLException {
		conn = Conexao.getConnection();
		
		String sql = "INSERT INTO Especialidade (Nome, Valor) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, e.getNome());
		ps.setDouble(2, e.getValor());
		
		ps.executeUpdate();
		
		conn.close();
	}

	public Especialidade[] getAllEspecialidades() throws SQLException {
		conn = Conexao.getConnection();
		
		String sql = "SELECT * FROM Especialidade";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		List<Especialidade> especialidades = new ArrayList<>();
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Especialidade e = new Especialidade();
			
			e.setNome(rs.getString("Nome"));
			e.setValor(rs.getDouble("Valor"));
			
			especialidades.add(e);
		}
		
		
		return especialidades.toArray(new Especialidade[especialidades.size()]);	
	}

	public Especialidade getEspecialidadePorNome(String nome) throws SQLException {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM Especialidade where nome='" + nome + "'";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		conn.close();

		rs.next();

		Especialidade e = new Especialidade();

		e.setNome(rs.getString("Nome"));
		e.setValor(rs.getDouble("valor"));

		return e;
	}
	
}
