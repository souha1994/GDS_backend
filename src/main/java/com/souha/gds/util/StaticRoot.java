package com.souha.gds.util;

public interface StaticRoot {

    String APP_ROOT = "stockiteasy";
    String COMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT + "/commandeFournisseurs";
    String ENTREPRISE_ENDPOINT = APP_ROOT + "/entreprises";
    String ENTREPRISE_ENDPOINT_SAVE = ENTREPRISE_ENDPOINT + "/create";
    String ENTREPRISE_ENDPOINT_FINDBYID = ENTREPRISE_ENDPOINT + "/{idEntreprise}";
    String ENTREPRISE_ENDPOINT_FINDBYNOM = ENTREPRISE_ENDPOINT + "/{nomEntreprise}";
    String ENTREPRISE_ENDPOINT_DELETE = ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}";
    String FOURNISSEUR_ENDPOINT = APP_ROOT + "/fournisseurs";
    String FOURNISSEUR_ENDPOINT_SAVE = FOURNISSEUR_ENDPOINT + "/create";

    String FOURNISSEUR_ENDPOINT_FINDBYID = FOURNISSEUR_ENDPOINT + "/{idFournisseur}";
    String FOURNISSEUR_ENDPOINT_FINDBYMAIL = FOURNISSEUR_ENDPOINT + "/{mail}";
    String FOURNISSEUR_ENDPOINT_DELETE = FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}";

    String UTILISATEUR_ENDPOINT = APP_ROOT +"/utilisateurs";
    String UTILISATEUR_ENDPOINT_SAVE = UTILISATEUR_ENDPOINT + "/create";
    String UTILISATEUR_ENDPOINT_FINDBYID = UTILISATEUR_ENDPOINT + "/{idUtilisateur}";
    String UTILISATEUR_ENDPOINT_FINDBYMAIL = UTILISATEUR_ENDPOINT + "/findbyemail/{email}";
    String UTILISATEUR_ENDPOINT_FINDBYMOTPASSE = UTILISATEUR_ENDPOINT + "/{motPasse}";
    String UTILISATEUR_ENDPOINT_DELETE = UTILISATEUR_ENDPOINT + "/delete/{idFournisseur}";
    String VENTE_ENDPOINT = APP_ROOT +"/ventes";
    String VENTE_ENDPOINT_SAVE = VENTE_ENDPOINT + "/create";
    String VENTE_ENDPOINT_FINDBYID = VENTE_ENDPOINT + "/{idVente}";
    String VENTE_ENDPOINT_FINDBYCODE = VENTE_ENDPOINT + "/{codeVente}";
    String VENTE_ENDPOINT_FINDBYDATEVENTE = VENTE_ENDPOINT + "/{dateVente}";
    String VENTE_ENDPOINT_DELETE = VENTE_ENDPOINT + "/delete/{idVente}";
    String AUTHENTICATION_ENDPOINT = APP_ROOT+"/authentication";

    String MVT_ENDPOINT = APP_ROOT+"/mouvements";
}
