package io.github.aquerr.lem.domain.log.model;

import io.github.aquerr.lem.domain.log.util.UbuntuLogFileUtils;

import java.nio.file.Path;

public enum UbuntuLogFile
{
    AUTH(UbuntuLogFileUtils.AUTH_LOG_FILE),
    UFW(UbuntuLogFileUtils.UFW_LOG),
    DPKG(UbuntuLogFileUtils.DPKG_LOG),
    KERN(UbuntuLogFileUtils.KERN_LOG),
    FAIL2BAN(UbuntuLogFileUtils.FAIL2BAN_LOG),
    BOOTSTRAP(UbuntuLogFileUtils.BOOTSTRAP_LOG),
    APPORT(UbuntuLogFileUtils.APPORT_LOG),
    ALTERNATIVES(UbuntuLogFileUtils.ALTERNATIVES_LOG),

    APACHE2_ACCESS(UbuntuLogFileUtils.APACHE_2_ACCESS_LOG),
    APACHE2_ERROR(UbuntuLogFileUtils.APACHE_2_ERROR_LOG),
    APACHE2_OTHER_VHOSTS_ACCESS(UbuntuLogFileUtils.APACHE_2_OTHER_VHOSTS_ACCESS_LOG);

    private final Path filePath;

    UbuntuLogFile(Path filePath)
    {
        this.filePath = filePath;
    }

    public Path getFilePath()
    {
        return filePath;
    }
}