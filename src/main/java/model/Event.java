package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String id;
    private String name;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String description;
    private List<String> participants;
    
    public Event(String id, String name, String address, String category, 
                 LocalDateTime dateTime, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
        this.participants = new ArrayList<>();
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<String> getParticipants() { return participants; }
    
    public void addParticipant(String userId) {
        if (!participants.contains(userId)) {
            participants.add(userId);
        }
    }
    
    public void removeParticipant(String userId) {
        participants.remove(userId);
    }
    
    public boolean isParticipating(String userId) {
        return participants.contains(userId);
    }
    
    public boolean isHappeningNow() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = dateTime.plusHours(3); // Assumindo que eventos duram 3 horas
        return now.isAfter(dateTime) && now.isBefore(endTime);
    }
    
    public boolean hasPassed() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = dateTime.plusHours(3);
        return now.isAfter(endTime);
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | %s | %s | %s | %s | %s | Participantes: %d", 
                           id, name, address, category, 
                           dateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                           description, participants.size());
    }
} 