package com.fiap.tc.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
@Slf4j
public class QRCodeGenerator {

    public static final String PNG = "png";

    public String generate(String text) {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        try {
            var bitMatrix = barcodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            var qrcode = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return Base64.getEncoder().encodeToString(toByteArray(qrcode));
        } catch (WriterException | IOException e) {
            log.error("QRCodeGenerator error: ", e);
            throw new RuntimeException(e);
        }
    }

    private byte[] toByteArray(BufferedImage bi)
            throws IOException {
        var baos = new ByteArrayOutputStream();
        ImageIO.write(bi, PNG, baos);
        return baos.toByteArray();
    }
}
