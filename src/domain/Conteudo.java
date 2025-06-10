package domain;

public abstract class Conteudo {
    private String titulo;
    private String ano;
    private String genero;

    public Conteudo(String titulo, String ano, String genero) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public abstract String getTipo();

    public abstract String getDetalhes();

    @Override
    public String toString() {
        return String.format(
                "%s (%s) - Gênero: %s - Tipo: %s - Detalhes: %s",
                this.titulo,
                this.ano,
                this.genero,
                this.getTipo(),
                this.getDetalhes()
        );
    }
}
