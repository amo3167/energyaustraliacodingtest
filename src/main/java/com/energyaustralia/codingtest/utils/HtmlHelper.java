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
        doc.head().appendElement("style").append("div.padding{padding-left:2cm;} div.padding2{padding-left:5cm;}");
        doc.body().appendElement("div");
        recordLabelList.forEach(r->{
            doc.body().appendElement("br");
            doc.body().appendElement("div").text(r.getRecordLabel());
            r.getBands().forEach(b->{
                doc.body().appendElement("div").addClass("padding").text(b.getBandName());
                doc.body().appendElement("div").addClass("padding2").text(b.getFestivalName());

            });
        });


        writeToFile(doc.toString(),filePath);
    }

    private static void writeToFile(String fileContent, String fileName) throws IOException {

        File file = new File(fileName);

        try(OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());) {
            Writer writer = new OutputStreamWriter(outputStream);
            writer.write(fileContent);
            writer.close();
        }
    }
}
