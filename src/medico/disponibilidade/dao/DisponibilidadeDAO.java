package medico.disponibilidade.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import dao.DAO;
import medico.Medico;
import medico.disponibilidade.Disponibilidade;

public class DisponibilidadeDAO extends DAO{

	public void salvar(Disponibilidade d, Medico m) throws SQLException {
		conn = Conexao.getConnection();
		
		String sql = "INSERT INTO Disponibilidade (Dia_semana, Hora_inicio, Hora_fim, Registro_medico) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, d.getDiaSemana());
		ps.setString(2, d.getHoraInicio());
		ps.setString(3, d.getHoraFim());
		ps.setString(4, m.getCodRegistro());
		
		ps.executeUpdate();
		
		conn.close();
	}
}
