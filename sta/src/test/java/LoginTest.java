import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("E:/home.html");
    }

    @Test
    public void validLogin() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("loginBtn")).click();

        Thread.sleep(1000); // wait for JS

        String msg = driver.findElement(By.id("message")).getText();
        System.out.println("Valid Login Message: " + msg);

        Assert.assertTrue(msg.contains("Successful"));
    }

    @Test
    public void invalidPassword() throws InterruptedException {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.id("loginBtn")).click();

        Thread.sleep(1000);

        String msg = driver.findElement(By.id("message")).getText();
        System.out.println("Invalid Password Message: " + msg);

        Assert.assertTrue(msg.contains("Invalid"));
    }

    @Test
    public void emptyFields() throws InterruptedException {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("loginBtn")).click();

        Thread.sleep(1000);

        String msg = driver.findElement(By.id("message")).getText();
        System.out.println("Empty Fields Message: " + msg);

        Assert.assertTrue(msg.contains("empty"));
    }

    @AfterMethod
    public void clearFields() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}