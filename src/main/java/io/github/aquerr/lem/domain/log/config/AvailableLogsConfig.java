package io.github.aquerr.lem.domain.log.config;

import io.github.aquerr.lem.domain.log.model.UbuntuLogFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AvailableLogsConfig
{
    @Bean
    public EnumSet<UbuntuLogFile> availableLogFiles()
    {
        return determineAvailableLogs();
    }

    private EnumSet<UbuntuLogFile> determineAvailableLogs()
    {
        Set<UbuntuLogFile> logFiles = Arrays.stream(UbuntuLogFile.values())
                .filter(this::isLogAvailable)
                .collect(Collectors.toSet());
        if (logFiles.isEmpty())
        {
            return EnumSet.noneOf(UbuntuLogFile.class);
        }
        else return EnumSet.copyOf(logFiles);
    }

    private boolean isLogAvailable(UbuntuLogFile ubuntuLogFile)
    {
        return true;
//        File logFile = ubuntuLogFile.getFilePath().toFile();
//        return logFile.exists() && logFile.canRead();
    }
}
