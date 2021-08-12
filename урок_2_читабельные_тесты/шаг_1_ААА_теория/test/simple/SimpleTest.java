package simple;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {

    @Test
    // тест, проверяющий, что 2 + 2 = 4
    public void should_get_4_when_adding_2_to_2() {
        // Arrange - здесь мы подготавливаем данные
        // для наглядности запишем в переменные, что складывать
        var first2 = 2;
        var second2 = 2;

        // Act - здесь мы выполняем тестируемое действие
        // складываем 2 + 2
        var sumResult = first2 + second2;

        // Assert - здесь мы проверяем, что результат такой,
        // как мы ожидали, что должно получиться 4
        assertThat(sumResult).isEqualTo(4);
    }
}