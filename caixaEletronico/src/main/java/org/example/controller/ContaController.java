package org.example.controller;

import org.example.Usuario; // Importe Usuario
import org.example.contas.Conta; // Importe Conta
import org.example.dto.ContaResponse;
import org.example.proxy.ContaProxy; // Importe ContaProxy
import org.example.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final UsuarioService usuarioService;

    public ContaController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{nomeUsuario}/tipo")
    public ResponseEntity<?> getTipoConta(@PathVariable String nomeUsuario) {
        Optional<Conta> optionalConta = usuarioService.getContaDoUsuario(nomeUsuario); // CHAMA O NOVO MÉTODO

        if (optionalConta.isPresent()) {
            Conta contaReal = optionalConta.get();
            // PRECISAMOS DO USUARIO PARA O PROXY, ENTÃO OBTEMOS ELE TAMBÉM
            Optional<Usuario> optionalUsuario = usuarioService.autenticarUsuario(nomeUsuario, /* Senha não deve vir aqui. A autenticação deveria ser por token */ ""); // Simulação para o Proxy

            if (optionalUsuario.isPresent() && optionalUsuario.get().isAutenticado()) {
                Usuario usuarioAutenticado = optionalUsuario.get();
                ContaProxy proxy = new ContaProxy(contaReal, usuarioAutenticado); // Aplica o Proxy

                // A lógica do exibirTipoConta() imprime no console, precisamos adaptar para retornar string
                // Vamos criar uma forma de obter o tipo da conta para o DTO
                String tipoContaStr = null;
                if (contaReal instanceof org.example.contas.ContaCorrente) { // Qualificar com o pacote completo
                    tipoContaStr = "Conta Corrente";
                } else if (contaReal instanceof org.example.contas.ContaPoupanca) {
                    tipoContaStr = "Conta Poupança";
                } else if (contaReal instanceof org.example.contas.ContaEmpresarial) {
                    tipoContaStr = "Conta Empresarial";
                }

                // Se o proxy exibirTipoConta() for chamado, ele ainda imprime.
                // O Proxy aqui serve mais para demonstrar o padrão de controle de acesso.
                // Para um sistema real, essa verificação de acesso já teria ocorrido antes.
                // Por simplicidade, assumimos que se o getContaDoUsuario retornou, e o usuário está "autenticado"
                // (pela simulação), o acesso é permitido.
                if (tipoContaStr != null) {
                    return ResponseEntity.ok(
                            new ContaResponse(tipoContaStr, "Tipo de conta obtido com sucesso.", true)
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                            new ContaResponse(null, "Tipo de conta desconhecido.", false)
                    );
                }

            } else {
                // Se o usuário não estiver autenticado ou não encontrado (simulação para o proxy)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new ContaResponse(null, "Usuário não autenticado ou não encontrado.", false)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ContaResponse(null, "Conta não encontrada para o usuário especificado.", false)
            );
        }
    }
}