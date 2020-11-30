package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ScaleDao;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import io.reactivex.Completable;
import java.util.List;

/**
 * {@code ScaleRepository} contains methods that provide a layer of abstraction above the {@link
 * ScaleDao}, and allows for creation, reading, updating, and deleting of attempts.
 */
public class ScaleRepository {

  private final Context context;
  private final ScaleDao scaleDao;

  /**
   * The constructor initializes the context, the database, and the dao.
   *
   * @param context The application context.
   */
  public ScaleRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    scaleDao = database.getScaleDao();
  }

  /**
   * Creates or updates a scale record in the database.
   *
   * @param scale The {@code Scale}.
   * @return A {@code Completable} indicating the success or failure of the creation/update.
   */
  public Completable save(Scale scale) {
    return (scale.getId() == 0)
        ? scaleDao.insert(scale)
        .doAfterSuccess(scale::setId)
        .ignoreElement()
        : scaleDao.update(scale)
            .ignoreElement();
  }

  /**
   * Deletes a scale record in the database.
   *
   * @param scale The {@code Scale}.
   * @return A {@code Completable} indicating the success or failure of the deletion.
   */
  public Completable delete(Scale scale) {
    return (scale.getId() == 0)
        ? Completable.complete()
        : scaleDao.delete(scale)
            .ignoreElement();
  }

  /**
   * Returns LiveData of a scale, given its id.
   *
   * @param id The id of the scale.
   * @return {@code LiveData} of a {@code Scale}.
   */
  public LiveData<Scale> get(long id) {
    return scaleDao.select(id);
  }

  /**
   * Returns LiveData of all scales.
   *
   * @return {@code LiveData} of a {@code List} of {@code Scale}.
   */
  public LiveData<List<Scale>> getAll() {
    return scaleDao.selectAll();
  }

  /**
   * Returns LiveData of a scale, given its tonic and mode.
   *
   * @param mode  The mode of the scale.
   * @param tonic The tonic of the scale.
   * @return {@code LiveData} of a {@code Scale}.
   */
  public LiveData<Scale> getByScaleName(Mode mode, Note tonic) {
    return scaleDao.selectWithScaleName(mode, tonic);
  }

  /**
   * Returns LiveData of all scales with the specified tonic.
   *
   * @param tonic The tonic of the scale.
   * @return {@code LiveData} of a {@code List} of {@code Scale}.
   */
  public LiveData<List<Scale>> getByTonic(Note tonic) {
    return scaleDao.selectWithTonic(tonic);
  }

  /**
   * Returns LiveData of all scales with the specified mode.
   *
   * @param mode The mode of the scale.
   * @return {@code LiveData} of a {@code List} of {@code Scale}.
   */
  public LiveData<List<Scale>> getByMode(Mode mode) {
    return scaleDao.selectWithMode(mode);
  }

}
