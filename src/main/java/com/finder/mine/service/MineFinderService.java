package com.finder.mine.service;

import com.finder.mine.model.Table;
import com.finder.mine.util.MineFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MineFinderService {

  private MineFinderUtil mineFinderUtil;

  @Autowired
  public MineFinderService(MineFinderUtil mineFinderUtil) {
    this.mineFinderUtil = mineFinderUtil;
  }

  public Table startGame(int width, int height) {
    return mineFinderUtil.startGame(width, height);
  }

  public Table getTable() {
    return mineFinderUtil.getTable();
  }

  public Table takeStep(int width, int height) {
    if(!mineFinderUtil.isValidField(width, height)) {
      throw new IllegalArgumentException("Your step is out of bounds");
    }
    boolean isMine = mineFinderUtil.isMine(width, height);
    if(isMine) {
      mineFinderUtil.setFieldToMine(width, height);
    } else {
      mineFinderUtil.uncoverField(width, height);
    }

    return mineFinderUtil.getTable();
  }
}
