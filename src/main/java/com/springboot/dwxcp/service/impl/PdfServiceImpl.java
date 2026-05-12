package com.springboot.dwxcp.service.impl;

import com.springboot.dwxcp.entity.Pdf;
import com.springboot.dwxcp.mapper.PdfMapper;
import com.springboot.dwxcp.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
