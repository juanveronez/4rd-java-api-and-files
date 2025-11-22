package modules.module2.models;

import java.util.List;

public record Book (String title, List<String> writers, Publisher publisher) {
    @Override
    public String toString() {
        return """
               Title: %s,
               Writers: %s,
               Publisher Name: %s,
               Publisher Contact Email: %s
               """.formatted(title(), writers(), publisher.name(), publisher.email());
    }
}
