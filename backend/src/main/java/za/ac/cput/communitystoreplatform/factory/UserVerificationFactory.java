package za.ac.cput.communitystoreplatform.factory;

import za.ac.cput.communitystoreplatform.domain.UserVerification;

import java.time.LocalDate;

public class UserVerificationFactory {

    public static UserVerification createUserVerification(
            int verificationId,
            String verificationType,
            String verificationStatus,
            String verificationDocument,
            LocalDate verifiedAt) {

        return new UserVerification.Builder()
                .setVerificationId(verificationId)
                .setVerificationType(verificationType)
                .setVerificationStatus(verificationStatus)
                .setVerificationDocument(verificationDocument)
                .setVerifiedAt(verifiedAt)
                .build();
    }
}