package fr.univ_amu.iut;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    // **************************************************
    // Package private methods
    // **************************************************

    @Test
    public final void testAddUnderCategory() {
        Category testCategory = new Category("testCategory", new CategoryPicture("",15,15));
        Category testUnderCategory = new Category("testUnderCategory",new CategoryPicture("",15,15));
        testCategory.addUnderCategory(testUnderCategory);

        assertTrue("testCategory should be an upper category",
                            testCategory.getIsUpperCategory());
        assertTrue("testUnderCategory should be a under category of testCategory",
                   testCategory.getMyUnderCategories().get(0) == testUnderCategory);
        assertTrue("testUnderCategory should have testCategory as parent",
                   testUnderCategory.getParent() == testCategory);
    }

    @Test
    public final void testAddProduct() {
        Category testCategory = new Category("testCategory",new CategoryPicture("",15,15));
        // Product testProduct = new Product("testProduct","testBrand","","A test product", testTechnicalSheet,testCategory);
        //assertTrue("testProduct should be in category testCategory",
        //        testCategory.getMyProducts().get(0) == testProduct);
    }
}