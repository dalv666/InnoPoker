/*
package ru.innopolis.university.summerbootcamp.java.project.services;

import static org.junit.Assert.*;

import org.junit.Test;
import ru.innopolis.university.summerbootcamp.java.project.model.Player;
import ru.innopolis.university.summerbootcamp.java.project.model.Settings;
import ru.innopolis.university.summerbootcamp.java.project.services.impl.PlayerServices;
import ru.innopolis.university.summerbootcamp.java.project.services.impl.SettingsServices;

import java.util.List;

public class PlayerServicesTest {


    @Test
    public void write() {
*/
/*        PlayerServices instance = PlayerServices.getInstance();
        Player player = new Player();
        player.setLogin("test");
        player.setPassword("password");
        //player.setPoints(2.0);
        instance.save(player);
        List<Player> all = instance.findAll();
        assertTrue(all.contains(player));*//*


        SettingsServices settingsServices = SettingsServices.getInstance();
        List<Settings> all = settingsServices.findAll();
        for (Settings settings : all) {
            //System.out.println(settings.getUserName());
        }
    }

}
*/
