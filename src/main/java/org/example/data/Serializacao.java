package org.example.data;

import java.io.*;

public abstract class Serializacao implements Serializable{

    public void serializar(Object object){
        ObjectOutputStream objectOutput;

        try {
                objectOutput = new ObjectOutputStream(new FileOutputStream("livraria.byte"));
                objectOutput.writeObject(object);
                objectOutput.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    public void deserializar(Object object){
        ObjectInputStream objectInput;

        try{
           objectInput = new ObjectInputStream(new FileInputStream("livraria.byte"));
           objectInput.readObject();
           objectInput.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
