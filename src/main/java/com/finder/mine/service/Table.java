package com.finder.mine.service;

import java.util.Arrays;
import java.util.Random;

public class Table {

    private final int width;
    private final int height;
    // CHARACTER
    private final String[][] table;
    private final int[][] mines;

    public void printMines(){
        Arrays.stream(mines).forEach(m -> System.out.println(Arrays.toString(m)));
    }

    public Table(int width, int height) {
        this.width = width;
        this.height = height;
        table = new String[width][height];
        fillTable();
        mines = new int[10][2];
        generateMines();
        drawTable();
    }

    private void generateMines() {
        Random rand = new Random();
        int counter = 0;
        while(counter < 10) {
            int w = rand.nextInt(width);
            int h = rand.nextInt(height);
            boolean contains = isMine(w, h);
            if(!contains) {
                mines[counter++] = new int[]{w,h};;
            }
        }
    }

    private void fillTable() {
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = "+";
            }
        }
    }

    private void drawTable() {
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    public void takeStep(int w, int h) {
        if(!isValidField(w, h)) {
            throw new IllegalArgumentException("Your step is out of bounds");
        }
        boolean isMine = isMine(w, h);
        if(isMine) {
            table[w][h] = "M";
        } else {
            uncoverField(w, h);
        }
        drawTable();
    }

    private boolean isValidField(int w, int h) {
        return w >= 0 && w < width && h >= 0 && h < height;
    }

    private int minesAround(int w, int h) {
        int[][] options = getOptions(w, h);
        return (int) Arrays.stream(options).filter(o -> isMine(o[0], o[1])).count();
    }

    private int[][] getOptions(int w, int h) {
        int[][] options = new int[][]{
                new int[]{w-1, h-1},
                new int[]{w, h-1},
                new int[]{w+1, h-1},
                new int[]{w-1, h},
                new int[]{w+1, h},
                new int[]{w-1, h+1},
                new int[]{w, h+1},
                new int[]{w+1, h+1},
        };

        return Arrays.stream(options).filter(o -> isValidField(o[0], o[1])).toArray(int[][]::new);
    }

    private boolean isMine(int w, int h) {
        return Arrays.stream(mines).anyMatch(m -> m[0] == w && m[1] == h);
    }

    private void uncoverField(int w, int h) {
        int minesAround = minesAround(w, h);
        if(minesAround != 0) {
            table[w][h] = "" + minesAround;
        } else {
            table[w][h] = " ";
            int[][] options = Arrays.stream(getOptions(w, h)).filter(o -> table[o[0]][o[1]].equals("+")).toArray(int[][]::new);
            Arrays.stream(options).forEach(o -> uncoverField(o[0], o[1]));
        }
    }
}
