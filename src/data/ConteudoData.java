package data;

import domain.Conteudo;
import domain.Filme;
import domain.Serie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConteudoData {

    private static final String FILE_PATH = "conteudos.txt";
    private static final String SEP = ";";

    // Carrega todos os conteúdos do arquivo em uma lista
    public static List<Conteudo> listarTodos() throws Exception {
        List<Conteudo> lista = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String linha = reader.readLine();
        while (linha != null) {
            String[] p = linha.split(SEP);
            String tipo   = p[0];
            String titulo = p[1];
            String ano    = p[2];
            String genero = p[3];
            String valor  = p[4];

            if (tipo.equals("Filme")) {
                lista.add(new Filme(titulo, ano, genero, Integer.parseInt(valor)));
            } else if (tipo.equals("Serie")) {
                lista.add(new Serie(titulo, ano, genero, Integer.parseInt(valor)));
            }
            linha = reader.readLine();
        }
        reader.close();
        return lista;
    }

    // Adiciona um novo conteúdo ao arquivo
    public static void adicionar(Conteudo c) throws Exception {
        List<Conteudo> lista = listarTodos();
        lista.add(c);
        salvarLista(lista);
    }

    // Remove um conteúdo (por título) do arquivo
    public static void remover(String titulo) throws Exception {
        List<Conteudo> lista = listarTodos();
        int i = 0;
        while (i < lista.size()) {
            if (lista.get(i).getTitulo().equals(titulo)) {
                lista.remove(i);
            } else {
                i = i + 1;
            }
        }
        salvarLista(lista);
    }

    // Sobrescreve o arquivo com todos os conteúdos da lista
    private static void salvarLista(List<Conteudo> lista) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        int i = 0;
        while (i < lista.size()) {
            Conteudo c = lista.get(i);
            String linha;
            if (c.getTipo().equals("Filme")) {
                Filme f = (Filme) c;
                linha = "Filme" + SEP
                        + f.getTitulo() + SEP
                        + f.getAno()    + SEP
                        + f.getGenero() + SEP
                        + f.getDuracaoMinutos();
            } else {
                Serie s = (Serie) c;
                linha = "Serie" + SEP
                        + s.getTitulo() + SEP
                        + s.getAno()    + SEP
                        + s.getGenero() + SEP
                        + s.getNumeroTemporadas();
            }
            writer.write(linha);
            writer.newLine();
            i = i + 1;
        }
        writer.close();
    }
}
