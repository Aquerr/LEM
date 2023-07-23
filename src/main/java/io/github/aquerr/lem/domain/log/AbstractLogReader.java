package io.github.aquerr.lem.domain.log;

import io.github.aquerr.lem.domain.log.model.Logs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractLogReader implements LogReader
{
    protected final File file;

    protected AbstractLogReader(File file)
    {
        this.file = file;
    }

    @Override
    public Logs readLines(int startLine, int lines) throws IOException
    {
        if (lines > 10)
            lines = 10;

        List<String> fileLines = Files.readAllLines(file.toPath());

        // Correct index
        if (startLine < 0)
            startLine = 0;
        else if (startLine > fileLines.size() - 1)
            return Logs.of(fileLines.size(), startLine, lines, Collections.emptyList());

        // Correct lines
        if (startLine + lines > fileLines.size() - 1)
            lines = fileLines.size() - startLine;

        List<String> result = new ArrayList<>(lines);
        for (int i = startLine; i < startLine + lines; i++)
        {
            result.add(fileLines.get(i));
        }
        return Logs.of(fileLines.size(), startLine, lines, result);
    }
}
