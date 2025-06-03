import java.util.ArrayList;

public class Cliente extends Usuario{
    ArrayList<String> favoritos;                //Alterar objeto para Conteudo
    ArrayList<String> historicoReproducao;      //Alterar objeto para Conteudo
    public Cliente(String nome, String senha, String email){
        super(nome, senha, email);
        this.favoritos = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public String adicionarFavorito(String filme) {  //Alterar objeto para Conteudo
        favoritos.add(filme);
        return filme + "adicionado aos Favoritos!";
    }

    public String removerFavorito(String filme){           //Alterar objeto para Conteudo
        if (favoritos.contains(filme)) {
            favoritos.remove(filme);
            return filme + "removido dos Favoritos!";
        } else {
            return filme + "não está nos Favoritos!";
        }
    }

    public String listarFavorito(){
        return "Favoritos: " + favoritos;
    }

    public String registarReproducao(){
        return "REGISTRANDO...";
    }

    public void limparHistorico(){
        favoritos.clear();
    }


    //    public String obterRecomendacao(){
//        return "Implementando...";
//    }

}
