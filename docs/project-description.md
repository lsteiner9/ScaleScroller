## Project Description

ScaleScroller is a game that allows the user to learn or practice their knowledge of musical scales. Each level uses a different musical scale and is populated with coins, labeled with either correct musical note names found in that scale or with incorrect note names. The user controls their character by moving up and down to collect coins. Collecting correct coins adds points, but collecting an incorrect coin takes away one of three in-game lives. There are two gamemodes: Learn mode allows the user to start with the easiest scales and gradually teaches them all common Western classical scales. Challenge mode allows the user to test their knowledge: any scale can appear at any point, and the sidescroller gets progressively faster and faster with each completed scale level.

My motivation for creating this app was to bring together my enjoyment of video games and my passion for teaching music. As a music theory teacher for undergraduate music majors, I found that scales were simultaneously one of the hardest and one of the most important things for students to master. Unfortunately, the best way to master scales is a lot of practice and drilling, which students usually either get bored with or don't have time for. This particular app was born out of ideas from a chapter in *Studies in Historical Improvisation*, edited by Massimiliano Guido. In the chapter, "Teaching Theory through Improvisation," theorist Peter Schubert discusses how to make theory classrooms provide the same sort of feedback as a mobile phone game. This immediate, painless feedback allows players of games to quickly learn how to succeed, and this type of feedback in the classroom can help students to learn more quickly and enjoyably. My app is an extension of that idea - if we can bring aspects of games into the theory classroom, then why not bring aspects of the theory classroom into a game? The app focuses on scales, which here only require knowledge of the alphabet to understand. This means that even non-musicians can start playing with the app, and with progress through the levels can learn one of the most foundational aspects of Western classical music. Players who already have knowledge of scales can continue learning or test their knowledge with random scales. This allows the app to have wide appeal to people interested in improving their musical knowledge.

### Key functional elements

* The user can sign in with GoogleSignIn, or continue as a guest.

* From the title screen, the user can choose either Learn mode or Challenge mode. They can also change settings or logout.

    * Selecting Learn mode takes the user to a scale select screen, allowing them to choose to play any of their unlocked scales for the level.
    
    * Selecting Challenge mode takes the user directly to gameplay, with a random scale.
    
    * Selecting settings takes the user to a settings screen, where they can choose to change audio settings, the speed of the sidescroller, or see high scores.

* Gameplay:

    * For each level, the app displays a scale name and its notes in a dialog box. Then the sidescroller starts, and the user can control their character to move up and down. The objective is to collect (run over) the coins labeled with correct notes, and avoid coins labeled with incorrect notes. Correct notes add points, while incorrect notes take away one of three total lives. To beat the level, the user must get a certain amount of points without losing all three lives.

    * Learn mode is the easier mode. Unlocked scales start with C Major and progresse around the circle of fifths with each level, gradually increasing difficulty (C Major, G Major, F Major, D Major, Bb Major, and so on). In learn mode, the user must beat the level in order unlock more difficult scales. If the player does not beat the level, they can repeat the current level or try previously unlocked levels. This allows for practicing and learning each scale. 

    * Challenge mode is harder: the user is expected to already know their scales, either from progressing through learn mode or through external instruction. Challenge mode can start with any scale, and once each level is beaten, a new random scale appears. The speed of the sidescroller progressively gets faster as the user beats more scale levels. Once the game has ended, the score is recorded and added to a high score board, if applicable.
	
[*Return to previous page*](index.md)
