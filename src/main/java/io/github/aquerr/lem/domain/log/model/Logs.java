package io.github.aquerr.lem.domain.log.model;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class Logs
{
    int totalLines;
    int startLine;
    int endLine;
    List<String> lines;
}
