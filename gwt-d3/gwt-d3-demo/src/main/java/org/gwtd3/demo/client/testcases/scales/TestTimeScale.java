package org.gwtd3.demo.client.testcases.scales;

import org.gwtd3.api.D3;
import org.gwtd3.api.scales.Domain;
import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.user.client.ui.ComplexPanel;

public class TestTimeScale extends AbstractTestCase {

    @Override
    public void doTest(final ComplexPanel sandbox) {
        Domain domain = D3.time().scale().domain();
        assertEquals(2, domain.length());
//        System.out.println(domain.get(0).asJsDate());
//        System.out.println(domain.get(1).asJsDate());
    }
}
