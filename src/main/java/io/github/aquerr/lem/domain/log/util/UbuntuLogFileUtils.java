package io.github.aquerr.lem.domain.log.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UbuntuLogFileUtils
{
    private static final Path VAR_LOG_DIR = Paths.get("/var/log");
    public static final Path AUTH_LOG_FILE = VAR_LOG_DIR.resolve("auth.log");
    public static final Path UFW_LOG = VAR_LOG_DIR.resolve("ufw.log");
    public static final Path DPKG_LOG = VAR_LOG_DIR.resolve("dpkg.log");
    public static final Path KERN_LOG = VAR_LOG_DIR.resolve("kern.log");
    public static final Path FAIL2BAN_LOG = VAR_LOG_DIR.resolve("fail2ban.log");
    public static final Path BOOTSTRAP_LOG = VAR_LOG_DIR.resolve("bootstrap.log");
    public static final Path APPORT_LOG = VAR_LOG_DIR.resolve("apport.log");
    public static final Path ALTERNATIVES_LOG = VAR_LOG_DIR.resolve("alternatives.log");


    public static final Path APACHE_2_DIR = VAR_LOG_DIR.resolve("apache2");
    public static final Path APACHE_2_ACCESS_LOG = APACHE_2_DIR.resolve("access.log");
    public static final Path APACHE_2_ERROR_LOG = APACHE_2_DIR.resolve("error.log");
    public static final Path APACHE_2_OTHER_VHOSTS_ACCESS_LOG = APACHE_2_DIR.resolve("other_vhosts_access.log");
}
