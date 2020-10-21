## Project Description

ScaleScroller is a game that allows the user to learn or practice their knowledge of musical scales. The user controls their character by moving up and down to collect coins. Each level uses a different musical scale, and coins are labeled with either correct musical note names found in that scale or with incorrect note names. Collecting correct coins adds points, but collecting an incorrect coin takes away one of three in-game lives.

Learn mode allows the user to start with the easiest scales and gradually teaches them all of the common musical scales. Challenge mode allows the user to test their knowledge: any scale can appear at any point, and the sidescroller progressively gets faster and faster as they go on.

* For each level, the app displays a scale name and its notes for several seconds.

* The sidescroller starts, and the user can control their character (represented as an eighth note) to move up and down.

	* The objective is to collect (run over) the coins labeled with correct notes, and avoid coins labeled with incorrect notes.

	* Correct notes add points, while incorrect notes take away lives. There are three lives total.

	* To beat the level, the user must get a certain amount of points/go for a certain amount of time without losing all three lives.

* Once the level has been beaten, the user is asked whether to play again or to go to the next level, with a new scale. 

* Learn mode is the standard mode. This mode starts with C Major and progresses around the circle of fifths with each level, gradually increasing difficulty. In learn mode, the user must beat the level in order to progress; otherwise, the current level must be repeated. This allows for practicing and learning each scale. 

* Challenge mode is more difficult, and works best when the user has already progressed through learn mode to learn most or all of their scales. In challenge mode, any scale can appear at any point. The speed of the sidescroller progressively gets faster as the user beats more scale levels. Once the game has ended, the score is added to a high score board, if applicable.

* Settings:

	* Audio settings: volume, sound effects/soundtrack.

	* Change speed of sidescroller (learn mode only).
	
	* Change set of allowed scales (challenge mode only).
	
	
* Persistent Data:
	
	* Settings

	* Learn mode level reached

	* Challenge mode high score board


## Intended Users 

* Someone who wants to learn the basics of music theory

    > I'm an adult who has never studied written music before, but I would like to learn. The learn mode of this app gradually teaches me scales in the form of a fun game, so I don't get bored with rote memorization.

	> As a middle-school student taking violin lessons, my teacher talks about key signatures and scales, but I hate that I always get them wrong when she asks me about them. This app helps teach me my scales in learn mode, so we can spend my lesson time playing violin!

* Someone who needs to practice their music theory knowledge

	> I'm a high-school student who knows most of my scales, but there are some that take me a long time to figure out. The challenge mode of this app helps me to make sure my knowledge of all scales is up to speed.

	> As a Theory 1 student at UNM, my professor expects me to be able to write out scales very quickly, with complete accuracy. The challenge mode of this app lets me practice multiple different scales and gives me immediate feedback on the wrong notes - and my grade has already started going up!


## Wireframe Diagram

[SoundScroller Wireframe](wireframe.md)


## Entity-Relationship Diagram

[SoundScroller ERD](erd.md)

   
## Cloud- or Device-Based Services or Data

* Device audio, accessed through MediaPlayer 

	* Click for [an overview](https://developer.android.com/guide/topics/media/mediaplayer) or [documentation.](https://developer.android.com/reference/android/media/MediaPlayer)

	* Audio played through MediaPlayer enhances the game with music and sound effects. For example, when an incorrect coin is picked up, a sound effect is part of the indication of the mistake. A different sound effect indicates a correct answer. A soundtrack for the game is a stretch goal, but if implemented, will also be played through MediaPlayer.

	* Audio is not critical to the functionality of the app, and can be turned off if desired. For example, there will be visual indications of correct/incorrect coins as well as sound, so those sound effects are not critical. 

* Google Sign-in 
	
	* Click for [documentation.](https://developers.google.com/identity/sign-in/android/start-integrating)

	* Sign-in will let the user associate their data with a username and account. This will allow multiple players to use the app on the same device. 
	
	* Sign-in should not be required to play the game or to save local data. However, depending on implementation, it is likely that sign-in may end up being required for all users in order to keep consistent representation of user data. If this is the case, it should not promt the user to log-in every time the app opens, only upon first use and after logging out. This should provide for a better user experience, as well as allowing the app to function if Sign-in is unavailable. 
