package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "config_info")
public class ConfigInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "version")
    private String version;
    
    @Column(name = "compatibility_mode")
    private String compatibilityMode;
    
    @Column(name = "hash")
    private String hash;
    
    @Column(name = "change_enabled")
    private Boolean changeEnabled;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "config_extensions",
               joinColumns = @JoinColumn(name = "config_info_id"),
               inverseJoinColumns = @JoinColumn(name = "extension_id"))
    private List<Extension> extensions;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "config_disabled_extensions",
               joinColumns = @JoinColumn(name = "config_info_id"),
               inverseJoinColumns = @JoinColumn(name = "extension_id"))
    private List<Extension> disabledExtensions;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public ConfigInfo() {}

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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getCompatibilityMode() {
        return compatibilityMode;
    }
    
    public void setCompatibilityMode(String compatibilityMode) {
        this.compatibilityMode = compatibilityMode;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public Boolean getChangeEnabled() {
        return changeEnabled;
    }
    
    public void setChangeEnabled(Boolean changeEnabled) {
        this.changeEnabled = changeEnabled;
    }
    
    public List<Extension> getExtensions() {
        return extensions;
    }
    
    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }
    
    public List<Extension> getDisabledExtensions() {
        return disabledExtensions;
    }
    
    public void setDisabledExtensions(List<Extension> disabledExtensions) {
        this.disabledExtensions = disabledExtensions;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
