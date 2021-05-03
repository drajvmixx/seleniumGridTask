package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static driver.SingletonDriver.getDriver;

public class MainPage extends AbstractPage {
    @FindBy(xpath = "//*[@data-position='CONTROL_A']")
    private WebElement controlA;

    @FindBy(xpath = "//*[@data-position='CONTROL_B']")
    private WebElement controlB;

    @FindBy(xpath = "//*[@data-e2e='zoom-control-block' and contains(.,'Projects')]")
    private WebElement releaseControl;

    @FindBy(xpath = "//*[@data-e2e=\"perspective-selector\"]")
    private WebElement pespectiveSelector;

    @FindBy(xpath = "//*[@data-e2e='perspective-wrapper']//*[text()='Overall']")
    private WebElement overalItem;

    @FindBy(xpath = "//*[@data-e2e='perspective-wrapper']")
    private WebElement perspectiveWrapper;

    @FindBy(xpath = "//*[@data-e2e=\"tree-map-container\"]")
    private WebElement treemapElement;

    @FindBy(xpath = "(//*[@data-e2e=\"tree-map-container\"]/*[contains(.,'-')])[1]")
    private WebElement unitItemFromTreemap;

    @FindBy(xpath = "//*[@data-title='Health map']")
    private WebElement healthMap;

    public MainPage(SingletonDriver.Type type) {
        super(getDriver(type));
    }

