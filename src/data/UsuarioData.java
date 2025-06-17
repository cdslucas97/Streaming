package data;

import domain.Usuario;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsuarioData {
    private static final String FILE_PATH = "usuarios.dat";

    public static List<Usuario> listarTodos() throws Exception {
        File f = new File(FILE_PATH);
        if (!f.exists()) return new ArrayList<>();
        return Serializacao.carregar(FILE_PATH);
    }

    public static void adicionar(Usuario u) throws Exception {
        List<Usuario> lista = listarTodos();
        lista.add(u);
        Serializacao.salvar(lista, FILE_PATH);
    }

    public static void remover(String nome) throws Exception {
        List<Usuario> lista = listarTodos();
        int i = 0;
        while (i < lista.size()) {
            if (lista.get(i).getNome().equals(nome)) {
                lista.remove(i);
            } else {
                i++;
            }
        }
        Serializacao.salvar(lista, FILE_PATH);
    }

    public static void bloquear(String nome) throws Exception {
        List<Usuario> lista = listarTodos();
        for (Usuario u : lista) {
            if (u.getNome().equals(nome)) u.setBloqueado(true);
        }
        Serializacao.salvar(lista, FILE_PATH);
    }

    public static void desbloquear(String nome) throws Exception {
        List<Usuario> lista = listarTodos();
        for (Usuario u : lista) {
            if (u.getNome().equals(nome)) u.setBloqueado(false);
        }
        Serializacao.salvar(lista, FILE_PATH);
    }

    public static void atualizar(Usuario atualizado) throws Exception {
        List<Usuario> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equals(atualizado.getNome())) {
                lista.set(i, atualizado);
                break;
            }
        }
        Serializacao.salvar(lista, FILE_PATH);
    }
}
