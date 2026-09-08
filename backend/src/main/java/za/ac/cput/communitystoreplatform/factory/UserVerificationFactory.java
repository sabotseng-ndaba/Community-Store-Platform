package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.UserVerification;
import za.ac.cput.communitystoreplatform.util.Helper;

import java.time.LocalDate;

public class UserVerificationFactory {
    public UserVerification createUserVerification(int verificationId, String verificationType, String verificationStatus,
                                                   String verificationDocument, LocalDate verifiedAt) {

        if(Helper.isNullOrEmpty(verificationDocument)
        && Helper.isValidInt(verificationId)
        && Helper.isNullOrEmpty(verificationType)
        && Helper.isNullOrEmpty(verificationStatus)
        && Helper.isNull(verifiedAt)){
            return null;
        }

        return new UserVerification.Builder()
                .setVerificationId(verificationId)
                .setVerificationType(verificationType)
                .setVerificationStatus(verificationStatus)
                .setVerificationDocument(verificationDocument)
                .setVerifiedAt(verifiedAt)
                .build();
    }
}