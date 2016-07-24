package ru.innopolis.university.summerbootcamp.java.project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.university.summerbootcamp.java.project.model.Settings;
import ru.innopolis.university.summerbootcamp.java.project.repository.impl.SettingsRepository;
import ru.innopolis.university.summerbootcamp.java.project.services.Service;

import java.util.List;

/**
 * Created by dalv6_000 on 15.07.2016.
 */
@Component
public class SettingsServices implements Service<Settings> {
    @Autowired
    private SettingsRepository settingsRepository;

    public Settings save(Settings object) {
        settingsRepository.create(object);
        return object;
    }

    @Override
    public Settings findOne(String name) {
        Settings settings = new Settings();
        settings.setUserName(name);
        Settings read = settingsRepository.read(settings);
        if (read == null) {
            read = new Settings();
            read.setBet(10);
            read.setCash(1000);
            read.setDifficulty(1);
            read.setMusic(true);
            read.setUserName(name);
        }
        return read;
    }

    public List<Settings> findAll() {
        return settingsRepository.getAll();
    }

    public Settings update(Settings object) {
        return null;
    }

    public void delete(Long objectId) {

    }
}
