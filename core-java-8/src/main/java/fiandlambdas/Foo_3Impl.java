package fiandlambdas;

import java.util.function.Consumer;
import java.util.function.Function;

public class Foo_3Impl implements Foo_3 {

    @Override
    public  String addWithFunction(Function<String, String> f) {
        return f.apply("Something ");
    }

    @Override
    public void addWithConsumer(Consumer<Integer> f) {}

}
