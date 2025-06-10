package domain;

public class Serie extends Conteudo {
    private int numeroTemporadas;

    public Serie(String titulo, String ano, String genero, int numeroTemporadas) {
        super(titulo, ano, genero);
        this.numeroTemporadas = numeroTemporadas;
    }

    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    @Override
    public String getTipo() {
        return "Série";
    }

    @Override
    public String getDetalhes() {
        return String.format("Temporadas: %d", this.numeroTemporadas);
    }
}
