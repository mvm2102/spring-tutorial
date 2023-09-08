package com.baeldung.convert.exceldatatolist;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.baeldung.convert.exceldatatolist.fastexcel.ExcelDataToListOfObjectsFastExcel;
import com.baeldung.convert.exceldatatolist.jxl.ExcelDataToListOfObjectsJxl;
import com.baeldung.convert.exceldatatolist.poi.ExcelDataToListApachePOI;
import com.baeldung.convert.exceldatatolist.poiji.ExcelDataToListOfObjectsPOIJI;

import jxl.read.biff.BiffException;

public class ExcelDataToListOfObjectsUnistTest {

        @Test
        public void whenParsingExcelFileWithPOIJI_thenConvertsToList() throws IOException {
            List<FoodInfo> foodInfoList = ExcelDataToListOfObjectsPOIJI.excelDataToListOfObjets_withPOIJI("src\\main\\resources/food_info.xlsx");

            assertEquals("Beverages", foodInfoList.get(0).getCategory());
            assertEquals("Beverages", foodInfoList.get(1).getCategory());
            assertEquals("Beverages", foodInfoList.get(2).getCategory());
            assertEquals("Beverages", foodInfoList.get(3).getCategory());
        }

    @Test
    public void whenParsingExcelFileWithApachePOI_thenConvertsToList() throws IOException {
        List<FoodInfo> foodInfoList = ExcelDataToListApachePOI.excelDataToListOfObjets_withApachePOI("src\\main\\resources/food_info.xlsx");

        assertEquals("Beverages", foodInfoList.get(0).getCategory());
        assertEquals("Beverages", foodInfoList.get(1).getCategory());
        assertEquals("Beverages", foodInfoList.get(2).getCategory());
        assertEquals("Beverages", foodInfoList.get(3).getCategory());
    }

    @Test
    public void whenParsingExcelFileWithFastExcel_thenConvertsToList() throws IOException {
        List<FoodInfo> foodInfoList = ExcelDataToListOfObjectsFastExcel.excelDataToListOfObjets_withFastExcel("src\\main\\resources/food_info.xlsx");

        assertEquals("Beverages", foodInfoList.get(0).getCategory());
        assertEquals("Beverages", foodInfoList.get(1).getCategory());
        assertEquals("Beverages", foodInfoList.get(2).getCategory());
        assertEquals("Beverages", foodInfoList.get(3).getCategory());
    }

    @Test
    public void whenParsingExcelFileWithJxl_thenConvertsToList() throws IOException, BiffException {
        List<FoodInfo> foodInfoList = ExcelDataToListOfObjectsJxl.excelDataToListOfObjets_withJxl("src\\main\\resources/food_info.xls"); //only takes xls files

        assertEquals("Beverages", foodInfoList.get(0).getCategory());
        assertEquals("Beverages", foodInfoList.get(1).getCategory());
        assertEquals("Beverages", foodInfoList.get(2).getCategory());
        assertEquals("Beverages", foodInfoList.get(3).getCategory());
    }


}
