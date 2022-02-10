package com.epam.prejap.teatrees.block;

import org.testng.asserts.SoftAssert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

    private PBlock block;

    @BeforeMethod
    void setup() {
        block = new PBlock();
    }

    @Test
    void shouldHaveNonEmptyRows(){
        SoftAssert softAssertion = new SoftAssert();
        for (int i=0; i<block.rows;i++){
            softAssertion.assertNotNull(block.dotAt(i,0));
        }
        softAssertion.assertAll();
    }

    @Test
    void shouldHaveProperDotValues(){
        SoftAssert softAssertion = new SoftAssert();
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
    void shouldHaveExpectedColumnsSize(){
        assertEquals(block.cols(),2,
                String.format("The P block should have %d columns",block.cols()));
    }

    @Test
    void shouldHaveProperRowSize(){
        SoftAssert softAssertion = new SoftAssert();
        for (int i=0; i<block.rows; i++){
            softAssertion.assertEquals(block.image[i].length,block.cols,
                    String.format("This row should have %d dots",block.cols));
        }
        softAssertion.assertAll();
    }

    @Test (dataProvider = "PCoordinates")
    void shouldHaveExpectedValuesAtGivenCoordinates(int row, int col, int dot){
        assertEquals(block.dotAt(row,col),dot,
                String.format("The dot's value at coordinates (%d, %d) should be %d",row,col,dot));
    }
}
