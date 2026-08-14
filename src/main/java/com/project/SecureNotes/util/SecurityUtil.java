package com.project.SecureNotes.util;

import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    public static boolean isAdmin(UserDetails userDetails){
        return userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));

    }

}
