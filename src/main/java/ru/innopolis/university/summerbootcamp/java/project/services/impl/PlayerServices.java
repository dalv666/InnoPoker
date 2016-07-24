package ru.innopolis.university.summerbootcamp.java.project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.university.summerbootcamp.java.project.model.Player;
import ru.innopolis.university.summerbootcamp.java.project.repository.impl.PlayerRepository;
import ru.innopolis.university.summerbootcamp.java.project.services.Service;

import java.util.List;

/**
 * Created by dalv6_000 on 15.07.2016.
 */
@Component
public class PlayerServices implements Service<Player> {
    @Autowired
    private PlayerRepository playerRepository;

    public Player save(Player player) {
        playerRepository.create(player);
        return player;
    }

    public Player findOne(String name) {
        Player player = new Player();
        player.setLogin(name);
        player = playerRepository.read(player);
        return player;
    }

    public List<Player> findAll() {
        return playerRepository.getAll();
    }

    public Player update(Player player) {
        playerRepository.update(player);
        return player;
    }

    public void delete(Long playerId) {

    }
}
