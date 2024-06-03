// package project.medconnect.seleniumTest;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.By;
// import org.openqa.selenium.Dimension;
// import org.openqa.selenium.interactions.Actions;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.Select;
// import org.openqa.selenium.support.ui.WebDriverWait;

// import java.time.Duration;

// import io.github.bonigarcia.seljup.SeleniumJupiter;
// import io.github.bonigarcia.wdm.WebDriverManager;

// @ExtendWith(SeleniumJupiter.class)
// public class PatientMedConnectTest {
//   private ChromeDriver driver;

//   @Test
//   public void patientMedConnect() throws InterruptedException {

//     WebDriverManager.chromedriver().setup();
//     ChromeOptions options = new ChromeOptions();  

//     options.addArguments("--remote-allow-origins=*");

//     driver = new ChromeDriver(options);
//     driver.get("http://127.0.0.1:5500/frontend/html/index.html");
//     driver.manage().window().maximize();
    
//     clickWithWait(By.cssSelector("#loginButton .\\_area"));
//     clickWithWait(By.linkText("Create new account"));
    
//     clickWithWait(By.id("firstName"));
//     driver.findElement(By.id("firstName")).sendKeys("User");
//     driver.findElement(By.id("lastName")).click();
//     driver.findElement(By.id("lastName")).sendKeys("Tester");
//     driver.findElement(By.id("dateOfBirth")).click();
//     driver.findElement(By.id("dateOfBirth")).sendKeys("12-12-2001");
    
//     {
//       WebElement dropdown = driver.findElement(By.id("gender"));
//       dropdown.findElement(By.xpath("//option[. = 'Male']")).click();
//     }
//     {
//       WebElement element = driver.findElement(By.id("gender"));
//       Actions builder = new Actions(driver);
//       builder.moveToElement(element).clickAndHold().perform();
//     }
//     {
//       WebElement element = driver.findElement(By.id("gender"));
//       Actions builder = new Actions(driver);
//       builder.moveToElement(element).perform();
//     }
//     {
//       WebElement element = driver.findElement(By.id("gender"));
//       Actions builder = new Actions(driver);
//       builder.moveToElement(element).release().perform();
//     }
    
//     driver.findElement(By.id("ccNumber")).click();
//     driver.findElement(By.id("ccNumber")).sendKeys("123123123");
//     driver.findElement(By.id("phoneNumber")).click();
//     driver.findElement(By.id("phoneNumber")).sendKeys("123123123");
//     driver.findElement(By.id("newEmail")).click();
//     driver.findElement(By.id("newEmail")).sendKeys("usertester@ua.pt");
//     driver.findElement(By.id("new_password")).sendKeys("tester123");
//     driver.findElement(By.id("confirm_password")).click();
//     driver.findElement(By.id("confirm_password")).sendKeys("tester123");
    
//     clickWithWait(By.cssSelector("button:nth-child(1)"));
    
//     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//     wait.until(ExpectedConditions.alertIsPresent());
//     assertThat(driver.switchTo().alert().getText()).isEqualTo("Registration successful! You can now log in.");
//     driver.switchTo().alert().accept();
    
//     clickWithWait(By.id("email"));
//     driver.findElement(By.id("email")).sendKeys("usertester@ua.pt");
//     driver.findElement(By.id("password")).sendKeys("tester123");
    
//     clickWithWait(By.cssSelector("button:nth-child(10)"));
    
//     wait.until(ExpectedConditions.alertIsPresent());
//     assertThat(driver.switchTo().alert().getText()).isEqualTo("Login successful!");
//     driver.switchTo().alert().accept();
    
//     //clicar no appointment
//     clickWithWait(By.cssSelector("li a[href='#!/pageAppointment']"));
    
//     clickWithWait(By.id("appointment-date"));
//     driver.findElement(By.id("appointment-date")).sendKeys("06-12");
    
//     Thread.sleep(1000);

//     WebElement dropdown = driver.findElement(By.id("specialty"));
//     Select select = new Select(dropdown);
//     select.selectByVisibleText("Cardiology");
    
//     clickWithWait(By.id("checkMedics"));
//     clickWithWait(By.id("checkHours"));
    
//     Thread.sleep(1500);

//     WebElement dropdown2 = driver.findElement(By.id("time"));
//     Select select2 = new Select(dropdown2);
//     select2.selectByVisibleText("11h");
    
//     clickWithWait(By.id("bookAppointment"));

//     WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
//     wait3.until(ExpectedConditions.alertIsPresent());
//     assertThat(driver.switchTo().alert().getText()).isEqualTo("Appointment booked successfully!");
//     driver.switchTo().alert().accept();

//     clickWithWait(By.id("oldagenda"));
//     clickWithWait(By.linkText("Return to Agenda"));
    
//     driver.quit();
//   }
  
//   private void clickWithWait(By locator) throws InterruptedException {
//     Thread.sleep(1500);  // wait for 1 second before clicking
//     driver.findElement(locator).click();
//   }
// }
