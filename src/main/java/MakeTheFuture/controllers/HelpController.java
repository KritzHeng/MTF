package MakeTheFuture.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import MakeTheFuture.services.PDFFileDataSource;

import java.io.IOException;

public class HelpController {

    PDFFileDataSource pdfFileDataSource;

    @FXML
    private void handlePDFButton() throws IOException{
        pdfFileDataSource = new PDFFileDataSource("pdf", "6210450041.pdf");
        pdfFileDataSource.PDFFile();
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}