    private final static String moveZoomControlJS = "function getElementByXpath(path) {\n" +
            "    return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)\n" +
            "        .singleNodeValue;\n" +
            "}\n" +
            "\n" +
            "async function triggerMouseEvent(element, to, type) {\n" +
            "    let mouseMoveEvent = document.createEvent('MouseEvents');\n" +
            "    mouseMoveEvent.initMouseEvent(\n" +
            "        type,\n" +
            "        true,\n" +
            "        false,\n" +
            "        window,\n" +
            "        1,\n" +
            "        to.left,\n" +
            "        to.top,\n" +
            "        to.left,\n" +
            "        to.top,\n" +
            "        false,\n" +
            "        false,\n" +
            "        false,\n" +
            "        false,\n" +
            "        0,\n" +
            "        null\n" +
            "    );\n" +
            "    await element.dispatchEvent(mouseMoveEvent);\n" +
            "}\n" +
            "\n" +
            "async function moveSliders(sliderEl, destinationEl) {\n" +
            "    // the position between start and destionation\n" +
            "    let midPoint = {\n" +
            "        top: destinationEl.getBoundingClientRect().top,\n" +
            "        left: 0.5 * (sliderEl.getBoundingClientRect().left + destinationEl.getBoundingClientRect().left)\n" +
            "    }\n" +
            "\n" +
            "    await triggerMouseEvent(sliderEl, sliderEl.getBoundingClientRect(), \"mouseover\");\n" +
            "    await triggerMouseEvent(sliderEl, sliderEl.getBoundingClientRect(), \"mousedown\");\n" +
            "\n" +
            "    await triggerMouseEvent(sliderEl, midPoint, \"mousemove\")\n" +
            "    await new Promise(r => setTimeout(r, 10));\n" +
            "    await triggerMouseEvent(sliderEl, destinationEl.getBoundingClientRect(), \"mousemove\")\n" +
            "    await new Promise(r => setTimeout(r, 10));\n" +
            "\n" +
            "    await triggerMouseEvent(sliderEl, destinationEl.getBoundingClientRect(), \"mouseup\");\n" +
            "    await triggerMouseEvent(sliderEl, destinationEl.getBoundingClientRect(), \"click\");\n" +
            "}\n" +
            "\n" +
            "function vividusXPathToJsXPathAdapter(s) {\n" +
            "    if (s.toLowerCase().startsWith(\"by.xpath(\") === true) {\n" +
            "        return s.substring(9, s.length - 1);\n" +
            "    }\n" +
            "    return s;\n" +
            "}\n" +
            "\n" +
            "function getElementByPosition(left, elem1, elem2) {\n" +
            "    if (elem1.getBoundingClientRect().left === left) {\n" +
            "        return elem1;\n" +
            "    } else if (elem2.getBoundingClientRect().left === left) {\n" +
            "        return elem2;\n" +
            "    } else {\n" +
            "        return null;\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "async function moveSlidersToPosition(slider1Xpath, slider2Xpath, dest1Xpath, dest2Xpath) {\n" +
            "    let destination1Xpath = vividusXPathToJsXPathAdapter(dest1Xpath);\n" +
            "    let destination2Xpath = vividusXPathToJsXPathAdapter(dest2Xpath);\n" +
            "\n" +
            "    // We need to store original positions of sliders and destinations to understand which slider wasn't moved yet.\n" +
            "    // Looks like FE rebuilds DOM structure of sliders and change their classes according to their possitions so cannot find any other possibility to track which slider wasn't moved yet.\n" +
            "    let sliderPositions = [\n" +
            "        getElementByXpath(slider1Xpath).getBoundingClientRect().left,\n" +
            "        getElementByXpath(slider2Xpath).getBoundingClientRect().left\n" +
            "    ];\n" +
            "    let destinationPositions = [\n" +
            "        getElementByXpath(destination1Xpath).getBoundingClientRect().left,\n" +
            "        getElementByXpath(destination2Xpath).getBoundingClientRect().left\n" +
            "    ];\n" +
            "    sliderPositions.sort();\n" +
            "    destinationPositions.sort();\n" +
            "\n" +
            "    await moveSliders(\n" +
            "        getElementByPosition(sliderPositions[0], getElementByXpath(slider1Xpath), getElementByXpath(slider2Xpath)),\n" +
            "        getElementByPosition(destinationPositions[0], getElementByXpath(destination1Xpath), getElementByXpath(destination2Xpath))\n" +
            "    );\n" +
            "\n" +
            "    await new Promise(r => setTimeout(r, 300));\n" +
            "\n" +
            "    await moveSliders(\n" +
            "        getElementByPosition(sliderPositions[1], getElementByXpath(slider1Xpath), getElementByXpath(slider2Xpath)),\n" +
            "        getElementByPosition(destinationPositions[1], getElementByXpath(destination1Xpath), getElementByXpath(destination2Xpath))\n" +
            "    );\n" +
            "}\n" +
            "\n" +
            "await moveSlidersToPosition(\n" +
            "    \"//*[@data-position='CONTROL_A']\",\n" +
            "    \"//*[@data-position='CONTROL_B']\",\n" +
            "    \"//*[@data-e2e='zoom-control-block' and contains(.,'Projects')]\",\n" +
            "    \"//*[@data-e2e='zoom-control-block' and contains(.,'Projects')]\"\n" +
            ");";

    public void moveZoomControl() {
        WebDriverWaiter.waitForPageLoadComplete(driver);
        ((JavascriptExecutor) driver).executeScript(moveZoomControlJS);
        WebDriverWaiter.waitForPageLoadComplete(driver);
    }

    public void launchPrespectiveList() {
        pespectiveSelector.click();
    }

    public void selectOverallItem() {
        WebDriverWaiter.waitVisibilityOfElement(driver, perspectiveWrapper);
        overalItem.click();
        WebDriverWaiter.waitForPageLoadComplete(driver);
    }

    public boolean treemapIsShown() {
        return treemapElement.isDisplayed();
    }

    public String returnUnitNameFromTreemap() {
        WebDriverWaiter.waitUntilElementIsClickable(driver, unitItemFromTreemap);
        return unitItemFromTreemap.getText();
    }

    public void proceedToCertainUnit() {
        WebDriverWaiter.waitUntilElementIsClickable(driver, unitItemFromTreemap);
        try {
            unitItemFromTreemap.click();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
        WebDriverWaiter.waitForPageLoadComplete(driver);
    }

    public void waitOnHealthMap() {
        WebDriverWaiter.waitVisibilityOfElement(driver, controlA);
    }
}
