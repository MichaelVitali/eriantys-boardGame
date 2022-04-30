package it.polimi.ingsw.model;

public class TwoMoreStepsCharacter extends Character {

    /**
     * Creates a character card with the given two values
     *
     * @param id   integer that identifies the character card
     * @param cost amount of money needed to activate the card effect
     */
    public TwoMoreStepsCharacter(int id, int cost) {
        super(id, cost);
    }

    /**
     * Overrides the method of Round forcing it to allow a movement of one or two island more than the value the assistant allows
     * @param assistant assistant played by the player
     * @param islandIndex island where the player wants to move mother nature
     * @return true if the movement is legal, false otherwise
     */
    @Override
    public boolean isANewAllowedPositionForMotherNature(Assistant assistant, int islandIndex) {
            return super.isANewAllowedPositionForMotherNature(assistant, (islandIndex + super.getGame().getGameTable().getNumberOfIslands() - 2) % super.getGame().getGameTable().getNumberOfIslands()) ||
                    super.isANewAllowedPositionForMotherNature(assistant, (islandIndex + super.getGame().getGameTable().getNumberOfIslands() - 1) % super.getGame().getGameTable().getNumberOfIslands());
    }
}
