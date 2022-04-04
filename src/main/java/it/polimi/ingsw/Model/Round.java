package it.polimi.ingsw.Model;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Round{

    public class PianificationPhase{

        private int currentPlayer;
        private Game game;
        private int AlreadyPlayedAssistant[];
        private Assistant[] playedAssistants;

        private boolean assistantNoChoice(Assistant[] outer, Assistant[] inner) {
            if(outer.length< inner.length)
                return false;

            return Arrays.asList(outer).containsAll(Arrays.asList(inner));
        }

        public PianificationPhase(Game game){
            this.game=game;
            currentPlayer=-1;
        }

        /*public boolean checkPlayer(int player) {
            if(player != currentPlayer) return
        }*/

        private void calculateFirstPlayer(){
            currentPlayer= ThreadLocalRandom.current().nextInt(0, game.getNumberOfPlayers());
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
            
            for(int i=0; i<game.getRound().getPlayedAssistants().length; i++){
                if(game.getPlayer(player).getAssistant(pos).equals(game.getRound().getPlayedAssistants()[i])){
                    if(!assistantNoChoice(game.getRound().getPlayedAssistants(), (Assistant[]) game.getPlayer(player).assistants.toArray())) {
                        //c'è sicuramente un'altra opzione, rifai la scelta
                    }
                }
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

        private int currentPlayer;
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
            assistantValue=minValue;
        }

        private void calculateNextPlayer(){

            int minValue=Assistant.MAXVALUE;
            int nextPlayer=-1;

            for(int i=0; i<game.getNumberOfPlayers(); i++) {

                if (alreadyMovedMotherNature[i]==0) {
                    if (game.getRound().getPlayedAssistants()[i].getCardValue() == assistantValue) {
                        nextPlayer=i;
                        minValue=assistantValue;
                        break;
                    }
                    if(game.getRound().getPlayedAssistants()[i].getCardValue() > assistantValue
                            && game.getRound().getPlayedAssistants()[i].getCardValue() < minValue){
                        minValue=game.getRound().getPlayedAssistants()[i].getCardValue();
                        nextPlayer=i;
                    }
                }
            }

            currentPlayer=nextPlayer;
            assistantValue=minValue;
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

}
