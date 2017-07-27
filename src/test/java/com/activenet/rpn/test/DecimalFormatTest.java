package com.activenet.rpn.test;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * DecimalFormatTest
 *
 * @author hexiaohu@bravowhale.com
 * @date 2017-07-26 15:17
 */
public class DecimalFormatTest extends TestCase {
    DecimalFormat format = new DecimalFormat();

    public void testPaser() throws ParseException {
        Number num = format.parse("300000.000001415926");
        System.out.println(new BigDecimal("300000.000001415926"));
    }
}
