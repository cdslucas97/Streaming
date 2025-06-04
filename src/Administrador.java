import java.util.ArrayList;

public class Administrador extends Usuario {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public Administrador(String nome, String senha, String email){
        super(nome,senha,email);
    }

    public void adicionarUsuario(Usuario user){
        if (user != null){
            usuarios.add(user);
        }
    }

    public String listarUsuarios(){
        if (usuarios.isEmpty()) {
            return "Não há usuarios cadastrados no sistema";
        }
        return "Usuarios: " + usuarios;
    }

    public String bloquearUsuario(Usuario user){
        if (user == null) {
            return "Usuario invalido.";
        }

        if (usuarios.contains(user)) {
            user.setBloqueado(true);
            return "Usuario " + user.getNome() + " foi bloqueado";
        } else {
         return "Usuario nao encontrado";
        }
    }

    public String desbloquearUsuario(Usuario user) {
        if (user == null) {
            return "Usuario invalido";
        }

        if (usuarios.contains(user)) {
            user.setBloqueado(false);
            return "Usuario " + user.getNome() + " foi desbloqueado";
        } else {
            return "Usuario nao foi encontrado";
        }
    }

    public String excluirUsuario(Usuario user){
        if (user == null) {
            return "Usuario invalido";
        }

        if (usuarios.contains(user)) {
            usuarios.remove(user);
            return "Usuario " + user.getNome() + " foi excluido com sucesso";
        } else {
            return "Usuario nao encontrado";
        }
    }
}
