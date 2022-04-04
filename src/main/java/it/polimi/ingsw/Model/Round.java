package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Assistant;
import it.polimi.ingsw.Model.Game;

import java.util.concurrent.ThreadLocalRandom;

public class Round{
    private int currentPlayer;

    Round() {

    }

    public class PianificationPhase{

        private Game game;
        private int AlreadyPlayedAssistant[];
        private Assistant[] playedAssistants;

        public PianificationPhase(Game game){
            this.game=game;
            currentPlayer=-1;
        }

        private void calculateFirstPlayer(){
            currentPlayer = ThreadLocalRandom.current().nextInt(0, game.getNumberOfPlayers());
        }

        private void calculateNextPlayer(){
            if(currentPlayer+1< game.getNumberOfPlayers())
                currentPlayer++;
            //else
                //si è verificato un problema
        }

        public int getCurrentPlayer(){
            return currentPlayer;
        }

        public void playAssistant(int player, int pos){
            if(player!=getCurrentPlayer()){
                //non può giocare l'assistant
            }
            playedAssistants[player]=game.getPlayer(player).playAssistant(pos);
            AlreadyPlayedAssistant[player]=1;
            calculateNextPlayer();
        }

        public Assistant[] getPlayedAssistants(){
            return playedAssistants.clone();
        }
    }

    public class ActionPhase{

        private int assistantValue;
        private int[] movesCounter;
        private int[] alreadyMovedMotherNature;
        private Game game;

        public ActionPhase(Game game){
            this.game=game;
            currentPlayer=-1;

            for(int i=0; i< game.getNumberOfPlayers(); i++){
                movesCounter[i]=0;
                alreadyMovedMotherNature[i]=0;
            }
        }

        private void calculateFirstPlayer(){

            int minValue=Assistant.MAXVALUE; //ho messo attributo public static final MAXVALUE in Assistant
            int nextPlayer=-1;

            for (int i=0; i<game.getNumberOfPlayers(); i++){

                if(game.getRound().getPlayedAssistants()[i].getCardValue()<minValue
                    || (game.getRound().getPlayedAssistants()[i].getCardValue()==minValue && nextPlayer==-1)){

                    minValue=game.getRound().getPlayedAssistants()[i].getCardValue();
                    nextPlayer=i;
                }
            }
            currentPlayer=nextPlayer;
            this.assistantValue=minValue;
        }

        private void calculateNextPlayer(){
            int minValue=this.assistantValue;
            int nextPlayer=-1;

            for (int i=0; i<game.getNumberOfPlayers(); i++){
                if(game.getRound().getPlayedAssistants()[i].getCardValue()<minValue){

                    minValue=game.getRound().getPlayedAssistants()[i].getCardValue();
                    nextPlayer=i;
                }
            }
        }
    }

    private PianificationPhase pianificationPhase;
    private int currentPhase;
    private int roundState;
    private boolean roundFinished;
    private Assistant[] playedAssistants;
    private Game game;

    public Round(Game game){
        this.currentPhase=0;
        this.roundFinished=false;
        this.roundState=-1;
        this.game=game;
    }

    private void startPianificationPhase(Game g){
        //TODO
    }

    public Assistant[] getPlayedAssistants(){
        return playedAssistants.clone();
    }

    private void endPianificationPhase(){
        //TODO
    }

    public boolean checkPlayer(int player) {
            if(player == currentPlayer) return true;
            return false;
    }
}
