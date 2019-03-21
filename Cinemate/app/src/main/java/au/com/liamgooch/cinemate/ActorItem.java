package au.com.liamgooch.cinemate;

import java.util.ArrayList;

public class ActorItem {

    private String name;
    private String characterName;
    private String image;

    private ArrayList<String> infoList = new ArrayList<>();

    public ActorItem(String name, String characterName, String image, ArrayList<String> infoList){
        this.name = name;
        this.characterName = characterName;
        this.image = image;
        this.infoList = infoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<String> infoList) {
        this.infoList = infoList;
    }
}
