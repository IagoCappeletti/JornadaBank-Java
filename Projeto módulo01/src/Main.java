import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int numUsers = 1;

    static int userId = 0;
    static String[][] usuario = new String[100][6];


    public static void main(String[] args) {

        usuario[0][0] = "nome";
        usuario[0][1] = "cpf";
        usuario[0][2] = "email";
        usuario[0][3] = "senha";
        usuario[0][4] = "conta";
        usuario[0][5] = "saldo";

        while (true) {
            System.out.println("===== Jornada Bank =====");
            System.out.println("#1 - Entrar");
            System.out.println("#2 - Cadastrar usuário");
            System.out.println("#3 - Sair do aplicativo");
            System.out.println("==========================");
            System.out.println("Qual opção você deseja acessar? ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    //logar();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    System.out.println("Obrigado por acessar o aplicativo do Jornada Bank!");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }


    }


    public static void logar() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite seu e-mail: ");
        String email = input.next();
        System.out.println("Digite sua senha: ");
        String senha = input.next();

        validaLogin(email, senha);
    }

    public static void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite seu nome completo: ");
        String nome = sc.nextLine();
        System.out.println("Digite seu CPF: ");
        String cpf = sc.next();
        System.out.println("Digite seu e-mail: ");
        String email = sc.next();
        System.out.println("Digite uma senha: ");
        String senha = sc.next();

        if (numUsers > 1) {
            if (validaRegistro(cpf, email)) {
                return;
            }
        }

        usuario[numUsers][0] = nome;
        usuario[numUsers][1] = cpf;
        usuario[numUsers][2] = email;
        usuario[numUsers][3] = senha;
        numUsers++;

        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println("CONTA => " + usuario[numUsers][4]);
    }

    public static void validaLogin(String email, String senha) {
        for (int i = 1; i <= numUsers; i++) {
            if (usuario[i][2].equals(email) && usuario[i][3].equals(senha)) {
                System.out.println("Bem vindo!!");
                userId = i;

            } else {
                System.out.println("E-mail ou senha inválidos!!");
            }
        }
    }

    public static boolean validaRegistro(String cpf, String email) {
        for (int i = 1; i <= numUsers; i++) {
            if (usuario[i][1].equals(cpf) || usuario[i][2].equals(email)) {
                System.out.println("Usuário já cadastrado!");
                return true;
            }
        }
        return false;
    }


    public void logado() {
        Scanner input = new Scanner(System.in);
        System.out.println("===== Jornada Bank =====");
        System.out.println("#1 - Fazer um depósito");
        System.out.println("#2 - Fazer um saque");
        System.out.println("#3 - Fazer uma transferência");
        System.out.println("#4 - Sair");
        System.out.println("==========================");
        System.out.println("Qual opção você deseja acessar? ");
        int opcao = input.nextInt();

        switch (opcao) {
            case 1:
                fazerDeposito();
                break;
            case 2:
                fazerSaque();
                break;
            case 3:
                fazerTransferencia();
                break;
            case 4:
                System.out.println("Retornando a página de login!!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public void logadoSemConta() {
        Scanner input = new Scanner(System.in);
        System.out.println("===== Jornada Bank =====");
        System.out.println("#1 - Criar uma conta");
        System.out.println("#2 - Voltar");
        System.out.println("==========================");
        System.out.println("Qual opção você deseja acessar? ");
        int opcao = input.nextInt();

        switch (opcao) {
            case 1:
                //criarConta();
                break;
            case 2:
                System.out.println("Retornando a página de login!!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void criarConta() {
    }

    public static void fazerDeposito() {
    }

    public static void fazerSaque() {
    }

    public static void fazerTransferencia() {
    }
}
