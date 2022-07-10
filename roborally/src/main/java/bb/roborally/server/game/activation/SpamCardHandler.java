package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;

public class SpamCardHandler {
    Server server;
    Game game;
    User user;
    private int register;

    public SpamCardHandler(Server server, Game game, User user, int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }
    public void handle(){
        PlayingCard playedCardisSpam = user.getProgram().getCardInRegister(register);
        playedCardisSpam.setMarked(true);
        for (int i = 0; i< user.getProgram().getProgram().length; i++){
            if (user.getProgram().getProgram()[i] == playedCardisSpam){
                user.getProgram().resetOneRegister(playedCardisSpam);
            }
        }
        game.getSpamDeck().getSpamDeck().add(playedCardisSpam);
        playedCardisSpam.setMarked(false);


    }


}
