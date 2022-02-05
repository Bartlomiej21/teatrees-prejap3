package com.epam.prejap.teatrees.block;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

@Test(groups = "blocks")
public class PBlockTest {

    @DataProvider (name = "Images")
    public Object[][] dpMethod(Method m){
        final byte[][] EMPTY_IMAGE = {};
        final byte[][] WRONG_IMAGE1 = {
                {1, 1},
                {1,}
        };
        final byte[][] WRONG_IMAGE2 = {
                {1, -1},
        };
        switch (m.getName()){
            case "checkIfBlockWithZeroHeightIsCaught":
                return new Object[][]{
                        {EMPTY_IMAGE},
                };
            case "checkIfBlockThatIsNotARectangleIsCaught":
                return new Object[][]{
                        {WRONG_IMAGE1},
                };
            case "checkIfWrongDotValueIsDetected":
                return new Object[][]{
                        {WRONG_IMAGE2},
                };

        }
        return null;
    }

    private PBlock block;

    //HAPPY PATH
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
    void checkIfLengthOfEveryRowEqualsNumberOfColumns(){
        SoftAssert softAssertion= new SoftAssert();
        for (int i=0; i<block.rows; i++){
            softAssertion.assertEquals(block.image[i].length,block.cols);
        }
        softAssertion.assertAll();
    }

    //NOT SO HAPPY PATH
    @Test (dataProvider = "Images", expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Image has height equal to 0")
    void checkIfBlockWithZeroHeightIsCaught(byte[][] image) throws IllegalArgumentException{
        new PBlock(image);
    }

    @Test (dataProvider = "Images", expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Image is not a rectangle")
    void checkIfBlockThatIsNotARectangleIsCaught(byte[][] image) throws IllegalArgumentException {
        new PBlock(image);
    }

    @Test (dataProvider = "Images", expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Invalid dot value")
    void checkIfWrongDotValueIsDetected(byte[][] image) throws IllegalArgumentException {
        //System.out.println(val1);
            new PBlock(image);
    }
}
