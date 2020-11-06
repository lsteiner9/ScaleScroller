package edu.cnm.deepdive.scalescroller.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
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
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

@Database(
    entities = {Player.class, LearnLevelAttempt.class, ChallengeAttempt.class,
        Scale.class, ScaleChallengeAttempt.class},
    version = 1,
    exportSchema = true)
@TypeConverters(value = {Converters.class})
public abstract class ScaleScrollerDatabase extends RoomDatabase {

  private static final String DB_NAME = "scale_scroller_db";

  private static Application context;

  public static void setContext(Application context) {
    ScaleScrollerDatabase.context = context;
  }

  public static ScaleScrollerDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract PlayerDao getPlayerDao();

  public abstract LearnLevelAttemptDao getLearnLevelAttemptDao();

  public abstract ChallengeAttemptDao getChallengeAttemptDao();

  public abstract ScaleDao getScaleDao();

  public abstract ScaleChallengeAttemptDao getScaleChallengeAttemptDao();


  private static class InstanceHolder {

    private static final ScaleScrollerDatabase INSTANCE =
        Room.databaseBuilder(context, ScaleScrollerDatabase.class, DB_NAME)
            .build();
  }

  public static class Converters {
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

    @TypeConverter
    public static Integer noteToInteger(Note note) {
      return (note != null)? note.ordinal() : null;
    }

    @TypeConverter
    public static Note integerToNote(Integer value) {
      return (value != null)? Note.values()[value] : null;
    }

    @TypeConverter
    public static Integer modeToInteger(Mode mode) {
      return (mode != null)? mode.ordinal() : null;
    }

    @TypeConverter
    public static Mode integerToMode(Integer value) {
      return (value != null)? Mode.values()[value] : null;
    }

    @TypeConverter
    public static byte[] scaleToBytes(Scale scale) {
      byte[] bytes = null;
      if (scale != null) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(scale.getId());
        buffer.putInt(modeToInteger(scale.getMode()));
        buffer.putInt(noteToInteger(scale.getTonic()));
        bytes = buffer.array();
      }
      return bytes;
    }

    @TypeConverter
    public static Scale bytesToScale(byte[] bytes) {
      Scale scale = null;
      if (bytes != null) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        scale = new Scale();
        scale.setId(buffer.getLong());
        scale.setMode(integerToMode(buffer.getInt()));
        scale.setTonic(integerToNote(buffer.getInt()));
      }
      return scale;
    }
  }

}
