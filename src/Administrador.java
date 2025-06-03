import java.util.Scanner;

public class Administrador extends Usuario {

    Scanner sc = new Scanner(System.in);

    public Administrador(String nome, String senha, String email){
        super(nome,senha,email);
    }

    public String visualizarUsuarios(){
        return "VISUALIZANDO...";
    }

    public Usuario bloquearUsuario(Usuario user){
        // Adicionar lógica para realizar switch - isBloqueado = true
        return user;
    }

    public void excluirUsuario(Usuario user){
        System.out.println("");
    }
}
