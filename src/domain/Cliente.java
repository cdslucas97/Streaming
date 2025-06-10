package domain;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private ArrayList<Conteudo> favoritos;
    private ArrayList<Conteudo> historicoReproducao;

    public Cliente(String nome, String senha, String email){
        super(nome, senha, email);
        this.favoritos = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public String adicionarFavorito(Conteudo conteudo) {
        if (conteudo == null){
            return "domain.Conteudo invalido.";
        }

        if (favoritos.contains(conteudo)) {
            return conteudo.getTitulo() + " ja esta nos favoritos";
        }

        favoritos.add(conteudo);
        return conteudo.getTitulo() + " adicionado aos Favoritos";
    }

    public String removerFavorito(Conteudo conteudo){           //Alterar objeto para domain.Conteudo
        if (conteudo == null) {
            return "domain.Conteudo invalido";
        }
        if (favoritos.contains(conteudo)) {
            favoritos.remove(conteudo);
            return conteudo.getTitulo() + " foi deletado com successo";
        } else {
            return conteudo.getTitulo() + " não foi encontrado";
        }
    }

    public String listarFavorito(){
        if (favoritos.isEmpty()){
            return "Você não possui conteudos favoritos";
        }
        return "Favoritos: " + favoritos;
    }

    public String registarReproducao(Conteudo conteudo){
        if (conteudo == null) {
            return "domain.Conteudo invalido";
        }

        historicoReproducao.add(conteudo);
        return conteudo.getTitulo() + " foi adicionado ao seu histórico";
    }

    public String listarHistorico(){
        if (historicoReproducao.isEmpty()) {
            return "Assista um filme de nosso incrivel catalogo";
        }

        return "Historico: " + historicoReproducao;
    }

    public String limparHistorico(){
        if (historicoReproducao.isEmpty()) {
            return "Seu historico de reproducao ja esta vazio";
        }
        favoritos.clear();
        return "Historico de reproducao limpo";
    }
    //    public String obterRecomendacao(){
    //        return "Implementando...";
    //    }

}
