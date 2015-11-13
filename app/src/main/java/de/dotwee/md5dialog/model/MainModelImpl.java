package de.dotwee.md5dialog.model;

import android.content.Context;
import android.support.annotation.NonNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.dotwee.md5dialog.R;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
public final class MainModelImpl implements MainModel {
    // --Commented out by Inspection (13.11.2015 15:43):private static final String LOG_TAG = "MainModelImpl";
    private static MainModelImpl instance;

    private MainModelImpl() {
        instance = this;
    }

    public static synchronized MainModel getInstance() {
        if (instance == null) {
            instance = new MainModelImpl();
        }

        return instance;
    }

    /**
     * This method returns a md5-hash of the input string.
     *
     * @param value The base string.
     * @return Hash of base string.
     */
    @NonNull
    @Override
    public String getMd5Hash(@NonNull String value) {
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");

            // Encryption algorithm
            mdEnc.update(value.getBytes(), 0, value.length());
            String md5 = new BigInteger(1, mdEnc.digest()).toString(16);

            while (md5.length() < 32) {
                md5 = "0" + md5;
            }

            return md5;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        }

        return "";
    }

    /**
     * This method wraps a md5-hash into a message for the user.
     *
     * @param context Needed to access resources.
     * @param value   The value of the hash.
     * @param hash    The hash to wrap.
     * @return Created message.
     */
    @NonNull
    @Override
    public String getMd5Message(@NonNull Context context, @NonNull String value, @NonNull String hash) {

        String raw = context.getResources().getString(R.string.hash_wrapper);

        // embed value
        raw = raw.replace("&1", value);

        // embed hash
        raw = raw.replace("&2", hash);

        return raw;
    }
}
