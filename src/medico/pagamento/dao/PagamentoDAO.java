package medico.pagamento.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import dao.DAO;
import medico.Medico;
import medico.pagamento.Pagamento;

public class PagamentoDAO extends DAO {
	
	public void salvar(Pagamento p, Medico m) throws SQLException {
		conn = Conexao.getConnection();
		
		String sql = "INSERT INTO Pagamento (Mes, Ano, Valor_total, Registro_medico) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, p.getMes());
		ps.setInt(2, p.getAno());
		ps.setDouble(3, p.getValorTotal());
		ps.setString(4, m.getCodRegistro());
		
		ps.executeUpdate();
		
		conn.close();
	}

}
