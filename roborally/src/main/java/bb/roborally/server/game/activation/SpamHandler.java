package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.protocol.gameplay.ReplaceCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;

import java.io.IOException;

public class SpamHandler {
    Server server;
    Game game;
    User user;
    private int register;

    public SpamHandler(Server server, Game game, User user, int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }
    public void handle() throws IOException {
        PlayingCard playedCardisSpam = user.getProgram().getCardInRegister(register);
        game.getSpamDeck().getSpamDeck().add(playedCardisSpam);
        user.getProgram().resetOneRegister(register);

        user.getProgram().add(user.getProgrammingDeck().draw(),register);
        if (user.getProgram().getCardInRegister(register).getName().equals("Move1")){
            ReplaceCard replaceCard = new ReplaceCard(register,"Move1", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            Move1Handler move1Handler = new Move1Handler(server,game, user);
            move1Handler.handle();
        } else if (user.getProgram().getCardInRegister(register).getName().equals("Move2")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Move2", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            Move2Handler move2Handler = new Move2Handler(server,game,user);
            move2Handler.handleAlt();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Move3")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Move3", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            Move3Handler move3Handler = new Move3Handler(server,game,user);
            move3Handler.handleAlt();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Again")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Again", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            AgainHandler againHandler = new AgainHandler(server,game,user,register);
            againHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("BackUp")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"BackUp", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            BackUpHandler backUpHandler = new BackUpHandler(server,game, user);
            backUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("PowerUp")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"PowerUp", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            PowerUpHandler powerUpHandler = new PowerUpHandler(server,game,user);
            powerUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnLeft")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"TurnLeft", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            TurnLeftHandler turnLeftHandler = new TurnLeftHandler(server,game,user);
            turnLeftHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnRight")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"TurnRight", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            TurnRightHandler turnRightHandler = new TurnRightHandler(server,game,user);
            turnRightHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("UTurn")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"UTurn", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            UTurnHandler uTurnHandler = new UTurnHandler(server,game,user);
            uTurnHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Spam")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Spam", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            SpamHandler spamHandler = new SpamHandler(server,game, user, register);
            spamHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TrojanHorse")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"TrojanHorse", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            TrojanHandler trojanHandler = new TrojanHandler(server, game, user, register);
            trojanHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Worm")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Worm", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            WormHandler wormHandler = new WormHandler(server,game,user,register);
            wormHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Virus")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Virus", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            VirusHandler virusCardHandler = new VirusHandler(server,game,user,register);
            virusCardHandler.handle();
        }

        PlayCard playCard = new PlayCard("Spam");
        //server.broadcastOnly(playCard, user.getClientID());

        CardPlayed cardPlayed = new CardPlayed(user.getClientID(), "Spam");
        server.broadcastExcept(cardPlayed, user.getClientID());
    }


}
