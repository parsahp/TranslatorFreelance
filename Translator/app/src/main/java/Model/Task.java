package Model;

public class Task {
    private String translatorId;
    private String clientId;
    private String description;
    private String language;
    private String jobName;
    private String isActive;
    private String clientFirstName;
    private String clientLastName;
    private String clientRating;
    private String clientEmail;
    private String clientPhoneNumber;

    public Task() {
    }

    public Task(String translatorId, String clientId, String description, String language, String jobName, String isActive, String clientFirstName, String clientLastName, String clientRating, String clientEmail, String clientPhoneNumber) {
        this.translatorId = translatorId;
        this.clientId = clientId;
        this.description = description;
        this.language = language;
        this.jobName = jobName;
        this.isActive = isActive;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientRating = clientRating;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public Task(String translatorId, String clientId, String description, String language, String jobName, String isActive, String clientFirstName, String clientLastName, String clientRating) {
        this.translatorId = translatorId;
        this.clientId = clientId;
        this.description = description;
        this.language = language;
        this.jobName = jobName;
        this.isActive = isActive;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientRating = clientRating;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String active) {
        isActive = active;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientRating() {
        return clientRating;
    }

    public void setClientRating(String clientRating) {
        this.clientRating = clientRating;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
