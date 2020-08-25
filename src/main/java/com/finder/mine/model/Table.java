package com.finder.mine.model;

import java.util.Arrays;
import java.util.Random;

public class Table {

  private static Table TABLE;

  private int width;
  private int height;
  private char[][] table;
  private int[][] mines;

  private Table(int width, int height) {
    this.width = width;
    this.height = height;
    table = new char[width][height];
    mines = new int[10][2];
    fillTable();
    generateMines();
  }

  public static void init(int width, int height) {
    TABLE = new Table(width, height);
  }

  public static Table getInstance() throws IllegalAccessException {
    if(TABLE == null) {
      throw new IllegalAccessException("Table is not initialized, call init first");
    }
    return TABLE;
  }

  private void fillTable() {
    for(int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        table[i][j] = '+';
      }
    }
  }

  private void generateMines() {
    Random rand = new Random();
    int counter = 0;
    while(counter < 10) {
      int w = rand.nextInt(width);
      int h = rand.nextInt(height);
      boolean contains = Arrays.stream(mines).anyMatch(m -> m[0] == w && m[1] == h);
      if(!contains) {
        mines[counter++] = new int[]{w,h};
      }
    }
  }
}
