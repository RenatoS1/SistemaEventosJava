package controller;

import model.Event;
import model.User;
import service.EventService;
import service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EventController {
    private EventService eventService;
    private UserService userService;
    private Scanner scanner;
    private DateTimeFormatter formatter;
    
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }
    
    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== SISTEMA DE EVENTOS DA CIDADE ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Cadastrar Evento");
            System.out.println("3. Consultar Eventos");
            System.out.println("4. Participar de Evento");
            System.out.println("5. Cancelar Participação");
            System.out.println("6. Meus Eventos");
            System.out.println("7. Eventos Acontecendo Agora");
            System.out.println("8. Eventos Passados");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            
            switch (choice) {
                case 1: registerUser(); break;
                case 2: registerEvent(); break;
                case 3: consultEvents(); break;
                case 4: participateInEvent(); break;
                case 5: cancelParticipation(); break;
                case 6: showUserEvents(); break;
                case 7: showHappeningNowEvents(); break;
                case 8: showPastEvents(); break;
                case 9: 
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void registerUser() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Cidade: ");
        String city = scanner.nextLine();
        
        userService.addUser(name, email, phone, city);
        System.out.println("Usuário cadastrado com sucesso!");
    }
    
    private void registerEvent() {
        System.out.println("\n=== CADASTRO DE EVENTO ===");
        
        System.out.print("Nome do evento: ");
        String name = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String address = scanner.nextLine();
        
        System.out.println("Categorias disponíveis:");
        List<String> categories = eventService.getAvailableCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        
        System.out.print("Escolha a categoria (número): ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();
        
        String category = categories.get(categoryChoice - 1);
        
        System.out.print("Data (dd/MM/yyyy): ");
        String dateStr = scanner.nextLine();
        
        System.out.print("Hora (HH:mm): ");
        String timeStr = scanner.nextLine();
        
        LocalDateTime dateTime = parseDateTime(dateStr, timeStr);
        
        System.out.print("Descrição: ");
        String description = scanner.nextLine();
        
        eventService.addEvent(name, address, category, dateTime, description);
        System.out.println("Evento cadastrado com sucesso!");
    }
    
    private void consultEvents() {
        System.out.println("\n=== CONSULTA DE EVENTOS ===");
        System.out.println("1. Todos os eventos");
        System.out.println("2. Por categoria");
        System.out.println("3. Próximos eventos");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        List<Event> events = null;
        
        switch (choice) {
            case 1:
                events = eventService.getAllEvents();
                break;
            case 2:
                System.out.println("Categorias disponíveis:");
                List<String> categories = eventService.getAvailableCategories();
                for (int i = 0; i < categories.size(); i++) {
                    System.out.println((i + 1) + ". " + categories.get(i));
                }
                System.out.print("Escolha a categoria (número): ");
                int categoryChoice = scanner.nextInt();
                scanner.nextLine();
                String category = categories.get(categoryChoice - 1);
                events = eventService.getEventsByCategory(category);
                break;
            case 3:
                events = eventService.getUpcomingEvents();
                break;
        }
        
        if (events != null && !events.isEmpty()) {
            System.out.println("\nEventos encontrados:");
            for (Event event : events) {
                System.out.println(event);
            }
        } else {
            System.out.println("Nenhum evento encontrado.");
        }
    }
    
    private void participateInEvent() {
        System.out.println("\n=== PARTICIPAR DE EVENTO ===");
        
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        
        User user = userService.getUserByEmail(email);
        if (user == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        System.out.print("Digite o ID do evento: ");
        String eventId = scanner.nextLine();
        
        if (eventService.participateInEvent(eventId, user.getId())) {
            System.out.println("Participação confirmada com sucesso!");
        } else {
            System.out.println("Erro ao confirmar participação. Verifique o ID do evento.");
        }
    }
    
    private void cancelParticipation() {
        System.out.println("\n=== CANCELAR PARTICIPAÇÃO ===");
        
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        
        User user = userService.getUserByEmail(email);
        if (user == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        System.out.print("Digite o ID do evento: ");
        String eventId = scanner.nextLine();
        
        if (eventService.cancelParticipation(eventId, user.getId())) {
            System.out.println("Participação cancelada com sucesso!");
        } else {
            System.out.println("Erro ao cancelar participação. Verifique o ID do evento.");
        }
    }
    
    private void showUserEvents() {
        System.out.println("\n=== MEUS EVENTOS ===");
        
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        
        User user = userService.getUserByEmail(email);
        if (user == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        List<Event> userEvents = eventService.getUserEvents(user.getId());
        
        if (!userEvents.isEmpty()) {
            System.out.println("Seus eventos:");
            for (Event event : userEvents) {
                System.out.println(event);
            }
        } else {
            System.out.println("Você não está participando de nenhum evento.");
        }
    }
    
    private void showHappeningNowEvents() {
        System.out.println("\n=== EVENTOS ACONTECENDO AGORA ===");
        
        List<Event> happeningEvents = eventService.getHappeningNowEvents();
        
        if (!happeningEvents.isEmpty()) {
            System.out.println("Eventos acontecendo agora:");
            for (Event event : happeningEvents) {
                System.out.println(event);
            }
        } else {
            System.out.println("Nenhum evento acontecendo no momento.");
        }
    }
    
    private void showPastEvents() {
        System.out.println("\n=== EVENTOS PASSADOS ===");
        
        List<Event> pastEvents = eventService.getPastEvents();
        
        if (!pastEvents.isEmpty()) {
            System.out.println("Eventos que já ocorreram:");
            for (Event event : pastEvents) {
                System.out.println(event);
            }
        } else {
            System.out.println("Nenhum evento passado encontrado.");
        }
    }
    
    private LocalDateTime parseDateTime(String dateStr, String timeStr) {
        try {
            String[] dateParts = dateStr.split("/");
            String[] timeParts = timeStr.split(":");
            
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido. Usando data atual.");
            return LocalDateTime.now().plusDays(1);
        }
    }
} 