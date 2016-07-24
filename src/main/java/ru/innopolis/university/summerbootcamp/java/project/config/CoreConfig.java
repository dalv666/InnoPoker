package ru.innopolis.university.summerbootcamp.java.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.innopolis.university.summerbootcamp.java.project.repository.impl.PlayerRepository;
import ru.innopolis.university.summerbootcamp.java.project.repository.impl.SettingsRepository;
import ru.innopolis.university.summerbootcamp.java.project.services.impl.PlayerServices;
import ru.innopolis.university.summerbootcamp.java.project.services.impl.SettingsServices;

/**
 * Created by dalv6_000 on 24.07.2016.
 */

@Configuration
@ComponentScan("ru.innopolis.university.summerbootcamp.java.project")
public class CoreConfig {


}
