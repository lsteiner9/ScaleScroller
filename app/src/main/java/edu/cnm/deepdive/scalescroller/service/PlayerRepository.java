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

/**
 * {@code PlayerRepository} contains methods that provide a layer of abstraction above the {@link
 * PlayerDao}, and allows for creation, reading, updating, and deleting of players.
 */
public class PlayerRepository {

  private final Context context;
  private final PlayerDao playerDao;
  private final GoogleSignInService signInService;

  /**
   * The constructor initializes the context, the database, the dao, and the GoogleSignInService.
   *
   * @param context The application context.
   */
  public PlayerRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    playerDao = database.getPlayerDao();
    signInService = GoogleSignInService.getInstance();
  }

  /**
   * Creates a player record in the database given a Google Sign In account.
   *
   * @param account A GoogleSignInAccount that will be associated with the player.
   * @return A {@code Single} {@link Player} that has been created.
   */
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

  /**
   * Creates or updates a player record in the database.
   *
   * @param player The {@code Player} entity.
   * @return A {@code Completable} indicating the success or failure of the creation/update.
   */
  public Completable save(Player player) {
    return (player.getId() == 0)
        ? playerDao.insert(player)
        .doAfterSuccess(player::setId)
        .ignoreElement()
        : playerDao.update(player)
            .ignoreElement();
  }

  /**
   * Deletes a player record in the database.
   *
   * @param player The {@code Player} entity.
   * @return A {@code Completable} indicating the success or failure of the deletion.
   */
  public Completable delete(Player player) {
    return (player.getId() == 0)
        ? Completable.complete()
        : playerDao.delete(player)
            .ignoreElement();
  }

  /**
   * Returns LiveData on a player, given their id.
   *
   * @param id The player's id.
   * @return {@code LiveData} of a {@code Player}.
   */
  public LiveData<Player> get(long id) {
    return playerDao.select(id);
  }

  /**
   * Returns LiveData on all players.
   *
   * @return {@code LiveData} of a {@code List} of {@code Player}.
   */
  public LiveData<List<Player>> getAll() {
    return playerDao.selectAll();
  }

  /**
   * Returns LiveData on a player, given their OAuth key.
   *
   * @param oauth The player's OAuth Key.
   * @return {@code LiveData} of a {@code Player}.
   */
  public LiveData<Player> getByOauth(String oauth) {
    return playerDao.selectWithOauth(oauth);
  }

}
