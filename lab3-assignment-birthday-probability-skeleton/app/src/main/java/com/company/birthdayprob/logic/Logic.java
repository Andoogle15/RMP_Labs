package com.company.birthdayprob.logic;

import java.util.Random;

import com.company.birthdayprob.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early Android interactions.
 * Designing the assignments this way allows you to first learn key 'Java' features without
 * having to beforehand learn the complexities of Android.
 *
 */
public class Logic 
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG =
        Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface
     * [MainActivity.java].
     * <p>
     * It is called 'mOut' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'mOut' is good enough).
    */
    OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance
     * (which implements [OutputInterface]) to 'out'
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labelled 'Process...' is pressed.
     */
    public void process() {
        int groupSize = mOut.getSize();
        int simulationCount = mOut.getCount();

        if (groupSize < 2 || groupSize > 365) {
            mOut.makeAlertToast("Group Size must be in the range 2-365.");
            return;
        }
        if (simulationCount <= 0) {
            mOut.makeAlertToast("Simulation Count must be positive.");
            return;
        }

        double percent = calculate(groupSize, simulationCount);

        // report results
        mOut.println("For a group of " + groupSize + " people, the percentage");
        mOut.println("of times that two people share the same birthday is");
        mOut.println(String.format("%.2f%% of the time.", percent));

    }

    /**
     * This is the method that actually does the calculations.
     * <p>
     * We provide you this method that way we can test it with unit testing.
     */
    public double calculate(int size, int count) {
        int sameBirthdaysSum = 0;
        for (int i = 0; i < count; i++) { // Цикл, идущий по прогонам моделирования
            Random random = new Random(i + 1); // грубо говоря шаблон для вычисления даты рождения
            int[] birthdays = new int[size];// инициализация массива с днями рождения, здесь они будут храниться
            boolean sameBirthdayFound = false; // булевая переменная для того чтобы определить, когда было найдено сходство
            for (int j = 0; j < size; j++) { // цикл отвечает за генерацию случайных дней рождения для человека из группы
                birthdays[j] = random.nextInt(365) + 1; // в массив birthdays сохраняются дни рождения
                for (int k = 0; k < j; k++) { // цикл отвечает за сравнение дня рождения текущего человека с др предыдущих людей
                    if (birthdays[j] == birthdays[k]) {
                        sameBirthdayFound = true;
                        break;
                    }
                }
                if (sameBirthdayFound) { break; } // Прерываем когда нашли совпадения
            }
            if (sameBirthdayFound) { sameBirthdaysSum++; } // Считаем общее количество найденых совпадения для вычисления %
        }
        return (double) sameBirthdaysSum / count * 100; // высчитывание процента
    }
}
