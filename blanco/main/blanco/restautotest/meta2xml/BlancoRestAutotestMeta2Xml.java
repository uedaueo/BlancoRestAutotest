/*
 * This source code has been generated by blanco Framework.
 * BlancoRestAutotestのための変換処理を定義します。
 */
package blanco.restautotest.meta2xml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerException;

import blanco.commons.calc.parser.BlancoCalcParser;

/**
 * BlancoRestAutotestのための変換処理を定義します。
 */
public class BlancoRestAutotestMeta2Xml {
    /**
     * Flag whether the conversion from definition metafile to intermediate XML file should be done in cache or not.
     */
    protected boolean fCacheMeta2Xml = false;

    /**
     * The number of times the conversion from a definition metafile to an intermediate XML file was completed in cache.
     */
    protected int fCacheMeta2XmlCount = 0;

    /**
     * A cache to reduce the number of times the definition structure XML file is read from the class loader.
     */
    protected byte[] fCacheMetaDefXml = null;

    /**
     * Sets the flag whether the conversion from definition metafile to intermediate XML file should be done in cache or not.
     *
     * @param argCacheMeta2Xml Whether the conversion from the definition metafile to the intermediate XML file should be done in cache.
     */
    public void setCacheMeta2Xml(final boolean argCacheMeta2Xml) {
        fCacheMeta2Xml = argCacheMeta2Xml;
    }

    /**
     * Converts a stream of an Excel file to a stream of an XML file.
     *
     * The definition file holds the path internally.
     *
     * @param inStreamMetaSource Input stream of the metafile.
     * @param outStreamTarget Output stream of an XML file.
     * @throws IOException If an I/O exception occurs.
     * @throws TransformerException If an XML conversion exception occurs.
     */
    public void process(final InputStream inStreamMetaSource, final OutputStream outStreamTarget) throws IOException, TransformerException {
        if (inStreamMetaSource == null) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: inStreamMetaSource is null.");
        }
        if (outStreamTarget == null) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: outStreamTarget is null.");
        }

        if (fCacheMetaDefXml == null) {
            // Loads the XML configuration file from the same class loader as itself.
            final InputStream meta2xmlStream = getClass().getClassLoader().getResourceAsStream("blanco/restautotest/BlancoRestAutotestMeta2Xml.xml");
            if (meta2xmlStream == null) {
                throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Resource [blanco/restautotest/BlancoRestAutotestMeta2Xml.xml] failed to be obtained.");
            }
            final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            final byte[] bufWrk = new byte[8192];
            for (;;) {
                final int readLength = meta2xmlStream.read(bufWrk);
                if (readLength <= 0) {
                    break;
                }
                outStream.write(bufWrk, 0, readLength);
            }
            outStream.flush();
            meta2xmlStream.close();
            fCacheMetaDefXml = outStream.toByteArray();
        }

        InputStream inStreamDef = new ByteArrayInputStream(fCacheMetaDefXml);
        try {
            new BlancoCalcParser().process(inStreamDef, inStreamMetaSource, outStreamTarget);
        } finally {
            if (inStreamDef != null) {
                inStreamDef.close();
            }
        }
    }

    /**
     * Convert an Excel file to an XML file.
     *
     * @param fileMeta Input file for the metafile.
     * @param fileOutput XML file output.
     * @throws IOException If an I/O exception occurs.
     * @throws TransformerException If an XML conversion exception occurs.
     */
    public void process(final File fileMeta, final File fileOutput) throws IOException, TransformerException {
        if (fileMeta == null) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: fileMeta is null.");
        }
        if (fileOutput == null) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: fileOutput is null.");
        }
        if (fileMeta.exists() == false) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: file file [" + fileMeta.getAbsolutePath() + "] not found.");
        }

        if (fCacheMeta2Xml && fileMeta.lastModified() < fileOutput.lastModified()) {
            // Uses the cache to skips the process.
            fCacheMeta2XmlCount++;
            return;
        }

        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new BufferedInputStream(new FileInputStream(fileMeta), 8192);
            outStream = new BufferedOutputStream(new FileOutputStream(fileOutput), 8192);

            // Now that the stream is ready, it will do the actual processing.
            process(inStream, outStream);

            outStream.flush();
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }
    }

    /**
     * Converts the Excel file in the specified directory to the XML file.
     *
     * Processes a file with the extension [.xls] in the specified folder.<br>
     * Processes a file with the extension [.xlsx] in the specified folder.<br>
     * The processed data will be saved to a file with the extension [.xml] added to the original file name.
     *
     * @param fileMetadir The input directory where the metafile is stored.
     * @param targetDirectory Output directory.
     * @throws IOException If an I/O exception occurs.
     * @throws TransformerException If an XML conversion exception occurs.
     */
    public void processDirectory(final File fileMetadir, final String targetDirectory) throws IOException, TransformerException {
        System.out.println("m2x: begin.");
        final long startMills = System.currentTimeMillis();
        long totalFileCount = 0;
        long totalFileBytes = 0;

        if (fileMetadir == null) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: fileMetadir is null.");
        }
        if (targetDirectory == null) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: targetDirectory is null.");
        }
        if (fileMetadir.exists() == false) {
            throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Invalid argument: file [" + fileMetadir.getAbsolutePath() + "] not found.");
        }
        final File fileTargetDirectory = new File(targetDirectory);
        if (fileTargetDirectory.exists() == false) {
            // Since the output directory does not exist, creates it in advance.
            fileTargetDirectory.mkdirs();
        }

        // Gets a list of files in the specified directory.
        final File[] fileMeta = fileMetadir.listFiles();
        if (fileMeta == null) {
            throw new IllegalArgumentException("BlancoMeta2XmlProcessMeta2Xml: list directory [" + fileMetadir.getAbsolutePath() + "] is failed.");
        }
        for (int index = 0; index < fileMeta.length; index++) {
            if ((fileMeta[index].getName().endsWith(".xls") == false
            && fileMeta[index].getName().endsWith(".xlsx") == false)
            || fileMeta[index].getName().startsWith("~$")
            ) {
                // Skips processing because the file extension is different from the one that should be processed.
                continue;
            }

            if (progress(index + 1, fileMeta.length, fileMeta[index].getName()) == false) {
                // Since the command to suspend processing came from the progress indicator, so we will.
                break;
            }

            try {
                totalFileCount++;
                totalFileBytes += fileMeta[index].length();
                process(fileMeta[index], new File(targetDirectory + "/" + fileMeta[index].getName() + ".xml"));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new IllegalArgumentException("BlancoRestAutotestMeta2Xml: Exception occurs during processing the file [" + fileMeta[index].getAbsolutePath() + "]. " + ex.toString());
            }
        }

        if (fCacheMeta2Xml) {
            System.out.println("m2x: cache: " + fCacheMeta2XmlCount + " file skipped.");
        }
        final long costMills = System.currentTimeMillis() - startMills + 1;
        System.out.println("m2x: end: " + (costMills / 1000) + " sec, " + totalFileCount + " file, " + totalFileBytes + " byte (" + (totalFileBytes * 1000 / costMills) + " byte/sec).");
    }

    /**
     * Indicates the progress of the process.
     *
     * Inherit from it and create the process if you want to display the progress.
     *
     * @param progressCurrent The number of cases currently being processed.
     * @param progressTotal Total number of cases processed.
     * @param progressItem The name of the item being processed.
     * @return Whether the process can be continued or not. Aborts the process if false.
     */
    protected boolean progress(final int progressCurrent, final int progressTotal, final String progressItem) {
        // Always returns true to indicate that processing continues.
        return true;
    }
}
