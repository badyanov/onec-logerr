package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class ErrorInfo {

    @Column(name = "user_description", columnDefinition = "TEXT")
    private String userDescription;

    @Embedded
    private SystemErrorInfo systemErrorInfo;

    @Embedded
    private ApplicationErrorInfo applicationErrorInfo;

    // Конструкторы
    public ErrorInfo() {}

    // Геттеры и сеттеры
    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public SystemErrorInfo getSystemErrorInfo() {
        return systemErrorInfo;
    }

    public void setSystemErrorInfo(SystemErrorInfo systemErrorInfo) {
        this.systemErrorInfo = systemErrorInfo;
    }

    public ApplicationErrorInfo getApplicationErrorInfo() {
        return applicationErrorInfo;
    }

    public void setApplicationErrorInfo(ApplicationErrorInfo applicationErrorInfo) {
        this.applicationErrorInfo = applicationErrorInfo;
    }
}
