package com.company;

import java.util.Arrays;
import java.util.Random;

public class QuickSort extends Thread {
    Random random = new Random();
    int[] sequence;
    int begin;
    int end;

    public void sort(int[] sequence, int begin, int end) {
        this.begin = begin;
        this.end = end;
        int div = div(sequence);//miejsce, gdzie skonczyl sortowac

        QuickSort sort1 = new QuickSort();
        QuickSort sort2 = new QuickSort();

        if (begin != div) {
            sort1.setSequence(sequence,begin, div);
            sort1.start();
        }
        if ((div + 1) != end) {
            sort2.setSequence(sequence,div+1, end);
            sort2.start();
        }
        try {
        sort1.join();
        sort2.join(); } catch (InterruptedException e){};
    }

    public int div(int[] sequence) {
        int pivot = sequence[random.nextInt(end - begin) + begin];
        int findBigger = begin - 1;
        int findSmaller = end + 1;
        while (true) {
            do {
                findBigger++;
            } while (sequence[findBigger] < pivot);
            do {
                findSmaller--;
            } while (sequence[findSmaller] > pivot);

            if (findBigger >= findSmaller) {
                return findSmaller;
            }//zwraca poczatek prawej podtablicy

            int temp = sequence[findBigger];
            sequence[findBigger] = sequence[findSmaller];
            sequence[findSmaller] = temp;
            //zamiana wartosci, ktore stoja po zlych stronach
        }
    }

    public void sort(int[] sequence) {
        sort(sequence, begin, end);
    }

    public void setSequence(int[] sequence){
        this.sequence = sequence;
        this.begin = 0;
        this.end = sequence.length-1;
    }

    public void setSequence(int[] sequence, int begin, int end) {
        this.sequence = sequence;
        this.begin = begin;
        this.end = end;
    }



    @Override
    public void run() {
        this.sort(sequence);
    }
}