package io.github.aquerr.lem.domain.log.model;

import lombok.Value;

import java.util.Set;

@Value
public class AvailableLogTypes
{
    Set<String> availableLogTypes;
}
