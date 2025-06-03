import java.util.ArrayList;

public class Cliente extends Usuario{
    ArrayList<String> favoritos;                //Alterar objeto para Conteudo
    ArrayList<String> historicoReproducao;      //Alterar objeto para Conteudo
    public Cliente(String nome, String senha, String email, ArrayList favoritos, ArrayList historicoReproducao){
        super(nome, senha, email);
        this.favoritos = favoritos;
        this.historicoReproducao = historicoReproducao;
    }

    public String adicionarFavorito(){          //Alterar objeto para Conteudo
        return "Adicionado aos Favoritos!";
    }

    public String removerFavorito(){           //Alterar objeto para Conteudo
        return "Removido dos Favoritos!";
    }

    public String listarFavorito(){
        return "Exibindo os Favoritos!";
    }

    public String registarReproducao(){
        return "REGISTRANDO...";
    }

    public String limparHistorico(){
        return "LIMPANDO...";
    }

//    public String obterRecomendacao(){
//        return "Implementando...";
//    }

}
