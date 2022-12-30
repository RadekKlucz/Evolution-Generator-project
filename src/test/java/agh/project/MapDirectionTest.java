package agh.project;

import agh.project.EnumClasses.MapDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    void checkIfNextIsOkForNorth() {
        // given
        var north = MapDirection.NORTH;
        var expected = MapDirection.NORTH_EAST;
        // when
        var northEast = north.next();
        // test
        assertEquals(northEast, expected);
    }

    @Test
    void checkIfNextIsOkForNorthEast() {
        // given
        var northEast = MapDirection.NORTH_EAST;
        var expected = MapDirection.EAST;
        //when
        var east = northEast.next();
        // test
        assertEquals(east, expected);
    }

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
    void checkIfNextIsOkForSouthEast() {
        // given
        var southEast = MapDirection.SOUTH_EAST;
        var expected = MapDirection.SOUTH;
        // when
        var south = southEast.next();
        // test
        assertEquals(south, expected);
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
    void checkIfNextIsOkForSouthWest() {
        // given
        var southWest = MapDirection.SOUTH_WEST;
        var expected = MapDirection.WEST;
        // when
        var west = southWest.next();
        // test
        assertEquals(west, expected);
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
    void checkIfNextIsOkForNorthWest() {
        // given
        var northWest = MapDirection.NORTH_WEST;
        var expected = MapDirection.NORTH;
        // when
        var north = northWest.next();
        // test
        assertEquals(north, expected);
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
    void checkIfPreviousIsOkForNorthWest() {
        // given
        var northWest = MapDirection.NORTH_WEST;
        var expected = MapDirection.WEST;
        // when
        var south = northWest.previous();
        // test
        assertEquals(south, expected);
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
    void checkIfPreviousIsOkForSouthWest() {
        // given
        var southWest = MapDirection.SOUTH_WEST;
        var expected = MapDirection.SOUTH;
        // when
        var south = southWest.previous();
        // test
        assertEquals(south, expected);
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

    @Test
    void checkIfPreviousIsOkForSouthEast() {
        // given
        var southEast = MapDirection.SOUTH_EAST;
        var expected = MapDirection.EAST;
        // when
        var east = southEast.previous();
        // test
        assertEquals(east, expected);
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
}