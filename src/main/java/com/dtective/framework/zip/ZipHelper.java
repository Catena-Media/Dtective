package com.dtective.framework.zip;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip Static Class.
 * <ul>
 * <li>Ability to get a com.catena.qa.framework.zip encoder using output file name</li>
 * <li>Ability to add a target file to the com.catena.qa.framework.zip</li>
 * <li>Ability to add a folder and all subsequent items to a com.catena.qa.framework.zip</li>
 * </ul>
 */
public class ZipHelper {

    /**
     * Size of the buffer to read/write data.
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * Class logger.
     */
    private static Logger logger = LogManager.getLogger(ZipHelper.class);

    /**
     * Private constructor.
     */
    private ZipHelper() {
    }

    /**
     * get a ZipOutputStream to encode files.
     * - Ability to get a com.catena.qa.framework.zip encoder using output file name
     *
     * @param outputFileName - 'com.catena.qa.framework.test.com.catena.qa.framework.zip"
     * @return - ZipOutputStream ready for zipping
     */
    public static ZipOutputStream getZipper(String outputFileName) {

        logger.debug("Creating -ZipOutputStream- for file : " + outputFileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFileName);
        } catch (FileNotFoundException e) {
            logger.error("Error while creating ZipOutputStream for file : " + outputFileName);
            logger.error(e);
        }
        assert fos != null;
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        return new ZipOutputStream(bos);
    }

    /**
     * add Folder to Zip.
     * - Ability to add a target file to the com.catena.qa.framework.zip
     *
     * @param folder - Folder to be added to Zip
     * @param zip    - ZipOutputStream
     *               //     * @throws IOException - In the event of file reading / writing actions failure
     */
    public static void enZipAllDirectories(File folder, ZipOutputStream zip) {

        logger.debug("Recursive Adding of files to ZIP from folder  : " + folder);

        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    logger.trace("Found new folder to add to ZIP. Recursive call add sub-items : " + file.getName());
                    enZipAllDirectories(file, zip);
                } else {
                    logger.trace("Found file, adding to ZIP: " + file.getName());
                    addEntryToZip(file, zip);
                }
            }
        }

    }

    /**
     * add the target folder and all sub-folders / content to a com.catena.qa.framework.zip file.
     *
     * @param folderFile - Name of Source folder for zipping
     * @param outputFile - File name of the target com.catena.qa.framework.zip file
     */
    public static void addFolderToZip(String folderFile, String outputFile) {

        File folder = new File(folderFile);

        ZipOutputStream stream = getZipper(outputFile);

        try {
            enZipAllDirectories(folder, stream);
            stream.close();
        } catch (IOException e) {
            logger.error("Error while creating Zip file for directory : " + folder.getName());
            logger.error(e);
            Assert.fail(e.toString());
        }

    }

    /**
     * add a single file to a com.catena.qa.framework.zip using a ZipOutputStream.
     *
     * @param sourceFile - File to be added to the com.catena.qa.framework.zip
     * @param zip        - ZipOutputStream for zipping
     */
    private static void addEntryToZip(File sourceFile, ZipOutputStream zip) {
        ZipEntry zipEntry = new ZipEntry(sourceFile.getPath());
        try {
            zip.putNextEntry(zipEntry);
            IOUtils.copy(new FileInputStream(sourceFile), zip);
            zip.closeEntry();
        } catch (IOException e) {
            logger.error("Error while creating Zip file for file : " + sourceFile.getName());
            logger.error(e);
            Assert.fail(e.toString());
        }

    }

    /**
     * add a single file to a com.catena.qa.framework.zip, close and save after added.
     *
     * @param sourceFile - File to be added to the com.catena.qa.framework.zip
     * @param targetFile - ZIP File to be created
     */
    public static void addFileToZip(File sourceFile, String targetFile) {
        ZipOutputStream zOS = getZipper(targetFile);

        addEntryToZip(sourceFile, zOS);
        try {
            zOS.flush();
            zOS.close();
        } catch (IOException ex) {
            logger.error("Error while adding file to ZIP --> source : " + sourceFile.getName() + " target : " + targetFile);
            logger.error(ex);
            Assert.fail(ex.toString());
        }
    }

    /**
     * Extracts a com.catena.qa.framework.zip file specified by the zipFilePath to a directory specified.
     *
     * @param zipFilePath   - Zip target to be extracted
     * @param destDirectory - Destination folder for the unzipped contents
     * @throws IOException - Exception thrown for file interaction errors
     */
    public static void extractFileFromZip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        logger.debug("Reading ZIP file for extraction : " + zipFilePath);
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();

        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            filePath = filePath.replace("/", File.separator);
            logger.debug("ZIP-Extraction of : " + filePath);
            if (!entry.isDirectory()) {

                extractFile(zipIn, filePath);
            } else {

                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    /**
     * Extracts a com.catena.qa.framework.zip entry (file entry).
     *
     * @param zipIn    - ZipInputStream
     * @param filePath - Path to extract
     * @throws IOException - Exception thrown for file interaction errors
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        filePath = System.getProperty("user.dir") + File.separator + filePath;

        logger.debug("Extracting File : " + filePath);
        File target = new File(filePath);
        if (!target.isDirectory()) {
            File rootFolder = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            logger.debug("Creating folder of the extracted ZIP : " + rootFolder);
            rootFolder.mkdirs();
        } else {
            target.mkdirs();
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.flush();
        bos.close();
    }
}
