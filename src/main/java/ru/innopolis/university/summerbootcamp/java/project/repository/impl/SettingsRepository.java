package ru.innopolis.university.summerbootcamp.java.project.repository.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import ru.innopolis.university.summerbootcamp.java.project.model.Player;
import ru.innopolis.university.summerbootcamp.java.project.repository.Repository;
import ru.innopolis.university.summerbootcamp.java.project.model.Settings;
import ru.innopolis.university.summerbootcamp.java.project.util.FileReaderWriterUtil;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dalv6_000 on 15.07.2016.
 */
@Component
public class SettingsRepository implements Repository<Settings> {

    private static Map<String, Settings> settings;
    private final static String FILE_NAME = "settings.json";

    @PostConstruct
    private void loadFiles() {
        String s = FileReaderWriterUtil.readFile(FILE_NAME);
        Type hashMapType = new TypeToken<HashMap<String, Settings>>() {
        }.getType();
        this.settings = new Gson().fromJson(s, hashMapType);
        if (settings == null) {
            settings = new HashMap<>();
        }
    }

    public SettingsRepository() {
        loadFiles();
    }


    public void create(Settings setting) {
        settings.put(setting.getUserName(), setting);
        save();
    }

    private void save() {
        String s = new Gson().toJson(settings);
        FileReaderWriterUtil.writeGeneratedStringToFile(s, FILE_NAME);
    }

    public Settings read(Settings setting) {
        return settings.get(setting.getUserName());
    }

    public void update(Settings settings) {

    }

    public void delete(Settings s) {
        settings.remove(s.getUserName());
    }

    @Override
    public List<Settings> getAll() {
        ArrayList<Settings> settingses = new ArrayList<>();
        settingses.addAll(settings.values());
        return settingses;
    }
}
