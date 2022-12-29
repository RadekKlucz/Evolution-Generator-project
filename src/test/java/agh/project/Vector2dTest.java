package agh.project;
import agh.project.Classes.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void checkEquals() {
        // given
        Vector2d object1 = new Vector2d(1, 2);
        Vector2d object2 = new Vector2d(-2, 1);
        // test
        assertNotEquals(object1, object2);
    }

    @Test
    void checkToString() {
        // given
        String values = "(1, 2)";
        // when
        Vector2d object1 = new Vector2d(1, 2);
        var expected = object1.toString();
        // test
        assertEquals(values, expected);
    }

    @Test
    void checkPrecedes() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        // when
        var expected = object3.precedes(object4);
        // test
        assertFalse(expected);
    }

    @Test
    void checkFollows() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        // when
        var expected = object3.follows(object4);
        assertFalse(expected);
    }

    @Test
    void checkUpperRight() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        Vector2d object5 = new Vector2d(4, 5);
        // when
        var expected = object3.upperRight(object4);
        // test
        assertEquals(object5, expected);
    }

    @Test
    void checkLowerLeft() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        Vector2d object6 = new Vector2d(2, 3);
        // when
        var expected = object3.lowerLeft(object4);
        // test
        assertEquals(object6, expected);
    }

    @Test
    void checkAdd() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        Vector2d object7 = new Vector2d(6, 8);
        // when
        var expected = object3.add(object4);
        // test
        assertEquals(object7, expected);
    }

    @Test
    void checkSubtract() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        Vector2d object8 = new Vector2d(-2, 2);
        // when
        var expected = object3.subtract(object4);
        assertEquals(object8, expected);
    }

    @Test
    void checkOpposite() {
        // given
        Vector2d object3 = new Vector2d(2, 5);
        Vector2d object4 = new Vector2d(4, 3);
        Vector2d object9 = new Vector2d(-2, -5);
        // when
        var expected = object3.opposite();
        // test
        assertEquals(object9, expected);
    }

    @Test
    void checkEqualsMethod() {
        // given
        Vector2d object1 = new Vector2d(1, 2);
        Vector2d object2 = new Vector2d(-2, 1);
        // when
        var expected = object1.equals(object2);
        // test
        assertFalse(expected);
    }
}