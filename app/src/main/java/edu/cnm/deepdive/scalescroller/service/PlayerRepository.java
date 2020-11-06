package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import edu.cnm.deepdive.scalescroller.model.dao.PlayerDao;
import io.reactivex.Completable;

public class PlayerRepository {

  private final Context context;
  private final PlayerDao playerDao;

  public PlayerRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    playerDao = database.getPlayerDao();
  }
}
