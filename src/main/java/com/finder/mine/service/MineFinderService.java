package com.finder.mine.service;

import com.finder.mine.model.Table;
import org.springframework.stereotype.Service;

@Service
public class MineFinderService {

  public Table startGame(int width, int height) throws IllegalAccessException {
    Table.init(width,height);
    return Table.getInstance();
  }

  public Table getTable() throws IllegalAccessException {
    return Table.getInstance();
  }

  public Table takeStep(int width, int height) {
  }
}
