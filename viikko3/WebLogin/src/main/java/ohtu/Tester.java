package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);

        
      /*  WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        //element.sendKeys("pekka");
        element.sendKeys("mikko");
        element = driver.findElement(By.name("password"));
        //element.sendKeys("akkep");
        element.sendKeys("ilmapallo123");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();*/

       String username = createAccount(driver);
        sleep(2);
        logoutAfterCreation(driver);
        sleep(2);
        correctUserWrongPassword(driver, username);
        sleep(2);
        driver.quit();

    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static String createAccount(WebDriver driver) {
        Random r = new Random();
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);

        //Selects username and sends input
        element = driver.findElement(By.name("username"));
        String username = "Marko"+r.nextInt(100000);
        element.sendKeys(username);

        //Inputs passwords
        element = driver.findElement(By.name("password"));
        element.sendKeys("asd1234");


        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("asd1234");
        sleep(2);

        element = driver.findElement(By.name("signup"));
        element.click();
        return username;
    }

    private static void logoutAfterCreation(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();

    }

    private static void correctUserWrongPassword(WebDriver driver, String username) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);

        element = driver.findElement(By.name("password"));
        element.sendKeys("wrongpassword");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

    }
}
