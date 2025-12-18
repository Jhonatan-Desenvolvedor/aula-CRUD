package crud;

import java.sql.SQLException;
import java.util.Scanner;
public class AppJava {
	
	public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao sistema de gerenciamento de usuários!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Cliente");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Deletar Cliente");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // consumir nova linha

            try {
                switch (escolha) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();                        

                        Cliente cliente = new Cliente();
                        cliente.setNome(nome);
                        cliente.setCpf(cpf);
                        cliente.setEmail(email);
                        

                        clienteDAO.adicionarCliente(cliente);
                        System.out.println("Usuário adicionado!");
                        break;

                    case 2:
                    	clienteDAO.listarClientes()
                                .forEach(c -> System.out.println(
                                        c.getIdcliente() + " - " +
                                        c.getNome() + " - " +
                                        c.getCpf() + " - " +
                                        c.getEmail()));
                        break;

                    case 3:
                        System.out.print("Digite o ID do usuário a ser atualizado: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        
                        System.out.print("Novo Cpf: ");
                        String novoCpf = scanner.nextLine();

                        System.out.print("Novo Email: ");
                        String novoEmail = scanner.nextLine();

                        

                        Cliente clienteAtualizar = new Cliente();
                        clienteAtualizar.setIdcliente(idAtualizar);
                        clienteAtualizar.setNome(novoNome);
                        clienteAtualizar.setCpf(novoCpf);
                        clienteAtualizar.setEmail(novoEmail);
                        

                        clienteDAO.atualizarCliente(clienteAtualizar);
                        System.out.println("Usuário atualizado com sucesso!");
                        break;

                    case 4:
                        System.out.print("Digite o ID do usuário a ser deletado: ");
                        int idDeletar = scanner.nextInt();
                        clienteDAO.deletarCliente(idDeletar);
                        System.out.println("Usuário deletado com sucesso!");
                        break;

                    case 5:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SQLException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }
}
