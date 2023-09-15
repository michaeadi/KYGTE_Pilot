package org_automation.base;

import org.openqa.selenium.By;

public class PageObjects {

    public static String pageURL = "https://www.kygchain.app/home";

    public static By loginHeader = By.xpath("//*[@id=\"ApplicationHeaderMainContainer\"]/a");

    public static By emailField = By.cssSelector("input[type=\"email\"]");

    public static By passwordField = By.cssSelector("input[type=\"password\"]");

    public static By loginButton = By.xpath("//button[@id=':r3:' or @name='Log In']");

    public static By kygLogo = By.xpath("//*[@id=\"ApplicationHeaderMainContainer\"]/div/a");

    public static By attrDef = By.xpath("//*[@id=\"root\"]/main/div/aside[2]/ul/li[1]/span/a");

    public static By userGuide = By.xpath("//*[@id=\"root\"]/main/div/aside[2]/ul/li[2]/span/a");

    public static By contactUs = By.xpath("//*[@id=\"root\"]/main/div/aside[2]/ul/li[3]/span/a");

    public static By horizLogo = By.xpath("//*[@id=\"root\"]/main/div/aside[2]/div/img");

    public static By productCode = By.xpath("//*[@id=\"productCode\"]");

    public static By searchRegB = By.xpath("//*[@id=\":r0:\"]");

    public static By createNewB = By.xpath("//*[@id=\":r1:\"]");

    public static By advancedSearchB = By.xpath("//*[@id=\":r2:\"]");

    public static By newItemBtn = By.xpath("//*[@id=\"create-button\"]");

    public static By itemTypes = By.xpath("//*[@id=\"create-product\"]/div[3]/ul");

    public static By itemName = By.xpath("//*[@id=\"NAME\"]");

    public static By itemDesc = By.xpath("//*[@id=\"DESCRIPTIONSHORT\"]");

    public static By itemNum = By.xpath("//*[@id=\"SKU\"]");

    public static By itemRev = By.xpath("//*[@id=\"REVISION\"]");

    public static By createProdBtn = By.xpath("//*[@id=\":r18:\" or @id=\":ru:\"]");

    public static By prodWrapper = By.xpath("//*[@id=\"root\"]/main/div/div/div/div/div/div[3]/div/div[1]/div/div");

    public static By selectKYGID = By.xpath("//*[@id=\"tableWrapperReport\"]/div/table/tbody/tr[1]/td[1]/div/span/div/span/a");

    public static By createECN = By.xpath("//*[@id=\"create-product\"]/div[3]/ul/span[1]/li");

    public static By createHS = By.xpath("//*[@id=\"create-product\"]/div[3]/ul/span[2]/li");








}

