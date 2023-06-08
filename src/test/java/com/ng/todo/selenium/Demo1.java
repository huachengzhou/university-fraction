package com.ng.todo.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Demo1 {


    @Test
    public void test2(){
        String projectPath = System.getProperty("user.dir") ;
        System.out.println(projectPath);
    }


    @Test
    public void test1(){
        String projectPath = System.getProperty("user.dir") + File.separator +"src\\main\\resources\\lib\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",projectPath);   //设置chrome驱动程序的路径
        //1. 使用驱动实例开启会话
        WebDriver driver = new ChromeDriver();


        //2. 在浏览器上执行操作

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        //3. 请求 浏览器信息
        String title = driver.getTitle();

        //4. 建立等待策略
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //5发送命令 查找元素
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));


        //6:操作元素
        textBox.sendKeys("Selenium");
        submitButton.click();

        //7. 获取元素信息
        String value = driver.findElement(By.name("body")).getText();

        //8. 结束会话
        driver.quit();

    }



}
