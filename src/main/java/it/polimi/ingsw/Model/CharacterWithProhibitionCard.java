package it.polimi.ingsw.Model;

public class CharacterWithProhibitionCard extends Character{
    private boolean[] prohibitionCard;

    public CharacterWithProhibitionCard(int id, int cost) {
        super(id, cost);
        this.prohibitionCard = new boolean[4];

        for(int i = 0; i < 4; i++) prohibitionCard[i] = true;
    }

    public boolean getProhibitionCard(){
        for(int i = 0; i < this.prohibitionCard.length; i++){
            if(this.prohibitionCard[i] == true){
                this.prohibitionCard[i] = false;
                return true;
            }
        }
        return false;
    }

    public void setProhibitionCard(){
        for(int i = 0; i < prohibitionCard.length; i++){
            if(this.prohibitionCard[i] == false){
                this.prohibitionCard[i] = true;
                return;
            }
        }
    }
}
