package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServerInfo {

    @Column(name = "server_platform_type")
    private String platformType;

    @Column(name = "server_app_version")
    private String appVersion;

    @Column(name = "dbms")
    private String dbms;

    // Конструкторы
    public ServerInfo() {}

    // Геттеры и сеттеры
    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDbms() {
        return dbms;
    }

    public void setDbms(String dbms) {
        this.dbms = dbms;
    }
}
