package com.example.qrcode.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @author wuting
 * @date 2020/10/20
 */
public class QRCodeUtil {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static void generateQRCode(String website, OutputStream output) throws WriterException, IOException {
        int width = 300;
        int height = 300;
        String format = "jpg";
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bm = new MultiFormatWriter().encode(website, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = toImage(bm);
        //设置logo图标
        LogoConfig logoConfig = new LogoConfig();
        image = logoConfig.logoMatrix(image);
        ImageIO.write(image, format, output);
    }

    private static BufferedImage toImage(BitMatrix bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

}
