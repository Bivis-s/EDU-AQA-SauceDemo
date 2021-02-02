package utilities;

@FunctionalInterface
public interface TripleExpression<T, F, R> {
    R invoke(T p1, F p2);
}
