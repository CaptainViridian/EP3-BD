package medico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import dao.DAO;
import medico.Medico;
import medico.disponibilidade.Disponibilidade;
import medico.disponibilidade.dao.DisponibilidadeDAO;
import medico.especialidade.Especialidade;
import medico.pagamento.Pagamento;
import medico.pagamento.dao.PagamentoDAO;

public class MedicoDAO extends DAO {
	public void salvar(Medico m) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "INSERT INTO Medico (Nome, Cod_registro, Porcentagem_recebimento) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, m.getNome());
		ps.setString(2, m.getCodRegistro());
		ps.setDouble(3, m.getPorcentagemRecebimento());

		ps.executeUpdate();

		conn.close();

		DisponibilidadeDAO dDAO = new DisponibilidadeDAO();
		for (Disponibilidade d : m.getDisponibilidades()) {
			dDAO.salvar(d, m);
		}

		PagamentoDAO pDAO = new PagamentoDAO();
		for (Pagamento p : m.getPagamentos()) {
			pDAO.salvar(p, m);
		}

		for (Especialidade e : m.getEspecialidades()) {
			System.out.println(e.getNome());
			this.salvarEspecialidade(m, e);
		}

	}

	public void salvarEspecialidade(Medico m, Especialidade e) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "INSERT INTO Especialidade_medico (Registro_medico, Nome_especialidade) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, m.getCodRegistro());
		ps.setString(2, e.getNome());

		ps.executeUpdate();

		conn.close();
	}

	public Medico[] getAllMedicos() throws SQLException {
		conn = Conexao.getConnection();

		String sql = "SELECT * FROM Medico";
		PreparedStatement ps = conn.prepareStatement(sql);

		List<Medico> medicos = new ArrayList<>();

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Medico m = new Medico();

			m.setNome(rs.getString("Nome"));
			m.setCodRegistro(rs.getString("Cod_registro"));
			m.setPorcentagemRecebimento(rs.getDouble("Porcentagem_recebimento"));

			// setar pagamentos, disponibilidades, especialidades

			medicos.add(m);
		}

		return medicos.toArray(new Medico[medicos.size()]);
	}

	public Especialidade[] getEspecialidades(Medico m) throws SQLException {
		conn = Conexao.getConnection();

		String sql = "select E.nome, E.valor, M.nome from Especialidade as E inner join Especialidade_Medico on E.nome=nome_especialidade "
				+ "inner join Medico as M on registro_medico=cod_registro where M.cod_registro = '" + m.getCodRegistro()
				+ "'";
		PreparedStatement ps = conn.prepareStatement(sql);

		List<Especialidade> especialidades = new ArrayList<>();

		ResultSet rs = ps.executeQuery();

		conn.close();

		while (rs.next()) {
			Especialidade e = new Especialidade();

			e.setNome(rs.getString("Nome"));
			e.setValor(rs.getDouble("Valor"));

			especialidades.add(e);
		}
		return especialidades.toArray(new Especialidade[especialidades.size()]);
	}

	public Medico getMedicoPorRegistro(String registro) throws SQLException {
		conn = Conexao.getConnection();
		String sql = "SELECT * FROM Medico where cod_registro='" + registro + "'";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		
		conn.close();

		rs.next();

		Medico m = new Medico();

		m.setNome(rs.getString("Nome"));
		m.setCodRegistro(rs.getString("Cod_registro"));
		m.setPorcentagemRecebimento(rs.getDouble("Porcentagem_recebimento"));

		// setar pagamentos, disponibilidades, especialidades

		return m;
	}

}
