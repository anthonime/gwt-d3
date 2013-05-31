package com.github.gwtd3.ui.chart;

import org.gwtd3.api.Coords;
import org.gwtd3.api.D3;
import org.gwtd3.api.behaviour.Drag;
import org.gwtd3.api.behaviour.Drag.DragEventType;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.functions.DatumFunction;
import org.gwtd3.api.scales.Domain;
import org.gwtd3.api.scales.LinearScale;

import com.google.common.collect.Range;
import com.google.gwt.dom.client.Element;

/**
 * Wrap the complexity of drag events to let the user navigate through the X
 * dimension.
 * <p>
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
class DragSupport {
    private final class OnDrag implements DatumFunction<Void> {
        @Override
        public Void apply(final Element context, final Datum d, final int index) {
            if (disabled) {
                return null;
            }
            // when starting, save the starting Point
            if (starting) {
                starting = false;
                startPoint = D3.eventAsCoords();
            }
            // compute the new X domain according to the X
            // distance
            // of mouse with the starting point
            double xDistance = (D3.eventAsCoords().x()) - startPoint.x();
            Domain<?> domain = startXScale.domain();
            double dXDomain = startXScale.invert(xDistance).asDouble() - domain.getNumber(0);
            double newX1 = domain.getNumber(0) - dXDomain;
            double newX2 = domain.getNumber(1) - dXDomain;

            // update the new scale and redraw Xaxis and Series
            chart.model().visibleXRange(Range.closed(newX1, newX2), true);
            return null;
        }
    }

    private final class OnDragEnd implements DatumFunction<Void> {
        @Override
        public Void apply(final Element context, final Datum d, final int index) {
            if (disabled) {
                return null;
            }
            starting = false;
            startPoint = null;
            return null;
        }
    }

    private final class OnDragStart implements DatumFunction<Void> {
        @Override
        public Void apply(final Element context, final Datum d, final int index) {
            if (disabled) {
                return null;
            }
            starting = true;
            startXScale = chart.xAxis().scale().copy();
            return null;
        }
    }

    /**
     * flag to indicate a drag gesture starting
     */
    private boolean starting;
    /**
     * The point when the drag gesture starts, serve as reference for distance
     * computations.
     */
    private Coords startPoint;
    /**
     * Copy of the scale of the X axis when the drag gesture begins
     */
    private LinearScale startXScale;
    /**
     * The chart
     */
    private final LineChart<?> chart;
    /**
     * The D3 {@link Drag} object.
     */
    private Drag drag;

    /**
     * Disable the drag feature.
     * 
     */
    private boolean disabled;

    public DragSupport(final LineChart<?> chart) {
        this.chart = chart;
        enable();
    }

    public void disable() {
        disabled = true;
        drag = createDisabledDrag();
        update();

    }

    private Drag createDisabledDrag() {
        return D3.behavior().drag();
    }

    public void enable() {
        disabled = false;
        drag = createEnabledDragObject();
        update();
    }

    private void update() {
        chart.select().call(drag);
    }

    private Drag createEnabledDragObject() {
        return D3.behavior().drag()
                // on drag start init flags
                .on(DragEventType.dragstart, new OnDragStart())
                .on(DragEventType.dragend, new OnDragEnd())
                .on(DragEventType.drag, new OnDrag());
    }
}