/*
 * Option monad example in Java 8 from http://java.dzone.com/articles/no-more-excuses-use-null
 *
 * @author Mario Fusco
 */
public class None<A> extends Option<A> {

    None() {
    }

    @Override
    public <B> Option<B> map(Func1<A, B> f) {
        return NONE;
    }

    @Override
    public <B> Option<B> flatMap(Func1<A, Option<B>> f) {
        return NONE;
    }

    @Override
    public Option<A> filter(Predicate<? super A> predicate) {
        return NONE;
    }

    @Override
    public A getOrElse(A def) {
        return def;
    }
}

