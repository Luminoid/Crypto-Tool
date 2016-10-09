package view;

import encryption.*;
import convertion.Ascii_Hex;
import encoding.Base64Encoding;
import encoding.UrlEncoding;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import operation.Operation;
import util.StringFormat;

import java.util.Optional;

/**
 * Created by Ethan on 16/9/27.
 */
public class MainViewController{
    @FXML
    private TextArea txtMain;

    @FXML
    private TextArea txtAux;

    @FXML
    private TextArea txtOutput;

    @FXML
    private void handleEncCeasarCipherDecrypt(){
        txtOutput.setText(StringFormat.formatArray(CaesarCipher.decrypt(txtMain.getText())));
    }

    @FXML
    private void handleEncCeasarCipherEncrypt() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter Shift Number: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(res -> {
            try {
                int num = Integer.parseInt(res);
                if (num < 0 || num > 26) {
                    throw new NumberFormatException();
                } else {
                    txtOutput.setText(CaesarCipher.encrypt(txtMain.getText(), num));
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Format error");
                alert.setHeaderText(null);
                alert.setContentText("The shift number should be between 0 and 26");
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void handleEncMorseCodeDecrypt() {
        txtOutput.setText(MorseCode.decrypt(txtMain.getText()));
    }

    @FXML
    private void handleEncMorseCodeEncrypt() {
        txtOutput.setText(MorseCode.encrypt(txtMain.getText()));
    }

    @FXML
    private void handleEncVigenereCipherDecrypt() {
        txtOutput.setText(VigenereCipher.decrypt(txtMain.getText()));
    }

    @FXML
    private void handleEncVigenereCipherEncrypt() {
        txtOutput.setText(VigenereCipher.encrypt(txtMain.getText(), txtAux.getText()));
    }

    @FXML
    private void handleEncRailFenceCipherDecrypt() {
        txtOutput.setText(StringFormat.formatList(RailFenceCipher.decrypt(txtMain.getText())));
    }

    @FXML
    private void handleCoBase64Decode() {
        txtOutput.setText(Base64Encoding.decode(txtMain.getText()));
    }

    @FXML
    private void handleCoBase64Encode() {
        txtOutput.setText(Base64Encoding.encode(txtMain.getText()));
    }

    @FXML
    private void handleCoUrlDecode() {
        txtOutput.setText(UrlEncoding.decode(txtMain.getText()));
    }

    @FXML
    private void handleCoUrlEncode() {
        txtOutput.setText(UrlEncoding.encode(txtMain.getText()));
    }

    @FXML
    private void handleCvtAsciiToHex() {
        txtOutput.setText(Ascii_Hex.asciiToHex(txtMain.getText()));
    }

    @FXML
    private void handleCvtHexToAscii() {
        txtOutput.setText(Ascii_Hex.hexToAscii(txtMain.getText()));
    }

    @FXML
    private void handleOpShift() {
        txtOutput.setText(StringFormat.formatArray(Operation.shift(txtMain.getText())));
    }

    @FXML
    private void handleOpXorHex() {
        txtOutput.setText(Operation.xorHex(txtMain.getText(), txtAux.getText()));
    }

    @FXML
    private void handleOpCribDrag() {
        txtOutput.setText(StringFormat.formatArray(Operation.cribDrag(txtMain.getText(), txtAux.getText())));
    }

}
