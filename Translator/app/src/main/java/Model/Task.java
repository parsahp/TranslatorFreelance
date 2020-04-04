package Model;

public class Task {
    private String translatorId;
    private String clientId;
    private String description;
    private String language;
    private String jobName;
    private boolean isActive;

    public Task() {
    }

    public Task(String translatorId, String clientId, String description, String language, String jobName, boolean isActive) {
        this.translatorId = translatorId;
        this.clientId = clientId;
        this.description = description;
        this.language = language;
        this.jobName = jobName;
        this.isActive = isActive;
    }

    public String getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(String translatorId) {
        this.translatorId = translatorId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
