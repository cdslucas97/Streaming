public class Administrador extends Usuario {
    public Administrador(String nome, String senha, String email){
        super(nome,senha,email);
    }

    public String adicionarConteudo(){
        return "Conteudo adicionado";
    }

    public String removerConteudo(){
        return "Conteudo removido";
    }

    public String visualizarUsuarios(){
        return "VISUALIZANDO...";
    }

    public String bloquearUsuario(){
        return "Usuario bloqueado";
    }

    public String excluirUsuario(){
        return "Usuario excluido com sucesso";
    }
}
