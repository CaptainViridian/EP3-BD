package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;
import consulta.dao.ConsultaDAO;
import consulta.diagnostico.Diagnostico;
import consulta.diagnostico.dao.DiagnosticoDAO;
import consulta.diagnostico.doenca.Doenca;
import consulta.diagnostico.doenca.dao.DoencaDAO;

public class RegistrarDiagnostico extends JDialog {

	private final JPanel contentPanel = new JPanel();

	List list;
	Doenca[] doencas;
	JTextArea textArea;

	public RegistrarDiagnostico(Consulta c) {
		try {
			doencas = new DoencaDAO().getAllDoencas();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setBounds(100, 100, 675, 257);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDoenca = new JLabel("Doença");
		lblDoenca.setBounds(54, 24, 201, 15);
		contentPanel.add(lblDoenca);

		list = new List();
		list.setBounds(54, 60, 201, 102);
		contentPanel.add(list);
		{
			JLabel lblTratamento = new JLabel("Tratamento");
			lblTratamento.setBounds(330, 24, 201, 15);
			contentPanel.add(lblTratamento);
		}
		{
			textArea = new JTextArea();
			textArea.setBounds(340, 60, 253, 102);
			contentPanel.add(textArea);
		}

		for (Doenca d : doencas) {
			list.add(d.getNome());
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(e -> {
					this.registrarDiagnostico(c);
					this.dispose();
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e -> this.dispose());
				buttonPane.add(cancelButton);
			}
		}
	}

	private void registrarDiagnostico(Consulta c) {
		Diagnostico d = new Diagnostico();

		DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();

		d.setDoenca(doencas[list.getSelectedIndex()]);
		d.setTratamento(textArea.getText());

		c.setDiagnostico(d);

		try {
			diagnosticoDAO.salvar(d);
			d = diagnosticoDAO.getRecemCriado();
			c.setDiagnostico(d);
			new ConsultaDAO().registrarDiagnostico(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
