package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "info_base_info")
public class InfoBaseInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "locale_code")
    private String localeCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public InfoBaseInfo() {}

    // Callback-методы JPA lifecycle
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLocaleCode() {
        return localeCode;
    }
    
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
