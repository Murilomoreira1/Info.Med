import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.time.format.DateTimeFormatter;

public class RelatoriosMed {

    private JFrame frame;
    private JTextArea txtResultado;
    private JComboBox<String> comboFiltro; // <-- FILTRO

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RelatoriosMed window = new RelatoriosMed();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RelatoriosMed() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(142, 217, 164));
        frame.setBounds(500, 10, 360, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Info.Med");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(104, 11, 136, 38);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Medicamentos Cadastrados");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblNewLabel_1.setBounds(10, 82, 324, 32);
        frame.getContentPane().add(lblNewLabel_1);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(39, 149, 266, 385);
        frame.getContentPane().add(scroll);
        
        
        // FILTRO DOS RELATÓRIOS
        comboFiltro = new JComboBox<>();
        scroll.setColumnHeaderView(comboFiltro);
        comboFiltro.addItem("Todos");
        comboFiltro.addItem("Válidos");
        comboFiltro.addItem("Inválidos");
        comboFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        comboFiltro.addActionListener(e -> carregarMedicamentos());
        
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
		
		
		btnNewButton.setBounds(0, 60, 344, 545);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2;
		btnNewButton_2 = new JButton("Cadastrar");
		btnNewButton_2.setBounds(39, 627, 105, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(e -> {
		    frame.dispose();   // fecha a tela atual
		    CadastroMed.main(null); // abre tela de cadastro
		});
		
		JButton btnNewButton_3 = new JButton("Relatórios");
		btnNewButton_3.setBounds(200, 627, 105, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		carregarMedicamentos();

    }

    private void carregarMedicamentos() {
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        BD banco = new BD();
        banco.conectar();

        if (!banco.estaConectado()) {
            txtResultado.setText("Erro ao conectar ao banco.");
            return;
        }

        try {
            // consulta básica
            var rs = banco.getStatement().executeQuery(
                    "SELECT * FROM medicamento ORDER BY nome_medicamento");

            String filtro = comboFiltro.getSelectedItem().toString();
            StringBuilder sb = new StringBuilder();
            LocalDate hoje = LocalDate.now();

            while (rs.next()) {

                LocalDate validade = LocalDate.parse(rs.getString("data_validade"));
                boolean valido = validade.isAfter(hoje);

                // ------------------------------
                // LÓGICA DO FILTRO
                // ------------------------------
                if (filtro.equals("Válidos") && !valido) continue;
                if (filtro.equals("Inválidos") && valido) continue;

                String status = valido ? "Válido" : "Inválido";

                	sb.append("Nome: ").append(rs.getString("nome_medicamento")).append("\n")
                        .append("Validade: ").append(validade.format(formatter)).append("\n")
                        .append("Status: ").append(status).append("\n")
                        .append("-------------------------------------\n");
            }

            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("Erro ao carregar: " + ex.getMessage());
        }

        banco.desconectar();
    }
}