package service;

import model.Event;
import model.EventCategory;
import dao.EventDAO;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventService {
    private List<Event> events;
    private EventDAO eventDAO;
    
    public EventService() {
        this.eventDAO = new EventDAO();
        this.events = eventDAO.loadEvents();
    }
    
    public void addEvent(String name, String address, String category, 
                        LocalDateTime dateTime, String description) {
        String id = generateEventId();
        Event event = new Event(id, name, address, category, dateTime, description);
        events.add(event);
        saveEvents();
    }
    
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
    
    public List<Event> getEventsByCategory(String category) {
        return events.stream()
                    .filter(e -> e.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
    }
    
    public List<Event> getUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        return events.stream()
                    .filter(e -> e.getDateTime().isAfter(now))
                    .sorted(Comparator.comparing(Event::getDateTime))
                    .collect(Collectors.toList());
    }
    
    public List<Event> getHappeningNowEvents() {
        return events.stream()
                    .filter(Event::isHappeningNow)
                    .collect(Collectors.toList());
    }
    
    public List<Event> getPastEvents() {
        return events.stream()
                    .filter(Event::hasPassed)
                    .sorted(Comparator.comparing(Event::getDateTime).reversed())
                    .collect(Collectors.toList());
    }
    
    public Event getEventById(String id) {
        return events.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }
    
    public boolean participateInEvent(String eventId, String userId) {
        Event event = getEventById(eventId);
        if (event != null) {
            event.addParticipant(userId);
            saveEvents();
            return true;
        }
        return false;
    }
    
    public boolean cancelParticipation(String eventId, String userId) {
        Event event = getEventById(eventId);
        if (event != null) {
            event.removeParticipant(userId);
            saveEvents();
            return true;
        }
        return false;
    }
    
    public List<Event> getUserEvents(String userId) {
        return events.stream()
                    .filter(e -> e.isParticipating(userId))
                    .sorted(Comparator.comparing(Event::getDateTime))
                    .collect(Collectors.toList());
    }
    
    private String generateEventId() {
        return "EVT" + System.currentTimeMillis();
    }
    
    private void saveEvents() {
        eventDAO.saveEvents(events);
    }
    
    public List<String> getAvailableCategories() {
        return Arrays.stream(EventCategory.values())
                    .map(EventCategory::toString)
                    .collect(Collectors.toList());
    }
} 