package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;
import consulta.dao.ConsultaDAO;

public class RegistrarPagamentoConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();

	List list;
	String[] elementos = { "Dinheiro", "Outra forma" };

	public RegistrarPagamentoConsulta(Consulta c) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento");
		lblFormaDePagamento.setBounds(54, 24, 201, 15);
		contentPanel.add(lblFormaDePagamento);

		list = new List();
		list.setBounds(54, 73, 201, 102);
		contentPanel.add(list);

		for (String s : elementos) {
			list.add(s);
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
					this.registrarPagamento(c);
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

	private void registrarPagamento(Consulta c) {
		c.setFormaPagamento(elementos[list.getSelectedIndex()]);

		try {
			new ConsultaDAO().registrarPagamento(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
