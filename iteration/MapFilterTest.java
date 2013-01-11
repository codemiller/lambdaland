import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MapFilterTest {
    @Test
    public void filterList() {

        ImmutableList<String> species = ImmutableList.of(
                "Sahelanthropus tchadensis",
                "Orrorin tugenensis",
                "Ardipithecus ramidus",
                "Australopithecus anamensis",
                "Australopithecus afarensis",
                "Kenyanthropus platyops",
                "Australopithecus africanus",
                "Australopithecus garhi",
                "Australopithecus sediba",
                "Australopithecus aethiopicus",
                "Australopithecus robustus",
                "Australopithecus boisei",
                "Homo habilis",
                "Homo georgicus",
                "Homo erectus",
                "Homo ergaster",
                "Homo antecessor",
                "Homo heidelbergensis",
                "Homo neanderthalensis",
                "Homo floresiensis",
                "Homo sapiens sapiens");


        Predicate<String> filter = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("Homo");
            }
        };

        ImmutableList<String> sortedFiltered = ImmutableList.copyOf(
                Ordering.natural().sortedCopy(
                        Iterables.filter(species, filter)
                )
        );

        String [] expected = {
                "Homo antecessor",
                "Homo erectus",
                "Homo ergaster",
                "Homo floresiensis",
                "Homo georgicus",
                "Homo habilis",
                "Homo heidelbergensis",
                "Homo neanderthalensis",
                "Homo sapiens sapiens" };
        assertArrayEquals(expected, sortedFiltered.toArray());

    }


}



