package task5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class PeriPlusTestCase {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.periplus.com/");
    }

    @AfterClass
    public void tearDown(){
//        driver.quit();
    }

    @Test
    public void loggingIn() throws InterruptedException {

        driver.findElement(By.className("nav-button-title")).click();

        WebElement Email = driver.findElement(By.name("email"));
        Email.sendKeys("baihaqirafli30@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("periplus01");

        WebElement login = driver.findElement(By.id("button-login"));
        login.click();
        Thread.sleep(5000);

        WebElement searchInput = driver.findElement(By.name("filter_name"));
        searchInput.click();
        searchInput.sendKeys("The Memory Book");

        WebElement searchBtn = driver.findElement(By.className("ti-search"));
        searchBtn.click();
        Thread.sleep(5000);


        WebElement item = driver.findElement(By.xpath("(//h3/a[normalize-space(text())='The Memory Book'])[1]"));
        item.click();
        Thread.sleep(5000);

        WebElement addToCart = driver.findElement(By.className("btn-add-to-cart"));
        addToCart.click();
        Thread.sleep(5000);

        WebElement cartButton = driver.findElement(By.id("show-your-cart"));
        cartButton.click();
        Thread.sleep(5000);

        WebElement itemName = driver.findElement(By.linkText("The Memory Book"));
        if (itemName.isDisplayed()) {
            System.out.println("The Memory Book is in the cart.");
        } else {
            System.out.println("The Memory Book is not in the cart.");
        }
    }
}
