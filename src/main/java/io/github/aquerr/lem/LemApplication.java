package io.github.aquerr.lem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LemApplication implements CommandLineRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(LemApplication.class);
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (!System.getProperty("os.name").endsWith("ux"))
        {
            log.warn("This application supports Linux only and will not work properly on other operating systems! Sorry! :(");
        }
    }
}
