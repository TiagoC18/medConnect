package project.medconnect.seleniumTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SeleniumJupiter.class)
public class StaffMedConnectTest {
  private ChromeDriver driver;

  @Test
  public void staffMedConnect() throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();  
    options.addArguments("--remote-allow-origins=*");

    driver = new ChromeDriver(options);
    driver.get("http://127.0.0.1:5500/staff/html/index.html");
    driver.manage().window().maximize();

    clickWithWait(By.cssSelector("#loginButton .\\_area"));
    clickWithWait(By.id("email"));
    driver.findElement(By.id("email")).sendKeys("staff@ua.pt");
    driver.findElement(By.id("password")).sendKeys("staff");
    clickWithWait(By.cssSelector("button:nth-child(10)"));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.alertIsPresent());
    assertThat(driver.switchTo().alert().getText()).isEqualTo("Login successful!");
    driver.switchTo().alert().accept();

    clickWithWait(By.id("firstName"));
    driver.findElement(By.id("firstName")).sendKeys("Alice");
    driver.findElement(By.cssSelector(".box:nth-child(2) .col1 > div")).click();
    driver.findElement(By.id("lastName")).click();
    driver.findElement(By.id("lastName")).sendKeys("Johnson");
    driver.findElement(By.id("ccNumber")).click();
    driver.findElement(By.id("ccNumber")).sendKeys("123456789");
    driver.findElement(By.id("emailStaff")).click();
    driver.findElement(By.id("emailStaff")).sendKeys("alice@example.com");

    clickWithWait(By.cssSelector("button:nth-child(18)"));
    Actions builder = new Actions(driver);
    builder.moveToElement(driver.findElement(By.cssSelector("button:nth-child(18)"))).perform();
    builder.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();

    clickWithWait(By.cssSelector("button:nth-child(5)"));

    wait.until(ExpectedConditions.alertIsPresent());
    assertThat(driver.switchTo().alert().getText()).isEqualTo("Appointment status updated to Waiting.");
    driver.switchTo().alert().accept();

    clickWithWait(By.cssSelector(".waiting-list-item:nth-child(2) > button"));
    wait.until(ExpectedConditions.alertIsPresent());
    assertThat(driver.switchTo().alert().getText()).isEqualTo("Patient called successfully.");
    driver.switchTo().alert().accept();

    clickWithWait(By.cssSelector(".waiting-list-item:nth-child(4) > button"));
    wait.until(ExpectedConditions.alertIsPresent());
    assertThat(driver.switchTo().alert().getText()).isEqualTo("Appointment marked as done.");
    driver.switchTo().alert().accept();

    clickWithWait(By.cssSelector("#logoutButton .\\_area"));
    
    driver.quit();
  }

  private void clickWithWait(By locator) throws InterruptedException {
    Thread.sleep(1500);  // wait for 1.5 seconds before clicking
    driver.findElement(locator).click();
  }
}
