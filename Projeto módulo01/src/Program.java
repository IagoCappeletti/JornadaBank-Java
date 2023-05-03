import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Program {
    static Scanner sc = new Scanner(System.in);
    static int numUsers = 1;
    static int userId = 0;
    static String[][] usuario = new String[100][7];


    public static void main(String[] args) {

        usuario[0][0] = "nome";
        usuario[0][1] = "cpf";
        usuario[0][2] = "email";
        usuario[0][3] = "senha";
        usuario[0][4] = "conta";
        usuario[0][5] = "saldo";
        usuario[0][6] = "idConta";


        while (true) {
            System.out.println("===== Jornada Bank =====");
            System.out.println("#1 - Entrar");
            System.out.println("#2 - Cadastrar usuário");
            System.out.println("#3 - Sair do aplicativo");
            System.out.println("==========================");
            System.out.print("Qual opção você deseja acessar? ");
            int opcao = sc.nextInt();
            System.out.println();

            switch (opcao) {
                case 1:
                    logar();
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

        System.out.print("Digite seu e-mail: ");
        String email = input.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();
        System.out.println();

        if (numUsers > 1) {
            if (validaLogin(email, senha)) {
               logado();
            }
        } else {
            System.out.println("Usuário inválido!");
        }
    }

    public static void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite seu nome completo: ");
        String nome = sc.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = sc.next();
        System.out.print("Digite seu e-mail: ");
        String email = sc.next();
        System.out.print("Digite uma senha: ");
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
        usuario[numUsers][4] = String.valueOf(new Random(0).nextInt(9999));
        usuario[numUsers][5] = "0";
        usuario[numUsers][6] = String.valueOf(numUsers);
        numUsers++;

        System.out.println("Usuário cadastrado com sucesso!\n");
    }

    public static boolean validaLogin(String email, String senha) {
        for (int i = 1; i < numUsers; i++) {
            if (usuario[i][2].equals(email) && usuario[i][3].equals(senha)) {
                userId = i;
                return true;
            }
        }
        System.out.println(userId > 0 ? "Bem vindo!!" : "E-mail ou senha inválidos!!");
        return false;
    }

    public static boolean validaRegistro(String cpf, String email) {
        for (int i = 1; i < numUsers; i++) {
            if (usuario[i][1].equals(cpf) || usuario[i][2].equals(email)) {
                System.out.println("Usuário já cadastrado!");
                return true;
            }
        }
        return false;
    }


    public static void logado() {
        Scanner input = new Scanner(System.in);
        System.out.println("===== Jornada Bank =====");
        System.out.println("#1 - Fazer um depósito");
        System.out.println("#2 - Fazer um saque");
        System.out.println("#3 - Fazer uma transferência");
        System.out.println("#4 - Sair");
        System.out.println("==========================");
        System.out.print("Qual opção você deseja acessar? ");
        int opcao = input.nextInt();
        System.out.println();

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
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void fazerDeposito() {
        Locale.setDefault(Locale.US);
        System.out.println("Saldo inicial: R$ " + String.format("%.1f",Double.parseDouble(usuario[userId][5])));
        System.out.print("Digite o valor do deposito: R$ ");
        double valorDeposito = sc.nextDouble();

        if(valorDeposito <= 0){
            System.out.println("Valor inválido");
        }
        else {
            usuario[userId][5] = String.valueOf(Double.parseDouble(usuario[userId][5]) + valorDeposito);
            System.out.println("Depósitos realizado com sucesso.");
            System.out.println("Saldo atual: R$ " + String.format("%.2f",Double.parseDouble(usuario[userId][5])));
            System.out.println();
        }
        logado();
    }

    public static void fazerSaque() {
        Locale.setDefault(Locale.US);
        System.out.println("Saldo dispónivel em conta: R$ "+ String.format("%.2f",Double.parseDouble(usuario[userId][5])));
        System.out.print("Quanto você quer sacar? R$ ");
        double valorSaque = sc.nextDouble();

        if (valorSaque <= 0) {
            System.out.println("Valor inválido!\n");
        } else if (valorSaque > Double.parseDouble(usuario[userId][5])) {
            System.out.println("O valor selecionado é maior que o saldo disponível!\n");
        } else {
            double novoSaldo = Double.parseDouble(usuario[userId][5]) - valorSaque;
            usuario[userId][5] = Double.toString(novoSaldo);
            System.out.println("Saque realizado com sucesso.");
            System.out.println("Novo saldo siponível: R$ " + String.format("%.2f",Double.parseDouble(usuario[userId][5])));
            System.out.println();
        }
        logado();
    }

    public static void fazerTransferencia() {
        Locale.setDefault(Locale.US);
        System.out.println("Saldo dispónivel em conta: R$ "+ String.format("%.2f",Double.parseDouble(usuario[userId][5])));
        System.out.print("Qual é o valor da transferência? ");
        double valorTransferencia = sc.nextDouble();
        System.out.print("Para qual Id você quer transferir?");
        String id = sc.next();
        System.out.println(userId);
        System.out.println(id);

        if (valorTransferencia <= 0){
            System.out.println("Você não tem saldo suficiente para essa transferência. Saldo disponível: " + usuario[userId][5]);
        } else if (valorTransferencia > Double.parseDouble(usuario[userId][5])) {
            System.out.println("O valor selecionado é maior que o saldo disponível!");
        } else {
            double novoSaldo = Double.parseDouble(usuario[userId][5]) - valorTransferencia;
            usuario[userId][5] = Double.toString(novoSaldo);
            for (int i = 0; i < numUsers; i++) {
                if (usuario[i][6].equals(id)){
                    double novoSaldoDestino = Double.parseDouble(usuario[i][5]) + valorTransferencia;
                    usuario[i][5] = Double.toString(novoSaldoDestino);
                    userId = i;

                }
            }
        }
    }
}
