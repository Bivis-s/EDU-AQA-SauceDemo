package utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductListTransformer<E, F> {
    abstract F transform(E e);

    public List<F> transform(List<E> list) {
        List<F> newList = new ArrayList<F>();
        for (E e : list) {
            newList.add(transform(e));
        }
        return newList;
    }
}
