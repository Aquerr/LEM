package io.github.aquerr.lem.domain.log;

import io.github.aquerr.lem.domain.log.exception.CouldNotReadLogException;
import io.github.aquerr.lem.domain.log.exception.UnavailableLogFileException;
import io.github.aquerr.lem.domain.log.model.AvailableLogTypes;
import io.github.aquerr.lem.domain.log.model.Logs;
import io.github.aquerr.lem.domain.log.model.UbuntuLogFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.stream.Collectors;

import static java.lang.String.format;

@AllArgsConstructor
@Service
public class LogService
{
    private final EnumSet<UbuntuLogFile> availableLogFiles;

    public AvailableLogTypes getAvailableLogTypes()
    {
        return new AvailableLogTypes(availableLogFiles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));
    }

    public Logs readLines(UbuntuLogFile ubuntuLogFile, int startLine, int lines)
    {
        if (!isLogFileAvailable(ubuntuLogFile))
        {
            throw new UnavailableLogFileException(format("Log file: %s is not supported!", ubuntuLogFile.name()));
        }

        try
        {
            return new DefaultLogReader(ubuntuLogFile.getFilePath().toFile()).readLines(startLine, lines);
        }
        catch (Exception exception)
        {
            throw new CouldNotReadLogException(exception.getMessage());
        }
    }

    private boolean isLogFileAvailable(UbuntuLogFile ubuntuLogFile)
    {
        return availableLogFiles.contains(ubuntuLogFile);
    }
}
