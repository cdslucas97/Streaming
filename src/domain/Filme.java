package domain;

public class Filme extends Conteudo {
    private int duracaoMinutos;

    public Filme(String titulo, String ano, String genero, int duracaoMinutos) {
        super(titulo, ano, genero);
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String getTipo() {
        return "Filme";
    }

    @Override
    public String getDetalhes() {
        return String.format("Duração: %d minutos", this.duracaoMinutos);
    }
}
