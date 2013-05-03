package org.gwtd3.demo.client.testcases.time;

import org.gwtd3.api.D3;
import org.gwtd3.api.JsArrays;
import org.gwtd3.api.time.Range;
import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.user.client.ui.ComplexPanel;

public class TestTimeScales extends AbstractTestCase {

    @Override
    public void doTest(final ComplexPanel sandbox) {
        Range range = D3.time().scale().range(JsArrays.asJsArray(0, 5)).range();
    }
}
