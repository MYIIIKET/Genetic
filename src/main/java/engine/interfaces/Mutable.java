package engine.interfaces;

@FunctionalInterface
public interface Mutable<Class, Argument> {
    Class mutate(Argument argument);
}
