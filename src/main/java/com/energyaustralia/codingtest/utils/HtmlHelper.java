package com.energyaustralia.codingtest.utils;

import com.energyaustralia.codingtest.model.RecordLabel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.List;

/**
 * @author amo31
 */
public class HtmlHelper {

    private HtmlHelper() {
    }

    /**
     * Generate HTML file for record labels.
     * @param recordLabelList
     * @param filePath
     */
    public static void writeHtmlFile(List<RecordLabel> recordLabelList,String filePath) throws IOException {
        Document doc = Jsoup.parse("<html></html>");
        doc.body().addClass("body-styles-cls");
        doc.body().appendElement("div");
        recordLabelList.forEach(r->{
            doc.body().appendElement("br");
            doc.body().appendElement("div").text(r.getRecordLabel());
            r.getBands().forEach(b->{
                doc.body().appendElement("div").text(b.getBandName()).prepend("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                doc.body().appendElement("div").text(b.getFestivalName()).prepend("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");

            });
        });


        writeToFile(doc.toString(),filePath);
    }

    public static void writeToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator+fileName;
        File file = new File(tempFile);

        try(OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());) {
            Writer writer = new OutputStreamWriter(outputStream);
            writer.write(fileContent);
            writer.close();
        }
    }
}
