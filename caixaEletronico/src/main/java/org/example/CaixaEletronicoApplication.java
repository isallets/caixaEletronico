package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaixaEletronicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaixaEletronicoApplication.class, args);
        // TODO: Remover o código do Main antigo daqui
        // Todo o código de console (Scanner, System.out.println de cadastro/login)
        // deve ser REMOVIDO ou COMENTADO.
        // A lógica agora será acessada via HTTP pelos Controllers.
    }

}