package com.springboot.dwxcp.service.impl;

import com.springboot.dwxcp.entity.Pdf;
import com.springboot.dwxcp.mapper.PdfMapper;
import com.springboot.dwxcp.service.PdfService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private PdfMapper pdfMapper;

    @Override
    public boolean uploadPdf(Pdf pdf) {
        return pdfMapper.insert(pdf) > 0;
    }

    @Override
    public List<Pdf> selectPdfList(String type) {
        return pdfMapper.selectPdfListWithoutContent(type);
    }

    @Override
    public Pdf selectPdfById(Long id) {
        return pdfMapper.selectPdfById(id);
    }

    @Override
    public boolean deletePdfById(Long id) {
        return pdfMapper.deletePdfById(id) > 0;
    }

    @Override
    public List<String> pdfToImages(byte[] pdfData) {
        List<String> result = new ArrayList<>();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(pdfData);
             PDDocument doc = PDDocument.load(bais)) {
            PDFRenderer renderer = new PDFRenderer(doc);
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                BufferedImage img = renderer.renderImageWithDPI(i, 150);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", baos);
                String b64 = Base64.getEncoder().encodeToString(baos.toByteArray());
                result.add("data:image/jpeg;base64," + b64);
                baos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
