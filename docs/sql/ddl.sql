CREATE TABLE IF NOT EXISTS `Player`
(
    `player_id`           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth_key`           TEXT,
    `username`            TEXT                              NOT NULL,
    `highest_learn_level` INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Player_oauth_key` ON `Player` (`oauth_key`);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Player_username` ON `Player` (`username`);

CREATE TABLE IF NOT EXISTS `LearnLevelAttempt`
(
    `learn_level_attempt_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `player_id`              INTEGER                           NOT NULL,
    `scale_id`               INTEGER                           NOT NULL,
    `difficulty`             INTEGER                           NOT NULL,
    `timestamp`              INTEGER                           NOT NULL,
    `correct_coins`          INTEGER                           NOT NULL,
    `incorrect_coins`        INTEGER                           NOT NULL,
    FOREIGN KEY (`player_id`) REFERENCES `Player` (`player_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`scale_id`) REFERENCES `Scale` (`scale_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_LearnLevelAttempt_timestamp` ON `LearnLevelAttempt` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_LearnLevelAttempt_player_id` ON `LearnLevelAttempt` (`player_id`);

CREATE INDEX IF NOT EXISTS `index_LearnLevelAttempt_scale_id` ON `LearnLevelAttempt` (`scale_id`);

CREATE INDEX IF NOT EXISTS `index_LearnLevelAttempt_difficulty` ON `LearnLevelAttempt` (`difficulty`);

CREATE TABLE IF NOT EXISTS `ChallengeAttempt`
(
    `challenge_attempt_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `player_id`            INTEGER                           NOT NULL,
    `total_score`          INTEGER                           NOT NULL,
    `timestamp`            INTEGER                           NOT NULL,
    `correct_coins`        INTEGER                           NOT NULL,
    `incorrect_coins`      INTEGER                           NOT NULL,
    `last_scale_id`        INTEGER                           NOT NULL,
    FOREIGN KEY (`player_id`) REFERENCES `Player` (`player_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_ChallengeAttempt_timestamp` ON `ChallengeAttempt` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_ChallengeAttempt_player_id` ON `ChallengeAttempt` (`player_id`);

CREATE INDEX IF NOT EXISTS `index_ChallengeAttempt_total_score` ON `ChallengeAttempt` (`total_score`);

CREATE INDEX IF NOT EXISTS `index_ChallengeAttempt_last_scale_id` ON `ChallengeAttempt` (`last_scale_id`);

CREATE TABLE IF NOT EXISTS `Scale`
(
    `scale_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `tonic`    INTEGER                           NOT NULL,
    `mode`     INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Scale_tonic_mode` ON `Scale` (`tonic`, `mode`);

CREATE TABLE IF NOT EXISTS `ScaleChallengeAttempt`
(
    `scale_challenge_attempt_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `challenge_attempt_id`       INTEGER                           NOT NULL,
    `scale_id`                   INTEGER                           NOT NULL,
    `timestamp`                  INTEGER                           NOT NULL,
    FOREIGN KEY (`scale_id`) REFERENCES `Scale` (`scale_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`challenge_attempt_id`) REFERENCES `ChallengeAttempt` (`challenge_attempt_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_ScaleChallengeAttempt_challenge_attempt_id_scale_id` ON `ScaleChallengeAttempt` (`challenge_attempt_id`, `scale_id`);

CREATE INDEX IF NOT EXISTS `index_ScaleChallengeAttempt_challenge_attempt_id` ON `ScaleChallengeAttempt` (`challenge_attempt_id`);

CREATE INDEX IF NOT EXISTS `index_ScaleChallengeAttempt_scale_id` ON `ScaleChallengeAttempt` (`scale_id`);

CREATE INDEX IF NOT EXISTS `index_ScaleChallengeAttempt_timestamp` ON `ScaleChallengeAttempt` (`timestamp`);