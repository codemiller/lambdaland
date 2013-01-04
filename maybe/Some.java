/*
 * Option monad example in Java 8 from http://java.dzone.com/articles/no-more-excuses-use-null
 *
 * @author Mario Fusco
 */
public class Some<A> extends Option<A> {

    private final A value;

    Some(A value) {
        this.value = value;
    }

    @Override
    public <B> Option<B> map(Func1<A, B> f) {
        return some(f.apply(value));
    }

    @Override
    public <B> Option<B> flatMap(Func1<A, Option<B>> f) {
        return f.apply(value);
    }

    @Override
    public Option<A> filter(Predicate<? super A> predicate) {
        if (predicate.test(value)) return this;
        else return None.NONE;
    }

    @Override
    public A getOrElse(A def) {
        return value;
    }
}
