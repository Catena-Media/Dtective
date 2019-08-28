package io.dtective.utils;


import org.openqa.selenium.NotFoundException;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardHelper {

    public static void add(String value) {
        StringSelection selection = new StringSelection(value);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public static String get() {
        String result = null;

        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            result = (String) clipboard.getData(DataFlavor.stringFlavor);

        } catch (IOException | UnsupportedFlavorException e) {
            e.printStackTrace();
        }

        if (result == null)
            throw new NotFoundException("Unable to retrieve clipboard data");

        return result;
    }
}
