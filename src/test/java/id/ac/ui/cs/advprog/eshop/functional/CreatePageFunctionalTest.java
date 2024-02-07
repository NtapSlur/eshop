package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreatePageFunctionalTest {

    /**
     * The port number assigned to the running application during test execution. * Set automatically during each test run by Spring Framework's test context. */
    @LocalServerPort
    private int serverPort;
    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl: http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void listProductTitle_isCorrect (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement buttonListProduct = driver.findElement(By.tagName("a"));
        buttonListProduct.click();

        String pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);
    }

    void fillProductForm(ChromeDriver driver, String productName, int productQuantity)
    {
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(productName);
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(productQuantity));
        driver.findElement(By.tagName("button")).click();
    }

    @Test
    void createProduct_isCorrect (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement buttonListProduct = driver.findElement(By.tagName("a"));
        buttonListProduct.click();

        WebElement buttonCreateProduct = driver.findElement(By.tagName("a"));
        buttonCreateProduct.click();

        fillProductForm(driver, "Indomie", 10);

        List<WebElement> tableRows = driver.findElements(By.tagName("td"));
        assertEquals("Indomie", tableRows.get(0).getText());
    }
}

