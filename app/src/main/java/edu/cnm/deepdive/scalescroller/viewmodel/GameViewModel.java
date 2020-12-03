package edu.cnm.deepdive.scalescroller.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.controller.GameFragment.GameMode;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.LearnLevelAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

//TODO work on game logic

/**
 * Serves as the ViewModel for the GameFragment.
 */
public class GameViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<ChallengeAttempt> challengeAttempt;
  private final MutableLiveData<ScaleChallengeAttempt> scaleChallengeAttempt;
  private final MutableLiveData<LearnLevelAttempt> learnLevelAttempt;
  private final MutableLiveData<Boolean> levelWon;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Integer> hearts;
  private final MutableLiveData<Integer> score;
  private final MutableLiveData<Boolean> resume;
  private final MutableLiveData<Boolean> paused;

  private final CompositeDisposable pending;
  private final Random rng;
  private final PlayerRepository playerRepository;
  private final LearnLevelAttemptRepository learnLevelAttemptRepository;
  private final ChallengeAttemptRepository challengeAttemptRepository;
  private final ScaleRepository scaleRepository;
  private final ScaleChallengeAttemptRepository scaleChallengeAttemptRepository;
  private final GoogleSignInService signInService;

  private Level level;
  private Scale scale;
  private Note tonic;
  private Mode mode;
  private Note[] notes;
  private GameMode gameMode;

  /**
   * The constructor initializes repositories, services, and other elements needed by the
   * ViewModel.
   *
   * @param application The ScaleScroller application.
   */
  public GameViewModel(@NonNull Application application) {
    super(application);
    playerRepository = new PlayerRepository(application);
    learnLevelAttemptRepository = new LearnLevelAttemptRepository(application);
    challengeAttemptRepository = new ChallengeAttemptRepository(application);
    scaleRepository = new ScaleRepository(application);
    scaleChallengeAttemptRepository = new ScaleChallengeAttemptRepository(application);
    signInService = GoogleSignInService.getInstance();

    challengeAttempt = new MutableLiveData<>();
    scaleChallengeAttempt = new MutableLiveData<>();
    learnLevelAttempt = new MutableLiveData<>();
    levelWon = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    hearts = new MutableLiveData<>();
    score = new MutableLiveData<>();
    resume = new MutableLiveData<>(true);
    paused = new MutableLiveData<>(false);
    rng = new SecureRandom();
    pending = new CompositeDisposable();
    do {
      tonic = getRandomTonic(rng);
      mode = getRandomMode(rng);
    } while (getScale(tonic, mode) == null);
    notes = calculateNotes();
    level = new Level(tonic, mode);
  }

  /**
   * Returns LiveData of a scale, given tonic and mode.
   *
   * @param tonic The scale's tonic.
   * @param mode The scale's mode.
   * @return {@code LiveData} of the {@code Scale}.
   */
  public LiveData<Scale> getScale(Note tonic, Mode mode) {
    return scaleRepository.getByScaleName(mode, tonic);
  }

  /**
   * Returns LiveData of a Boolean indicating whether the player has won the level.
   *
   * @return {@code LiveData} of a {@code Boolean}.
   */
  public MutableLiveData<Boolean> getLevelWon() {
    return levelWon;
  }

  /**
   * Returns LiveData of a list of scales, ordered by difficulty.
   *
   * @return {@code LiveData} of a {@code List} of {@link Scale}
   */
  public LiveData<List<Scale>> getScales() {
    return scaleRepository.getAllOrdered();
  }

  /**
   * Returns LiveData of a Boolean value indicating dialog directions.
   *
   * @return {@code LiveData} of a {@code Boolean}.
   */
  public LiveData<Boolean> getResume() {
    return resume;
  }

  /**
   * Sets the resume value indicating dialog directions.
   *
   * @param resume A boolean value.
   */
  public void setResume(boolean resume) {
    this.resume.setValue(resume);
  }

  /**
   * Returns LiveData of an Integer value that holds the player's hearts.
   *
   * @return {@code LiveData} of a {@code Integer}.
   */
  public LiveData<Integer> getHearts() {
    return hearts;
  }

  /**
   * Returns LiveData of an Integer value that holds the player's score.
   *
   * @return {@code LiveData} of a {@code Integer}.
   */
  public LiveData<Integer> getScore() {
    return score;
  }

  /**
   * Sets the GameMode of the ViewModel to the enumerated types LEARN or CHALLENGE.
   *
   * @param gameMode A enumerated {@link GameMode} type.
   */
  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }

  /**
   * Returns the tonic of the scale.
   *
   * @return The tonic of the scale.
   */
  public Note getTonic() {
    return tonic;
  }

  /**
   * Sets the tonic of the scale in the ViewModel to one of the enumerated types.
   *
   * @param tonic A enumerated {@link Note} type.
   */
  public void setTonic(Note tonic) {
    this.tonic = tonic;
  }

  /**
   * Returns the mode of the scale.
   *
   * @return The mode of the scale.
   */
  public Mode getMode() {
    return mode;
  }

  /**
   * Sets the mode of the scale in the ViewModel to one of the enumerated types.
   *
   * @param mode A enumerated {@link Mode} type.
   */
  public void setMode(Mode mode) {
    this.mode = mode;
  }

  /**
   * Returns the {@link Level} object.
   *
   * @return The level.
   */
  public Level getLevel() {
    return level;
  }

  /**
   * Sets the {@code Level} object.
   *
   * @param level The leve.
   */
  public void setLevel(Level level) {
    this.level = level;
  }

  /**
   * Starts a single level.
   */
  public void startLevel() {
    //start the level, do stuff
  }

  /**
   * Sets a boolean value indicating whether the game is paused or not.
   *
   * @param paused A boolean that indicates whether the game is paused or not.
   */
  public void setPaused(boolean paused) {
    this.paused.setValue(paused);
  }


  /**
   * Returns a String representation of the correct notes of the scale.
   *
   * @return A String of correctNotes.
   */
  public String getNotesString() {
    StringBuilder builder = new StringBuilder();
    for (Note note : notes) {
      builder.append(note).append(", ");
    }
    builder.append(tonic);
    //TODO take this out once game is working
    builder.append(getApplication().getString(R.string.placeholder_note_names));
    return builder.toString();
  }

  /**
   * Returns an array of correct notes.
   *
   * @return An array of correct notes.
   */
  public Note[] getNotes() {
    return notes;
  }

  /**
   * Saves a challenge attempt to the database.
   *
   * @param challengeAttempt The attempt to be saved.
   */
  public void save(ChallengeAttempt challengeAttempt) {
    challengeAttemptRepository.save(challengeAttempt);
  }

  /**
   * Saves a learn attempt to the database.
   *
   * @param learnLevelAttempt The attempt to be saved.
   */
  public void save(LearnLevelAttempt learnLevelAttempt) {
    learnLevelAttemptRepository.save(learnLevelAttempt);
  }

  /**
   * Returns LiveData of the current player.
   *
   * @return {@code LiveData} of the current {@link Player}.
   */
  public LiveData<Player> getPlayer() {
    return playerRepository.getByOauth(signInService.getAccount().getId());
  }

  private Note[] calculateNotes() {
    Map<Integer, Note[]> letterNameMap = Note.getNoteMap();
    int tonicNumber = tonic.getNumber();
    int[] steps = mode.getSteps();
    Set<Integer> noteNumbers = new HashSet<>();
    for (int step : steps) {
      noteNumbers.add((step + tonicNumber) % 12);
    }
    Set<Note> notes = new HashSet<>();
    notes.add(tonic);
    for (Note note : Note.values()) {
      int number = note.getNumber();
      if (noteNumbers.contains(number)) {
        Note[] possibilities = letterNameMap.get(number);
        String tonicString = tonic.toString();
        String possibleString = possibilities[0].toString();
        if ((tonicString.contains("\u266f") && possibleString.contains("\u266d"))
            || (tonicString.contains("\u266d") && possibleString.contains("\u266f"))) {
          notes.add(possibilities[1]);
//          TODO more checks here to narrow down the note possibilities
//        } else if (!tonicString.contains("\u266f") && !tonicString.contains("\u266d")
//            && (possibleString.contains(??))) {

        } else {
          notes.add(possibilities[0]);
        }
      }
    }
    return notes.toArray(new Note[notes.size()]);
  }

  private Mode getRandomMode(Random rng) {
    Mode[] modes = Mode.values();
    return modes[rng.nextInt(modes.length)];
  }

  private Note getRandomTonic(Random rng) {
    Note[] notes = Note.tonics();
    return notes[rng.nextInt(notes.length)];
  }

}
