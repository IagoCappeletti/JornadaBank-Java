import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int numUsers = 0;
    String[][] usuario = new String[100][];
    public static void main(String[] args) {

        while (true) {
            System.out.println("===== Jornada Bank =====");
            System.out.println("#1 - Criar uma conta");
            System.out.println("#2 - Criar uma conta");
            System.out.println("#3 - Fazer um depósito");
            System.out.println("#4 - Fazer um saque");
            System.out.println("#5 - Fazer uma transferencia");
            System.out.println("#6 - Sair do aplicativo");
            System.out.println("==========================");
            System.out.println("Qual opção você deseja acessar? ");
            int opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    //criarConta;
                break;
                case 3:
                    //fazerDeposito;
                    break;
                case 4:
                    //fazerSaque;
                    break;
                case 5:
                    //fazerTransferencia;
                    break;
                case 6:
                    System.out.println("Obrigado por acessar o aplicativo do Jornada Bank!");
                    return;
                default:
                    System.out.println("Escolha inválida! Tente novamente.");
                    break;
            }
        }


    }
    public static void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual seu nome completo? ");
        sc.nextLine();
        String nome = sc.nextLine();
        System.out.println("Qual seu CPF? ");
        String cpf = sc.next();
        System.out.println("Qual seu email ?");
        String email = sc.next();
        System.out.println("Digite uma senha: ");
        String senha = sc.next();

            for (int i = 0; i < numUsers; i++) {
            if (usuario[i][1].equals(cpf)) {
                System.out.println("Usuário já cadastrado!");
                return;
            }
        }

        usuario[numUsers][0] = nome;
        usuario[numUsers][1] = cpf;
        usuario[numUsers][2] = email;
        usuario[numUsers][3] = senha;
        numUsers++;

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void criarConta() {
    }
    public static void fazerDeposito() {
    }
    public static void fazerSaque() {
    }
    public static void fazerTransferencia(){
    }
}