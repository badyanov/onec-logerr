package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SessionInfo {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "data_separation")
    private String dataSeparation;

    @Column(name = "platform_interface_language_code")
    private String platformInterfaceLanguageCode;

    @Column(name = "configuration_interface_language_code")
    private String configurationInterfaceLanguageCode;

    @Column(name = "session_locale_code")
    private String localeCode;

    // Конструкторы
    public SessionInfo() {}

    // Геттеры и сеттеры
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDataSeparation() {
        return dataSeparation;
    }

    public void setDataSeparation(String dataSeparation) {
        this.dataSeparation = dataSeparation;
    }

    public String getPlatformInterfaceLanguageCode() {
        return platformInterfaceLanguageCode;
    }

    public void setPlatformInterfaceLanguageCode(String platformInterfaceLanguageCode) {
        this.platformInterfaceLanguageCode = platformInterfaceLanguageCode;
    }

    public String getConfigurationInterfaceLanguageCode() {
        return configurationInterfaceLanguageCode;
    }

    public void setConfigurationInterfaceLanguageCode(String configurationInterfaceLanguageCode) {
        this.configurationInterfaceLanguageCode = configurationInterfaceLanguageCode;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
}
