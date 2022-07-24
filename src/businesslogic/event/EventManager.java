package businesslogic.event;

import businesslogic.CatERing;
import javafx.collections.ObservableList;

public class EventManager {
    public ObservableList<EventInfo> getEventInfo() {
        return EventInfo.loadAllEventInfo();
    }

    public EventInfo getEventInfoByService(ServiceInfo serviceCorrelated) {
        ObservableList<EventInfo> eventInfos = CatERing.getInstance().getEventManager().getEventInfo();

        EventInfo eventInfoCorrelated = null;

        for(EventInfo e : eventInfos) {
            ObservableList<ServiceInfo> serviceInfos = e.getServices();
            for(ServiceInfo s : serviceInfos) {
                if(s.getId() == serviceCorrelated.getId()){
                    eventInfoCorrelated = e;
                    break;
                }
            }
        }

        return eventInfoCorrelated;
    }
}
