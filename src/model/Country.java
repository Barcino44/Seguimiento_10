package model;

public class Country implements Comparable<Country> {
    private String name;
    private int gold;
    private int plate;
    private int bronze;

    public Country(String name, int gold, int plate, int bronze) {
        this.name = name;
        this.gold = gold;
        this.plate = plate;
        this.bronze = bronze;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public int getBronze() {
        return bronze;
    }
    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
    public int getTotalMedals(){
        return gold+plate+bronze;
    }
    public void addGold(int gold){
        this.gold+=gold;
    }
    public void addPlate(int plate){
        this.plate+=plate;
    }
    public void addBronze(int bronze){
        this.bronze+=bronze;
    }
    @Override
    public int compareTo(Country o) {
        int criteria1=o.gold-this.gold;
        int criteria2=o.plate-this.plate;
        int criteria3=o.bronze-this.bronze;
        int criteria4=this.name.compareTo(o.name);
        if(criteria1==0&&criteria2!=0){ //Estos son criterios de desempate en caso de que sean iguales
            return criteria2;
        }
        else if(criteria1==0&&criteria3!=0){ //Estos son criterios de desempate en caso de que sean igual
            return criteria3;
        }
        else if (criteria1==0&&criteria4!=0){
            return criteria4;
        }
        else{
            return criteria1;
        }
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Country){
            Country other = (Country) obj;
            return this.name.equals(other.name);
        }else{
            return false;
        }
    }
}
