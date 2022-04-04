package edu.psu.ist311.megalist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class SLMegaTests {

    @Test
    public void TestAdd1(){
        //adding all elements
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);

        Assertions.assertEquals("<><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());

        a.addToRightAtFront(22);
        Assertions.assertEquals("<><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());

        a.addToRightAtFront(11);
        Assertions.assertEquals("<><11, 22, 33>", a.toString());
        Assertions.assertEquals(3, a.rightLength());

    }

    @Test
    public void TestAdd2(){
        //adding 33 and 22, then moving 22 to the left sequence, then adding 11 to the right
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        Assertions.assertEquals("<><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals(0, a.leftLength());

        a.addToRightAtFront(22);
        Assertions.assertEquals("<><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(0, a.leftLength());

        a.moveForward();
        Assertions.assertEquals("<22><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());

        a.addToRightAtFront(11);
        Assertions.assertEquals("<22><11, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());
    }

    @Test
    public void TestRemove1(){
        //adding 22 and 33 and then removing them
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        a.addToRightAtFront(22);



        Assertions.assertEquals("<><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());

        Assertions.assertEquals(22, a.removeFromRightAtFront());
        Assertions.assertEquals("<><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());

        Assertions.assertEquals(33, a.removeFromRightAtFront());
        Assertions.assertEquals("<><>", a.toString());
        Assertions.assertEquals(0, a.rightLength());

    }

    @Test
    public void TestRemove2(){
        //adding all elements, then moving 11 to the left sequence, then removing 22
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        a.addToRightAtFront(22);
        a.addToRightAtFront(11);
        Assertions.assertEquals("<><11, 22, 33>", a.toString());
        Assertions.assertEquals(3, a.rightLength());

        a.moveForward();
        Assertions.assertEquals("<11><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());

        a.removeFromRightAtFront();
        Assertions.assertEquals("<11><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());
    }

    @Test
    public void TestRemoveFailure3(){
        //adding 33, then removing it and trying to remove another element from the empty list: should throw no such element exception
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);

        Assertions.assertEquals("<><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());

        a.removeFromRightAtFront();
        Assertions.assertEquals("<><>", a.toString());
        Assertions.assertEquals(0, a.rightLength());

        Assertions.assertThrows(NoSuchElementException.class, () -> a.removeFromRightAtFront());

    }

    @Test
    public void TestMoveToBegin1(){
        //adds 11, 22, and 33 and then moves the 11 and 22 forward and then uses the move to very beginning
        //to move the 11 and 22 to the beginning of the right sequence
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        a.addToRightAtFront(22);
        a.addToRightAtFront(11);

        Assertions.assertEquals("<><11, 22, 33>", a.toString());
        Assertions.assertEquals(3, a.rightLength());

        a.moveForward();
        a.moveForward();
        Assertions.assertEquals("<11, 22><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals(2, a.leftLength());

        a.moveToVeryBeginning();
        Assertions.assertEquals("<><11, 22, 33>", a.toString());
        Assertions.assertEquals(3, a.rightLength());
    }

    @Test
    public void TestMoveToBegin2(){
        //adding all 3 elements into the right sequence then moves 11 into the left sequence then removes 22
        //then moves the left sequence back into the right
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        a.addToRightAtFront(22);
        a.addToRightAtFront(11);

        Assertions.assertEquals("<><11, 22, 33>", a.toString());
        Assertions.assertEquals(3, a.rightLength());

        a.moveForward();
        Assertions.assertEquals("<11><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());

        a.removeFromRightAtFront();
        Assertions.assertEquals("<11><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());

        a.moveToVeryBeginning();
        Assertions.assertEquals("<><11, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(0, a.leftLength());
    }

    @Test
    public void TestMoveForward1(){
        //adding all 3 elements then moving 11 to the left sequence
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        a.addToRightAtFront(22);
        a.addToRightAtFront(11);

        a.moveForward();

        Assertions.assertEquals("<11><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());
    }

    @Test
    public void TestMoveForwardFailure(){
        // adding all 3 elements then moving the most left element into the left sequence then deleted the
        // first element of the right sequence then moves the 33 into the left sequence,
        // the Illegal state exception is thrown when there is nothing left in the right sequence
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);
        a.addToRightAtFront(22);
        a.addToRightAtFront(11);

        a.moveForward();
        Assertions.assertEquals("<11><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());

        a.removeFromRightAtFront();
        Assertions.assertEquals("<11><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals(1, a.leftLength());

        a.moveForward();
        Assertions.assertEquals("<11, 33><>", a.toString());
        Assertions.assertEquals(0, a.rightLength());
        Assertions.assertEquals(2, a.leftLength());


        Assertions.assertThrows(IllegalStateException.class, () -> a.moveForward());
    }

    @Test
    public void TestClear1(){
        //adding all elements and then clearing them
        IMegaList<Integer> a = new SLMegaList<>();
        a.addToRightAtFront(33);

        Assertions.assertEquals("<><33>", a.toString());
        Assertions.assertEquals(1, a.rightLength());

        a.addToRightAtFront(22);
        Assertions.assertEquals("<><22, 33>", a.toString());
        Assertions.assertEquals(2, a.rightLength());

        a.addToRightAtFront(11);
        Assertions.assertEquals("<><11, 22, 33>", a.toString());
        Assertions.assertEquals(3, a.rightLength());

        a.clear();
        Assertions.assertEquals("<><>", a.toString());
        Assertions.assertEquals(0, a.rightLength());
        Assertions.assertEquals(0, a.leftLength());
    }


    @Test
    public void testFundamentals1(){
        IMegaList<Integer> a = new SLMegaList<>();


        Assertions.assertEquals(0, a.leftLength());
        Assertions.assertEquals(0, a.rightLength());
        Assertions.assertEquals("<><>", a.toString());

        a.addToRightAtFront(4);
        Assertions.assertEquals(0, a.leftLength());
        Assertions.assertEquals(1, a.rightLength());
        Assertions.assertEquals("<><4>", a.toString());
    }




}