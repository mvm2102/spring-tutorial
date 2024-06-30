package com.baeldung.java.io.pojotocsv;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.bean.BeanField;
import com.opencsv.bean.CsvBindByName;

public class CustomColumnPositionStrategy<T> extends ColumnPositionMappingStrategy<T> {
    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        final int numCols = getFieldMap().values().size();
        super.generateHeader(bean);
        final String[] header = new String[numCols];
        BeanField<?, ?> beanField;
        for (int i = 0; i < numCols; i++) {
            beanField = findField(i);
            final String columnHeaderName = extractHeaderName(beanField);
            header[i] = columnHeaderName;
        }
        return header;
    }

    private String extractHeaderName(final BeanField<?, ?> beanField) {
        if (beanField == null || beanField.getField() == null || beanField.getField().getDeclaredAnnotationsByType(
                CsvBindByName.class).length == 0) {
            return "";
        }

        final CsvBindByName bindByNameAnnotation = beanField.getField()
                .getDeclaredAnnotationsByType(CsvBindByName.class)[0];
        return bindByNameAnnotation.column();
    }
}
