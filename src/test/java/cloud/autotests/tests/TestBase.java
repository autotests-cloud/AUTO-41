package cloud.autotests.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static cloud.autotests.helpers.AttachmentsHelper.*;
import static cloud.autotests.helpers.DriverHelper.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@ExtendWith({AllureJunit5.class})
public class TestBase {
    public static final String baseUrl = "https://the-internet.herokuapp.com";
    public static final String username = "tomsmith";
    public static final String password = "SuperSecretPassword!";

    @BeforeEach
    void setUp() {
        configureDriver();
        Configuration.baseUrl = baseUrl;
    }

    @AfterEach
    public void addAttachments(){
        String sessionId = getSessionId();

        attachScreenshot("Last screenshot");
        attachPageSource();
//        attachNetwork(); // todo
        attachAsText("Browser console logs", getConsoleLogs());
        if (isVideoOn()) attachVideo(sessionId);

        closeWebDriver();
    }
}
