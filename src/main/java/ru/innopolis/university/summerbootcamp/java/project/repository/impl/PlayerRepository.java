package ru.innopolis.university.summerbootcamp.java.project.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import ru.innopolis.university.summerbootcamp.java.project.repository.Repository;
import ru.innopolis.university.summerbootcamp.java.project.model.Player;
import ru.innopolis.university.summerbootcamp.java.project.util.FileReaderWriterUtil;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dalv6_000 on 14.07.2016.
 */
@Component
public class PlayerRepository implements Repository<Player> {

    private static Map<String, Player> players;
    private final static String FILE_NAME = "players.json";

    @PostConstruct
    private void loadFiles() {
        String s = FileReaderWriterUtil.readFile(FILE_NAME);
        Type hashMapType = new TypeToken<HashMap<String, Player>>() {
        }.getType();
        this.players = new Gson().fromJson(s, hashMapType);
        if (players == null) {
            players = new HashMap<>();
        }
    }

    public PlayerRepository() {
        loadFiles();
    }


    public void create(Player player) {
        players.put(player.getLogin(), player);
        save();
    }

    private void save() {
        String s = new Gson().toJson(players);
        FileReaderWriterUtil.writeGeneratedStringToFile(s, FILE_NAME);
    }

    public Player read(Player player) {
        return players.get(player.getLogin());
    }

    public void update(Player player) {

    }

    public void delete(Player player) {

    }

    @Override
    public List<Player> getAll() {
        ArrayList<Player> players = new ArrayList<>();
        players.addAll(PlayerRepository.players.values());
        return players;
    }
}
