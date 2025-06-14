package data;

import domain.Usuario;
import domain.Cliente;
import domain.Administrador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioData {

    private static final String FILE_PATH = "usuarios.txt";
    private static final String SEP       = ";";

    // Carrega todos os usuários de usuarios.txt
    public static List<Usuario> listarTodos() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String linha = reader.readLine();

        while (linha != null) {
            // agora esperamos 5 campos: tipo;nome;senha;email;bloqueado
            String[] p        = linha.split(SEP, -1);
            String tipo       = p[0];           // "Cliente" ou "Administrador"
            String nome       = p[1];
            String senha      = p[2];
            String email      = p[3];
            boolean bloqueado = Boolean.parseBoolean(p[4]);

            Usuario u;
            if (tipo.equals("Cliente")) {
                u = new Cliente(nome, senha, email);
            } else {
                u = new Administrador(nome, senha, email);
            }

            u.setBloqueado(bloqueado);
            lista.add(u);

            linha = reader.readLine();
        }

        reader.close();
        return lista;
    }

    // Adiciona um usuário novo ao arquivo
    public static void adicionar(Usuario u) throws Exception {
        List<Usuario> lista = listarTodos();
        lista.add(u);
        salvarLista(lista);
    }

    // Remove usuário pelo nome
    public static void remover(String nome) throws Exception {
        List<Usuario> lista = listarTodos();
        int i = 0;
        while (i < lista.size()) {
            if (lista.get(i).getNome().equals(nome)) {
                lista.remove(i);
            } else {
                i = i + 1;
            }
        }
        salvarLista(lista);
    }

    // Bloqueia o usuário (setBloqueado = true)
    public static void bloquear(String nome) throws Exception {
        List<Usuario> lista = listarTodos();
        int i = 0;
        while (i < lista.size()) {
            Usuario u = lista.get(i);
            if (u.getNome().equals(nome)) {
                u.setBloqueado(true);
            }
            i = i + 1;
        }
        salvarLista(lista);
    }

    // Desbloqueia o usuário (setBloqueado = false)
    public static void desbloquear(String nome) throws Exception {
        List<Usuario> lista = listarTodos();
        int i = 0;
        while (i < lista.size()) {
            Usuario u = lista.get(i);
            if (u.getNome().equals(nome)) {
                u.setBloqueado(false);
            }
            i = i + 1;
        }
        salvarLista(lista);
    }

    // Sobrescreve o arquivo com a lista atualizada
    private static void salvarLista(List<Usuario> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        int i = 0;
        while (i < lista.size()) {
            Usuario u = lista.get(i);
            String tipo = (u instanceof Administrador) ? "Administrador" : "Cliente";

            // agora usamos getNome(), getSenha() e getEmail()
            String linha = tipo  + SEP
                    + u.getNome()  + SEP
                    + u.getSenha() + SEP
                    + u.getEmail() + SEP
                    + u.isBloqueado();

            writer.write(linha);
            writer.newLine();
            i = i + 1;
        }
        writer.close();
    }
}
