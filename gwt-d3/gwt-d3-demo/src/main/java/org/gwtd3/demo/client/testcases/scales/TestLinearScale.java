package org.gwtd3.demo.client.testcases.scales;

import org.gwtd3.api.D3;
import org.gwtd3.api.scales.Domain;
import org.gwtd3.api.scales.LinearScale;
import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.user.client.ui.ComplexPanel;

public class TestLinearScale extends AbstractTestCase {

    @Override
    public void doTest(final ComplexPanel sandbox) {
        LinearScale scale = D3.scale().linear();
        // get default domain
        Domain domain = scale.domain();
        assertEquals(2, domain.length());
        assertEquals(0, domain.get(0).asInt());
        assertEquals(1, domain.get(1).asInt());

        // set range
        LinearScale range = scale.range(5, 8);

    }
}
