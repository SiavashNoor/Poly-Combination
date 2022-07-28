package com.javatestSandBox;

import java.util.Arrays;

public class PolyCombination {

    int numberOfInputTerms;
    int maxDegree;
    public int[] oneTermArray;
    public int[][] mainArray;

    PolyCombination(int inputTerms, int maxDegree) {
        this.numberOfInputTerms = inputTerms;
        this.maxDegree = maxDegree;
        this.CalculatePolyCombination(numberOfInputTerms, maxDegree);
    }

    public void CalculatePolyCombination(int numberOfInputTerms, int maxDegree) {
        //this array is the constructor of one term .so the final output of
        //this function would be   an ordered collection  of ' oneTermArray's

        oneTermArray = new int[numberOfInputTerms];
        mainArray = new int[(int) calculateCombinationSize(numberOfInputTerms, maxDegree)][numberOfInputTerms];
        int rowPointer = 0;
        int verticalPointer;

        copyToMainArray(oneTermArray, rowPointer);


        for (int sumOfDegrees = 0; sumOfDegrees <= maxDegree; sumOfDegrees++) {
            verticalPointer = 0;
            cleanArray(oneTermArray);
            oneTermArray[verticalPointer] = sumOfDegrees;
            copyToMainArray(oneTermArray, rowPointer);
            rowPointer++;
            System.out.println(rowPointer);


            while (oneTermArray[oneTermArray.length - 1] != sumOfDegrees) {
                if (valueInPointerIsBreakable(verticalPointer, oneTermArray)) {
                    oneTermArray[verticalPointer] -= 1;
                    verticalPointer++;
                    oneTermArray[verticalPointer] = sumOfDegrees - sumArrayTerms(oneTermArray, 0, verticalPointer - 1);
                    cleanArray(oneTermArray, verticalPointer + 1, oneTermArray.length - 1);
                    copyToMainArray(oneTermArray, rowPointer);
                    rowPointer++;


                } else {
                    verticalPointer--;
                    System.out.println("verticalpointer " + verticalPointer);
                }


            }
        }
        System.out.println(Arrays.deepToString(mainArray));

        System.out.println(mainArray.length);
    }


    //this method is used to calculate the final 2D array size which contains all the possibilities.
    public long calculateCombinationSize(int numberOfInputTerms, int maxDegree) {
        return (calculateFactorial(numberOfInputTerms + maxDegree) / (calculateFactorial(numberOfInputTerms) * calculateFactorial(maxDegree)));
    }


    //a recursion method to calculate Factorial .
    public long calculateFactorial(int x) {
        if (x == 0) {
            return 1;
        } else
            return x * calculateFactorial(x - 1);
    }

    //because arrays are non-primitive data types, we can't copy a data from array to another
    // we have to copy whatever that contains not just the pointer address.
    public void copyToMainArray(int[] oneTermArray, int mainArrayRowNumber) {
        for (int i = 0; i < 1; i++) {
            mainArray[mainArrayRowNumber] = Arrays.copyOf(oneTermArray, numberOfInputTerms);
        }
    }

    public void cleanArray(int[] array, int start, int end) {
        for (int i = start; i <= end; i++)
            array[i] = 0;

    }

    public void cleanArray(int[] array) {
        Arrays.fill(array, 0);
    }

    public int sumArrayTerms(int[] array, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    public boolean valueInPointerIsBreakable(int pointer, int[] oneTermArray) {
        if (oneTermArray[pointer] >= 0 & pointer < oneTermArray.length - 1) {
            return true;
        } else if (oneTermArray[pointer] == 0 & pointer == oneTermArray.length - 1) {
            return false;
        } else return false;
    }
}
