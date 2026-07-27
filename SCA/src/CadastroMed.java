import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.JFormattedTextField;

public class CadastroMed {

	private JFrame frame;
	private JTextField txtSadada;
	private JFormattedTextField txtDat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMed window = new CadastroMed();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroMed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(142, 217, 164));
		frame.getContentPane().setBounds(new Rectangle(8, 0, 0, 0));
		frame.getContentPane().setForeground(new Color(142, 217, 164));
		frame.setBounds(500, 10, 360, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Info.Med");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBounds(104, 11, 136, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cadastrar Remédio");
		lblNewLabel_1.setDoubleBuffered(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(10, 118, 324, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JFormattedTextField textField;
		txtDat = new JFormattedTextField();
		txtDat.setBounds(57, 397, 229, 45);
		frame.getContentPane().add(txtDat);
		
		txtSadada = new JTextField();
		txtSadada.setHorizontalAlignment(SwingConstants.LEFT);
		txtSadada.setToolTipText("");
		txtSadada.setBounds(57, 308, 229, 45);
		frame.getContentPane().add(txtSadada);
		txtSadada.setColumns(10);
		
		// Bloquear números no nome
        txtSadada.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isLetter(c) && c != ' ') {
                    e.consume();
                }
            }
        });
		
		JLabel lblNewLabel_2 = new JLabel("Escrever Manualmente");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(52, 210, 239, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nome do Medicamento");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(80, 283, 184, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Data de Validade");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(80, 364, 184, 22);
		frame.getContentPane().add(lblNewLabel_4);
		
		try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            
			textField = new JFormattedTextField(mascara);
			textField.setHorizontalAlignment(SwingConstants.LEFT);
			textField.setBounds(57, 397, 229, 45);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
	
			 } catch (Exception e) {
		            e.printStackTrace();
		        }
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(UIManager.getBorder("DesktopIcon.border"));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(96, 509, 152, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		// EVENTO DO BOTÃO ENVIAR
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtSadada.getText().trim();
                String data = txtDat.getText().trim();

                // Verificar se todos os campos foram preenchidos
                if (nome.isEmpty() || data.contains("_")) {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos corretamente!",
                        "Aviso",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                cadastrarMedicamento(nome, data);

            }
        });
			frame.getContentPane().add(btnNewButton_1);
		
			JButton btnNewButton = new JButton("");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton.setBorderPainted(false);
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setVerifyInputWhenFocusTarget(false);
			btnNewButton.setRolloverEnabled(false);
			btnNewButton.setRequestFocusEnabled(false);
			btnNewButton.setFocusable(false);
			btnNewButton.setFocusTraversalKeysEnabled(false);
			btnNewButton.setFocusPainted(false);
			btnNewButton.setEnabled(false);
			btnNewButton.setDefaultCapable(false);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setBounds(0, 60, 344, 545);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnNewButton_2;
			btnNewButton_2 = new JButton("Cadastrar");
			btnNewButton_2.setBounds(39, 627, 105, 23);
			frame.getContentPane().add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("Relatórios");
			btnNewButton_3.setBounds(200, 627, 105, 23);
			frame.getContentPane().add(btnNewButton_3);
			
			btnNewButton_3.addActionListener(e -> {
			    frame.dispose(); // fecha a tela atual
			    RelatoriosMed.main(null); // abre tela de relatórios
			});
		
		}

	/**
	 * Método que cadastra o medicamento no banco
	 */
	private void cadastrarMedicamento(String nome, String dataTexto) {
            
		try {
			// Converte data para formato do MySQL (AAAA-MM-DD)
			DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataValidade = LocalDate.parse(dataTexto, formatoEntrada);

			// Calcula status
			String status = dataValidade.isBefore(LocalDate.now()) ? "Inválido" : "Válido";

			// Gera um código de barras fake
			Random r = new Random();
			int codigoBarras = 100000 + r.nextInt(900000);

			// ID do usuário fixo (pois você não tem login ainda)
			int idUsuario = 1;

			// Conexão com o banco
			BD banco = new BD();
			banco.conectar();

			if (!banco.estaConectado()) {
				JOptionPane.showMessageDialog(frame, "Erro ao conectar ao banco.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Gera ID automaticamente
			var rs = banco.getStatement().executeQuery("SELECT COALESCE(MAX(id_medicamento),0)+1 AS novoID FROM medicamento");
			rs.next();
			int id = rs.getInt("novoID");

			// Formata a data para o MySQL
			String dataMySQL = dataValidade.toString(); // AAAA-MM-DD
			
			String queryCB = "INSERT INTO codigo_barras (id_codigobarras, pais, empresa, info_produto, verificador) "
		               + "VALUES (" + codigoBarras + ", 1, 1, 1, 1)";
			
			banco.getStatement().executeUpdate(queryCB);
			
			String query = "INSERT INTO medicamento (id_medicamento, nome_medicamento, data_validade, status_medicamento, id_usuario, id_codigobarras) "
					+ "VALUES (" + id + ", '" + nome + "', '" + dataMySQL + "', '" + status + "', " + idUsuario
					+ ", " + codigoBarras + ")";

			banco.getStatement().executeUpdate(query);
			banco.desconectar();

			JOptionPane.showMessageDialog(frame, "Medicamento cadastrado com sucesso!");

			// Limpa campos
			txtSadada.setText("");
			txtDat.setText("");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Erro ao cadastrar: " + "Insira uma data válida (Ex: 01/01/2001)", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}