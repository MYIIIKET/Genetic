package engine.interfaces;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface DeepCloneable<Class> extends Serializable {
    default Class copy() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new ObjectOutputStream(baos).writeObject(this);
            return (Class) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not clone");
        }
    }
}
