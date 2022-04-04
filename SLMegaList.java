package edu.psu.ist311.megalist;

import edu.psu.ist311.megalist.IMegaList;

import java.util.NoSuchElementException;


public class SLMegaList<E> implements IMegaList<E> {
    private int lenLeft;
    private int lenRight;
    private MNode lastInLeft;
    private MNode last;
    private MNode preFirst;

    @Override
    public void addToRightAtFront(E e) {

        MNode m = new MNode(lastInLeft.next, e);

        lastInLeft.next = m;

        if (lenRight == 0) {
            last = m;
        }
        lenRight++;

    }

    @Override
    public E removeFromRightAtFront() {
        if (lenRight == 0) {
            throw new NoSuchElementException("Right List is empty!");
        }
        E dataToReturn = lastInLeft.next.data;
        MNode newRightFront = lastInLeft.next.next;
        lastInLeft.next = newRightFront;
        if (lenRight == 1) {
            last = lastInLeft;
        }

        lenRight--;
        return dataToReturn;
    }


    @Override
    public void moveToVeryBeginning() {
        lastInLeft = preFirst;
        lenRight = lenRight + lenLeft;
        lenLeft = 0;
    }

    @Override
    public void moveForward() {

        if (lenRight == 0) {
            throw new IllegalStateException("Illegal State!");
        }


        MNode newMoveRight = lastInLeft.next;
        lastInLeft = lastInLeft.next;
        if (newMoveRight != null) {
            last = lastInLeft;
            lenRight --;
        }
        lenLeft++;
    }


    @Override
    public int leftLength() {
        return lenLeft;
    }

    @Override
    public int rightLength() {
        return lenRight;
    }

    @Override
    public void clear() {
        this.preFirst = new MNode(null, null);
        this.last = preFirst;
        this.lastInLeft = preFirst;

        lenLeft = 0;
        lenRight = 0;


    }

    public String toString() {
        MNode curr = preFirst.next;
        StringBuilder sb = new StringBuilder("<");

        boolean first = true;
        while (curr != lastInLeft.next) {
            if (first) {
                sb.append(curr.data);
                first = false;
            } else {
                sb.append(", ").append(curr.data);
            }
            curr = curr.next;   // advance to the next node in the left chain
        }
        sb.append(">" + "<");


        first = true;
        while (curr != null) {
            if (first) {
                sb.append(curr.data);
                first = false;
            } else {
                sb.append(", ").append(curr.data);
            }

            curr = curr.next;
        }
        sb.append(">");
        return sb.toString();
    }



    public SLMegaList() {
        this.preFirst = new MNode(null, null); // dummy node (its next and data hold null initially)
        this.last = preFirst; // last node points to dummy node initially
        this.lastInLeft = preFirst; // lenLeft and lenRight both automatically get initialized to 0 if you declare them with type int
    }

    private final class MNode {

        E data;    //the generic payload stored in this node
        MNode next;     //pointer to next node (or null)


        public MNode(MNode next, E data) {
            this.data = data;
            this.next = next;

        }


        // this is called a "ternary" if-stmt; the part after '?' produces the string specified;
        // and everything after the colon ':' serves as the else part. So ternary if-stmts
        // return/produce values and can be written in a single line (in this case, values of type String)

        @Override
        public String toString() {
            return data == null ? "<preFirst>" : data.toString();
        }

    }


}
