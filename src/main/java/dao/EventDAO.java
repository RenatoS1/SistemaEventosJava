package dao;

import model.Event;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private static final String FILE_PATH = "events.data";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public void saveEvents(List<Event> events) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Event event : events) {
                writer.println(serializeEvent(event));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }
    
    public List<Event> loadEvents() {
        List<Event> events = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            return events;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Event event = deserializeEvent(line);
                if (event != null) {
                    events.add(event);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
        }
        
        return events;
    }
    
    private String serializeEvent(Event event) {
        return String.format("%s|%s|%s|%s|%s|%s|%s",
                           event.getId(),
                           event.getName(),
                           event.getAddress(),
                           event.getCategory(),
                           event.getDateTime().format(formatter),
                           event.getDescription(),
                           String.join(",", event.getParticipants()));
    }
    
    private Event deserializeEvent(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 6) {
                String id = parts[0];
                String name = parts[1];
                String address = parts[2];
                String category = parts[3];
                LocalDateTime dateTime = LocalDateTime.parse(parts[4], formatter);
                String description = parts[5];
                
                Event event = new Event(id, name, address, category, dateTime, description);
                
                if (parts.length > 6 && !parts[6].isEmpty()) {
                    String[] participants = parts[6].split(",");
                    for (String participant : participants) {
                        event.addParticipant(participant);
                    }
                }
                
                return event;
            }
        } catch (Exception e) {
            System.err.println("Erro ao deserializar evento: " + e.getMessage());
        }
        return null;
    }
} 