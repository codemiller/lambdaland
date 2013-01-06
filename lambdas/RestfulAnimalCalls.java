package com.example.animal;

/**
 * Example based on real-world code from project.
 * Original class had 26 methods like the below for invoking REST calls.
 *
 * @author Katie Miller
 */
public final class RestfulAnimalCalls {

    private RestfulAnimalCalls() {
    }

    public interface AnimalCallback<T> {
        void begin();

        void generalException(final Exception ex);

        void success(final T returnValue);

        void failure();
    }

    public static void saveCat(final AnimalCallback<Cat> callback, final Cat cat) {
        try {
            callback.begin();
            Common.createRestClient(callback).updateCat(cat);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }

    public static void saveDog(final AnimalCallback<Dog> callback, final Dog dog) {
        try {
            callback.begin();
            Common.createRestClient(callback).updateDog(dog, Common.BARK);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }

    public static void getElephant(final AnimalCallback<Elephant> callback, final int id) {
        final String expand = "{\"branches\":[{\"trunk\":{\"type\": \"" + Common.TRUNK_TYPE + "\"}}]}";
        try {
            callback.begin();
            Common.createRestClient(callback).getElephant(id, expand);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }

    public static void saveElephant(final AnimalCallback<Elephant> callback, final Elephant elephant) {
        try {
            callback.begin();
            Common.createRestClient(callback).updateElephant(elephant, Common.TRUNK_SIZE);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }

    public static void saveGiraffe(final AnimalCallback<Giraffe> callback, final Giraffe giraffe) {
        try {
            callback.begin();
            Common.createRestClient(callback).updateGiraffe(giraffe);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }

    public static void getMonkey(final AnimalCallback<Monkey> callback, final int id) {
        final String expand = "{\"branches\":[" + Common.MONKEY_EXPANSION + "]}";
        try {
            callback.begin();
            Common.createRestClient(callback).getMonkey(id, expand);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }

    public static void getCattle(final AnimalCallback<Cow> callback) {
        try {
            callback.begin();
            Common.createRestClient(callback).getCattle(Common.CATTLE_BRAND);
        } catch (final Exception ex) {
            callback.generalException(ex);
        }
    }
}
