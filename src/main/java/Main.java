import controller.EventController;
import service.EventService;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Eventos da Cidade...");
        
        EventService eventService = new EventService();
        UserService userService = new UserService();
        
        EventController controller = new EventController(eventService, userService);
        controller.showMainMenu();
    }
} 