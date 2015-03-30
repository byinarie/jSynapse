package org.swarmcom.jsynapse.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.swarmcom.jsynapse.JSynapseServer.DOMAIN;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.RandomStringUtils.random;

public class Registration {

    public static class RegistrationInfo {
        @JsonProperty
        String type;

        @JsonProperty
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<String> stages;

        public void setStages(List<String> stages) {
            this.stages = stages;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class RegistrationFlows {

        @JsonProperty
        List<RegistrationInfo> flows = new LinkedList<>();

        public RegistrationFlows(List<RegistrationInfo> flows) {
            this.flows = flows;
        }
    }

    public static class RegistrationResult {

        @JsonProperty("user_id")
        String user;

        @JsonProperty("access_token")
        String accessToken;

        public RegistrationResult(String user) {
            this.user = join(new String[]{"@", user,":", DOMAIN});
            this.accessToken = random(16, true, false);
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getAccessToken() {
            return accessToken;
        }
    }

    public static class RegistrationSubmission extends HashMap<String, String> {

        public RegistrationSubmission() {
        }

        public String getType() {
            return get("type");
        }
    }
}
