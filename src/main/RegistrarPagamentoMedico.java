package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;
import consulta.dao.ConsultaDAO;
import medico.Medico;
import medico.dao.MedicoDAO;
import medico.pagamento.Pagamento;
import medico.pagamento.dao.PagamentoDAO;

public class RegistrarPagamentoMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Medico[] medicos;
	private JTextField mesTf;
	private JTextField anoTf;

	private List list;

	public RegistrarPagamentoMedico() {
		try {
			medicos = new MedicoDAO().getAllMedicos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setBounds(100, 100, 495, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		list = new List();
		list.setBounds(21, 26, 432, 204);
		contentPanel.add(list);

		mesTf = new JTextField();
		mesTf.setBounds(21, 285, 114, 19);
		contentPanel.add(mesTf);
		mesTf.setColumns(10);

		JLabel lblMes = new JLabel("Mês");
		lblMes.setBounds(21, 258, 70, 15);
		contentPanel.add(lblMes);

		anoTf = new JTextField();
		anoTf.setBounds(163, 285, 114, 19);
		contentPanel.add(anoTf);
		anoTf.setColumns(10);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(163, 258, 70, 15);
		contentPanel.add(lblAno);

		for (Medico medico : medicos) {
			list.add(medico.getNome() + " - " + medico.getCodRegistro());
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
					this.registrarPagamento();
					this.dispose();
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void registrarPagamento() {
		Pagamento p = new Pagamento();
		Medico m = medicos[list.getSelectedIndex()];

		int ano = Integer.parseInt(anoTf.getText());
		int mes = Integer.parseInt(mesTf.getText());

		p.setAno(ano);
		p.setMes(mes);

		try {
			Consulta[] consultas = new ConsultaDAO()
					.getConsultasDeMedicoEmIntervalo(m,
							LocalDate.of(ano, mes, 1),
							LocalDate.of(ano, mes, getLastDayOfMonth(mes)));
			double valor = Arrays.stream(consultas).mapToDouble(c -> c.getValor() * m.getPorcentagemRecebimento()).sum();
			p.setValorTotal(valor);
			new PagamentoDAO().salvar(p, m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getLastDayOfMonth(int mes) {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.MONTH, mes - 1);

		return c.getActualMaximum(Calendar.DATE);
	}
}
