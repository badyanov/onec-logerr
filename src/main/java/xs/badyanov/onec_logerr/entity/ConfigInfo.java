package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.util.List;

@Embeddable
public class ConfigInfo {

    @Column(name = "config_name")
    private String name;

    @Column(name = "config_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "config_version")
    private String version;

    @Column(name = "compatibility_mode")
    private String compatibilityMode;

    @Column(name = "config_hash")
    private String hash;

    @Column(name = "change_enabled")
    private Boolean changeEnabled;

    @ElementCollection
    @CollectionTable(name = "config_extensions", joinColumns = @JoinColumn(name = "onec_error_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "extension_name")),
            @AttributeOverride(name = "hash", column = @Column(name = "extension_hash"))
    })
    private List<Extension> extensions;

    @ElementCollection
    @CollectionTable(name = "config_disabled_extensions", joinColumns = @JoinColumn(name = "onec_error_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "extension_name")),
            @AttributeOverride(name = "hash", column = @Column(name = "extension_hash"))
    })
    private List<Extension> disabledExtensions;

    // Конструкторы
    public ConfigInfo() {}

    // Геттеры и сеттеры
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
}
