package io.github.aquerr.lem.web.rest;

import io.github.aquerr.lem.domain.log.LogService;
import io.github.aquerr.lem.domain.log.model.AvailableLogTypes;
import io.github.aquerr.lem.domain.log.model.Logs;
import io.github.aquerr.lem.domain.log.model.UbuntuLogFile;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/v1/logs")
@RestController
public class LogRestController
{
    private final LogService logService;

    @GetMapping("/available-log-types")
    public AvailableLogTypes getAvailableLogTypes()
    {
        return logService.getAvailableLogTypes();
    }

    @GetMapping("/logs")
    public Logs getLogs(@RequestParam(name = "log_type") UbuntuLogFile ubuntuLogFile,
                        @RequestParam(name = "start_line", defaultValue = "0") int startLine,
                        @RequestParam(name = "lines", defaultValue = "10") int endLine)
    {
        return logService.readLines(ubuntuLogFile, startLine, endLine);
    }
}
