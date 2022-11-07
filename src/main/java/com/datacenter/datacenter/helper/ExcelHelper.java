package com.datacenter.datacenter.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id", "namaSiswa", "tanggalLahir", "tempatLahir", "agama", "gender"};
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static ByteArrayInputStream siswasToExcel(List<Siswa> siswas) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Siswa siswa : siswas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(siswa.getId());
                row.createCell(1).setCellValue(siswa.getNamaSiswa());
                row.createCell(2).setCellValue(siswa.getTanggalLahir());
                row.createCell(3).setCellValue(siswa.getTempatLahir());
                row.createCell(4).setCellValue(siswa.getAgama());
                row.createCell(5).setCellValue(siswa.getGender());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Siswa> excelToSiswas(InputStream is, Sekolah sekolah) {

        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Siswa> siswas = new ArrayList<Siswa>();
            int tahun = new Date().getYear();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Siswa siswa = new Siswa();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            siswa.setNamaSiswa(currentCell.getStringCellValue());
                            break;
                        case 1:
                            siswa.setTanggalLahir(currentCell.getStringCellValue());
                            break;
                        case 2:
                            siswa.setTempatLahir(currentCell.getStringCellValue());
                            break;
                        case 3:
                            siswa.setAgama(currentCell.getStringCellValue());
                            break;
                        case 4:
                            siswa.setGender(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                siswa.setSekolah(sekolah);
                siswa.setTahunDaftar(tahun);
                siswas.add(siswa);
            }
            workbook.close();
            return siswas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}