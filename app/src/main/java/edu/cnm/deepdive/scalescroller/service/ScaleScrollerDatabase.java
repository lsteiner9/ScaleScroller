package edu.cnm.deepdive.scalescroller.service;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.scalescroller.model.dao.ChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.dao.LearnLevelAttemptDao;
import edu.cnm.deepdive.scalescroller.model.dao.PlayerDao;
import edu.cnm.deepdive.scalescroller.model.dao.ScaleChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.dao.ScaleDao;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import edu.cnm.deepdive.scalescroller.service.ScaleScrollerDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

/**
 * The ScaleScroller Database provides abstract methods that return the DAOs for each entity. It
 * also provides methods to set the context and to return an instance of itself. It contains two public
 * nested classes: Callback and Converters.
 */
@Database(
    entities = {Player.class, LearnLevelAttempt.class, ChallengeAttempt.class,
        Scale.class, ScaleChallengeAttempt.class},
    version = 1,
    exportSchema = true)
@TypeConverters(value = {Converters.class})
public abstract class ScaleScrollerDatabase extends RoomDatabase {

  private static final String DB_NAME = "scale_scroller_db";
  private static Application context;

  /**
   * Sets the application context.
   * @param context The application context.
   */
  public static void setContext(Application context) {
    ScaleScrollerDatabase.context = context;
  }

  /**
   * Returns an instance of the singleton database.
   * @return An instance of {@code ScaleScrollerDatabase}
   */
  public static ScaleScrollerDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Returns the PlayerDao.
   * @return The PlayerDao.
   */
  public abstract PlayerDao getPlayerDao();

  /**
   * Returns the LearnLevelAttemptDao.
   * @return The LearnLevelAttemptDao.
   */
  public abstract LearnLevelAttemptDao getLearnLevelAttemptDao();

  /**
   * Returns the ChallengeAttemptDao.
   * @return The ChallengeAttemptDao.
   */
  public abstract ChallengeAttemptDao getChallengeAttemptDao();

  /**
   * Returns the ScaleDao.
   * @return The ScaleDao.
   */
  public abstract ScaleDao getScaleDao();

  /**
   * Returns the ScaleChallengeAttemptDao.
   * @return The ScaleChallengeAttemptDao.
   */
  public abstract ScaleChallengeAttemptDao getScaleChallengeAttemptDao();


  private static class InstanceHolder {

    private static final ScaleScrollerDatabase INSTANCE =
        Room.databaseBuilder(context, ScaleScrollerDatabase.class, DB_NAME)
            .addCallback(new Callback())
            .build();
  }

  /**
   * Overrides a method to populate the database with scales.
   */
  public static class Callback extends RoomDatabase.Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      ScaleDao scaleDao = ScaleScrollerDatabase.getInstance().getScaleDao();
      Collection<Scale> scales = new PriorityQueue<>(Comparator
          .comparingInt((scale) -> scale.getMode().getDifficulty().get(scale.getTonic())));
      for (Mode mode : Mode.values()) {
        for (Note note : mode.getDifficulty().keySet()) {
          Scale scale = new Scale();
          scale.setMode(mode);
          scale.setTonic(note);
          scales.add(scale);
        }
      }
      scaleDao.insert(scales)
          .subscribeOn(Schedulers.io())
          .subscribe();
    }
  }

  /**
   * Provides type converters to translate Java objects into data that can be stored in the SQLite database.
   */
  public static class Converters {

    /**
     * Converts a {@code Date} object into a Long.
     * @param value A {@code Date} object.
     * @return A Long.
     */
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    /**
     * Converts a Long into a {@code Date} object.
     * @param value A Long.
     * @return A {@code Date} object.
     */
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

    /**
     * Converts a {@link Note} object into an Integer.
     * @param note A {@code Note} object.
     * @return An Integer.
     */
    @TypeConverter
    public static Integer noteToInteger(Note note) {
      return (note != null) ? note.ordinal() : null;
    }

    /**
     * Converts an Integer into a {@link Note} object.
     * @param value An Integer.
     * @return A {@code Note} object.
     */
    @TypeConverter
    public static Note integerToNote(Integer value) {
      return (value != null) ? Note.values()[value] : null;
    }

    /**
     * Converts a {@link Mode} object into an Integer.
     * @param mode A {@code Mode} object.
     * @return An Integer.
     */
    @TypeConverter
    public static Integer modeToInteger(Mode mode) {
      return (mode != null) ? mode.ordinal() : null;
    }

    /**
     * Converts an Integer into a {@link Mode} object.
     * @param value An Integer.
     * @return A {@code Mode} object.
     */
    @TypeConverter
    public static Mode integerToMode(Integer value) {
      return (value != null) ? Mode.values()[value] : null;
    }

  }

}
