package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.scalescroller.model.dao.PlayerDao;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class PlayerRepository {

  private final Context context;
  private final PlayerDao playerDao;
  private final GoogleSignInService signInService;

  public PlayerRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    playerDao = database.getPlayerDao();
    signInService = GoogleSignInService.getInstance();
  }

  @SuppressWarnings("ConstantConditions")
  public Single<Player> createPlayer(@NonNull GoogleSignInAccount account) {
    return Single.fromCallable(() -> {
      Player player = new Player();
      player.setUsername(account.getDisplayName());
      player.setHighestLearnLevel(0);
      player.setOauthKey(account.getId());
      return player;
    })
        .flatMap((player) ->
            playerDao.insert(player)
                .map((id) -> {
                  if (id > 0) {
                    player.setId(id);
                  }
                  return player;
                })
        )
        .subscribeOn(Schedulers.io());
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

  public LiveData<Player> getByOauth(String oauth) {
    return playerDao.selectWithOauth(oauth);
  }

}
