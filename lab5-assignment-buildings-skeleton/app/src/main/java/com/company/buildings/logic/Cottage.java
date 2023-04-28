package com.company.buildings.logic;

/**
 * This is the cottage class file.  It is a subclass of House.
 */
public class Cottage
        extends House {

    // TODO - Put your code here.
    private boolean mSecondFloor;

    public Cottage(int dimension, int lotLength, int lotWidth) {
        super(dimension, dimension, lotLength, lotWidth);
        mSecondFloor = false;
    }

    public Cottage(int dimension, int lotLength, int lotWidth, String owner, boolean secondFloor) {
        super(dimension, dimension, lotLength, lotWidth, owner);
        mSecondFloor = secondFloor;
    }

    public boolean hasSecondFloor() {
        return mSecondFloor;
    }

    @Override
    public String toString() {
        String secondFloorString = mSecondFloor ? "with" : "without";
        return "Cottage owned by " + getOwner() + ", " + getLength() + "x" + getWidth() + ", " +
                "lot size " + getLotLength() + "x" + getLotWidth() + ", " + secondFloorString + " a second floor";
    }
}
