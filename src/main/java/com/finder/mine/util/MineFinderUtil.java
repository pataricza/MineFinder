package com.finder.mine.util;

import com.finder.mine.model.Table;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MineFinderUtil {

    public Table getTable() {
        Table table = null;
        try {
            table = Table.getInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return table;
    }

    public Table startGame(int width, int height) {
        Table.init(width, height);
        return getTable();
    }

    public void uncoverField(int width, int height) {
        Table table = getTable();
        uncoverField(width, height, table);
    }

    public void uncoverField(int width, int height, Table table) {
        int minesAround = minesAround(width, height);
        if(minesAround != 0) {
            table.setFieldToNumberOfMines(width, height, minesAround);
        } else {
            table.setFieldToEmpty(width, height);
            char[][] fields = table.getFields();
            int[][] options = Arrays.stream(getOptions(width, height)).filter(o -> fields[o[0]][o[1]] == '+').toArray(int[][]::new);
            Arrays.stream(options).forEach(o -> uncoverField(o[0], o[1]));
        }
    }

    public int minesAround(int width, int height) {
        int[][] options = getOptions(width, height);
        return (int) Arrays.stream(options).filter(o -> isMine(o[0], o[1])).count();
    }

    public int[][] getOptions(int width, int height) {
        int[][] options = new int[][]{
                new int[]{width-1, height-1},
                new int[]{width, height-1},
                new int[]{width+1, height-1},
                new int[]{width-1, height},
                new int[]{width+1, height},
                new int[]{width-1, height+1},
                new int[]{width, height+1},
                new int[]{width+1, height+1},
        };

        return Arrays.stream(options).filter(o -> isValidField(o[0], o[1])).toArray(int[][]::new);
    }

    public boolean isMine(int width, int height) {
        Table table = getTable();
        int[][] mines = table.getMines();
        return Arrays.stream(mines).anyMatch(m -> m[0] == width && m[1] == height);
    }

    public boolean isValidField(int width, int height) {
        Table table = getTable();
        int tableWidth = table.getWidth();
        int tableHeight = table.getHeight();
        return width >= 0 && width < tableWidth && height >= 0 && height < tableHeight;
    }

    public void setFieldToMine(int width, int height) {
        Table table = getTable();
        table.setFieldToMine(width, height);
    }
}
