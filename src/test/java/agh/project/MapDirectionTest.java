package agh.project;

import agh.project.EnumClasses.MapDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    // dodac testy dla wszystkich kierunkow zarowno dla next jak i previous
    @Test
    void checkIfNextIsOkForNorth() {
        // given
        var north = MapDirection.NORTH;
        var expected = MapDirection.NORTH_EAST;
        // when
        var northEast = north.next();
        // test
        assertEquals(northEast, expected); }

    @Test
    void checkIfNextIsOkForEast() {
        // given
        var east = MapDirection.EAST;
        var expected = MapDirection.SOUTH_EAST;
        // when
        var southEast = east.next();
        // test
        assertEquals(southEast, expected);
    }

    @Test
    void checkIfNextIsOkForWest() {
        // given
        var west = MapDirection.WEST;
        var expected = MapDirection.NORTH_WEST;
        // when
        var northWest = west.next();
        // test
        assertEquals(northWest, expected);
    }

    @Test
    void checkIfNextIsOkForSouth() {
        // given
        var south = MapDirection.SOUTH;
        var expected = MapDirection.SOUTH_WEST;
        // when
        var southWest = south.next();
        // test
        assertEquals(southWest, expected);
    }

    @Test
    void checkIfPreviousIsOkForWest() {
        // given
        var west = MapDirection.WEST;
        var expected = MapDirection.SOUTH_WEST;
        // when
        var southWest = west.previous();
        // test
        assertEquals(southWest, expected);
    }

    @Test
    void checkIfPreviousIsOkForEast() {
        // given
        var east = MapDirection.EAST;
        var expeceted = MapDirection.NORTH_EAST;
        // when
        var northEast = east.previous();
        // test
        assertEquals(northEast, expeceted);
    }

    @Test
    void checkIfPreviousIsOkForNorth() {
        // given
        var north = MapDirection.NORTH;
        var expected = MapDirection.NORTH_WEST;
        // when
        var northWest = north.previous();
        // test
        assertEquals(northWest, expected);
    }

    @Test
    void checkIfPreviousIsOkForSouth() {
        // given
        var south = MapDirection.SOUTH;
        var expected = MapDirection.SOUTH_EAST;
        // when
        var southEast = south.previous();
        // test
        assertEquals(southEast, expected);
    }
}