package com.boa.unittest;

import com.boa.util.DataUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DataUtilTest {
    @Test
    public void testToBooleanSucess(){
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("1"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("true"));
        Assert.assertEquals(true, DataUtil.toBoolean("Yeah"));
    }

    @Test
    public void testToBooleanFailure(){
        Assert.assertEquals(false, DataUtil.toBoolean(null));
        Assert.assertEquals(false, DataUtil.toBoolean(false));
        Assert.assertEquals(false, DataUtil.toBoolean(new ArrayList<>()));
   }
}
