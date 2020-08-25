package com.finder.mine.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

@Getter
public class Table {

  private static final int ASCII = 48;

  private static Table TABLE;

  private final int width;
  private final int height;
  private final char[][] fields;
  private final int[][] mines;

  private Table(int width, int height) {
    this.width = width;
    this.height = height;
    fields = new char[width][height];
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

  public void setFieldToEmpty(int width, int height) {
    fields[width][height] = ' ';
  }

  public void setFieldToNumberOfMines(int width, int height, int numberOfMines) {
    fields[width][height] = (char) (ASCII + numberOfMines);
  }

  public void setFieldToMine(int width, int height) {
    fields[width][height] = 'M';
  }

  private void fillTable() {
    for(int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        fields[i][j] = '+';
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
