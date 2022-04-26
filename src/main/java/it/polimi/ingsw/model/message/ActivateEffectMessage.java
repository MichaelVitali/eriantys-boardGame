package it.polimi.ingsw.model.message;

import it.polimi.ingsw.model.Game;

import java.io.Serializable;
import java.util.List;

public class ActivateEffectMessage extends PlayerMessage implements Serializable {
    private static final long serialVersionUID = 6L;
    private int indexCard;
    private List<Integer> studentsIndex;
    private List<Integer> studentsIndexEntrance;
    private int islandIndex;
    private List<Integer> indexTable;
    private String color;

    public ActivateEffectMessage(int playerId, int indexCard, List<Integer> studentsIndex, List<Integer> studentsIndexEntrance, int islandIndex, List<Integer> indexTable, String color) {
        super(playerId);
        this.indexCard = indexCard;
        this.studentsIndex = studentsIndex;
        this.studentsIndexEntrance = studentsIndexEntrance;
        this.islandIndex = islandIndex;
        this.indexTable = indexTable;
        this.color = color;
    }

    @Override
    public void performMove(Game game) {
        game.getRound().activateEffect(getPlayerId(), indexCard, studentsIndex, studentsIndexEntrance, islandIndex, indexTable, color);
    }
}