import java.util.ArrayList;

public class Square {

    private int bombNeighbours; //Number of bomb neighbours, includes corner neighbours.
    private ArrayList<Square> neighbours; //Neighbouring Squares, excludes corner neighbours.
    private boolean hidden, bomb, flagged;

    public Square(boolean bomb) {
        this.bomb = bomb;
        hidden = true;
        flagged = false;
        bombNeighbours = 0;
        neighbours = new ArrayList<Square>();
    }

    public void addNeighbour(Square sq) {
        neighbours.add(sq);
    }

    public void increaseBombNeighbours() {
        bombNeighbours++;
    }

    public void open() {
        hidden = false;
        flagged = false;
    }

    public void flagg() {
        if(hidden && !flagged) flagged = true;
        else flagged = false;
    }

    public String getValue() {
        if(bomb) return "B";
        else if(bombNeighbours == 0) return "";
        else {
            return String.valueOf(bombNeighbours);
        }
    }

    public ArrayList<Square> getNeighbours() {
        return neighbours;
    }

    public boolean isBomb() {
        return bomb;
    }

    public boolean isHidden() {
        return hidden;
    }

    @Override
    public String toString() {
        if(flagged) return "<|";
        else if(hidden) return "[]";
        else return getValue();
    }
}
