package com.example.animal;

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import com.example.animal.Common;
import com.example.animal.Cat;
import com.example.animal.Dog;
import com.example.animal.Elephant;
import com.example.animal.Giraffe;
import com.example.animal.Cow;
import com.example.animal.Monkey;

/**
 * Refactored example based on real-world code from project.
 * Original class had 26 methods like the below for invoking REST calls.
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

    public static void saveCat(final AnimalCallback<Cat> callback, final Cat cat) {
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).updateCat(cat);
            }
        });
    }

    public static void saveDog(final AnimalCallback<Dog> callback, final Dog dog) {
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).updateDog(dog, Common.BARK);
            }
        });
    }

    public static void getElephant(final AnimalCallback<Elephant> callback, final int id) {
        final String expand = "{\"branches\":[{\"trunk\":{\"type\": \"" + Common.TRUNK_TYPE + "\"}}]}";
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).getElephant(id, expand);
            }
        });
    }

    public static void saveElephant(final AnimalCallback<Elephant> callback, final Elephant elephant) {
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).updateElephant(elephant, Common.TRUNK_SIZE);
            }
        });
    }

    public static void saveGiraffe(final AnimalCallback<Giraffe> callback, final Giraffe giraffe) {
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).updateGiraffe(giraffe);
            }
        });
    }

    public static void getMonkey(final AnimalCallback<Monkey> callback, final int id) {
        final String expand = "{\"branches\":[" + Common.MONKEY_EXPANSION + "]}";
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).getMonkey(id, expand);
            }
        });
    }

    public static void getCattle(final AnimalCallback<Cow> callback) {
        doRestCall(callback, new RestCaller() {
            @Override
            public void call() throws Exception {
                Common.createRestClient(callback).getCattle(Common.CATTLE_BRAND);
            }
        });
    }
}
