package au.com.liamgooch.moviemate;

import java.util.ArrayList;

import static au.com.liamgooch.moviemate.String_Values.ACTORS_CARD;
import static au.com.liamgooch.moviemate.String_Values.KEY_STORYLINES;
import static au.com.liamgooch.moviemate.String_Values.NOTITLE_DETAILS_CARD;
import static au.com.liamgooch.moviemate.String_Values.TITLE_BULLET_CARD;
import static au.com.liamgooch.moviemate.String_Values.TITLE_DETAILS_CARD;

public class MovieItem {
    private ArrayList<String> importantInfo;
    private String location;
    private String movie_id;
    private String name;
    private String synopsis;
    private String runtime;
    private String genre;
    private String rating;
    private String poster_link;

    private ArrayList<ActorItem> actorList = new ArrayList<>();
    private ArrayList<ArrayList<String>> key_storylines = new ArrayList<>();
    private ArrayList<String> key_information = new ArrayList<>();
    private ArrayList<String> other_information = new ArrayList<>();

    private ArrayList<Integer> cardList = new ArrayList<>();

    private int size;

    public MovieItem(){
        this.size = 0;
    }

    public MovieItem(ArrayList<String> importantInfo, ArrayList<ActorItem> actorList, ArrayList<ArrayList<String>> key_storylines,
                     ArrayList<String> key_information, ArrayList<String> other_information){
        this.importantInfo = importantInfo;
        location = importantInfo.get(0);
        movie_id = importantInfo.get(1);
        name = importantInfo.get(2);
        synopsis = importantInfo.get(3);

        this.actorList = actorList;
        this.key_storylines = key_storylines;
        this.key_information = key_information;
        this.other_information = other_information;

        setSize(sizeSetter());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int sizeSetter(){
        int s = 0;

        if (importantInfo != null){
            cardList.add(TITLE_DETAILS_CARD);
            s += 1;
        }
        if (synopsis != null){
            cardList.add(NOTITLE_DETAILS_CARD);
            s += 1;
        }
        if (actorList != null){
            cardList.add(ACTORS_CARD);
        }
        if (key_storylines != null){
            for (int i = 0; i < key_storylines.size(); i++){
                cardList.add(TITLE_BULLET_CARD);
                s += 1;
            }
        }
        if (key_information != null){
            for (int i = 0; i < key_information.size(); i++){
                cardList.add(TITLE_BULLET_CARD);
                s += 1;
            }
        }
        if (other_information != null){
            for (int i = 0; i < other_information.size(); i++){
                cardList.add(TITLE_BULLET_CARD);
                s += 1;
            }
        }

        return s;
    }

    public ArrayList<String> getImportantInfo() {
        return importantInfo;
    }

    public void setImportantInfo(ArrayList<String> importantInfo) {
        this.importantInfo = importantInfo;
    }

    public ArrayList<ActorItem> getActorList() {
        return actorList;
    }

    public void setActorList(ArrayList<ActorItem> actorList) {
        this.actorList = actorList;
    }

    public ArrayList<ArrayList<String>> getKey_storylines() {
        return key_storylines;
    }

    public void setKey_storylines(ArrayList<ArrayList<String>> key_storylines) {
        this.key_storylines = key_storylines;
    }

    public ArrayList<String> getKey_information() {
        return key_information;
    }

    public void setKey_information(ArrayList<String> key_information) {
        this.key_information = key_information;
    }

    public ArrayList<String> getOther_information() {
        return other_information;
    }

    public void setOther_information(ArrayList<String> other_information) {
        this.other_information = other_information;
    }

    public int getKeyStorylinesSize(){
        if (key_storylines != null){
            return key_storylines.size();
        }else {
            return 0;
        }
    }

    public int getKeyInfoSize(){
        if (key_information != null){
            return key_information.size();
        }else {
            return 0;
        }
    }

    public ArrayList<Integer> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Integer> cardList) {
        this.cardList = cardList;
    }
}
