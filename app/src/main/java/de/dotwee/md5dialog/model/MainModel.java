package de.dotwee.md5dialog.model;

import android.support.annotation.NonNull;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
public interface MainModel {
    // --Commented out by Inspection (13.11.2015 15:43):String LOG_TAG = "MainModel";

    /**
     * This method returns a md5-hash of the input string.
     *
     * @param value The base string.
     * @return Hash of base string.
     */
    @NonNull
    String getMd5Hash(@NonNull String value);

    /**
     * This method wraps a md5-hash into a message for the user.
     *
     * @param value   The value of the hash.
     * @param hash    The hash to wrap.
     * @return Created message.
     */
    @NonNull
    String getMd5Message(@NonNull String value, @NonNull String hash);
}
