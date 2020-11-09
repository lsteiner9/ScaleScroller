package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.PlayerDao;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import io.reactivex.Completable;
import java.util.List;

public class PlayerRepository {

  private final Context context;
  private final PlayerDao playerDao;

  public PlayerRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    playerDao = database.getPlayerDao();
  }

  public Completable save(Player player) {
    return (player.getId() == 0)
        ? playerDao.insert(player)
            .doAfterSuccess(player::setId)
            .ignoreElement()
        : playerDao.update(player)
            .ignoreElement();
  }

  public Completable delete(Player player) {
    return (player.getId() == 0)
        ? Completable.complete()
        : playerDao.delete(player)
            .ignoreElement();
  }

  public LiveData<Player> get(long id) {
    return playerDao.select(id);
  }

  public LiveData<List<Player>> getAll() {
    return playerDao.selectAll();
  }

  public LiveData<Player> getByOauth(long oauth) {
    return playerDao.selectWithOauth(oauth);
  }

}
