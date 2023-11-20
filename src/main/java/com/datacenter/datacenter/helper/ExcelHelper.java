package com.datacenter.datacenter.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id", "namaMurid", "tanggalLahir", "tempatLahir", "umur", "agama", "gender", "kelas", "namaOrtu", "noTeleponOrtu"};
    static String SHEET = "Sheet1";

    static String[] HEADERS = {"id", "namaGuru", "tanggalLahir", "tempatLahir", "umur", "agama", "gender", "noTelepon", "gelarPendidikan", "statusKawin"};
    static String SHEETs = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static boolean hassExcelFormat(MultipartFile file) {
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
                row.createCell(1).setCellValue(siswa.getNamaMurid());
                row.createCell(2).setCellValue(siswa.getTanggalLahir());
                row.createCell(3).setCellValue(siswa.getTempatLahir());
                row.createCell(4).setCellValue(siswa.getUmur());
                row.createCell(5).setCellValue(siswa.getAgama());
                row.createCell(6).setCellValue(siswa.getGender());
                row.createCell(7).setCellValue(siswa.getKelas());
                row.createCell(8).setCellValue(siswa.getNamaOrtu());
                row.createCell(9).setCellValue(siswa.getNoTeleponOrtu());
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

            List<Siswa> siswas = new ArrayList<>();
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
                            siswa.setNamaMurid(currentCell.getStringCellValue());
                            break;
                        case 1:
                            siswa.setTanggalLahir(currentCell.getStringCellValue());
                            break;
                        case 2:
                            siswa.setTempatLahir(currentCell.getStringCellValue());
                            break;
                        case 3:
                            siswa.setUmur( currentCell.getStringCellValue());
                            break;
                        case 4:
                            siswa.setAgama(currentCell.getStringCellValue());
                            break;
                        case 5:
                            siswa.setGender(currentCell.getStringCellValue());
                            break;
                        case 6:
                            siswa.setKelas(currentCell.getStringCellValue());
                            break;
                        case 7:
                            siswa.setNamaOrtu(currentCell.getStringCellValue());
                            break;
                        case 8:
                            siswa.setNoTeleponOrtu(currentCell.getStringCellValue());
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
    public static ByteArrayInputStream gurusToExcel(List<Guru> gurus) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEETs);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
            for (Guru guru : gurus) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(guru.getId());
                row.createCell(1).setCellValue(guru.getNamaGuru());
                row.createCell(2).setCellValue(guru.getTanggalLahir());
                row.createCell(3).setCellValue(guru.getTempatLahir());
                row.createCell(4).setCellValue(guru.getUmur());
                row.createCell(5).setCellValue(guru.getAgama());
                row.createCell(6).setCellValue(guru.getGender());
                row.createCell(7).setCellValue(guru.getNoTelepon());
                row.createCell(8).setCellValue(guru.getGelarPendidikan());
                row.createCell(9).setCellValue(guru.getStatusKawin());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Guru> excelToGurus(InputStream is, Sekolah sekolah) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEETs);
            Iterator<Row> rows = sheet.iterator();

            List<Guru> gurus = new ArrayList<>();
            int tahun = new Date().getYear();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Guru guru = new Guru();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            guru.setNamaGuru(currentCell.getStringCellValue());
                            break;
                        case 1:
                            guru.setTanggalLahir(currentCell.getStringCellValue());
                            break;
                        case 2:
                            guru.setTempatLahir(currentCell.getStringCellValue());
                            break;
                        case 3:
                            guru.setUmur( currentCell.getStringCellValue());
                            break;
                        case 4:
                            guru.setAgama(currentCell.getStringCellValue());
                            break;
                        case 5:
                            guru.setGender(currentCell.getStringCellValue());
                            break;
                        case 6:
                            guru.setNoTelepon(currentCell.getStringCellValue());
                            break;
                        case 7:
                            guru.setGelarPendidikan(currentCell.getStringCellValue());
                            break;
                        case 8:
                            guru.setStatusKawin(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                guru.setSekolah(sekolah);
                gurus.add(guru);
            }
            workbook.close();
            return gurus;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
