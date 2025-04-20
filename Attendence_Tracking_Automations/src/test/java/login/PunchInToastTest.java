package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PunchInToastTest {
    public static void main(String[] args) {
        // 1. Setup WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 2. Open dashboard login page
            driver.get("https://testffc.nimapinfotech.com/");
            driver.manage().window().maximize();

            // 3. Log in
            driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys("manasdabhade12@gmail.com");
            driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("123456789");
            driver.findElement(By.name("Sign In")).click();

            // 4. Wait for redirection to dashboard
            wait.until(ExpectedConditions.urlContains("/dashboard"));

            // 5. Wait for Punch In button and click it
            WebElement punchInButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Punch In')]")));
            punchInButton.click();

            // 6. Wait for toast popup to appear
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'Toastify__toast-body')]")));

            // 7. Get and print the toast message
            String toastText = toast.getText();
            System.out.println(" Toast Message: " + toastText);

            // 8. Validate the toast content
            if (toastText.contains("Punch In Successfully")) {
                System.out.println(" Test Passed: Toast message verified.");
            } else {
                System.out.println(" Test Failed: Unexpected toast message.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
