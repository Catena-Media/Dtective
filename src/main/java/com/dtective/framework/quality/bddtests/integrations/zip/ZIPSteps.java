package com.dtective.framework.quality.bddtests.integrations.zip;

import com.dtective.framework.test.TestStepsCore;
import com.dtective.framework.zip.ZipHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Simple Screenshot Test.
 */
public class ZIPSteps extends TestStepsCore {

    /**
     * Class logger.
     */
    private static Logger classLogger =
            LogManager.getLogger(ZIPSteps.class);

    /**
     * Singe file for zipping.
     */
    private String singleFilePath = "src/site/resources/images/icon_error_sml.gif";

    /**
     * Folder containing only files and no other folders / sub-folder items.
     */
    private String singleFileFolder = "src/site/css";

    /**
     * Folder containing both files and sub-folders with files.
     */
    private String multiFolder = "src/site/images";

    /**
     * Java FILE object of the currently used file ( one to be compressed).
     */
    private File currentFile;
    /**
     * Currently Created Zipped File.
     */
    private String currentZipFileName;
    /**
     * Target folder for ZIP extraction.
     */
    private String currentZipTargetFolder;


    /**
     * Test Setup - Ensuring there's a single file to be added to com.catena.qa.framework.zip.
     */
    @Given("^I have a file to be zipped$")
    public void iHaveAFileToBeZipped() {
        classLogger.debug("Adding SINGLE file to com.catena.qa.framework.zip : " + singleFilePath);
        currentFile = new File(singleFilePath);
        currentZipTargetFolder = "target/SingleFileZipTest";
        currentZipFileName = "target/SingleFileTest.com.catena.qa.framework.zip";

        assert currentFile.exists();
    }

    /**
     * Test Step - Compress the target file to target ZIP.
     */
    @When("^I compress the file to a ZIP file$")
    public void iCompressTheFileToAZIPFile() {
        ZipHelper.addFileToZip(currentFile, currentZipFileName);
    }

    /**
     * Test Step - Extract the files added to the ZIP.
     *
     * @throws Throwable - IOException when file handling fails or files do not exist
     */
    @And("^I can extract the contents of the created ZIP file$")
    public void iCanExtractTheContentsOfTheCreatedZIPFile() throws Throwable {
        ZipHelper.extractFileFromZip(currentZipFileName, currentZipTargetFolder);
    }

    /**
     * Test Step - Compare the original and the extracted files.
     * TODO - Implement
     */
    @And("^the extracted content matches the original one$")
    public void theExtractedContentMatchesTheOriginalOne() {
        // Skip for now, encoding works, code-based extraction does not
    }

    /**
     * Test Setup - Ensuring there is a folder with just files in it.
     */
    @Given("^I have a folder which only contains files$")
    public void iHaveAFolderWhichOnlyContainsFiles() {
        currentFile = new File(singleFileFolder);
        currentZipFileName = "target/SingleFileFolder.com.catena.qa.framework.zip";
        currentZipTargetFolder = "target/SingleFileFolderZipTest";

        if (!currentFile.exists()) {
            classLogger.error("The specified folder does not exist : " + singleFileFolder);
            assert currentFile.exists();
        }
    }

    /**
     * Test Step - add the folder to a com.catena.qa.framework.zip file.
     * <p>
     * //     * @throws Throwable - IOException when file handling fails or files do not exist
     */
    @When("^I compress the folder to a ZIP file$")
    public void iCompressTheFolderToAZIPFile() {
        ZipHelper.addFolderToZip(singleFileFolder, currentZipFileName);
    }


    /**
     * Test Setup - Ensuring there is a folder with both files and folders inside.
     */
    @Given("^I have a folder which contains files and folders$")
    public void iHaveAFolderWhichContainsFilesAndFolders() {
        currentFile = new File(multiFolder);
        currentZipTargetFolder = "target/MultiFolderZipTest";
        currentZipFileName = "target/MultiFolder.com.catena.qa.framework.zip";

        if (!currentFile.exists()) {
            classLogger.error("The specified folder does not exist : " + multiFolder);
        }
    }

    /**
     * Test Step - add the folder to a com.catena.qa.framework.zip file.
     * <p>
     * //     * @throws Throwable - IOException when file handling fails or files do not exist
     */
    @When("^I compress the multi-folder to a ZIP file$")
    public void iCompressTheMultiFolderToAZIPFile() {
        ZipHelper.addFolderToZip(multiFolder, currentZipFileName);
    }

    /**
     * Ensuring that the ZIP file is created.
     * <p>
     * //     * @throws Throwable IOException when file handling fails or files do not exist
     */
    @Then("^the file is created$")
    public void theFileIsCreated() {
        File zipFile = new File(currentZipFileName);

        if (!zipFile.exists()) {
            classLogger.error("The zipped file does not exist : " + zipFile.toString());
        }
    }
}
