package com.epam.prejap.teatrees.block;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;

@Test(groups = "blocks")
public class PBlockTest {

    @DataProvider (name="PCoordinates")
    public Object[][] provideCoordinates(){
        return new Object[][]{
                {0,0,1},
                {0,1,1},
                {1,0,1},
                {1,1,1},
                {2,0,1},
                {2,1,0}
        };
    }

    @DataProvider (name = "NonRectangularImage")
    public Object[][] provideNonRectangularImage() {
        final byte[][] WRONG_IMAGE1 = {
                {1, 1},
                {1,}
        };
        final byte[][] WRONG_IMAGE2 = {
                {1},
                {1, 1, 1}
        };
        return new Object[][]{
                {WRONG_IMAGE1},
                {WRONG_IMAGE2}
        };
    }

    private PBlock block;
    final byte[][] EMPTY_IMAGE = {};

    @BeforeMethod
    void setup() {
        block = new PBlock();
    }

    @Test
    void checkIfNoRowsAreVoid(){
        SoftAssert softAssertion= new SoftAssert();
        for (int i=0; i<block.rows;i++){
            softAssertion.assertNotNull(block.dotAt(i,0));
        }
        softAssertion.assertAll();
    }

    @Test
    void checkIfBlockFieldsContainOnlyZeroesAndAtLeastOneOne(){
        SoftAssert softAssertion= new SoftAssert();
        boolean onePresent = false;
        for (int i=0; i<block.rows; i++){
            for (int j=0; j<block.cols ; j++) {
                byte field = block.dotAt(i,j);
                softAssertion.assertTrue(field>=0);
                softAssertion.assertTrue(field <=1);
                if (field==1) onePresent=true;
            }
        }
        softAssertion.assertTrue(onePresent);
        softAssertion.assertAll();
    }

    @Test
    void checkIfNumberOfColumnsIsCorrect(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(block.cols(),2);
    }

    @Test
    void checkIfLengthOfEveryRowEqualsNumberOfColumns(){
        SoftAssert softAssertion= new SoftAssert();
        for (int i=0; i<block.rows; i++){
            softAssertion.assertEquals(block.image[i].length,block.cols);
        }
        softAssertion.assertAll();
    }

    @Test (dataProvider = "PCoordinates")
    void checkIfCoordinatesForPBlockAreCorrect(int row, int col, int dot){
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(block.dotAt(row,col),dot);
        softAssertion.assertAll();
    }

    @Test (expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Image has height equal to 0")
    void checkIfBlockWithZeroHeightIsCaught() throws IllegalArgumentException{
        new PBlock(EMPTY_IMAGE);
    }

    @Test (dataProvider = "NonRectangularImage", expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Image is not a rectangle")
    void checkIfBlockThatIsNotARectangleIsCaught(byte[][] image) throws IllegalArgumentException {
        new PBlock(image);
    }
}
