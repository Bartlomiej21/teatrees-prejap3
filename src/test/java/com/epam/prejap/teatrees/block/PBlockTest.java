package com.epam.prejap.teatrees.block;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class PBlockTest {

    final byte[][] IMAGE = {
            {1, 1},
            {1, 1},
            {1, 0},
    };
    final byte[][] WRONG_IMAGE = {
            {1, 1, 1},
            {1, 5},
            {1, 0},
            {},
    };

    //HAPPY PATH
    @Test
    void checkIfNoRowsAreVoid(){
        for (int i=0; i<IMAGE.length;i++){
            assertTrue(IMAGE[i].length>0);
        }
    }

    @Test
    void checkIfBlockFieldsContainOnlyOnesAndZeroes(){
        for (int i=0; i<IMAGE.length; i++){
            for (int j=0; j<IMAGE[0].length;j++) {
                byte field = IMAGE[i][j];
                assertTrue(field>=0 && field <=1 );
            }
        }
    }

    @Test
    void checkIfLengthOfEveryRowEqualsNumberOfColumns(){
        int columns = IMAGE[0].length;
        for (int i=0; i<IMAGE.length; i++){
            assertEquals(IMAGE[i].length,columns);
        }
    }

    @Test
    void checkIfBlockIsRectangular(){
        //every line should have the same length
        int rowLength = IMAGE[0].length;
        for (int i=0; i<IMAGE.length; i++){
            assertEquals(IMAGE[i].length,rowLength);
        }
    }

    //NOT SO HAPPY PATH
    @Test
    void checkIfVoidRowsAreDetected(){
        for (int i=0; i<WRONG_IMAGE.length;i++){
            assertTrue(WRONG_IMAGE[i].length>0);
        }
    }

    @Test
    void checkIfBlockFieldsContainOnlyOnesAndZeroesAreDetected(){
        for (int i=0; i<WRONG_IMAGE.length; i++){
            for (int j=0; j<WRONG_IMAGE[0].length;j++) {
                byte field = WRONG_IMAGE[i][j];
                assertTrue(field>=0 && field <=1 );
            }
        }
    }

    @Test
    void checkIfIncorrectRowLengthIsDetected(){
        int columns = WRONG_IMAGE[0].length;
        for (int i=0; i<WRONG_IMAGE.length; i++){
            assertEquals(WRONG_IMAGE[i].length,columns);
        }
    }

    @Test
    void checkIfBlocksThatAreNotRectangularsWillPass(){
        //every line should have the same length
        int rowLength = WRONG_IMAGE[0].length;
        for (int i=0; i<WRONG_IMAGE.length; i++){
            assertEquals(WRONG_IMAGE[i].length,rowLength);
        }
    }
}