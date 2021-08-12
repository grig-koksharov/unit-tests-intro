package online_course_internals;

class IncorrectTestMessages {

    static String noFailWhenBuggyImplementation(String testedMethodName) {
        return String.format("Ваш тест %s не падает, когда тестирует некорректную реализацию.", testedMethodName);
    }

    static String failWhenCorrectImplementation(String testedMethodName) {
        return String.format("Ваш тест %s падает, когда тестирует корректную реализацию.", testedMethodName);
    }

    static String notSingleTestedValue(String testedMethodName) {
        return String.format("Ваш тест %s не вызывает тестируемый метод или вызывает его больше 1 раза.", testedMethodName);
    }

    static String unexpectedTestedValue(String testedMethodName, String expectedTestedValueName) {
        return String.format("Ваш тест %s должен тестировать %s.", testedMethodName, expectedTestedValueName);
    }
}
