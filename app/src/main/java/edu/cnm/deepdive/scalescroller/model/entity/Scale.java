package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
    indices = {
        @Index(value = {"tonic", "mode"}, unique = true)
    }
)
public class Scale {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "scale_id")
  private long id;

  // TODO uncomment and add type converters
  @NonNull
//  @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
  private Note tonic;

  @NonNull
//  @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
  private Mode mode;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Note getTonic() {
    return tonic;
  }

  public void setTonic(@NonNull Note tonic) {
    this.tonic = tonic;
  }

  @NonNull
  public Mode getMode() {
    return mode;
  }

  public void setMode(@NonNull Mode mode) {
    this.mode = mode;
  }

}
