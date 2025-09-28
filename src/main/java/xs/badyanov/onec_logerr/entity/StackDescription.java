package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record StackDescription(
        @Column(name = "module_name")
        String moduleName,
        @Column(name = "line_number")
        Integer lineNumber,
        @Column(name = "line_content", columnDefinition = "TEXT")
        String lineContent
) {
    public StackDescription() {
        this(null, null, null);
    }
}