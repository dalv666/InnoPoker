package ru.innopolis.university.summerbootcamp.java.project.model;

/**
 * Created by dalv6_000 on 15.07.2016.
 */
public class HoldemPlayer extends Player {

    private boolean isDealer;
    private boolean isSmallBlind;
    private boolean isBigBlind;
    private int bet;
    private boolean isBot;
    private boolean isActive = true;

    public HoldemPlayer(Player one) {
        setCash(one.getCash());
        setLogin(one.getLogin());
    }
    public HoldemPlayer() {

    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public boolean isSmallBlind() {
        return isSmallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        isSmallBlind = smallBlind;
    }

    public boolean isBigBlind() {
        return isBigBlind;
    }

    public void setBigBlind(boolean bigBlind) {
        isBigBlind = bigBlind;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}
