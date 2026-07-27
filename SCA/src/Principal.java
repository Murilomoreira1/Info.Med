public class Principal {
    public static void main(String[] args) {

        BD bancoDeDados = new BD();
        bancoDeDados.conectar();

        if (bancoDeDados.estaConectado()) {

            // INSERIR MEDICAMENTO
            //bancoDeDados.inserirMedicamentos(2, "Flucetil", "2027-07-01", 1, 2);
        	//bancoDeDados.inserirMedicamentos(3, "Mesilato de Doxazosina", "2025-11-01", 1, 3);

            // LISTAR MEDICAMENTOS
            bancoDeDados.listarMedicamentos();
            
            // LISTAR MEDICAMENTOS VENCIDOS
            bancoDeDados.listarMedicamentosVencidos();


            bancoDeDados.desconectar();
        } else {
            System.out.println("Não foi possível conectar ao banco.");
        }
    }
}