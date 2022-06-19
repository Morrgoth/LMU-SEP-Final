package bb.roborally.data.messages.game_events;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Player;
import bb.roborally.game.PlayerInventory;
import bb.roborally.game.Robot;

import static bb.roborally.game.cards.DamageCard.CardType.SPAM_CARD;

public class AddCard implements Message{
	private int clientID;

	public AddCard(){

	}
	public AddCard(Robot robot){
		this.clientID = robot.getClientID();
		PlayerInventory playerInventory = new PlayerInventory();

		if(robot.getClientID() == playerInventory.getClientID()){
			this.clientID = robot.getClientID();
			PlayerInventory.addCard(SPAM_CARD); /// in PlayerInventory addCard(DamageCard damageCard) check - lässt sich nicht adden
		}
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.ADD_CARD, this);
	}
}
