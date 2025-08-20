package model;

public enum EventCategory {
    FESTA("Festa"),
    SHOW("Show"),
    EVENTO_ESPORTIVO("Evento Esportivo"),
    PALESTRA("Palestra"),
    WORKSHOP("Workshop"),
    EXPOSICAO("Exposição"),
    TEATRO("Teatro"),
    CINEMA("Cinema"),
    OUTROS("Outros");
    
    private final String displayName;
    
    EventCategory(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
    
    public static EventCategory fromString(String text) {
        for (EventCategory category : EventCategory.values()) {
            if (category.displayName.equalsIgnoreCase(text)) {
                return category;
            }
        }
        return OUTROS;
    }
} 