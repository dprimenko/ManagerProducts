package com.example.managerproducts.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by usuario on 20/01/17.
 */

public final class ManageProductContract {

    public static final String AUTHORITY = "com.example.managerproducts";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private ManageProductContract() {

    }

    public static class Category implements BaseColumns {

        public static final String CONTENT_PATH = "category";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String[] PROJECTION = new String[] {BaseColumns._ID, NAME};

    }

    public static class Product implements BaseColumns {

        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String BRAND = "brand";
        public static final String DOSAGE = "dosage";
        public static final String PRICE = "price";
        public static final String STOCK = "stock";
        public static final String IMAGE = "image";
        public static final String CATEGORY_ID = "category_id";
        public static final String[] PROJECTION = new String[] {BaseColumns._ID, NAME, DESCRIPTION, BRAND, DOSAGE, PRICE, STOCK, IMAGE, CATEGORY_ID};
    }

    public static class Pharmacy implements BaseColumns {

        public static final String CONTENT_PATH = "pharmacy";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String CIF = "cif";
        public static final String ADDRESS = "address";
        public static final String PHONENUMBER = "phoneNumber";
        public static final String EMAIL = "email";
        public static final String[] PROJECTION = new String[] {BaseColumns._ID, CIF, ADDRESS, PHONENUMBER, EMAIL};

    }

    public static class Invoice implements BaseColumns {

        public static final String CONTENT_PATH = "invoice";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String DATE = "date";
        public static final String PHARMACY_ID = "pharma_id";
        public static final String[] PROJECTION = new String[] {BaseColumns._ID, DATE, PHARMACY_ID};
    }

    public static class InvoiceStatus implements BaseColumns {

        public static final String CONTENT_PATH = "invoiceStatus";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String[] PROJECTION = new String[] {BaseColumns._ID, NAME};
    }

    public static class InvoiceLine implements BaseColumns {

        public static final String CONTENT_PATH = "invoiceLine";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String IDINVOICE = "idInvoice";
        public static final String ORDERPRODUCT = "orderProduct";
        public static final String IDPRODUCT = "idProduct";
        public static final String PRICE = "price";
        public static final String[] PROJECTION = new String[] {BaseColumns._ID, IDINVOICE, ORDERPRODUCT, IDPRODUCT, PRICE};

    }


}
