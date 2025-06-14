package org.example;

import org.example.contasfactory.ContaCorrenteFactory;
import org.example.contasfactory.ContaEmpresarialFactory;
import org.example.contasfactory.ContaFactory;
import org.example.contasfactory.ContaPoupancaFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();

        System.out.println("##### Cadastro de novo usuário #####");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.println("##### Tipo de conta (1-Corrente, 2-Poupança, 3-Empresarial): #####");
        int tipo = scanner.nextInt();

        ContaFactory factory;
        switch (tipo) {
            case 1:
                factory = new ContaCorrenteFactory();
                break;
            case 2:
                factory = new ContaPoupancaFactory();
                break;
            case 3:
                factory = new ContaEmpresarialFactory();
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }

        Usuario novoUsuario = new Usuario(nome, senha, factory);
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso.");

        System.out.println("\n##### Login #####");
        scanner.nextLine();
        System.out.print("Nome: ");
        String loginNome = scanner.nextLine();
        System.out.print("Senha: ");
        String loginSenha = scanner.nextLine();

        boolean autenticado = false;
        for (Usuario user : usuarios) {
            if (user.autenticar(loginNome, loginSenha)) {
                System.out.println("Login bem-sucedido!");
                user.exibirConta();
                autenticado = true;
                break;
            }
        }

        if (!autenticado) {
            System.out.println("Falha no login.");
        }

        scanner.close();
    }
}
