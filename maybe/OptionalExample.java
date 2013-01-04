import com.google.common.base.Optional;

import java.lang.String;
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
        petEnclosures.put(103, null);
        petEnclosures.put(104, "Felix");
        petEnclosures.put(105, null);
        petEnclosures.put(106, "Ra");

        if (args.length != 1) {
            System.err.println("Error: supply number of enclosure to check, ie: 101");
            System.exit(1);
        }
        int enclosure = Integer.parseInt(args[0]);

        // Null here could mean there is no enclosure with that number or it doesn't contain a pet
        String petName = petEnclosures.get(enclosure);
        printStatus(enclosure, petName);

        // This time there is no ambiguity
        Optional<String> enclosureContents = checkEnclosure(petEnclosures, enclosure);
        if (! enclosureContents.isPresent()) {
            System.err.println("Error: invalid enclosure number");
            System.exit(1);
        }
        petName = enclosureContents.get();
        printStatus(enclosure, petName);

        // A nullable value can be turned into an Optional with Optional.fromNullable
        Optional<String> popularityLevel = Optional.fromNullable(ratePetNamePopularity(petName));
        if ((!pet.equals("nothing"))
                && popularityLevel.isPresent()) {
            System.out.println("Pet name's popularity is " + popularityLevel.get() + ".");
        }
    }

    // Returning an Optional forces the coder to think about cases when a value should be represented as present,
    // and when it should be represented as absent, and encourages the users of this method to consider what
    // to do when a value is absent
    private static Optional<String> checkEnclosure(Map<Integer, String> petEnclosures, int enclosure) {
        if (! petEnclosures.containsKey(enclosure)) {
            return Optional.absent();
        }
        String value = petEnclosures.get(enclosure);
        if (value == null) {
            return Optional.of("nothing");
        }
        return Optional.of(value);
    }

    private static void printStatus(int enclosure, String pet) {
        System.out.println("Enclosure " + enclosure + " contains " + pet + ".");
    }

    // Returning an Optional here instead of String would make it clear that a value may not be returned in
    // all cases and the coder should check for this
    public static String ratePetNamePopularity(String petName) {
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
