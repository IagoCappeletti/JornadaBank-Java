import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Program {
    static Scanner sc = new Scanner(System.in);
    static int numUsers = 1;
    static int userId = 0;
    static String[][] usuario = new String[100][8];


    public static void main(String[] args) {

        usuario[0][0] = "nome";
        usuario[0][1] = "cpf";
        usuario[0][2] = "email";
        usuario[0][3] = "senha";
        usuario[0][4] = "conta";
        usuario[0][5] = "saldo";
        usuario[0][6] = "idConta";
        usuario[0][7] = "saldoCaixinha";


        while (true) {
            System.out.println("\n===== Jornada Bank =====");
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
        userId = 0;
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
        usuario[numUsers][4] = String.valueOf(new Random().nextInt(9999));
        usuario[numUsers][5] = "0";
        usuario[numUsers][6] = String.valueOf(numUsers);
        usuario[numUsers][7] = "0";
        numUsers++;

        System.out.println("\nUsuário cadastrado com sucesso!");
    }

    public static boolean validaLogin(String email, String senha) {
        for (int i = 1; i < numUsers; i++) {
            if (usuario[i][2].equals(email) && usuario[i][3].equals(senha)) {
                userId = i;
                System.out.println("Bem vindo(a) ao seu Jornada Bank!!");
                return true;
            }
        }
        if (userId == 0) {
            System.out.println("E-mail ou senha inválidos!!");
        }
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
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        System.out.println("\n===== Jornada Bank =====");
        System.out.println("Conta: " + usuario[userId][4] + "-" + usuario[userId][6] + "\nSaldo: R$ " + usuario[userId][5]
                + "\nReserva de Emergência: R$ " + usuario[userId][7] + "\n");
        System.out.println("#1 - Fazer um depósito");
        System.out.println("#2 - Fazer um saque");
        System.out.println("#3 - Fazer uma transferência");
        System.out.println("#4 - Fazer uma reserva de emergência");
        System.out.println("#5 - Alterar cadastro");
        System.out.println("#6 - Excluir usuário");
        System.out.println("#7 - Sair");
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
                reservaDeEmergencia();
                break;
            case 5:
                alterarCadastro();
                break;
            case 6:
                excluirUsuario();
                break;
            case 7:
                System.out.println("Retornando a página de login!!");
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void fazerDeposito() {
        Locale.setDefault(Locale.US);
        System.out.println("Saldo inicial: R$ " + String.format("%.1f", Double.parseDouble(usuario[userId][5])));
        System.out.print("Digite o valor do deposito: R$ ");
        double valorDeposito = sc.nextDouble();

        if (valorDeposito <= 0) {
            System.out.println("Valor inválido");
        } else {
            usuario[userId][5] = String.valueOf(Double.parseDouble(usuario[userId][5]) + valorDeposito);
            System.out.println("Depósitos realizado com sucesso.");
            System.out.println("Saldo atual: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][5])));
            System.out.println();
        }
        logado();
    }

    public static void fazerSaque() {
        Locale.setDefault(Locale.US);
        System.out.println("Saldo dispónivel em conta: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][5])));
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
            System.out.println("Novo saldo siponível: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][5])));
            System.out.println();
        }
        logado();
    }

    public static boolean confirmaTransferencia(int idUsuario) {
        Locale.setDefault(Locale.US);
        System.out.println("\n===== Jornada Bank =====");
        System.out.println("Nome: " + usuario[idUsuario][0] + "\nConta: " + usuario[idUsuario][4] + "-" + usuario[idUsuario][6]);
        System.out.println("#1 - Confirmar");
        System.out.println("#2 - Cancelar");
        System.out.println("==========================");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                System.out.println("Opção inválida!!");
                return false;
        }
    }

    public static void fazerTransferencia() {
        Locale.setDefault(Locale.US);
        System.out.println("Saldo dispónivel em conta: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][5])));
        System.out.print("Qual é o valor da transferência? ");
        double valorTransferencia = sc.nextDouble();
        System.out.print("Para qual conta você quer transferir? ");
        String conta = sc.next();
        System.out.println("Digite o dígito único: ");
        String id = sc.next();


        if (Double.parseDouble(usuario[userId][5]) <= 0) {
            System.out.println("Você não tem saldo suficiente para essa transferência. Saldo disponível: " + usuario[userId][5]);
        } else if (valorTransferencia > Double.parseDouble(usuario[userId][5])) {
            System.out.println("O valor selecionado é maior que o saldo disponível!");
        } else if (valorTransferencia <= 0) {
            System.out.println("Valor inválido, tente novamente!");
        } else {
            for (int i = 0; i < numUsers; i++) {
                if (usuario[i][4].equals(conta) && usuario[i][6].equals(id)) {
                    if (confirmaTransferencia(i)) {
                        double novoSaldo = Double.parseDouble(usuario[userId][5]) - valorTransferencia;
                        usuario[userId][5] = Double.toString(novoSaldo);
                        double novoSaldoDestino = Double.parseDouble(usuario[i][5]) + valorTransferencia;
                        usuario[i][5] = Double.toString(novoSaldoDestino);
                        System.out.println("Transferência realizada com sucesso!");
                    }
                } else {
                    if (i == numUsers - 1) {
                        System.out.println("Conta não encontrada, tente novamente!");
                    }
                }
            }
        }
        logado();
    }

    public static void reservaDeEmergencia(){
        Locale.setDefault(Locale.US);
        System.out.println("\n===== Jornada Bank =====");
        System.out.println("#1 - Depositar saldo de reservas de emergência");
        System.out.println("#2 - Sacar saldo de reservas de emergência");
        System.out.println("#3 - Excluir reservas de emergência");
        System.out.println("==========================");
        System.out.print("Qual opção você deseja acessar? ");
        int input = sc.nextInt();

        switch (input) {
            case 1:
                System.out.println("Saldo dispónivel em conta: R$ "+ String.format("%.2f",Double.parseDouble(usuario[userId][5])));
                System.out.print("Qual valor você quer colocar na sua reserva de emergência? R$ ");
                double valorCaixinha = sc.nextDouble();

                if (valorCaixinha <= 0) {
                    System.out.println("Valor inválido!\n");
                } else if (valorCaixinha > Double.parseDouble(usuario[userId][5])) {
                    System.out.println("O valor selecionado é maior que o saldo disponível!\n");
                } else {
                        usuario[userId][5] = Double.toString(Double.parseDouble(usuario[userId][5]) - valorCaixinha);
                        usuario[userId][7] = Double.toString(Double.parseDouble(usuario[userId][7]) + valorCaixinha);
                        System.out.println("Reserva de Emergência criada com sucesso!\n");
                }
                break;
            case 2:
                System.out.println("Saldo da reserva de Emergência: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][7])));
                System.out.print("Qual valor você quer sacar da sua reserva de emergência? R$ ");
                double valorDoSaqueCaixinha = sc.nextDouble();
                if (valorDoSaqueCaixinha <= 0){
                    System.out.println("Valor inválido!\n");
                } else if (valorDoSaqueCaixinha > Double.parseDouble(usuario[userId][7])) {
                    System.out.println("O valor selecionado é maior que o saldo disponível!\n");
                }else {
                        double updateSaldoCaixinha = Double.parseDouble(usuario[userId][7]) - valorDoSaqueCaixinha;
                        usuario[userId][7] = Double.toString(updateSaldoCaixinha);
                        double updateSaldoConta = Double.parseDouble(usuario[userId][5]) + valorDoSaqueCaixinha;
                        usuario[userId][5] = Double.toString(updateSaldoConta);
                        System.out.println("Saque da reserva de emergência efetuada com sucesso!\n");
                        System.out.println("Novo saldo em conta: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][5])));
                        System.out.println("Reserva de Emergência: R$ " + String.format("%.2f", Double.parseDouble(usuario[userId][7])));
                }
                break;
            case 3:
                System.out.println("\n===== Jornada Bank =====");
                System.out.println("Deseja excluir o a reserva de emergência? ");
                System.out.println("#1 - Sim");
                System.out.println("#2 - Não");
                System.out.println("==========================");
                System.out.print("Qual opção você deseja acessar? ");
                input = sc.nextInt();

                switch (input) {
                    case 1:
                        usuario[userId][7] = "";
                        System.out.println("Caixinha reservas de emergência deletada com sucesso!");
                        break;
                    case 2:
                        logado();
                        break;
                    default:
                        System.out.println("Opção inválida!!");
                }
        }
        logado();
    }

    public static void alterarCadastro() {
        Locale.setDefault(Locale.US);
        System.out.println("\n===== Jornada Bank =====");
        System.out.println("#1 - Alterar e-mail");
        System.out.println("#2 - Alterar senha");
        System.out.println("#3 - Cancelar");
        System.out.println("==========================");
        System.out.print("Qual opção você deseja acessar? ");
        int input = sc.nextInt();

        switch (input) {
            case 1:
                System.out.print("Digite seu novo e-mail: ");
                String email = sc.next();
                usuario[userId][2] = email;
                System.out.println("E-mail alterado com sucesso!");
                break;
            case 2:
                System.out.print("Digite sua nova senha: ");
                String senha = sc.next();
                usuario[userId][3] = senha;
                System.out.println("Senha alterada com sucesso!");
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida!!");
        }
        logado();
    }

    public static void excluirUsuario() {
        Locale.setDefault(Locale.US);
        System.out.println("\n===== Jornada Bank =====");
        System.out.println("Deseja mesmo excluir o usuário? ");
        System.out.println("#1 - Sim");
        System.out.println("#2 - Não");
        System.out.println("==========================");
        int input = sc.nextInt();

        switch (input) {
            case 1:
                usuario[userId][0] = "";
                usuario[userId][1] = "";
                usuario[userId][2] = "";
                usuario[userId][3] = "";
                usuario[userId][4] = "";
                usuario[userId][5] = "";
                usuario[userId][6] = "";
                System.out.println("Usuário deletado com sucesso!");
                break;
            case 2:
                logado();
                break;
            default:
                System.out.println("Opção inválida!!");
        }
    }
}