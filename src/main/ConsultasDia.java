package main;

import java.awt.List;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;
import consulta.dao.ConsultaDAO;

@SuppressWarnings("serial")
public class ConsultasDia extends JFrame {

	private JPanel contentPane;

	private Consulta[] consultasDia;
	private List list;
	private final JButton btnVisualizarConsulta = new JButton("Visualizar consulta");

	public ConsultasDia() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 629, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new List();
		list.setBounds(31, 10, 570, 331);
		contentPane.add(list);
		btnVisualizarConsulta.setBounds(41, 357, 175, 33);
		contentPane.add(btnVisualizarConsulta);
		
		btnVisualizarConsulta.addActionListener(e -> new VisualizarConsulta(consultasDia[list.getSelectedIndex()]).setVisible(true));

		getConsultasDoDia();
	}

	private void getConsultasDoDia() {
		try {
			consultasDia = new ConsultaDAO().getAllConsultasDoDia();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Arrays.sort(consultasDia, (a, b) -> a.getHora().compareTo(b.getHora()));

		for (Consulta consulta : consultasDia) {
			list.add(consulta.getHora().toString() + "   Médico: " + consulta.getMedico().getNome() + "   Paciente: "
					+ consulta.getPaciente().getNome() + "   Especialidade: " + consulta.getEspecialidade().getNome());
		}
	}
}
