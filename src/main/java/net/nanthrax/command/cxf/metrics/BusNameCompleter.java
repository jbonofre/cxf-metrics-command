package net.nanthrax.command.cxf.metrics;

import org.apache.cxf.Bus;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.api.console.CommandLine;
import org.apache.karaf.shell.api.console.Completer;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.support.completers.StringsCompleter;

import java.util.List;

@Service
public class BusNameCompleter extends CxfController implements Completer {

    @Override
    public int complete(Session session,
                        CommandLine commandLine,
                        List<String> list) {
        StringsCompleter delegate = new StringsCompleter();
        try {
            List<Bus> busses = getBusses();

            for (Bus bus : busses) {
                delegate.getStrings().add(bus.getId());
            }

        } catch (Exception e) {
            // Ignore
        }
        return delegate.complete(session, commandLine, list);
    }

}
