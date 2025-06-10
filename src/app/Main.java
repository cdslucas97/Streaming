package app;
import domain.*;
import exception.LoginInvalidoException;

public class Main{
    public static void main(String[] args) {

        Cliente c1 = new Cliente("Carlos","123","carlos@email.com");
        Administrador adm1 = new Administrador("CarlosAdm", "123","adm@email,com");
        Serie serie1 = new Serie("O dia do chacal", "2024", "Thriller", 1);
        Filme filme1 = new Filme("Ex_Machina", "2014", "Sci-Fi", 104);
        Catalogo catalogo = new Catalogo();

        adm1.adicionarUsuario(c1);
        System.out.println(adm1.listarUsuarios());
        adm1.adicionarConteudoAoCatalogo(serie1,catalogo);
        adm1.removerConteudoDoCatalogo(filme1,catalogo);
        adm1.bloquearUsuario(c1);
        adm1.excluirUsuario(c1);

        c1.adicionarFavorito(serie1);
        c1.adicionarFavorito(filme1);
        System.out.println(c1.listarFavorito());
        c1.registarReproducao(filme1);
        System.out.println(c1.listarHistorico());

        try {
            System.out.println(c1.login("senhaerrada"));
        } catch (LoginInvalidoException e) {
            System.err.println("Falha no login: " + e.getMessage());
        }
    }
}