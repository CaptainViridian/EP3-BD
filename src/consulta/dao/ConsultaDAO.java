package consulta.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import consulta.Consulta;
import consulta.diagnostico.dao.DiagnosticoDAO;
import dao.DAO;
import medico.Medico;
import medico.dao.MedicoDAO;
import medico.especialidade.dao.EspecialidadeDAO;
import paciente.Paciente;
import paciente.dao.PacienteDAO;

public class ConsultaDAO extends DAO {
	public void salvar(Consulta c) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "INSERT INTO Consulta (Data, Hora, Valor, Pago, Registro_medico, RG_paciente, Nome_especialidade, Forma_pagamento)"
				+ " VALUES (?, ?, ?, ? , ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(c.getData()));
		ps.setObject(2, c.getHora());
		ps.setDouble(3, c.getValor());
		ps.setBoolean(4, c.isPago());
		ps.setString(5, c.getMedico().getCodRegistro());
		ps.setString(6, c.getPaciente().getRG());
		ps.setString(7, c.getEspecialidade().getNome());
		ps.setString(8, c.getFormaPagamento());

		ps.executeUpdate();

		conn.close();
	}

	public Consulta[] getAllConsultasDoDia() throws SQLException {
		conn = Conexao.getConnection();

		String sql = "SELECT * FROM Consulta WHERE data = '" + LocalDate.now()
				+ "'";
		PreparedStatement ps = conn.prepareStatement(sql);

		List<Consulta> consultas = new ArrayList<>();

		ResultSet rs = ps.executeQuery();

		conn.close();

		while (rs.next()) {
			Consulta c = criarConsulta(rs);
			consultas.add(c);
		}

		return consultas.toArray(new Consulta[consultas.size()]);
	}

	public void registrarPagamento(Consulta c) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "UPDATE Consulta SET forma_pagamento=?, pago=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, c.getFormaPagamento());
		ps.setBoolean(2, true);
		ps.setInt(3, c.getId());

		ps.executeUpdate();

		conn.close();
	}

	public void registrarDiagnostico(Consulta c) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "UPDATE Consulta SET id_diagnostico=? where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, c.getDiagnostico().getId());
		ps.setInt(2, c.getId());

		ps.executeUpdate();

		conn.close();

	}

	public Consulta[] getAllConsultasDePaciente(Paciente p)
			throws SQLException {
		conn = Conexao.getConnection();

		String sql = "SELECT * FROM Consulta inner join paciente on RG_paciente=RG";
		PreparedStatement ps = conn.prepareStatement(sql);

		List<Consulta> consultas = new ArrayList<>();

		ResultSet rs = ps.executeQuery();

		conn.close();

		while (rs.next()) {
			Consulta c = criarConsulta(rs);
			consultas.add(c);
		}

		return consultas.toArray(new Consulta[consultas.size()]);
	}

	private Consulta criarConsulta(ResultSet rs) throws SQLException {
		Consulta c = new Consulta();

		c.setData(rs.getDate("Data").toLocalDate());
		c.setHora(rs.getTime("Hora").toLocalTime());
		c.setValor(rs.getDouble("Valor"));
		c.setFormaPagamento(rs.getString("forma_pagamento"));
		c.setPago(rs.getBoolean("Pago"));
		c.setId(rs.getInt("id"));

		c.setMedico(new MedicoDAO()
				.getMedicoPorRegistro(rs.getString("registro_medico")));
		c.setPaciente(new PacienteDAO()
				.getPacientePorRG(rs.getString("RG_paciente")));
		c.setEspecialidade(new EspecialidadeDAO()
				.getEspecialidadePorNome(rs.getString("nome_especialidade")));
		c.setDiagnostico(new DiagnosticoDAO()
				.getDiagnosticoPorId(rs.getInt("id_diagnostico")));

		return c;
	}

	public Consulta[] getConsultasDeMedicoEmIntervalo(Medico m,
			LocalDate inicio, LocalDate fim) throws SQLException {

		conn = Conexao.getConnection();

		String sql = "SELECT * from Consulta where registro_medico='"
				+ m.getCodRegistro() + "' and data between '" + inicio
				+ "' and '" + fim + "'";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		conn.close();

		List<Consulta> consultas = new ArrayList<>();

		while (rs.next()) {
			Consulta c = criarConsulta(rs);
			consultas.add(c);
		}

		return consultas.toArray(new Consulta[consultas.size()]);
	}
}
