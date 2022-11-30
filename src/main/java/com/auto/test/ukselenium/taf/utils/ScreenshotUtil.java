package com.auto.test.ukselenium.taf.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;

@Lazy
@Service
public class ScreenshotUtil {

    @Autowired
    private ApplicationContext ctx;

    @Value("${screenshot.path}")
    private Path path;

    public void takeScreenshot() throws IOException {

        boolean b = this.path.toFile().exists() ? this.path.toFile().isDirectory() : this.path.toFile().mkdirs();
        File file = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
        FileCopyUtils.copy(file, this.path.resolve("Screenshot_" + Instant.now().getEpochSecond() + ".png").toFile());

    }

}
