package net.nanthrax.command.cxf.metrics;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.lifecycle.Service;

@Service
@Command(scope = "metrics", name = "enable", description = "Enable CXF metrics on a bus")
public class EnableCommand extends CxfController implements Action {

    @Argument(index = 0, name = "bus", description = "CXF bus name", required = true, multiValued = false)
    @Completion(BusNameCompleter.class)
    String busName;

    @Override
    public Object execute() throws Exception {
        Bus bus = getBus(busName);
        if (bus != null) {
            bus.getFeatures().add(new MetricsFeature());
        }
        System.out.println("Bus Features");
        for (Feature feature : bus.getFeatures()) {
            System.out.println(feature.getClass().getName());
        }
        return null;
    }

}
