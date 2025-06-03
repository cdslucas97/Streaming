import java.util.ArrayList;

public class Cliente extends Usuario{
    private ArrayList<Conteudo> favoritos;                //Alterar objeto para Conteudo
    private ArrayList<Conteudo> historicoReproducao;      //Alterar objeto para Conteudo
    private boolean isBloqueado;

    public Cliente(String nome, String senha, String email){
        super(nome, senha, email);
        this.favoritos = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
        boolean isBloqueado;
    }

    public String adicionarFavorito(Conteudo filme) {  //Alterar objeto para Conteudo
        favoritos.add(filme);
        return filme + "adicionado aos Favoritos!";
    }

    public String removerFavorito(Conteudo filme){           //Alterar objeto para Conteudo
        if (favoritos.contains(filme)) {
            favoritos.remove(filme);
            return filme + " foi deletado com successo";
        } else {
            return filme + " não foi encontrado";
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
