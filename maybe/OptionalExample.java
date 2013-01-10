import com.google.common.base.Optional;

import java.lang.String;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Example showing use of Google Guava's Optional type.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class OptionalExample {
    public static void main(String[] args) {
        Map<Integer, String> petEnclosures = newHashMap();
        petEnclosures.put(101, "Spot");
        petEnclosures.put(102, "Fluffy");
        petEnclosures.put(103, null);
        petEnclosures.put(104, "Felix");

        int enclosure = -1;
        try {
            enclosure = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Error: supply number of enclosure to check, ie: 101");
            System.exit(1);
        }

        // The meaning of a null result here is ambiguous
        String petName = petEnclosures.get(enclosure);
        System.out.println("Enclosure " + enclosure + " contains " + petName + ".");

        // This time there is no ambiguity
        Optional<Optional<String>> enclosureValue = doLookup(petEnclosures, enclosure);
        if (! enclosureValue.isPresent()) {
            System.err.println("Error: invalid enclosure number");
            System.exit(1);
        }
        petName = enclosureValue.get().or("nothing");
        System.out.println("Enclosure " + enclosure + " contains " + petName + ".");
    }

    private static <K, V> Optional<Optional<V>> doLookup(Map<K, V> map, K key) {
        if (! map.containsKey(key)) {
            return Optional.absent();
        }
        return Optional.of(Optional.fromNullable(map.get(key)));
    }
}