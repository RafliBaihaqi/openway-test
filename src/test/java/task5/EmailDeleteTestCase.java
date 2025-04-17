package task5;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class EmailDeleteTestCase {
    WebDriver driver;


    @BeforeClass
    public void setUp(){
        String user = "HP";
        String profile = "Profile 20";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\" + user + "\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=" + profile);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/mail/");
    }

    @AfterClass
    public void tearDown(){
         driver.quit();
    }

    private static final By TITLE_PLACE = By.xpath("//span[@class='bog']/span");

    private List<WebElement> getEmailTitles() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(TITLE_PLACE));
    }

    @Test
    public void DeleteEmail() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        boolean retry = true;
        while (retry) {
            retry = false;
            List<WebElement> emailTitles = getEmailTitles();

            if (emailTitles.isEmpty()) {
                System.out.println("No emails found. Stopping the process.");
                break;

            }
            for (WebElement emailTitle : emailTitles) {
                try {
                    String fontWeight = emailTitle.getCssValue("font-weight");

                    if (fontWeight.equals("700") || fontWeight.equals("bold")) {
                        System.out.println("Unread Email title: " + emailTitle.getText());
                    } else {
                        System.out.println("Email title: " + emailTitle.getText());
                        emailTitle.click();
                        Thread.sleep(5000);


                        WebElement groupElement = driver.findElement(By.xpath("//div[@class='iH bzn']//div[@class='G-tF']//div[2][@class='G-Ni G-aE J-J5-Ji']"));
                        Actions action = new Actions(driver);
                        action.moveToElement(groupElement).build().perform();
                        driver.findElement(By.xpath("(//div[@aria-label='Delete']//div[@class='asa'])[2]")).click();
                        System.out.println("Email deleted");


                        try {
                            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(TITLE_PLACE));
                            retry = true;
                        } catch (org.openqa.selenium.TimeoutException e) {
                            System.out.println("No more emails found after deletion. Inbox is empty.");
                            retry = false;
                        }

                        break;
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("Element is stale, refetching and retrying...");
                    retry = true;
                    break;
                }
            }
        }
    }


}
