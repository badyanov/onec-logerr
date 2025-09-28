package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "dumps")
public class Dump {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "type")
    private String type;

    @Column(name = "reason_for_no_dump")
    private String reasonForNoDump;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "binary_file_id")
    private BinaryFile file;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public Dump() {}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReasonForNoDump() {
        return reasonForNoDump;
    }

    public void setReasonForNoDump(String reasonForNoDump) {
        this.reasonForNoDump = reasonForNoDump;
    }

    public BinaryFile getFile() {
        return file;
    }

    public void setFile(BinaryFile file) {
        this.file = file;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
