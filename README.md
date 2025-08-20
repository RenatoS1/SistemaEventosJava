# Sistema de Eventos da Cidade

## Descrição
Sistema desenvolvido em Java para cadastro e gerenciamento de eventos da cidade, permitindo que usuários se cadastrem, criem eventos e participem de atividades locais.

## Funcionalidades

### ✅ Usuários
- Cadastro com nome, email, telefone e cidade
- Atualização de dados pessoais
- Consulta de perfil

### ✅ Eventos
- Cadastro com nome, endereço, categoria, horário e descrição
- Categorias predefinidas: Festa, Show, Evento Esportivo, Palestra, Workshop, Exposição, Teatro, Cinema, Outros
- Consulta por diferentes critérios (todos, por categoria, próximos)
- Participação e cancelamento de participação
- Visualização de eventos do usuário

### ✅ Sistema Inteligente
- Ordenação automática por horário
- Identificação de eventos acontecendo no momento
- Listagem de eventos passados
- Persistência automática em arquivos de texto

## Estrutura do Projeto

```
src/main/java/
├── model/           # Classes de modelo
│   ├── User.java           # Usuário do sistema
│   ├── Event.java          # Evento da cidade
│   └── EventCategory.java  # Categorias de eventos
├── dao/            # Camada de acesso a dados
│   ├── UserDAO.java        # Persistência de usuários
│   └── EventDAO.java       # Persistência de eventos
├── service/        # Lógica de negócio
│   ├── UserService.java    # Serviços de usuário
│   └── EventService.java   # Serviços de evento
├── controller/     # Controladores da aplicação
│   └── EventController.java # Interface principal
└── Main.java       # Ponto de entrada da aplicação
```

## Tecnologias Utilizadas

- **Java 8+** - Linguagem de programação
- **Paradigma OO** - Programação Orientada a Objetos
- **Padrão MVC** - Model-View-Controller
- **Console** - Interface via linha de comando
- **Persistência** - Arquivos de texto (.data)

## Como Executar

### Pré-requisitos
- Java 8 ou superior instalado
- Terminal/linha de comando

### Compilação
```bash
# Criar pasta bin
mkdir -p bin

# Compilar o projeto
javac -d bin src/main/java/*.java src/main/java/*/*.java
```

### Execução
```bash
# Executar o programa
java -cp bin Main
```

## Arquivos de Dados

O sistema cria automaticamente os seguintes arquivos:
- `users.data` - Dados dos usuários cadastrados
- `events.data` - Dados dos eventos criados

## Menu Principal

1. **Cadastrar Usuário** - Criar nova conta no sistema
2. **Cadastrar Evento** - Criar novo evento na cidade
3. **Consultar Eventos** - Visualizar eventos disponíveis
4. **Participar de Evento** - Confirmar presença em evento
5. **Cancelar Participação** - Cancelar presença confirmada
6. **Meus Eventos** - Visualizar eventos em que participa
7. **Eventos Acontecendo Agora** - Ver eventos em andamento
8. **Eventos Passados** - Histórico de eventos realizados
9. **Sair** - Encerrar o programa

## Diagrama de Classes

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      User       │    │      Event      │    │  EventCategory  │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - id: String    │    │ - id: String    │    │ FESTA           │
│ - name: String  │    │ - name: String  │    │ SHOW            │
│ - email: String │    │ - address: String│   │ EVENTO_ESPORTIVO│
│ - phone: String │    │ - category: String│  │ PALESTRA        │
│ - city: String  │    │ - dateTime: LocalDateTime│ WORKSHOP      │
├─────────────────┤    │ - description: String│ EXPOSICAO       │
│ + getters/setters│   │ - participants: List<String>│ TEATRO     │
│ + toString()    │    ├─────────────────┤    │ CINEMA          │
└─────────────────┘    │ + getters/setters│   │ OUTROS          │
                       │ + addParticipant()│  └─────────────────┘
                       │ + removeParticipant()│
                       │ + isHappeningNow()│
                       │ + hasPassed()     │
                       └─────────────────┘
```

## Características Técnicas

- **Arquitetura MVC** para separação de responsabilidades
- **Persistência em arquivo** para simplicidade e portabilidade
- **Tratamento de erros** robusto
- **Validações** de entrada do usuário
- **Interface intuitiva** via console
- **Código limpo** e bem documentado

## Desenvolvedor

Sistema desenvolvido como atividade prática da disciplina de Programação Orientada a Objetos, demonstrando competências em:
- Análise e design de sistemas
- Implementação de padrões arquiteturais
- Desenvolvimento de aplicações Java
- Persistência de dados
- Interface com usuário

## Licença

Este projeto é de uso educacional e acadêmico. 