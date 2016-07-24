package ru.innopolis.university.summerbootcamp.java.project.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.innopolis.university.summerbootcamp.java.project.config.CoreConfig;
import ru.innopolis.university.summerbootcamp.java.project.engine.GameEngine;
import ru.innopolis.university.summerbootcamp.java.project.model.*;
import ru.innopolis.university.summerbootcamp.java.project.model.enums.GameStage;
import ru.innopolis.university.summerbootcamp.java.project.services.impl.PlayerServices;
import ru.innopolis.university.summerbootcamp.java.project.services.impl.SettingsServices;
import ru.innopolis.university.summerbootcamp.java.project.ui.util.AbstractController;
import ru.innopolis.university.summerbootcamp.java.project.ui.util.ViewUtil;

import java.io.IOException;
import java.util.*;

@Component
public class GameController{

    @FXML
    private Label roundBet;


    @FXML
    private AnchorPane player4;

    @FXML
    private AnchorPane player1;


    //TABLE CARDS
    @FXML
    private ImageView firstCard;
    @FXML
    private ImageView secondCard;
    @FXML
    private ImageView thirdCard;
    @FXML
    private ImageView fourthCard;
    @FXML
    private ImageView fithCard;


    //USER CARDS
    @FXML
    private ImageView userCard2;

    @FXML
    private ImageView userCard1;
    //USER CARDS


    //PLAYERS CARDS

    @FXML
    private ImageView player2Card1;
    @FXML
    private ImageView player2Card2;

    @FXML
    private ImageView player1Card2;
    @FXML
    private ImageView player1Card1;
    @FXML
    private ImageView player3Card2;

    @FXML
    private ImageView player3Card1;

    @FXML
    private ImageView player4Card2;
    @FXML
    private ImageView player4Card1;
    //PLAYERS CARDS

    //CHIPS
    @FXML
    private ImageView chip0;
    @FXML
    private ImageView chip1;
    @FXML
    private ImageView chip2;
    @FXML
    private ImageView chip3;
    @FXML
    private ImageView chip4;
    //CHIPS


    //BETS
    @FXML
    private Label bet0;

    @FXML
    private Label bet1;

    @FXML
    private Label bet2;
    @FXML
    private Label bet3;
    @FXML
    private Label bet4;
    //BETS


    //POINTS
    @FXML
    private Label point0;

    @FXML
    private Label point1;

    @FXML
    private Label point2;
    @FXML
    private Label point3;
    @FXML
    private Label point4;
    //POINTS

    private List<Label> points;


    //NAMES
    @FXML
    private Label userName;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private Label name3;
    @FXML
    private Label name4;
    //NAMES


    private List<ImageView> userCardIView;

    @FXML
    private Button confirm;
    @FXML
    private Button fold;

    @FXML
    private Button call;

    @FXML
    private Button check;

    @FXML
    private Label callLabel;

    @FXML
    private Slider rateSlider;


    @FXML
    private Button nextRound;


    @FXML
    ListView<String> history;


    ObservableList<String> logs = FXCollections.observableArrayList();

    @Autowired
    private SettingsServices settingsServices;

    private List<ImageView> chips;
    private List<ImageView> cards;
    private List<Label> bets;
    private List<Label> names;

    private List<List<ImageView>> playersHandCards;


    private Game game = createGame(new HoldemPlayer(), 5);

    @Autowired
    private PlayerServices playerServices;

    private HoldemPlayer user;

    @FXML
    private void initialize() {
        springInit();
        singIn();
        configureUser();
        history.setItems(logs);
        logs.add("Game start");
        Image image = new Image(getClass().getClassLoader().getResource("deck/card_place_holder_background.png").toExternalForm());
        firstCard.setImage(image);
        secondCard.setImage(image);
        thirdCard.setImage(image);
        fourthCard.setImage(image);
        fithCard.setImage(image);
        callLabel.setText("0");
        chips = new ArrayList<>();
        chips.add(chip0);
        chips.add(chip2);
        chips.add(chip3);
        chips.add(chip4);
        chips.add(chip1);


        cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);
        cards.add(thirdCard);
        cards.add(fourthCard);
        cards.add(fithCard);


        bets = new ArrayList<>();
        bets.add(bet0);
        bets.add(bet2);
        bets.add(bet3);
        bets.add(bet4);
        bets.add(bet1);


