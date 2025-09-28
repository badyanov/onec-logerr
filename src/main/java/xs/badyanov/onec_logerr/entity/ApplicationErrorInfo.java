package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "application_error_info")
public class ApplicationErrorInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    @CollectionTable(name = "application_error_descriptions", 
                     joinColumns = @JoinColumn(name = "application_error_info_id"))
    @OrderBy("id ASC")
    private List<ErrorDescription> errors;
    
    @ElementCollection
    @CollectionTable(name = "application_stack_descriptions", 
                     joinColumns = @JoinColumn(name = "application_error_info_id"))
    @OrderBy("id ASC")
    private List<StackDescription> stack;
    
    @Column(name = "stack_hash")
    private String stackHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public ApplicationErrorInfo() {}

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
    
    public List<ErrorDescription> getErrors() {
        return errors;
    }
    
    public void setErrors(List<ErrorDescription> errors) {
        this.errors = errors;
    }
    
    public List<StackDescription> getStack() {
        return stack;
    }
    
    public void setStack(List<StackDescription> stack) {
        this.stack = stack;
    }
    
    public String getStackHash() {
        return stackHash;
    }
    
    public void setStackHash(String stackHash) {
        this.stackHash = stackHash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
