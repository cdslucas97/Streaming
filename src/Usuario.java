public abstract class Usuario {
    private String nome;
    private String senha;
    private String email;
    private boolean isBloqueado;

    public Usuario(String nome, String senha, String email){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.isBloqueado = false;
    }

    public String login(String senhaInformada){
        if(this.senha.equals(senhaInformada)){
            if(this.isBloqueado){
                return "Usuario bloqueado, entre em contato com o suporte";
            }
            return "Logado com sucesso";
        } else{
            return "Usuario ou senha invalido.";
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBloqueado() {
        return isBloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.isBloqueado = bloqueado;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\nSenha: " + getSenha() + "\nEmail: " + getEmail() + "Bloqueado: " + isBloqueado();
    }
}
