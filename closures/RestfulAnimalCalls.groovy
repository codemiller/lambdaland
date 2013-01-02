package com.example.animal

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient
import com.example.animal.Common
import com.example.animal.Cat
import com.example.animal.Dog
import com.example.animal.Elephant
import com.example.animal.Giraffe
import com.example.animal.Cow
import com.example.animal.Monkey

/**
 * Example based on real-world code from project.
 * Original class had 26 methods like the below for invoking REST calls.
 * TODO finish this class 
 *
 * @author Katie Miller
 */

class RestfulAnimalCalls {

    interface AnimalCallback {
        void begin()

        void generalException(ex)

        void success(returnValue)

        void failure()
    }

    def static doRestCall(callback, restClosure) {
        try {
            callback.begin()
            restClosure()
        } catch (final Exception e) {
            callback.generalException(e)
        }
    }

    def static saveCat(callback, cat) {
        doRestCall(callback) {
            Common.createRestClient(callback).updateCat(cat)
        }
    }

    def static saveDog(callback, dog) {
        doRestCall(callback) {
            Common.createRestClient(callback).updateDog(dog, Common.BARK)
        }
    }

    def static getElephant(callback, id) {
        def expand = "{\"branches\":[{\"trunk\":{\"type\": \"${Common.TRUNK_TYPE}\"}}]}"
        doRestCall(callback) {
            Common.createRestClient(callback).getElephant(id, expand)
        }
    }

    def static saveElephant(callback, elephant) {
        doRestCall(callback) {
            Common.createRestClient(callback).updateElephant(elephant, Common.TRUNK_SIZE)
        }
    }

    def static saveGiraffe(callback, giraffe) {
        doRestCall(callback) {
            Common.createRestClient(callback).updateGiraffe(giraffe)
        }
    }

    def static getMonkey(callback, id) {
        def expand = "{\"branches\":[${Common.MONKEY_EXPANSION}]}";
        doRestCall(callback) {
            Common.createRestClient(callback).getMonkey(id, expand)
        }
    }

    def static getCattle(callback) {
        doRestCall(callback) {
            Common.createRestClient(callback).getCattle(Common.CATTLE_BRAND)
        }
    }
}

