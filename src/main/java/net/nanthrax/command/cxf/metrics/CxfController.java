package net.nanthrax.command.cxf.metrics;

import org.apache.cxf.Bus;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CxfController {

    @Reference
    private BundleContext bundleContext;

    public List<Bus> getBusses() {
        List<Bus> busses = new ArrayList<>();
        try {
            Collection<ServiceReference<Bus>> references = bundleContext.getServiceReferences(Bus.class, null);
            if (references != null) {
                for (ServiceReference<Bus> reference : references) {
                    if (reference != null) {
                        Bus bus = bundleContext.getService(reference);
                        if (bus != null) {
                            busses.add(bus);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // nothing to do
        }
        return busses;
    }

    public Bus getBus(String name) {
        try {
            Collection<ServiceReference<Bus>> references = bundleContext.getServiceReferences(Bus.class, null);
            if (references != null) {
                for (ServiceReference<Bus> reference : references) {
                    if (reference != null
                            && name.equals(reference.getProperty("cxf.bus.id"))) {
                        return bundleContext.getService(reference);
                    }
                }
            }
        } catch (Exception e) {
            // nothing to do
        }
        return null;
    }

}