        names = new ArrayList<>();
        names.add(userName);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name1);


        playersHandCards = new ArrayList<>();

        userCardIView = new ArrayList<>();
        userCardIView.add(userCard1);
        userCardIView.add(userCard2);


        List<ImageView> firstUserCardView = new ArrayList<>();
        firstUserCardView.add(player1Card1);
        firstUserCardView.add(player1Card2);

        List<ImageView> secondUserCardView = new ArrayList<>();
        secondUserCardView.add(player2Card1);
        secondUserCardView.add(player2Card2);


        List<ImageView> thirdUserCardView = new ArrayList<>();
        thirdUserCardView.add(player3Card1);
        thirdUserCardView.add(player3Card2);

        List<ImageView> fouthUserCardView = new ArrayList<>();
        fouthUserCardView.add(player4Card1);
        fouthUserCardView.add(player4Card2);


        playersHandCards.add(userCardIView);
        playersHandCards.add(secondUserCardView);
        playersHandCards.add(thirdUserCardView);
        playersHandCards.add(fouthUserCardView);
        playersHandCards.add(firstUserCardView);


        points = new ArrayList<>();
        points.add(point0);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point1);


        fold.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logs.add(game.getUser().getLogin() + " is fold");
                game.setTotalRoundBet(0);
                game.setWinPoint(0);
                displayWinPoints();
                nextRound();
                disableUserControl();
                step();
            }
        });

        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logs.add(game.getUser().getLogin() + " is rise " + rateSlider.getValue());
                int value = (int) rateSlider.getValue();
                HoldemPlayer user = game.getUser();
                makeBet(user, value);
                game.setCurrentBet(user.getBet());
                showBets();
                step();
            }
        });

        nextRound.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logs.add(game.getUser().getLogin() + " start next round");
                nextRound.setDisable(true);
                resetRound();
                nextRound();
                game.setGameStage(nextGameStage(game.getGameStage()));
                logs.add(game.getGameStage().toString());
                step();
            }
        });


        call.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int diff = game.getCurrentBet() - game.getUser().getBet();
                logs.add(game.getUser().getLogin() + " call " + diff);
                makeBet(game.getUser(), diff);
                showBets();
                disableUserControl();
                step();
            }
        });

        check.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logs.add(game.getUser().getLogin() + " check");
                if (game.getUser().isBigBlind()) {
                    runNextRound();
                }
            }
        });

        disableUserControl();

        rateSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                callLabel.setText(String.valueOf(new_val.intValue()));
            }
        });

        showPoints();
        initGame();
        //startGame();

    }

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(CoreConfig.class);


    private void springInit() {
        playerServices = APPLICATION_CONTEXT.getBean(PlayerServices.class);
        settingsServices = APPLICATION_CONTEXT.getBean(SettingsServices.class);

    }

    private void disableUserControl() {
        confirm.setDisable(true);
        check.setDisable(true);
        call.setDisable(true);
        fold.setDisable(true);
        rateSlider.setDisable(true);
    }


    public Game createGame(HoldemPlayer user, int needPlayers) {
        game = new Game();
        int botCounter = 0;
        List<HoldemPlayer> players = new ArrayList<>();
        players.add(user);
        while (players.size() < needPlayers) {
            botCounter++;
            HoldemPlayer holdemPlayer = new HoldemPlayer();
            holdemPlayer.setLogin("Bot" + botCounter);
            holdemPlayer.setBot(true);
            holdemPlayer.setCash(900);
            players.add(holdemPlayer);
        }
        game.setHoldemPlayers(players);

        return game;
    }

    private void configureUser() {
        Settings settings = settingsServices.findOne(user.getLogin());

        game.setLowestBet(settings.getBet());
        game.setCurrentBet(settings.getBet());
        game.setTotalRoundBet(0);


        //Setting dealer and blinds
        game.getHoldemPlayers().set(0, user);
        game.getHoldemPlayers().get(0).setDealer(true);
        game.getHoldemPlayers().get(1).setSmallBlind(true);
        game.getHoldemPlayers().get(2).setBigBlind(true);
        game.setCurrentPlayer(2 + 1);
    }


    public List<PlayingCard> createAndShuffleDeck() {
        LinkedList<PlayingCard> playingCards = new LinkedList<>();
        final Random random = new Random();
        int counter = random.nextInt(40);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 13; j++)
                playingCards.add(new PlayingCard(i, j));
        for (int i = 0; i < counter; i++)
            Collections.shuffle(playingCards);
        return playingCards;
    }


    public void initGame() {
        game.setGameStage(GameStage.Start);
        logs.add(GameStage.Start.toString());
        //TODO: check num of players
        settingDeck();
        distributePlayingCards();
        game.setGameStage(GameStage.Preflop);
        logs.add(GameStage.Preflop.toString());
        showUserCard();
        settingBets();
        showBets();
        displayNames();
        nextRound.setDisable(true);
        game.setGameStage(GameStage.Round1);
        logs.add(GameStage.Round1.toString());
        step();
    }

    private void step() {
        HoldemPlayer p;
        do {
            p = game.nextPlayer();
            if (p.isActive()) {
                //бот активен
                if (p.isBot()) {


                    //Бот принимает решение по поводу игры

                    Random random = new Random();

                    if (game.getCurrentBet() > p.getBet()) {

                        if (random.nextBoolean()) {
                            //call
                            //если текущая ставка больше чем его, то добавляем
                            int count = game.getCurrentBet() - p.getBet();
                            logs.add(p.getLogin() + " call " + count);
                            makeBet(p, count);
                            showBets();
                        } else {
                            //fold
                            logs.add(p.getLogin() + " is fold");
                            if (botFold(p)) {
                                break;
                            }
                        }
                    } else if (random.nextBoolean() && game.getCurrentBet() == p.getBet()) {
                        //check
                        logs.add(p.getLogin() + " is check");
                    } else if (random.nextBoolean()) {
                        logs.add(p.getLogin() + " is fold");
                        if (botFold(p)) {
                            break;
                        }

                    } else {
                        logs.add(p.getLogin() + " is rise");
                        //rise
                        //поднял ставку на 20
                        makeBet(p, 20);
                        game.setCurrentBet(p.getBet());
                        showBets();
                    }


                } else {
                    // передаем управление пользователю
                    enableUserControl();
                    break;
                }
                if (p.isBigBlind() && p.getBet() == game.getCurrentBet() && allActiveBet()) {
                    //следующий кон
                    //обнуляем ставки
                    runNextRound();
                    //заканчиваем раунд
                    break;
                }
            } else if (p.isBigBlind()) {
                //бот не активен и он большой блайнд


                //следующий кон
                //обнуляем ставки
                runNextRound();
                //заканчиваем раунд
                break;
            }

        } while (true);
    }

    private boolean allActiveBet() {
        for (HoldemPlayer holdemPlayer : game.getActivePlayers()) {
            if (holdemPlayer.getBet() != game.getCurrentBet()) {
                return false;
            }
        }
        return true;
    }

    private boolean botFold(HoldemPlayer p) {
        p.setActive(false);
        disableBackPlayerCard(game.getHoldemPlayers().indexOf(p));

        //fold
        if (!game.activeBotsIsExsist()) {
            //выигрывает игрок получение приза
            //Следующая игра
            countAndDisplayRoundBets();
            logs.add(game.getUser().getLogin() + " win " + game.getWinPoint());
            game.getUser().setCash(game.getUser().getCash() + game.getWinPoint());
            enableNextGame();
            return true;
        }

        if (p.isBigBlind()) {
            //следующий кон
            //обнуляем ставки
            runNextRound();
            //заканчиваем раунд
        }


        return p.isBigBlind();
    }

    private void runNextRound() {
        GameStage gameStage = nextGameStage(game.getGameStage());
        game.setGameStage(gameStage);
        logs.add(game.getGameStage().toString());
        countAndDisplayRoundBets();
        logs.add("Round is end. Bank is " + game.getWinPoint());
        resetBets();
        showBets();

        setCurrentPlayer();


        switch (game.getGameStage()) {
            case Flop:
                flop();
                break;
            case Tern:
                tern();
                break;
            case Riever:
                riever();
                break;
            case Final:
                int winner = showWinner();
                getPrize(winner);

                enableNextGame();
        }


        Settings settings = settingsServices.findOne(game.getUser().getLogin());
        settings.setCash((int) game.getUser().getCash());
        settingsServices.save(settings);
    }

    private void enableNextGame() {
        disableUserControl();
        nextRound.setDisable(false);
        for (HoldemPlayer holdemPlayer : game.getHoldemPlayers()) {
            holdemPlayer.setActive(true);
        }
    }


    private void resetRound() {
        game.setGameStage(GameStage.Preflop);
        logs.add(game.getGameStage().toString());
        game.setWinPoint(0);
        game.setCurrentBet(0);

        //делаю все карты не прозрачными
        for (int i = 1; i < game.getHoldemPlayers().size(); i++) {
            enableBackPlayerCard(i);
        }
    }

    private void getPrize(int winner) {
        HoldemPlayer win = game.getHoldemPlayers().get(winner);
        win.setCash(win.getCash() + game.getWinPoint());
    }

    private int showWinner() {
        List<HoldemPlayer> activePlayers = game.getActivePlayers();
        int winner = GameEngine.winnerPicker(activePlayers);
        HoldemPlayer wnPlayer = activePlayers.get(winner);

        winner = game.getHoldemPlayers().indexOf(wnPlayer);

        logs.add(wnPlayer.getLogin() + " is win " + game.getWinPoint());
        wnPlayer.setCash(wnPlayer.getCash() + game.getWinPoint());

        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            if (i != winner) {
                disableBackPlayerCard(i);
                continue;
            }
            showPlayerCard(i);
        }
        return winner;
    }


    private void showPlayerCard(int id) {
        showPlayerCard(id, 1.0);
    }


    private void disableBackPlayerCard(int id) {
        changeOpacityBackPlayerCard(id, 0.1);
    }

    private void enableBackPlayerCard(int id) {
        changeOpacityBackPlayerCard(id, 1.0);
    }


    private void showPlayerCard(int id, double opacity) {
        HoldemPlayer holdemPlayer = game.getHoldemPlayers().get(id);
        for (int j = 0; j < 2; j++) {
            PlayingCard card = holdemPlayer.getPlayingCards().get(j);
            playersHandCards.get(id).get(j).setImage(new Image(getClass().getClassLoader().getResource(ViewUtil.getPlayingCardImageUrlByValue(card)).toExternalForm()));
            playersHandCards.get(id).get(j).setStyle("-fx-opacity: " + opacity + ";");

        }
    }

    private void changeOpacityBackPlayerCard(int id, double opacity) {
        for (int j = 0; j < 2; j++) {
            playersHandCards.get(id).get(j).setImage(new Image(getClass().getClassLoader().getResource(ViewUtil.getBackCardImage()).toExternalForm()));
            playersHandCards.get(id).get(j).setStyle("-fx-opacity: " + opacity + ";");

        }
    }


    private GameStage nextGameStage(GameStage start) {
        for (GameStage st : GameStage.values()) {
            if (st.compareTo(start) > 0) {
                return st;
            }
        }
        return GameStage.Start;
    }

    private void tern() {
        //1 карта для терна
        game.getTableCards().add(takeCard());
        showTableCard();
        //ставим ставки
        settingBets();
        showBets();
        game.setGameStage(nextGameStage(game.getGameStage()));
        logs.add(game.getGameStage().toString());
        step();
    }


    private void riever() {
        //1 карта для ривера
        game.getTableCards().add(takeCard());
        showTableCard();
        //ставим ставки
        settingBets();
        showBets();
        game.setGameStage(nextGameStage(game.getGameStage()));
        logs.add(game.getGameStage().toString());
        step();
    }

    private void setCurrentPlayer() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            if (game.getHoldemPlayers().get(i).isBigBlind()) {
                game.setCurrentPlayer(i + 1);
            }
        }
    }

    private void countAndDisplayRoundBets() {
        int roundTotalBet = 0;
        for (HoldemPlayer player : game.getHoldemPlayers()) {
            roundTotalBet += player.getBet();
        }
        game.setWinPoint(game.getWinPoint() + roundTotalBet);
        displayWinPoints();
    }

    private void flop() {
        game.setTableCards(new ArrayList<>());
        for (int i = 0; i < 3; i++) {
            //3 карты для флопа
            game.getTableCards().add(takeCard());
            showTableCard();
        }
        //ставим ставки
        settingBets();
        showBets();
        game.setGameStage(nextGameStage(game.getGameStage()));
        logs.add(game.getGameStage().toString());
        step();
    }

    private void showTableCard() {
        for (int i = 0; i < game.getTableCards().size(); i++) {
            PlayingCard playingCard = game.getTableCards().get(i);
            cards.get(i).setImage(new Image(getClass().getClassLoader().getResource(ViewUtil.getPlayingCardImageUrlByValue(playingCard)).toExternalForm()));
        }
    }

    private void makeBet(HoldemPlayer p, int count) {
        p.setBet(p.getBet() + count);
        p.setCash(p.getCash() - count);
        showPoints();
    }


    public void settingBets() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            HoldemPlayer holdemPlayer = game.getHoldemPlayers().get(i);
            if (holdemPlayer.isSmallBlind()) {
                holdemPlayer.setBet((int) (game.getLowestBet() / 2.0));
            } else if (holdemPlayer.isBigBlind()) {
                holdemPlayer.setBet(game.getLowestBet());
            }
        }
        game.setCurrentBet(game.getLowestBet());
    }


    //TODO: refactor it
    public void changeDealer(List<HoldemPlayer> holdemPlayers, int number, boolean value) {
        holdemPlayers.get(number).setDealer(value);
        if (value) {
            chips.get(number).setImage(new Image(getClass().getClassLoader().getResource("ui/dealerChip.png").toExternalForm()));
        } else {
            chips.get(number).setImage(null);
        }
    }

    public void changeBigBlind(List<HoldemPlayer> holdemPlayers, int number, boolean value) {
        holdemPlayers.get(number).setBigBlind(value);
        if (value) {
            chips.get(number).setImage(new Image(getClass().getClassLoader().getResource("ui/bigBlindChip.png").toExternalForm()));
        } else {
            chips.get(number).setImage(null);
        }
    }

    public void changeSmallBlind(List<HoldemPlayer> holdemPlayers, int number, boolean value) {
        holdemPlayers.get(number).setSmallBlind(value);
        if (value) {
            chips.get(number).setImage(new Image(getClass().getClassLoader().getResource("ui/smallBlindChip.png").toExternalForm()));
        } else {
            chips.get(number).setImage(null);
        }
    }

    /**
     * Change dealer and blinds actors in a game
     *
     * @param game
     */
    public void changeRoles(Game game) {
        List<HoldemPlayer> holdemPlayers = game.getHoldemPlayers();
        int nDealer = 0;
        int nSB = 0;
        int nBB = 0;


        for (int i = 0; i < holdemPlayers.size(); i++) {
            HoldemPlayer holdemPlayer = holdemPlayers.get(i);


            if (holdemPlayer.isBigBlind()) {
                changeBigBlind(holdemPlayers, i, false);
                if (i != holdemPlayers.size() - 1) {
                    nBB = i + 1;
                }
            } else if (holdemPlayer.isSmallBlind()) {
                changeSmallBlind(holdemPlayers, i, false);
                if (i != holdemPlayers.size() - 1) {
                    nSB = i + 1;
                }
            } else if (holdemPlayer.isDealer()) {
                changeDealer(holdemPlayers, i, false);
                if (i != holdemPlayers.size() - 1) {
                    nDealer = i + 1;
                }
            }


        }
        changeBigBlind(holdemPlayers, nBB, true);
        changeSmallBlind(holdemPlayers, nSB, true);
        changeDealer(holdemPlayers, nDealer, true);
        game.setCurrentPlayer(nBB + 1);
    }


    private void bettingRound(int startPlayer) {
        if (startPlayer == game.getHoldemPlayers().size() - 1) {
            startPlayer = 0;
        }
        for (int i = startPlayer; i < game.getHoldemPlayers().size(); i++) {
            if (game.getHoldemPlayers().get(i).isBot()) {
                // FIXME: 19.07.2016 Bet sum and cash
                //CommandType decision = aiEngine.getDecision(game.getHoldemPlayers().get(i).getPlayingCards(), 0, 0);
                //AI engine
                if (game.getCurrentBet() > game.getHoldemPlayers().get(i).getBet()) {
                    int diff = game.getCurrentBet() - game.getHoldemPlayers().get(i).getBet();
                    game.getHoldemPlayers().get(i).setBet(game.getHoldemPlayers().get(i).getBet() + diff);
                    game.getHoldemPlayers().get(i).setCash(game.getHoldemPlayers().get(i).getCash() - diff);
                    refreshBet(i, game.getHoldemPlayers().get(i).getBet());
                }
            } else {
                //Give control to user
                enableUserControl();
            }
        }


    }

    private void showUserCard() {
        showPlayerCard(0);
    }

    private PlayingCard takeCard() {
        return game.getDeck().remove(0);
    }

    private void refreshBet(int userNumber, int value) {
        bets.get(userNumber).setText(value + "");
    }

    private void enableUserControl() {
        //если сумма не ровна то добираем или увеличиваем ставку
        if (game.getCurrentBet() == game.getUser().getBet()) {
            check.setDisable(false);
        } else {
            call.setDisable(false);
        }
        confirm.setDisable(false);
        fold.setDisable(false);
        rateSlider.setDisable(false);


        //устанавливаем слайдер в минимальную ставку
        rateSlider.setMin(game.getCurrentBet());
        rateSlider.setMax(game.getCurrentBet() + 100);


    }

    public void nextRound() {
        changeRoles(game);
        resetBets();
        settingBets();
        showBets();
        removeCards();
        settingDeck();
        game.setWinPoint(0);
        displayWinPoints();
        cleanTableCards();
        hidePlayersCards();
        distributePlayingCards();
        showUserCard();
    }

    private void hidePlayersCards() {
        for (int i = 1; i < game.getHoldemPlayers().size(); i++) {
            enableBackPlayerCard(i);
        }
    }

    private void settingDeck() {
        List<PlayingCard> deck = createAndShuffleDeck();
        game.setDeck(deck);
    }

    private void removeCards() {
        for (HoldemPlayer holdemPlayer : game.getHoldemPlayers()) {
            holdemPlayer.setPlayingCards(new ArrayList<>());
        }
    }

    private void resetBets() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            game.getHoldemPlayers().get(i).setBet(0);
        }
    }


    public void distributePlayingCards() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            HoldemPlayer user = game.getHoldemPlayers().get(i);
            PlayingCard playingCard = takeCard();
            user.getPlayingCards().add(playingCard);
            playingCard = takeCard();
            user.getPlayingCards().add(playingCard);
        }

    }

    private void showBets() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            bets.get(i).setText(game.getHoldemPlayers().get(i).getBet() + "");
        }
        showPoints();
    }

    private void showPoints() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            int cash = (int) game.getHoldemPlayers().get(i).getCash();
            points.get(i).setText(cash + "");
        }
    }

    private void displayNames() {
        for (int i = 0; i < game.getHoldemPlayers().size(); i++) {
            names.get(i).setText(game.getHoldemPlayers().get(i).getLogin() + "");
        }
    }

    private void cleanTableCards() {
        for (int i = 0; game.getTableCards() != null && i < game.getTableCards().size(); i++) {
            cards.get(i).setImage(new Image(getClass().getClassLoader().getResource(ViewUtil.getCardHolderImage()).toExternalForm()));
        }
    }


    private void displayWinPoints() {
        roundBet.setText(game.getWinPoint() + "");
    }


    //button menus

    public void settings() throws IOException {

    }


    public void choosePlayerCount() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Get the Stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(getClass().getClassLoader().getResource("ui/logo.png").toExternalForm()));

        alert.setTitle("Count bot chooser ");
        alert.setHeaderText("Please, select the number of players");
        alert.setContentText("Choose count of players");

        ButtonType buttonTypeOne = new ButtonType("3");
        ButtonType buttonTypeTwo = new ButtonType("4");
        ButtonType buttonTypeThree = new ButtonType("5");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        playerServices.save(game.getUser());

        int[] rg = {20, 20};
        int[] gg = {20, 20};

        if (result.get() == buttonTypeOne) {
            game = createGame(new HoldemPlayer(), 3);
            rg[0] = 4;
            rg[1] = 1;
        } else if (result.get() == buttonTypeTwo) {
            game = createGame(new HoldemPlayer(), 4);
            rg[0] = 1;
            gg[1] = 4;
        } else if (result.get() == buttonTypeThree) {
            game = createGame(new HoldemPlayer(), 5);
            gg[0] = 4;
            gg[1] = 1;
        } else {
            return;
            // ... user chose CANCEL or closed the dialog
        }
        initialize();
        //remove gues
        removeGues(rg);
        getGues(gg);
    }

    private void getGues(int[] rg) {
        for (int i : rg) {
            if (i == 4) {
                player4.setVisible(true);
            }
            if (i == 1) {
                player1.setVisible(true);
            }
        }
    }

    private void removeGues(int[] rg) {
        for (int i : rg) {
            if (i == 4) {
                player4.setVisible(false);
            }
            if (i == 1) {
                player1.setVisible(false);
            }
        }
    }


    public void singIn() {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();

        // Get the Stage.
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(getClass().getClassLoader().getResource("ui/logo.png").toExternalForm()));

        dialog.setTitle("Login");
        dialog.setHeaderText("Please, LogIn. If you don't have a account, we will create it for you.");

// Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(getClass().getClassLoader().getResource("ui/menu/exit.png").toExternalForm()));

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);


// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), null);
            } else {
                exit();
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            Player one = playerServices.findOne(usernamePassword.getKey());
            if (one != null) {
                HoldemPlayer holdemPlayer = new HoldemPlayer(one);
                user = holdemPlayer;
                //Setting user
            } else {
                HoldemPlayer holdemPlayer1 = new HoldemPlayer();
                holdemPlayer1.setLogin(usernamePassword.getKey());
                holdemPlayer1.setCash(900);
                user = holdemPlayer1;
                playerServices.save(user);
                //create new
            }
        });
    }

    public void exit() {
        playerServices.save(game.getUser());
        System.exit(0);
    }

    public void chooseBet() {
        TextInputDialog dialog = new TextInputDialog("25");
        // Get the Stage.
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(getClass().getClassLoader().getResource("ui/logo.png").toExternalForm()));
        dialog.setTitle("Choosing small bet");
        dialog.setHeaderText("Small bet");
        dialog.setContentText("Please enter min bet:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
        }

// The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> game.setLowestBet(Integer.valueOf(name)));
    }


    public void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tehas Holdem Rules");
        alert.setHeaderText("Tehas Holdem Rules");
        alert.setContentText("Please, before game read it first");


        // Get the Stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Add a custom icon.
        stage.getIcons().add(new Image(getClass().getClassLoader().getResource("ui/logo.png").toExternalForm()));

