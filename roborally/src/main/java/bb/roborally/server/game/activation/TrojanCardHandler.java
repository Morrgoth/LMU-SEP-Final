package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;

public class TrojanCardHandler {

    Server server;
    Game game;
    User user;
    private  int register;

    public TrojanCardHandler(Server server, Game game, User user,int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }

    public void handle (){
        for (int i = 0; i<2; i++ ){
            user.getProgrammingDeck().getDiscardPile().add(game.getSpamDeck().getSpamDeck().remove(0));
        }
        PlayingCard playedCardisTrojan = user.getProgram().getCardInRegister(register);
        game.getTrojanDeck().getTrojanDeck().add(playedCardisTrojan);
        user.getProgram().resetOneRegister(register);


    }
}
