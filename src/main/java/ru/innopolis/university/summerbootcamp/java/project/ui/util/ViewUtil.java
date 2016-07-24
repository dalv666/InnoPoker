package ru.innopolis.university.summerbootcamp.java.project.ui.util;

import ru.innopolis.university.summerbootcamp.java.project.model.PlayingCard;


/**
 * Created by dalv6_000 on 19.07.2016.
 */
public class ViewUtil {

    public static final String prefix = "deck/";

    public static String getPlayingCardImageUrlByValue(PlayingCard playingCard) {
        return prefix + playingCard.getRank() + "_" + playingCard.getSuit()+".png";
    }

    public static String getBackCardImage() {
        return prefix + "back.png";
    }
    public static String getCardHolderImage() {
        return prefix + "card_place_holder_background.png";
    }

}
