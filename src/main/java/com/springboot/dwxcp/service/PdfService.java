package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Pdf;
import java.util.List;

public interface PdfService {
    boolean uploadPdf(Pdf pdf);

    List<Pdf> selectPdfList(String type);

    Pdf selectPdfById(Long id);

    boolean deletePdfById(Long id);
}
