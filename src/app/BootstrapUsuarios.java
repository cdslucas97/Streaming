package app;

import data.Serializacao;
import domain.Administrador;
import domain.Usuario;
import java.util.ArrayList;
import java.util.List;

public class BootstrapUsuarios {
    public static void main(String[] args) throws Exception {
        List<Usuario> lista = new ArrayList<>();
        lista.add(new Administrador(
                "admin",            // nome
                "admin123",         // senha
                "admin@email.com" // e-mail
        ));
        Serializacao.salvar(lista, "usuarios.dat");
        System.out.println("Arquivo usuarios.dat criado com Admin padrão.");
    }
}
