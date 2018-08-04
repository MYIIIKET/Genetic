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
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteStream).writeObject(this);
            return (Class) new ObjectInputStream(new ByteArrayInputStream(byteStream.toByteArray())).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not clone");
        }
    }
}
