package data;

import java.io.*;
import java.util.List;

public class Serializacao {
    public static <T extends Serializable> void salvar(List<T> lista, String caminho)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
        }
    }

    public static <T> List<T> carregar(String caminho)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject();
        }
    }
}
