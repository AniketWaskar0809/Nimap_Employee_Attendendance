package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AddCustomer {
        WebDriver driver;

        @Parameters({"username", "password"})
        @BeforeClass
        public void setUp(String username, String password) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://testffc.nimapinfotech.com/");

            driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys("manasdabhade12@gmail.com");
            driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("123456789");
            driver.findElement(By.id("kt_login_signin_submit")).click(); // Update with actual ID
            waitForSeconds(3);
        }

        @DataProvider(name = "customerData")
        public Object[][] customerData() {
            return new Object[][] {
                    {"John Doe", "john@example.com", "1234567890"},
                    {"Jane Smith", "jane@example.com", "9876543210"}
            };
        }

        @Test(dataProvider = "customerData")
        public void addCustomer(String name, String email, String phone) {
            driver.findElement(By.linkText("Customers")).click(); // Or the actual nav element
            waitForSeconds(1);
            driver.findElement(By.id("addCustomerBtn")).click(); // Replace with actual ID
            waitForSeconds(1);

            driver.findElement(By.id("name")).sendKeys(name);
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("phone")).sendKeys(phone);

            driver.findElement(By.id("submitCustomer")).click(); // Replace with actual ID
            waitForSeconds(2);

            WebElement successMessage = driver.findElement(By.className("alert-success"));
            Assert.assertTrue(successMessage.getText().contains("Customer added successfully"));

            WebElement search = driver.findElement(By.id("searchCustomer")); // Replace with actual ID
            search.sendKeys(name);
            search.submit();
            waitForSeconds(1);

            Assert.assertTrue(driver.getPageSource().contains(name), "Customer not found in list.");
        }

        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        private void waitForSeconds(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException ignored) {}
        }
}
