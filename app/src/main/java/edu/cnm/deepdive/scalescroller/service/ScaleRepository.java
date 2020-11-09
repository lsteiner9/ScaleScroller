package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ScaleDao;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import io.reactivex.Completable;
import java.util.List;

public class ScaleRepository {

  private final Context context;
  private final ScaleDao scaleDao;

  public ScaleRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    scaleDao = database.getScaleDao();
  }

  public Completable save(Scale scale) {
    return (scale.getId() == 0)
        ? scaleDao.insert(scale)
        .doAfterSuccess(scale::setId)
        .ignoreElement()
        : scaleDao.update(scale)
            .ignoreElement();
  }

  public Completable delete(Scale scale) {
    return (scale.getId() == 0)
        ? Completable.complete()
        : scaleDao.delete(scale)
            .ignoreElement();
  }

  public LiveData<Scale> get(long id) {
    return scaleDao.select(id);
  }

  public LiveData<List<Scale>> getAll() {
    return scaleDao.selectAll();
  }

  public LiveData<Scale> getByScaleName(Mode mode, Note note) {
    return scaleDao.selectWithScaleName(mode, note);
  }


}
