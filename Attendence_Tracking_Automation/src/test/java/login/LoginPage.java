package login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginPage {
    @Test
    public static void loginToApp(WebDriver driver, String email, String password) throws InterruptedException {

        driver.get("https://testffc.nimapinfotech.com/auth/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement ele = driver.findElement(By.xpath("//html"));
        ele.sendKeys(Keys.PAGE_DOWN);

        WebElement emailInput = driver.findElement(By.id("mat-input-0"));
        emailInput.clear();
        emailInput.sendKeys(email);

        // Locate and fill password
        WebElement passwordInput = driver.findElement(By.id("mat-input-1"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        // Click login button
        WebElement loginBtn = driver.findElement(By.xpath("//button[@id='kt_login_signin_submit']"));
        loginBtn.click();

        // Wait for login to process
        Thread.sleep(5000);

        // Validate login
        try {
            WebElement dashboard = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
            if (dashboard.isDisplayed()) {
                System.out.println("Login Successful!");
            }
        } catch (Exception e) {
            System.out.println("Login Failed: Dashboard not found.");
        }
    }

    public static void main(String[] args) {
        // Set the path to your ChromeDriver

        WebDriver driver = new ChromeDriver();

        // Credentials (parameters)
        String email = "manasdabhade12@gmail.com";
        String password = "123456789";

        try {
            loginToApp(driver, email, password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
