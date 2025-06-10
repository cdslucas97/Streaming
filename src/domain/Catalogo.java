package domain;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Conteudo> conteudos;

    public Catalogo(){
        this.conteudos = conteudos;
    }

    public void adicionarConteudo(Conteudo conteudo){
        conteudos.add(conteudo);
    }

    public void removerConteudo(Conteudo conteudo){
        conteudos.remove(conteudo);
    }

    public ArrayList<Conteudo> listarTodos(){
        return conteudos;
    }
}
