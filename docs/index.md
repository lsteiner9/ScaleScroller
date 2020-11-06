## Project Description

ScaleScroller is a game that allows the user to learn or practice their knowledge of musical scales. The user controls their character by moving up and down to collect coins. Each level uses a different musical scale, and coins are labeled with either correct musical note names found in that scale or with incorrect note names. Collecting correct coins adds points, but collecting an incorrect coin takes away one of three in-game lives.

Learn mode allows the user to start with the easiest scales and gradually teaches them all of the common musical scales. Challenge mode allows the user to test their knowledge: any scale can appear at any point, and the sidescroller progressively gets faster and faster with each completed scale level.

[Click here for detailed functionality.](functionality.md)

## Intended Users 

* Someone who wants to learn the basics of music theory

    > I'm an adult who has never studied written music before, but I would like to learn. The learn mode of this app gradually teaches me scales in the form of a fun game, so I don't get bored with rote memorization.

    > [Frank Bower](persona/persona1.md)

	> As a 9-year-old taking piano lessons, my teacher quizzes me about key signatures and scales, but I always get them wrong and we spend a long time talking about them in each lesson. It's boringgggg. This app helps teach me my scales in learn mode, so we can spend more of my lesson time playing piano!

    > [Camilla Bower](persona/persona2.md)

* Someone who needs to practice their music theory knowledge

	> I'm a high-school student who knows most of my scales, but there are some that take me a long time to figure out. The challenge mode of this app helps me to make sure my knowledge of all scales is up to speed.

    > [Catey Rose Henderson](persona/persona3.md)

	> As a Theory 1 student at UNM, my professor expects me to be able to write out scales very quickly, with complete accuracy. The challenge mode of this app lets me practice multiple different scales and gives me immediate feedback on the wrong notes - and my grade has already started going up!

    > [David Trujillo](persona/persona4.md)

## Wireframe Diagram

[ScaleScroller Wireframe](wireframe.md)


## [Data Model Implementation](datamodelimplementation.md)

   
## Cloud- or Device-Based Services or Data

* Device audio, accessed through MediaPlayer 

	* Click for [an overview](https://developer.android.com/guide/topics/media/mediaplayer) or [documentation.](https://developer.android.com/reference/android/media/MediaPlayer)

	* Audio played through MediaPlayer enhances the game with music and sound effects. For example, when an incorrect coin is picked up, a sound effect is part of the indication of the mistake. A different sound effect indicates a correct answer. A soundtrack for the game is a stretch goal, but if implemented, will also be played through MediaPlayer.

	* Audio is not critical to the functionality of the app, and can be turned off if desired. For example, there will be visual indications of correct/incorrect coins as well as sound, so those sound effects are not critical. 

* Google Sign-in 
	
	* Click for [documentation.](https://developers.google.com/identity/sign-in/android/start-integrating)

	* Sign-in will let the user associate their data with a username and account. This will allow multiple players to use the app on the same device. 
	
	* Sign-in is not required. If SignIn is unavailable, the app will still work, and user data will still be recorded for one player per device.
