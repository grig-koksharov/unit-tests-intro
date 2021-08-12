package online_course_internals;

import java.util.ArrayList;
import java.util.Collection;

public class TestedValues {

    private final static Collection<Object> testedValues = new ArrayList<>();

    static void cleanup() {
        testedValues.clear();
    }

    static void recordTestedValue(Object object) {
        testedValues.add(object);
    }

    static Collection<Object> testedValues() {
        return testedValues;
    }
}
