package com.finder.mine.cotroller;
import com.finder.mine.model.Table;
import com.finder.mine.service.MineFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class MineFinderController {

  private MineFinderService mineFinderService;

  @Autowired
  public MineFinderController(MineFinderService mineFinderService) {
    this.mineFinderService = mineFinderService;
  }

  @GetMapping("/table")
  public Table getTable() {
    return mineFinderService.getTable();
  }
}
