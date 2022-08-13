# Blinde Bonbons Hp

The main project of the LMU internship - Softwareentwicklungspraktikum.
The task was to digitalize the board game "Robo Rally" with its board (elements), the robots, a server-client connection, and a UI.
Parts of the build up are taken from the first part of the SEP Love Letter.

Implemented mainly using Java 18
UI using JavaFX
JavaDocs included.

The uploaded version is in the state of final upload and not perfect.
The client-server communication works fine, while the internal game logic isn't fully implemented yet.
Upgrade cards are lacking and the movements of the game tiles and robots themselves aren't fluid.

Yet a good start to learn Java, building and structuring a project and git (using GitLab).

## Running the Jar Files

Client: `java -jar roborally.jar`


Server: `java -jar server.jar` or `java -jar server.jar MIN_PLAYER` (e.g. `java -jar server.jar 3` the minimum player count is 2 by default)


AI: `java -jar ai.jar` or `java -jar ai.jar IP PORT` (e.g. `java -jar server.jar sep21.dbs.ifi.lmu.de 52018` the ai uses localhost:6868 by default)

In case of running the Jar Files in terminal does not work, you can run it through IntelliJ. 
Should there also be a problem try adding following line into VM option. You can access this by navigating through Run -> edit configurations and then adding --module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml to VM Option. 

About

RoboRally is a board game where each player plays the role of a robot. The goal of the game is to move the robot from its starting position to the goal position as fast as possible. The first player to reach the goal position wins the game. This project creates a game loosely based on this board game supporting robots, boards, walls, batteries, repair kits, surprise boxes, laser shooting, a.o. Furthermore, robots can execute small programs (written in a customly defined language).

Design


## Links

Sourcecode:
https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2022/blinde_bonbons_hp/-/tree/main/roborally/src/main
Issues:
https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2022/blinde_bonbons_hp/-/issues/?sort=created_date&state=all&first_page_size=20
Tutorprotokolle:
https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2022/blinde_bonbons_hp/-/wikis/home

Authors @bemantec @Morrgoth @baizeynab @MuqiuW @verhec @tolgaenginn
