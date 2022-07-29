# Blinde Bonbons Hp

## Running the Jar Files

Client: `java -jar roborally.jar`


Server: `java -jar server.jar` or `java -jar server.jar MIN_PLAYER` (e.g. `java -jar server.jar 3` the minimum player count is 2 by default)


AI: `java -jar ai.jar` or `java -jar ai.jar IP PORT` (e.g. `java -jar server.jar sep21.dbs.ifi.lmu.de 52018` the ai uses localhost:6868 by default)

In case of running the Jar Files in terminal does not work, you can run it through IntelliJ. 
Should there also be a problem try adding following line into VM option. You can access this by navigating through Run -> edit configurations and then adding --module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml to VM Option. 

