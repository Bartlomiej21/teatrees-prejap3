package com.epam.prejap.teatrees.block;
/**
 *
 * This class introduces the P-shaped block in Tetris game.
 *
 * @author Bartłomiej Słomiński
 *
 */

final class PBlock extends Block {

    private static final byte[][] IMAGE = {
            {1, 1},
            {1, 1},
            {1, 0},
    };


    public PBlock() {
        super(IMAGE);
    }

}



