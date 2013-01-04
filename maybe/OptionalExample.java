import com.google.common.base.Optional;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author kamiller@redhat.com (Katie Miller)
 */
public class OptionalExample {
    public static void main(String[] args) {
        Map<Integer, String> petEnclosures = newHashMap();
        petEnclosures.put(101, "Spot");
        petEnclosures.put(102, "Fluffy");
        petEnclosures.put(104, null);
        petEnclosures.put(106, "Felix");
        petEnclosures.put(107, "Ra");

        if (args.length != 1) {
            System.err.println("Error: supply number of enclosure to check, ie: 101");
            System.exit(1);
        }
        int enclosure = Integer.parseInt(args[0]);

        String pet = petEnclosures.get(enclosure);
        // Null here could mean the enclosure is empty or the animal inside has no name
        printStatus(enclosure, pet);

        pet = checkEnclosure(petEnclosures, enclosure).isPresent()
                ? checkEnclosure(petEnclosures, enclosure).get()
                : "nothing";
        // This time there is no ambiguity between the null cases
        printStatus(enclosure, pet);

        // A nullable value can be turned into an Optional
        Optional<String> popularityLevel = Optional.fromNullable(ratePetNamePopularity(pet));
        if ((!pet.equals("nothing"))
                && (!pet.equals("unnamed"))
                && popularityLevel.isPresent()) {
            System.out.println("Pet name's popularity is " + popularityLevel.get() + ".");
        }
    }

    // Returning an Optional forces us to think about cases when a value should be represented as present,
    // and when it should be represented as absent, and encourages the users of this method to consider what
    // to do when a value is absent
    private static Optional<String> checkEnclosure(Map<Integer, String> petEnclosures, int enclosure) {
        if (! petEnclosures.containsKey(enclosure)) {
            return Optional.absent();
        }
        String value = petEnclosures.get(enclosure);
        if (value == null) {
            return Optional.of("unnamed");
        }
        return Optional.of(value);
    }

    private static void printStatus(int enclosure, String pet) {
        System.out.println("Enclosure " + enclosure + " contains " + pet + ".");
    }

    // Returning an Optional here instead of just String would make it clear that
    // a value may not be returned in all cases and the coder should check for this
    private static String ratePetNamePopularity(String petName) {
        if (petName.length() < 3) {
            return null;
        }
        int nameScore = 0;
        if (petName.length() > 5) {
            nameScore += 5;
        }
        if (petName.contains("o") || petName.contains("i")) {
            nameScore += 2 + petName.charAt(2);
        }
        if (petName.equals("Morpheus") || petName.equals("Neo")) {
            nameScore += 20;
        }
        if (nameScore < 10) return "low";
        if (nameScore > 120) return "high";
        return "average";
    }
}
