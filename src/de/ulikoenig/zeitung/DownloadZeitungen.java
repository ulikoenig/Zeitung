package de.ulikoenig.zeitung;

import static org.junit.Assert.fail;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DownloadZeitungen {



	public static void main(String[] args) throws Exception {
		JUnitCore.main("de.ulikoenig.zeitung.DownloadZeitungen");
	}

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {

		String downloadFilepath = "/home/seluser";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new RemoteWebDriver(new URL(
				"http://192.168.188.10:4444/wd/hub"), capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	// public void testLNHLDownload() throws Exception {
	// driver.get(baseUrl + "/luebecker-nachrichten/login/?se=ep");
	// driver.findElement(By.id("user")).clear();
	// driver.findElement(By.id("user")).sendKeys(
	// Credentials.LN2_USERNAME);
	// driver.findElement(By.id("pw")).clear();
	// driver.findElement(By.id("pw")).sendKeys(Credentials.LN2_PASSWORD);
	// driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
	// driver.findElement(By.cssSelector("a[title=\"Ausgabe als PDF\"] > img"))
	// .click();
	// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	// driver.get("http://8.8.8.8");
	// driver.manage().deleteAllCookies();
	// }

	@Test
	public void testLNOHSDownload() throws Exception {
		baseUrl = "https://www.ln-medienhaus.de/";
		driver.get(baseUrl + "/luebecker-nachrichten/login/?se=ep");
		driver.findElement(By.id("user")).clear();
		driver.findElement(By.id("user")).sendKeys(Credentials.LN_USERNAME);
		driver.findElement(By.id("pw")).clear();
		driver.findElement(By.id("pw")).sendKeys(Credentials.LN_PASSWORD);
		driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
		driver.findElement(By.linkText("Bad Schwartauer Nachrichten")).click();
		driver.findElement(By.cssSelector("a[title=\"Ausgabe als PDF\"] > img"))
				.click();
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://8.8.8.8");
		} catch (TimeoutException e) {
		}
		driver.manage().deleteAllCookies();
	}

	@Test
	public void testLNOHLDownload() throws Exception {
		baseUrl = "https://www.ln-medienhaus.de/";
		driver.get(baseUrl + "/luebecker-nachrichten/login/?se=ep");
		driver.findElement(By.id("user")).clear();
		driver.findElement(By.id("user")).sendKeys(Credentials.LN_USERNAME);
		driver.findElement(By.id("pw")).clear();
		driver.findElement(By.id("pw")).sendKeys(Credentials.LN_PASSWORD);
		driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
		driver.findElement(By.linkText("Lübecker Nachrichten")).click();
		driver.findElement(By.cssSelector("a[title=\"Ausgabe als PDF\"] > img"))
				.click();
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://8.8.8.8");
		} catch (TimeoutException e) {
		}
		driver.manage().deleteAllCookies();
	}

	@Test
	public void testSHZDownload() throws Exception {
		LinkedList<String> zeitungen = new LinkedList<String>();
		zeitungen.add("http://zeitung.shz.de/nordfrieslandtageblatt/");
		zeitungen.add("http://zeitung.shz.de/ostholsteineranzeiger/");
		zeitungen.add("http://zeitung.shz.de/schleibote/");
		zeitungen
				.add("http://zeitung.shz.de/schleswigholsteinischelandeszeitung/");
		zeitungen.add("http://zeitung.shz.de/schleswigernachrichten/");
		zeitungen.add("http://zeitung.shz.de/stormarnertageblatt/");
		zeitungen.add("http://zeitung.shz.de/sylterrundschau/");
		zeitungen.add("http://zeitung.shz.de/wilsterschezeitung//");
		zeitungen.add("http://zeitung.shz.de/shzdernordschleswiger/");
		zeitungen.add("http://zeitung.shz.de/derinselbote/");
		zeitungen.add("http://zeitung.shz.de/eckernforderzeitung/");
		zeitungen.add("http://zeitung.shz.de/flensburgertageblatt/");
		zeitungen.add("http://zeitung.shz.de/glueckstaedterfortuna/");
		zeitungen.add("http://zeitung.shz.de/husumernachrichten/");
		zeitungen.add("http://zeitung.shz.de/norddeutscherundschau/");
		baseUrl = "http://zeitung.shz.de";

		boolean ok = false;
		try {
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://zeitung.shz.de/stormarnertageblatt/1494/");
			driver.findElement(By.id("user")).clear();
			driver.findElement(By.id("user")).sendKeys(Credentials.SHZ_USERNAME);
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(Credentials.SHZ_PASSWORD);
			driver.findElement(By.name("submit")).click();
			ok = true;
		} catch (WebDriverException e) {
			System.out.println("Problem 1, Login");
		}

		for (Iterator<String> iterator = zeitungen.iterator(); iterator.hasNext();) {
			String zeitung = (String) iterator.next();
			ok = false;
			while (!ok) {
				try {
					System.out.print("Downloading: " + zeitung + "...");
					driver.manage().timeouts()
							.implicitlyWait(30, TimeUnit.SECONDS);
					driver.get(zeitung);
					//System.out.println("URL: "+driver.getCurrentUrl());
					driver.get(driver.getCurrentUrl());
					driver.get(driver.getCurrentUrl() + "fullpdf/?platform=desktop");
					System.out.println("done.");
					ok = true;
				} catch (WebDriverException e) {
					System.out.println("Problem " + zeitung);
				}
			}

		}
		// Warten
		try {
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			driver.get("http://8.8.8.8");
		} catch (TimeoutException e) {
		}
		driver.manage().deleteAllCookies();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
