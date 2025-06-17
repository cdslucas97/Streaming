package domain;

import java.io.Serializable;

public class Serie  extends Conteudo implements Serializable {
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
        return "Serie";
    }

    @Override
    public String getDetalhes() {
        return String.format("Temporadas: %d", this.numeroTemporadas);
    }
}
