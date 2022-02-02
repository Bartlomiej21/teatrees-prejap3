package com.epam.prejap.teatrees.block;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PBlockTest {

    final byte[][] IMAGE = {
            {1, 1},
            {1, 1},
            {1, 0},
    };
    final byte[][] WRONG_IMAGE = {};
    final byte[][] WRONG_IMAGE2 = {
            {1, 1},
            {1, 1},
            {1, 0},
            {1,}
    };
    final byte[][] WRONG_IMAGE3 = {
            {1, 1},
            {1, -1},
            {1, 0},
            {0, 1},
    };


    //PBlock image1 = new PBlock(IMAGE);
    PBlock image1 = new PBlock();

    //HAPPY PATH
    @Test
    void checkIfNoRowsAreVoid(){
        SoftAssert softAssertion= new SoftAssert();
        for (int i=0; i<image1.rows;i++){
            //assertTrue(image1.rows>0);
            //assertTrue(image1.dotAt(i,0)!=assertNotNull();
            softAssertion.assertNotNull(image1.dotAt(i,0));
        }
        softAssertion.assertAll();
    }

    /*
    public int rows() {return rows;}
    public int cols() {
        return cols;
    }
    public byte dotAt(int i, int j) { return image[i][j]; }
     */

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
    @Test (expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Image has height equal to 0")
    void checkIfBlockWithZeroHeightIsCaught() {
        try {
            new PBlock(WRONG_IMAGE);
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
    @Test (expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Image is not a rectangle")
    void checkIfBlockThatIsNotARectangleIsCaught() {
        try {
            new PBlock(WRONG_IMAGE2);
        }
        catch (Exception e){
            System.out.println(e);
            throw e; //IllegalArgumentException(e);
        }
    }
    @Test (expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid dot value")
    void checkIfWrongDotValueIsDetected() {
        try {
            new PBlock(WRONG_IMAGE3);
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
