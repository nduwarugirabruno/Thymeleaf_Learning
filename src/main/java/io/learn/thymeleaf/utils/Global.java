package io.nokash.app.user.utils;

import io.nokash.app.user.entity.app.Reunion;
import io.nokash.app.user.entity.app.RoleType;
import io.nokash.app.user.entity.metier.Membre;

import java.util.Date;

public class Global {

    public static boolean invalidCreatedMember(String name, String surname, Date dateNaiss, Date dateArrivee, Date dateInscription, String ncni, String quartier, String nomPere, String nomMere, Long numeroTelephone) {
        if (name == null) return true;
        if (surname == null) return true;
        if (dateNaiss == null) return true;
        if (dateArrivee == null) return true;
        if (dateInscription == null) return true;
        if (ncni == null) return true;
        if (quartier == null) return true;
        if (nomPere == null) return true;
        if (nomMere == null) return true;
        return numeroTelephone == null;
    }

    public static boolean invalidCreatedRole(RoleType roleType, Membre membre, Reunion reunion) {
        if (membre == null) return true;
        if (reunion == null) return true;
        return roleType == null;
    }

}
