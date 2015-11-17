package de.dotwee.md5dialog.presenter;

import android.support.annotation.NonNull;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
public interface MainPresenter {
    // --Commented out by Inspection (13.11.2015 15:43):static final String LOG_TAG = "MainPresenter";

    /**
     * This method reacts on clicks on the positive button.
     */
    void onButtonPositive();

    /**
     * This method displays the generated hash as a message on a {@link android.widget.TextView}.
     *
     * @param message The message to display.
     */
    void displayHash(@NonNull final String message);

    /**
     * This method displays the generated hash as a message on a {@link android.widget.Toast}.
     *
     * @param message the message to display.
     */
    void displayHashToast(@NonNull final String message);
}
