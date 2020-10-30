package com.example.qrcode.demo;

import com.example.qrcode.util.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuting
 * @date 2020/10/20
 */
@Controller
public class QrCodeContoller {

    @ResponseBody
    @RequestMapping("/generateQRCode")
    public void generateQRCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String website = request.getParameter("website");
            QRCodeUtil.generateQRCode(website, response.getOutputStream());
            System.out.println(222222);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
