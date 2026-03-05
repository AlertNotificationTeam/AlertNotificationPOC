package com.poc.alerts.security;

public final class SecurityMaskingUtil {

    private SecurityMaskingUtil() {
    }

    // Mask mobile number
    public static String maskMobile(String mobile) {

        if (mobile == null || mobile.length() < 6) {
            return mobile;
        }

        return mobile.substring(0, 3) + "******" + mobile.substring(mobile.length() - 2);
    }

    // Mask email address
    public static String maskEmail(String email) {

        if (email == null || !email.contains("@")) {
            return email;
        }

        String[] parts = email.split("@");

        String name = parts[0];
        String domain = parts[1];

        if (name.length() <= 2) {
            return "***@" + domain;
        }

        return name.substring(0, 2) + "***@" + domain;
    }

    // Mask account number
    public static String maskAccount(String account) {

        if (account == null || account.length() < 4) {
            return account;
        }

        return "******" + account.substring(account.length() - 4);
    }

    // Mask API token
    public static String maskToken(String token) {

        if (token == null || token.length() < 6) {
            return "****";
        }

        return token.substring(0, 4) + "****";
    }
}