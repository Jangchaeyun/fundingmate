package com.fund.fundingmate.domain.auth.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoResponse {
    private long id;
    private String connected_at;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    private KakaoProperties properties;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConnected_at() {
        return connected_at;
    }

    public void setConnected_at(String connected_at) {
        this.connected_at = connected_at;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    public void setKakaoAccount(KakaoAccount kakaoAccount) {
        this.kakaoAccount = kakaoAccount;
    }

    public KakaoProperties getProperties() {
        return properties;
    }

    public void setProperties(KakaoProperties properties) {
        this.properties = properties;
    }

    public static class KakaoAccount {
        private boolean profile_needs_agreement;
        private KakaoProfile profile;
        private boolean email_needs_agreement;
        private boolean is_email_valid;
        private boolean is_email_verified;
        private String email;

        public boolean isProfile_needs_agreement() {
            return profile_needs_agreement;
        }

        public void setProfile_needs_agreement(boolean profile_needs_agreement) {
            this.profile_needs_agreement = profile_needs_agreement;
        }

        public KakaoProfile getProfile() {
            return profile;
        }

        public void setProfile(KakaoProfile profile) {
            this.profile = profile;
        }

        public boolean isEmail_needs_agreement() {
            return email_needs_agreement;
        }

        public void setEmail_needs_agreement(boolean email_needs_agreement) {
            this.email_needs_agreement = email_needs_agreement;
        }

        public boolean isIs_email_valid() {
            return is_email_valid;
        }

        public void setIs_email_valid(boolean is_email_valid) {
            this.is_email_valid = is_email_valid;
        }

        public boolean isIs_email_verified() {
            return is_email_verified;
        }

        public void setIs_email_verified(boolean is_email_verified) {
            this.is_email_verified = is_email_verified;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class KakaoProfile {
        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    public static class KakaoProperties {
        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
