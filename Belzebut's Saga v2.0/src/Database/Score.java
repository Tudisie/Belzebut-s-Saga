package Database;

public class Score implements Comparable<Score> {
    private String name;
    private Integer points;

    public Score(String name, int points){
        this.name = name;
        this.points = points;
    }

    public String getName(){
        return name;
    }
    public int getPoints(){
        return points;
    }

    @Override
    public int compareTo(Score o) {
        return this.points.compareTo(o.points);
    }

    @Override
    public String toString(){
        return name + ": " + points;
    }
}
