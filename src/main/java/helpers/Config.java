package helpers;

public class Config {
    private TypedProperties testConfig = new TypedProperties("/config.properties");

    public Config() {
    }


    Config(final TypedProperties typedProperties) {
        this.testConfig = typedProperties;
    }

    public String getEnvironment() {
        return System.getProperty("environment", this.testConfig.getValue("environment")).toLowerCase();
    }

    public boolean useSelenium() {
        return Boolean.parseBoolean(System.getProperty("useSelenium", this.testConfig.getValue("use.selenium")).toLowerCase());
    }

    public String getActivityId() {
        return System.getProperty("activityId", this.testConfig.getValue("activity.id")).toLowerCase();
    }

    public String getAdminPassword() {
        return testConfig.getValue("admin.password");
    }

    public String getAdminUsername() {
        return testConfig.getValue("admin.username");
    }

    public String getClientId() {
        return testConfig.getValue("client.id");
    }

    public String getClientSecret() {
        return testConfig.getValue("client.secret");
    }

    public String getScope() {
        return testConfig.getValue("scope");
    }

    public int getTestDuration() {
        return Integer.parseInt(testConfig.getValue("duration"));
    }

    public boolean useRateLimiter() {
        return Boolean.parseBoolean(this.testConfig.getValue("use.rate.limiter"));
    }

    public String getRateLimiter() {
        return testConfig.getValue("rate.limiter");
    }

    public boolean takeScreenshots() {
        return Boolean.parseBoolean(this.testConfig.getValue("screenshots"));
    }

    public boolean printConsole() {
        return Boolean.parseBoolean(this.testConfig.getValue("print.console"));
    }

    public boolean closeBrowsers() {
        return Boolean.parseBoolean(this.testConfig.getValue("close.browsers"));
    }

    public boolean failSessions() {
        return Boolean.parseBoolean(this.testConfig.getValue("fail.sessions"));
    }

    public boolean askQuestions() {
        return Boolean.parseBoolean(this.testConfig.getValue("ask.questions"));
    }

    public boolean takeNotes() {
        return Boolean.parseBoolean(this.testConfig.getValue("take.notes"));
    }

    public boolean introSurvey() {
        return Boolean.parseBoolean(this.testConfig.getValue("intro.survey"));
    }

    public boolean rateSlides() {
        return Boolean.parseBoolean(this.testConfig.getValue("rate.slides"));
    }

    public boolean saveSlides() {
        return Boolean.parseBoolean(this.testConfig.getValue("save.slides"));
    }

    public boolean drawNotes() {
        return Boolean.parseBoolean(this.testConfig.getValue("draw.notes"));
    }

    public boolean loginUsersForApiCalls() {
        return Boolean.parseBoolean(this.testConfig.getValue("api.user.login"));
    }

    public boolean answerArsQuestions() {
        return Boolean.parseBoolean(this.testConfig.getValue("answer.ars.questions"));
    }

    public String getSeleniumGrid() {
        return testConfig.getValue("selenium.grid");
    }

    public String getBrowser() {
        return testConfig.getValue("browser");
    }

    public String getImplicitWait() {
        return testConfig.getValue("implicitly.wait");
    }

    public String getAuthServer() {
        String environment = getEnvironment();

        if(environment.contains("aws")){
            if(environment.contains("prod")) {
                return testConfig.getValue("aws.auth");
            } else if (environment.contains("qa")){
                return testConfig.getValue("aws.auth").replace("arraylive.com", "qa.emaws.net");
            }else {
                return testConfig.getValue("aws.auth").replace("arraylive.com", "dev.emaws.net");

            }
        }else if(environment.contains("virtual")){
            return testConfig.getValue("virtual.auth");
        } else{
            return testConfig.getValue("demo.auth");
        }
    }

    public String getArray3AdminHost() {
        String environment = getEnvironment();
        String host = "";
        host = testConfig.getValue("aws.a3.host");
        if(environment.contains("aws")){
            if(environment.contains("prod")) {
                return host;
            } else if (environment.contains("qa")){
                return host.replace("arraylive.com", "qa.emaws.net");
            }else {
                return host.replace("arraylive.com", "dev.emaws.net");

            }
        }else if(environment.contains("virtual")){
            return testConfig.getValue("virtual.a3.host");
        }
        else{
            return testConfig.getValue("demo.a3.host");
        }
    }


    public String getArray3ApiUri() {
        String environment = getEnvironment();
        String host = "";
        host = testConfig.getValue("aws.a3.uri");
        if(environment.contains("aws")){
            if(environment.contains("prod")) {
                return host;
            } else if (environment.contains("qa")){
                return host.replace("arraylive.com", "qa.emaws.net");
            }else {
                return host.replace("arraylive.com", "dev.emaws.net");

            }
        } else if(environment.contains("virtual")){
            return testConfig.getValue("virtual.a3.uri");
        }
        else{
            return testConfig.getValue("demo.a3.uri");
        }
    }


    public String getClientUrl() {
        String environment = getEnvironment();
        String host = "";
        host = testConfig.getValue("aws.client.url");
        if(environment.contains("aws")){
            if(environment.contains("prod")) {
                return host;
            } else if (environment.contains("qa")){
                return host.replace("arraylive.com", "live.qa.emaws.net");
            }else {
                return host.replace("arraylive.com", "live.dev.emaws.net");

            }
        }else if(environment.contains("virtual")){
            return testConfig.getValue("virtual.client.url");
        }
        else{
            return testConfig.getValue("demo.client.url");
        }
    }

    public String getArray2Url() {
        String environment = getEnvironment();
        String host = "";
        host = testConfig.getValue("aws.a2.url");
        if(environment.contains("aws")){
            if(environment.contains("prod")) {
                return host;
            } else if (environment.contains("qa")){
                return host.replace("arraylive.com", "qa.emaws.net");
            }else {
                return host.replace("arraylive.com", "dev.emaws.net");

            }
        }else if(environment.contains("virtual")){
            return testConfig.getValue("virtual.a2.url");
        }else{
            return testConfig.getValue("demo.a2.url");
        }
    }

}
