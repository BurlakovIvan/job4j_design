package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis())
                .isNotEmpty()
                .isNotBlank()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(1, 1);
        assertThat(box.whatsThis())
                .isNotEmpty()
                .contains("Unknown")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenHasFourVertex() {
        Box box = new Box(4, 15);
        assertThat(box.getNumberOfVertices())
                .isNotZero()
                .isNotNegative()
                .isEven()
                .isEqualTo(4);
    }

    @Test
    void whenHasNegativeVertex() {
        Box box = new Box(5, 23);
        assertThat(box.getNumberOfVertices())
                .isNotZero()
                .isNegative()
                .isOdd();
    }

    @Test
    void whenIsExist() {
        Box box = new Box(4, 6);
        assertThat(box.isExist())
                .isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void whenHasNotExist() {
        Box box = new Box(5, 20);
        assertThat(box.isExist())
                .isEqualTo(false)
                .isFalse();
    }

    @Test
    void whenAreaFigureIs216() {
        Box box = new Box(6, 6);
        assertThat(box.getArea())
                .isFinite()
                .isBetween(215d, 217d)
                .isCloseTo(216d, Percentage.withPercentage(0.01d));
    }

    @Test
    void whenAreaSphereIs201dot06() {
        Box box = new Box(0, 4);
        assertThat(box.getArea())
                .isCloseTo(201.06d, withPrecision(0.01d))
                .isGreaterThan(201d)
                .isLessThan(201.1d);
    }
}