package com.example;

import java.util.functions.Predicate;

/*
 * Option monad example in Java 8 from http://java.dzone.com/articles/no-more-excuses-use-null
 *
 * @author Mario Fusco
 */
public abstract class Option<A> {

    public static final None NONE = new None();

    public abstract <B> Option<B> map(Func1<A, B> f);

    public abstract <B> Option<B> flatMap(Func1<A, Option<B>> f);

    public abstract Option<A> filter(Predicate<? super A> predicate);

    public abstract A getOrElse(A def);

    public static <A> Some<A> some(A value) {
        return new Some(value);
    }

    public static <A> None<A> none() {
        return NONE;
    }

    public static <A> Option<A> asOption(A value) {
        if (value == null) return none(); else return some(value);
    }
}
