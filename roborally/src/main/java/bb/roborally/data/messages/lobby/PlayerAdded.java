package bb.roborally.data.messages.lobby;

public class PlayerAdded {
}


{

		Setzen des Spielernamens und der Figur
		Nach dem erfolgreichen Verbindungsaufbau kann ein Spieler seinen Namen und seine Spielfigur
		auswählen. Die Spielfigur soll dabei einzigartig sein, der Spielernamen darf mehrfach vergeben
		werden.

		"messageType": "PlayerAdded",
		"messageBody": {
		"clientID": 42,
		"name": "Nr. 5",
		"figure": 5
		}
		}