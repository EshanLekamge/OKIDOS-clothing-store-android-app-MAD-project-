package com.store.okidosmobileapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

    private Admin_InsertProduct admin_insertProduct;

    @Before
    public void setup(){
        admin_insertProduct = new Admin_InsertProduct();
    }
    @Test
    public void insertFieldEmpty(){
        Boolean result = admin_insertProduct.ValidateProductData();
        assertEquals(true,result);
    }
}