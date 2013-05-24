/**
 * 
 */
package org.gwtd3.demo.client.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gwtd3.demo.client.test.AbstractTestCase;
import org.gwtd3.demo.client.testcases.arrays.TestArrays;
import org.gwtd3.demo.client.testcases.arrays.TestD3Arrays;
import org.gwtd3.demo.client.testcases.csv.TestCsv;
import org.gwtd3.demo.client.testcases.d3.TestColors;
import org.gwtd3.demo.client.testcases.d3.TestD3;
import org.gwtd3.demo.client.testcases.d3.TestInterpolators;
import org.gwtd3.demo.client.testcases.scales.TestLinearScale;
import org.gwtd3.demo.client.testcases.scales.TestThresholdScale;
import org.gwtd3.demo.client.testcases.scales.TestTimeScale;
import org.gwtd3.demo.client.testcases.selection.TestSelectionAttr;
import org.gwtd3.demo.client.testcases.selection.TestSelectionClassed;
import org.gwtd3.demo.client.testcases.svg.TestArc;
import org.gwtd3.demo.client.testcases.svg.TestArea;
import org.gwtd3.demo.client.testcases.svg.TestAxis;
import org.gwtd3.demo.client.testcases.svg.TestLine;
import org.gwtd3.demo.client.testcases.time.TestTimeFormat;
import org.gwtd3.demo.client.testcases.time.TestTimeScales;
import org.gwtd3.demo.client.testcases.tsv.TestTsv;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class D3TestSuite {

	List<AbstractTestCase> tests = new ArrayList<AbstractTestCase>();

	public static D3TestSuite get() {
		D3TestSuite suite = new D3TestSuite();
		suite.tests = Arrays.asList(
		// arrays
				new TestArrays(),
                new TestD3Arrays(),
				// utils
				new TestValue(),
				// D3
				new TestD3(), new TestColors(), new TestInterpolators(),
				// seletions
				new TestSelectionAttr(), new TestSelectionClassed(),
				// scales
				new TestLinearScale(), new TestThresholdScale(), new TestTimeScale(),
				// svg
				new TestAxis(), new TestLine(), new TestArea(), new TestArc(),
				// time
				new TestTimeFormat(), new TestTimeScales(),
				// csv
				new TestCsv(),
				// tsv
				new TestTsv());
		return suite;
	}

	/**
	 * @return the tests
	 */
	public List<AbstractTestCase> getTests() {
		return tests;
	}
}
