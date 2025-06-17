package data;

import domain.Conteudo;
import java.util.ArrayList;
import java.util.List;

public class ConteudoData {

    private static final String FILE_PATH = "conteudos.dat";

    public static List<Conteudo> listarTodos() throws Exception {
        // Se o arquivo não existir, devolve lista vazia
        java.io.File f = new java.io.File(FILE_PATH);
        if (!f.exists()) return new ArrayList<>();
        return Serializacao.carregar(FILE_PATH);
    }

    public static void adicionar(Conteudo c) throws Exception {
        List<Conteudo> lista = listarTodos();
        lista.add(c);
        Serializacao.salvar(lista, FILE_PATH);
    }

    public static void remover(String titulo) throws Exception {
        List<Conteudo> lista = listarTodos();
        int i = 0;
        while (i < lista.size()) {
            if (lista.get(i).getTitulo().equals(titulo)) {
                lista.remove(i);
            } else {
                i++;
            }
        }
        Serializacao.salvar(lista, FILE_PATH);
    }

    public static void atualizar(Conteudo atualizado) throws Exception {
        List<Conteudo> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTitulo().equals(atualizado.getTitulo())) {
                lista.set(i, atualizado);
                break;
            }
        }
        Serializacao.salvar(lista, FILE_PATH);
    }
}
