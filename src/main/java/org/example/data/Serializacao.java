package org.example.data;

import java.io.*;

// Serializacao.java
public class Serializacao implements Serializable {

    public void serializar(Object objeto, String caminhoArquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            out.writeObject(objeto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object deserializar(String caminhoArquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return in.readObject();
        } catch (FileNotFoundException e) {
            return null; // primeira execução, arquivo ainda não existe — não é erro
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}