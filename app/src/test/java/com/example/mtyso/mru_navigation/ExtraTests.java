package com.example.mtyso.mru_navigation;

import org.junit.Assert;
import org.junit.Test;

public class ExtraTests {

    //search bar can accept letters and numbers
    @Test
    public void searchBarAcceptsInput(){

        Assert.assertNotNull(SearchView.getInputText());

    }



}