// Create expandable Exception.

        String exceptionText = "By Tim Ryerson\n" +
                "There are many different forms of poker including seven card stud, Omaha, five-card draw, and Texas hold’em to name a few. Hold’em has evolved as the most popular game worldwide and is the form we shall use to cover the basic rules of poker.\n" +
                "\n" +
                "In this lesson we’ll take you step by step through the basic rules of poker and we’ll use an example hand to illustrate. You can read the text that follows or alternatively you can watch the video version instead. Watching the video is probably the better option because it’s easier to follow the betting action, but the choice is yours.\n" +
                "\n" +
                "The Setup\n" +
                "\n" +
                "The game of hold’em is typically played with nine or ten players at the table.\n" +
                "\n" +
                "Figure 1\n" +
                "\n" +
                "Basic Rules, Setup\n" +
                "\n" +
                "In our example we have ten players. The dealer button is on player 1, the blinds have been posted, and each player has received their hole cards. If this doesn’t make sense to you, then don’t worry we’ll explain exactly what this all means.\n" +
                "\n" +
                "The Dealer Button\n" +
                "\n" +
                "The dealer button is the round disc labelled with “Dealer” or “D”, and it signifies who is the dealer for the hand. Please be aware that each player gets to ‘act’ as the dealer, even if they don’t physically deal the cards. After each hand the dealer button moves to the next player, in a clockwise manner. In figure 1, the dealer button is with player 1, but for the next hand it’ll be with player 2, and then player 3, and so on.\n" +
                "\n" +
                "The dealer position is also called “the button” and is the most favourable position in the game as that player gets to act last after he has seen everyone else make their decisions. The exception to this rule occurs on the very first round of betting wherein the “blinds” will act after the button.\n" +
                "\n" +
                "Blind Bets\n" +
                "\n" +
                "Before any cards are dealt the two players to the left of the dealer button are required to post what are referred to as the blinds. These are forced and compulsory bets that encourage the action and give players something to shoot for. Typically there is a small blind directly to the left of the button, who posts half the size of the big blind’s obligation. The amount of the blinds is determined by the stakes of the game.\n" +
                "\n" +
                "Figure 2\n" +
                "\n" +
                "Basic Rules, Blind Bets\n" +
                "\n" +
                "In our example we’re using blinds of $1 and $2. In case it’s not obvious, they are called blind bets because the two players are required to post them before they see their cards.\n" +
                "\n" +
                "The Deal\n" +
                "\n" +
                "Once the blinds have been posted each player is dealt two facedown cards, which are often referred to as their hole cards. The best two hole cards a player can be dealt in hold’em are two Aces’, but we’ll discuss the importance of starting hand selection in later lessons. All you need to know for now is that after the blinds have been posted and the hole cards have been dealt, the betting action can begin.\n" +
                "\n" +
                "Pre-Flop\n" +
                "\n" +
                "This first betting round is called the pre-flop betting round. The first player to act is the player directly to the left of the big blind (in our example this would be player 4) and he has three options; to call the amount of the big blind, which in our example would be $2, to raise, or to fold. If a player decides to fold then they muck (discard/throw away) their cards facedown and sit out until the next hand is dealt. The action then follows around the table in a clockwise direction until each player has been afforded the opportunity to call, raise or fold.\n" +
                "\n" +
                "The whole betting procedure of poker can be a little confusing for beginners. In our example hand you can see that players 2 and 3 have posted the blinds. If all the players to the left of player 3 decide to call the $2 bet then the action is on the small blind, player 2, who has so far only posted a small blind of $1. He now has the option to call $1 more to make up the bet, or he can raise. If he calls the extra $1, then the big blind is left with the final option. Since the blind bets are made before the cards are dealt, they are considered “live” which means that even if all the other players just call the big blind he then still has the option to raise, if he wishes. If the big blind doesn’t want to raise then the betting round is over and we can move onto the flop, which we’ll explain in a moment.\n" +
                "\n" +
                "What if a player decides to raise? If a player decides to raise the big blind bet of $2, then all the remaining players must either call the new bet, re-raise, or fold.\n" +
                "\n" +
                "Figure 3\n" +
                "\n" +
                "Basic Rules, Pre-Flop\n" +
                "\n" +
                "In our example player 6 raised the betting to $4, player 7 folded and is now out of the hand. Player 8 called, but players 9, 10, and 1 all folded and are also out of this hand. Players 2, 3, 4, and 5, all called player 6’s raise. Once all bets have been equalized (i.e. matched), then the betting round is over. In our example, six players are now left in and the pot contains $24 ($4 x 6). This brings us onto the next betting round, called the flop.\n" +
                "\n" +
                "The Flop\n" +
                "\n" +
                "After the first round of betting is complete three community cards are dealt face up in the middle of the table. All the active players can use these three cards, along with their two hole cards, to make their best possible hand. These three cards are known as “the flop”. Here’s the flop before any further betting has taken place:\n" +
                "\n" +
                "Figure 4\n" +
                "\n" +
                "Basic Rules, The Flop\n" +
                "\n" +
                "You will notice that all the bets from previous round (pre-flop) are now in the pot. After the flop has been dealt, another betting round begins. The first active player to the left of the dealer button starts the action. In our example this is the small blind from the opening round, player 2. The forced action created by the blind bets no longer exists and that player has two options; to check or bet. A check means to neither open the betting nor fold. When a player checks the action moves onto the next active player, who may also check. If a player wants to check they either verbally announce “check”, or they tap the table with their finger or hand (or click the mouse if playing online poker).\n" +
                "\n" +
                "If a player decides to open the betting, the option to check is over, and a player must either call, raise, or fold – this includes any players who have previously checked. Once all bets for this betting round have been matched or every player has opted to check, then we move onto the next betting round. For our example hand, let’s suppose that after the flop was dealt, players 2, 3, 4, and 5, all checked. But player 6 then bet $2, which was called by player 8. Players 2, 3, and 4 all fold, but player 5 calls the $2. These three players get to see “the turn”.\n" +
                "\n" +
                "The Turn\n" +
                "\n" +
                "After the second round of betting (the flop) a fourth card is dealt and that card is called “the turn” – it’s also sometimes referred to as 4th street. In our example hand we have three players remaining:\n" +
                "\n" +
                "Figure 5\n" +
                "\n" +
                "Basic Rules, The Turn\n" +
                "\n" +
                "You will also notice that the pot now contains $30, which is made up of $24 from the first betting round, and $6 from the betting on the flop. After the forth community card (the turn) has been dealt, another round of betting takes place, exactly the same format as the previous betting round, starting with the first active player to the left of the dealer button. In our example, this would be player 5. He may check or bet. Let’s suppose that player 5 checks, as do players 6 and 8. If all the players check, the round is over, and we move onto “the river”.\n" +
                "\n" +
                "The River\n" +
                "\n" +
                "After the third round of betting is complete the fifth and final board card is dealt and that card is called “the river” – it’s also sometimes referred to as 5th street.\n" +
                "\n" +
                "Figure 6\n" +
                "\n" +
                "Basic Rules, The River\n" +
                "\n" +
                "In our example hand, there is $30 in the pot and player 5 bets $4. He’s raised by player 6 (who makes it $8 total). Let’s suppose that player 8 folds, and player 5 decides to call the extra $4. When the final betting round ends with two or more active players (as our example has done), then we get to what is called a “showdown”.\n" +
                "\n" +
                "The Showdown\n" +
                "\n" +
                "After the final betting round is completed, it’s time to see who has won the hand, and the money in the pot. Now that all of the cards have been dealt, each player can combine his two hole cards with any of the five cards on the board to create his best five card poker hand. The winning hand must been seen, which means players can now reveal their two hole cards. The player that was called (player 6) is required to show first. Figure 7 shows an example showdown after both players have shown their hole cards:\n" +
                "\n" +
                "Figure 7\n" +
                "\n" +
                "Rules of Poker - Figure 6\n" +
                "\n" +
                "Which hand wins? Player 5 has a straight (9, T, J, Q, K), and player 6 has a higher straight (T, J, Q, K, A) but his best hand is a flush (A, K, Q, T, 5 of hearts). If you studied the poker hand rankings lesson then you will be aware that a flush always beats a straight. Therefore player 6 wins this hand, and as you can see in figure 6, after the final betting on the river, the pot now contains $46. Player 6 wins the pot of $46, and a new hand can begin.\n" +
                "\n" +
                "Some Important Points…\n" +
                "\n" +
                "In our above example it was mentioned on more than one occasion that not all hands will reach the point of a showdown. It’s important that you realize that a hand of poker can be over even before the players get to see the flop. Our example hand made it to a showdown, but if a player had made a bet which wasn’t called during any of the betting rounds, then this player would’ve won, uncontested. Think of it like a boxing fight, where a fighter could land a knockout blow in the first round, or both boxers could still be standing after 12 rounds, in which case it’d be decided on points.\n" +
                "\n" +
                "It’s also worth noting that if you do get to a showdown scenario then you are not obligated to even use your two hole cards. If the best five card hand you can make is comprised of the five community cards, you are said to be playing the board. For example, if the five community cards (flop, turn, and river) made up a Royal flush, the best possible hand in poker, then all the remaining active players would share the pot, regardless of their hole cards. Even though each player has seven cards in total, only five of them count – your best five.\n" +
                "\n" +
                "Of course there are many other rules and important concepts you need to understand, but hopefully you’ve learnt a lot from this lesson. The game of Texas hold’em is a deceptively simple game, but as you move through the myriad of poker lessons here on Pokerlogy you will experience an astounding wealth of information and strategy.";

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

}
