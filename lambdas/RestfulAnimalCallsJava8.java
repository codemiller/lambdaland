package com.example.animal;

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import com.example.animal.Common;
import com.example.animal.Cat;
import com.example.animal.Dog;
import com.example.animal.Elephant;
import com.example.animal.Giraffe;
import com.example.animal.Cow;
import com.example.animal.Monkey;

import java.lang.CharSequence;

/**
 * Example showing what code may look like using lambda expressions in Java 8.
 *
 * @author Katie Miller
 */
public final class RestfulAnimalCallsRefactored {

    private RestfulAnimalCallsRefactored() {
    }

    public interface AnimalCallback<T> {
        void begin();

        void generalException(final Exception ex);

        void success(final T returnValue);

        void failure();
    }

    public interface RestCaller {
        public void call() throws Exception;
    }

    private static <T> void doRestCall(final AnimalCallback<T> callback, final RestCaller restCaller) {
        try {
            callback.begin();
            restCaller.call();
        } catch (final Exception e) {
            callback.generalException(e);
        }
    }

    public static void saveCat(AnimalCallback<Cat> callback, Cat cat) {
        doRestCall(callback, () -> Common.createRestClient(callback).updateCat(cat));
    }

    public static void saveDog(AnimalCallback<Dog> callback, Dog dog) {
        doRestCall(callback, () -> Common.createRestClient(callback).updateDog(dog, Common.BARK));
    }

    public static void getElephant(AnimalCallback<Elephant> callback, int id) {
        String expand = "{\"branches\":[{\"trunk\":{\"type\": \"" + Common.TRUNK_TYPE + "\"}}]}";
        doRestCall(callback, () -> Common.createRestClient(callback).getElephant(id, expand));
    }

    public static void saveElephant(AnimalCallback<Elephant> callback, Elephant elephant) {
        doRestCall(callback, () -> Common.createRestClient(callback).updateElephant(elephant, Common.TRUNK_SIZE));
    }

    public static void saveGiraffe(AnimalCallback<Giraffe> callback, Giraffe giraffe) {
        doRestCall(callback, () -> Common.createRestClient(callback).updateGiraffe(giraffe));
    }

    public static void getMonkey(AnimalCallback<Monkey> callback, int id) {
        String expand = "{\"branches\":[" + Common.MONKEY_EXPANSION + "]}";
        doRestCall(callback, () -> Common.createRestClient(callback).getMonkey(id, expand));
    }

    public static void getCattle(AnimalCallback<Cow> callback) {
        doRestCall(callback, () -> Common.createRestClient(callback).getCattle(Common.CATTLE_BRAND));
    }
}
