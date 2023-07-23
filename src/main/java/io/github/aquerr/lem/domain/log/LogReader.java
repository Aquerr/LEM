package io.github.aquerr.lem.domain.log;

import io.github.aquerr.lem.domain.log.model.Logs;

import java.io.IOException;

/**
 * Base interface for all log readers.
 */
public interface LogReader
{
    Logs readLines(int startLine, int endLine) throws IOException;
}
