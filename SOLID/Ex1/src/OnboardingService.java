import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
    }

    public void registerFromRawInput(String raw) {
        ConsoleFormatter.printInput(raw);

        // Parse input
        Map<String, String> kv = Parsing.parseKeyValuePairs(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        // Validate input
        List<String> errors = StudentValidator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            ConsoleFormatter.printValidationErrors(errors);
            return;
        }

        // Generate ID and create record
        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        // Save to repository
        repository.save(rec);

        // Print success message
        ConsoleFormatter.printSuccess(id, repository.count(), rec);
    }
}
