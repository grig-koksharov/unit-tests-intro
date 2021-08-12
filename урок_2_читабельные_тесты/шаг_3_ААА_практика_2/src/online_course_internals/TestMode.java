package online_course_internals;

enum TestMode {
    BUGGY_IMPLEMENTATION,
    CORRECT_IMPLEMENTATION;

    private static TestMode testMode = CORRECT_IMPLEMENTATION;

    static void givenCorrectImplementation() {
        testMode = CORRECT_IMPLEMENTATION;
    }

    static void givenBuggyImplementation() {
        testMode = BUGGY_IMPLEMENTATION;
    }

    static boolean isBuggyImplementation() {
        return testMode == BUGGY_IMPLEMENTATION;
    }
}
