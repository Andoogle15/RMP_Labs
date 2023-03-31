package com.company.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {
    public static final int IN = 1; // Ворота открываются внутрь
    public static final int OUT = -1; // Ворота открываются наружу
    public static final int CLOSED = 0; // Ворота остановлены

    private int mSwing; // текущее направление поворота ворот
    private int mCount; // количество улиток в загоне

    public Gate() {
        mSwing = CLOSED; // по умолчанию ворота остановлены
        mCount = 0; // по умолчанию в загоне нет улиток
    }
    public int getSwingDirection() {
        return mSwing;
    }

    public boolean setSwing(int swing) {
        if (swing == IN || swing == OUT || swing == CLOSED) {     // Метод, который возвращает логическое значение
            mSwing = swing;                                       // для указания того, что было ли задано направление поворота
            return true;
        } else {
            return false;
        }
    }
    public boolean open(int direction) { // При открытии ворот, нужно, чтобы вызывающий метод указал направление поворота.
        if (direction == IN || direction == OUT) {   //Нужно убедиться, что входное значение равно IN или OUT
            setSwing(direction);           // Метод Open(), получая направление поворота, вызывает метод SetSwing()
            return true;
        } else {
            return false;
        }
    }
    public void close() {
        setSwing(CLOSED);
    }

    public int thru(int count) {  // метод возвращает count, -count или 0 в зависимости от положения направления ворот
        if (mSwing == IN) {
            mCount += count; // если ворота в положении IN, то количество улиток в загоне увеличивается
            return count;
        } else if (mSwing == OUT) {  // количество уменьшается
            mCount -= count;
            return -count;
        } else {
            return 0;
        }
    }
    @Override
    public String toString() { // Переопределяем метод toString(), чтобы результат был нужным
        if (mSwing == CLOSED) {
            return "This gate is closed";
        } else if (mSwing == IN) {
            return "This gate is open and swings to enter the pen only";
        } else if (mSwing == OUT) {
            return "This gate is open and swings to exit the pen only";
        } else {
            return "This gate has an invalid swing direction";
        }
    }

}
