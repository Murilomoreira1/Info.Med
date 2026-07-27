import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class BD {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultset = null;

    public void conectar() {
        String servidor = "jdbc:mysql://localhost:3306/InfoMed?useTimezone=true&serverTimezone=UTC&useSSL=false";
        String usuario = "root";
        String senha = ""; // Altere se tiver senha
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(servidor, usuario, senha);
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }

    public boolean estaConectado() {
        return this.connection != null;
    }
    
    // INSERIR com cálculo automático do status
    public void inserirMedicamentos(int id_medicamento, String nome_medicamento, String data_validade,
                                    int id_usuario, int id_codigobarras) {

        try {
            LocalDate validade = LocalDate.parse(data_validade);
            LocalDate hoje = LocalDate.now();
            String status = validade.isBefore(hoje) ? "Inválido" : "Válido";

            String query = "INSERT INTO medicamento (id_medicamento, nome_medicamento, data_validade, status_medicamento, id_usuario, id_codigobarras) "
                    + "VALUES ('" + id_medicamento + "', '" + nome_medicamento + "', '" + data_validade + "', '"
                    + status + "', '" + id_usuario + "', '" + id_codigobarras + "');";

            System.out.println(query);
            this.statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    // LISTAR com cálculo automático do status
    public void listarMedicamentos() {
        try {
            String query = "SELECT * FROM medicamento ORDER BY nome_medicamento";
            this.resultset = this.statement.executeQuery(query);

            LocalDate hoje = LocalDate.now();

            while (this.resultset.next()) {

                LocalDate validade = LocalDate.parse(this.resultset.getString("data_validade"));
                String statusCalculado = validade.isBefore(hoje) ? "Inválido" : "Válido";

                System.out.println("\nID: " + this.resultset.getString("id_medicamento")
                        + "\nNome: " + this.resultset.getString("nome_medicamento")
                        + "\nData de Validade: " + validade
                        + "\nStatus: " + statusCalculado
                        + "\nID Usuário: " + this.resultset.getString("id_usuario")
                        + "\nID Código de Barras: " + this.resultset.getString("id_codigobarras"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }
    
    public void listarMedicamentosVencidos() {
        try {
            String query = "SELECT * FROM medicamento ORDER BY nome_medicamento";
            this.resultset = this.statement.executeQuery(query);

            LocalDate hoje = LocalDate.now();

            System.out.println("\n=== MEDICAMENTOS VENCIDOS ===");

            boolean encontrou = false;

            while (this.resultset.next()) {

                LocalDate validade = LocalDate.parse(this.resultset.getString("data_validade"));
                String statusCalculado = validade.isBefore(hoje) ? "Inválido" : "Válido";

                if (statusCalculado.equals("Inválido")) {
                    encontrou = true;

                    System.out.println("\nID: " + this.resultset.getString("id_medicamento")
                            + "\nNome: " + this.resultset.getString("nome_medicamento")
                            + "\nData de Validade: " + validade
                            + "\nStatus: " + statusCalculado
                            + "\nID Usuário: " + this.resultset.getString("id_usuario")
                            + "\nID Código de Barras: " + this.resultset.getString("id_codigobarras"));
                }
            }

            if (!encontrou) {
                System.out.println("\nNenhum medicamento vencido encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar vencidos: " + e.getMessage());
        }
    }
    
    public Statement getStatement() {
        return this.statement;
    }

    public void desconectar() {
        try {
            this.connection.close();
        } catch (Exception e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }
}