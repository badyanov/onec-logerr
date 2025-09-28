package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record ErrorDescription(
        @Column(name = "description", columnDefinition = "TEXT")
        String description,
        @Column(name = "category")
        String category
) { 
    public ErrorDescription() {
        this(null, null);
    }
}