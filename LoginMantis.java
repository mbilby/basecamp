package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginMantis {

    private ChromeDriver driver;


    @Before
    public void inicializar() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://mantis-prova.base2.com.br");
        driver.navigate().to("https://mantis-prova.base2.com.br");
    }

    @After
    public void finalizar() {
       //driver.quit();
    }

    @Test
    public void correctPage() {
        String titulo = driver.getTitle();
        Assert.assertEquals("MantisBT", titulo);
    }


    @Test
    public void loginSucess() {
        correctPage();
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));

        usernameField.sendKeys("marcelo.barbosa");
        passwordField.sendKeys("1234567890");
        loginButton.click();
        WebElement loggedUser = driver.findElement(By.xpath("//td[@class='login-info-left']/span[@class='italic']"));
        Assert.assertEquals("marcelo.barbosa", loggedUser.getText());
    }

    @Test
    public void loginFailure() {
        correctPage();
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));

        usernameField.sendKeys("marcelo.barbosa");
        passwordField.sendKeys("1234");
        loginButton.click();
        WebElement loginFail = driver.findElement(By.xpath("//font[@color='red']"));
        Assert.assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.", loginFail.getText());
    }

    @Test
    public void logout() {
        correctPage();
        loginSucess();
        WebElement linkLogout = driver.findElement(By.linkText("Logout"));
        linkLogout.click();
        WebElement usernameText = driver.findElement(By.xpath("//td[@class='category']"));
        Assert.assertEquals("Username", usernameText.getText());


    }




    }


