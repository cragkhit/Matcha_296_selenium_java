package com.jprotractor.scripts;

/**
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 */
public final class ResumeAngularBootstrap implements Script {
    @Override
    public String content() {
        return new Loader("resumeAngularBootstrap").content();
    }
}
