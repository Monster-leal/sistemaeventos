package sistemaeventos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SistemaEventos {
    private List<Evento> eventos;
    private List<Usuario> usuarios;
    private Scanner scanner;

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarEvento() {
        System.out.println("\nCadastro de evento:");
        Evento evento = new Evento(null, null, null, null, null);

        System.out.print("Nome: ");
        evento.setNome(scanner.nextLine());

        System.out.print("Endereço: ");
        evento.setEndereco(scanner.nextLine());

        System.out.print("Categoria (festas, eventos esportivos, shows, missa em igreja): ");
        evento.setCategoria(scanner.nextLine());

        LocalDateTime horario = null;
        boolean dataValida = false;
        do {
            System.out.print("Horário (formato DD-MM-YYYY HH:MM, por exemplo, 01-03-2024 18:00): ");
            String horarioStr = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                horario = LocalDateTime.parse(horarioStr, formatter);
                if (horario.toLocalDate().isAfter(LocalDate.now())) {
                    dataValida = true;
                } else {
                    System.out.println("Data anterior à data atual. Tente novamente.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data/horário inválido. Tente novamente.");
            }

        } while (!dataValida);
        evento.setHorario(horario);

        System.out.print("Descrição: ");
        evento.setDescricao(scanner.nextLine());

        eventos.add(evento);

        System.out.println("Evento cadastrado com sucesso!");
    }

    public void cadastrarUsuario() {
        System.out.println("\nCadastro de usuário:");
        Usuario usuario = new Usuario();

        System.out.print("Nome completo: ");
        usuario.setNomeCompleto(scanner.nextLine());

        System.out.print("Idade: ");
        usuario.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.print("CPF: ");
        usuario.setCpf(scanner.nextLine());

        System.out.print("E-mail: ");
        usuario.setEmail(scanner.nextLine());

        System.out.print("Bairro: ");
        usuario.setBairro(scanner.nextLine());

        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void mostrarEventos() {
        eventos.sort(Comparator.comparing(Evento::getHorario));
        System.out.println("\nLista de eventos cadastrados:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (Evento evento : eventos) {
            System.out.println("Nome: " + evento.getNome());
            System.out.println("Endereço: " + evento.getEndereco());
            System.out.println("Categoria: " + evento.getCategoria());
            System.out.println("Horário: " + evento.getHorario().format(formatter));
            System.out.println("Descrição: " + evento.getDescricao());
            System.out.println();
        }
    }

    public void mostrarUsuarios() {
        System.out.println("\nLista de usuários cadastrados:");
        for (Usuario usuario : usuarios) {
            System.out.println("Nome completo: " + usuario.getNomeCompleto());
            System.out.println("Idade: " + usuario.getIdade());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("E-mail: " + usuario.getEmail());
            System.out.println("Bairro: " + usuario.getBairro());
            System.out.println();
        }
    }

    public void participarEvento() {
        try {
            if (usuarios.isEmpty() || eventos.isEmpty()) {
                System.out.println("Não há usuários cadastrados ou eventos disponíveis.");
                return;
            }

            System.out.print("Digite o primeiro nome do usuário que deseja participar do evento: ");
            String nomeUsuario = scanner.nextLine();

            Usuario usuario = null;
            for (Usuario u : usuarios) {
                if (u.getNomeCompleto().toLowerCase().startsWith(nomeUsuario.toLowerCase())) {
                    usuario = u;
                    break;
                }
            }

            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            System.out.print("Digite o nome do evento que deseja participar: ");
            String nomeEvento = scanner.nextLine();

            Evento evento = null;
            for (Evento e : eventos) {
                if (e.getNome().equalsIgnoreCase(nomeEvento)) {
                    evento = e;
                    break;
                }
            }

            if (evento == null) {
                System.out.println("Evento não encontrado.");
                return;
            }

            evento.adicionarParticipante(usuario.getNomeCompleto());

            System.out.println("Usuário " + usuario.getNomeCompleto() + " participou do evento " + evento.getNome() + " com sucesso!");
        } catch (NoSuchElementException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SistemaEventos sistema = new SistemaEventos();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar evento");
            System.out.println("2. Mostrar eventos cadastrados");
            System.out.println("3. Cadastrar usuário");
            System.out.println("4. Mostrar usuários cadastrados");
            System.out.println("5. Participar de um evento");
            System.out.println("6. Cancelar participação em um evento");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = sistema.scanner.nextInt();
            sistema.scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    sistema.cadastrarEvento();
                    break;

                case 2:
                    sistema.mostrarEventos();
                    break;

                case 3:
                    sistema.cadastrarUsuario();
                    break;

                case 4:
                    sistema.mostrarUsuarios();
                    break;

                case 5:
                    sistema.participarEvento();
                    break;

                case 6:
                    break;

                case 7:
                    System.out.println("Saindo do sistema...");
                    sistema.scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}