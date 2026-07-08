package org.example.data;

import java.io.*;

public abstract class Serializacao implements Serializable{

    public void serializar(Object object){
        ObjectOutputStream objectOutput;

        try {
                objectOutput = new ObjectOutputStream(new FileOutputStream("livraria.byte", true));
                objectOutput.writeObject(object);
                objectOutput.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    public Object deserializar(){
        ObjectInputStream objectInput;

        try{
           objectInput = new ObjectInputStream(new FileInputStream("livraria.byte"));
           //objectInput.close();
            return objectInput.readObject();
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
