package domain;

import data.ConteudoData;
import data.UsuarioData;
import java.util.List;

public class Administrador extends Usuario {

    public Administrador(String nome, String senha, String email) {
        super(nome, senha, email);
    }

    public List<Usuario> listarTodosOsUsuarios() throws Exception {
        return UsuarioData.listarTodos();
    }

    public String bloquearUsuario(Usuario usuarioParaBloquear) throws Exception {
        UsuarioData.bloquear(usuarioParaBloquear.getNome());
        return "Usuário " + usuarioParaBloquear.getNome() + " foi bloqueado.";
    }

    public String desbloquearUsuario(Usuario usuarioParaDesbloquear) throws Exception {
        UsuarioData.desbloquear(usuarioParaDesbloquear.getNome());
        return "Usuário " + usuarioParaDesbloquear.getNome() + " foi desbloqueado.";
    }

    public String excluirUsuario(Usuario usuarioParaExcluir) throws Exception {
        UsuarioData.remover(usuarioParaExcluir.getNome());
        return "Usuário " + usuarioParaExcluir.getNome() + " foi excluído com sucesso.";
    }

    public List<Conteudo> listarTodoOConteudo() throws Exception {
        return ConteudoData.listarTodos();
    }

    public String adicionarConteudo(Conteudo conteudo) throws Exception {
        ConteudoData.adicionar(conteudo);
        return "Conteúdo '" + conteudo.getTitulo() + "' adicionado ao catálogo.";
    }
}