package de.dotwee.md5dialog.presenter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.dotwee.md5dialog.R;
import de.dotwee.md5dialog.model.MainModelImpl;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
public final class MainPresenterImpl implements MainPresenter {
    // --Commented out by Inspection (13.11.2015 15:43):private static final String LOG_TAG = "MainPresenterImpl";
    private final ClipboardManager clipboard;
    private final EditText editText;
    private final Activity activity;

    public MainPresenterImpl(Activity activity) {
        this.clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        this.editText = (EditText) activity.findViewById(R.id.editText);
        this.activity = activity;
    }

    /**
     * This method displays the user's hash as toast.
     *
     * @param message Final message to display.
     */
    private void displayHashToast(@NonNull final String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method displays the user's hash to a textView.
     *
     * @param message Final Md5 hash to display.
     */
    private void displayHashText(@NonNull final String message) {

        TextView textView = (TextView) activity.findViewById(R.id.textViewHash);

        if (textView != null) {

            if (textView.getVisibility() != View.VISIBLE) {
                textView.setVisibility(View.VISIBLE);
            }

            textView.setText(message);
        }
    }

    /**
     * This method copies the user's hash to its clipboard.
     *
     * @param hash Final Md5 hash
     */
    private void copyHashToClipboard(@NonNull final String hash) {
        clipboard.setPrimaryClip(ClipData.newPlainText("Md5 hash", hash));
    }

    /**
     * This method reacts on clicks on the positive button.
     */
    @Override
    public void onButtonPositive() {

        if (editText != null) {
            String value = editText.getText().toString();
            String hash = MainModelImpl.getInstance()
                    .getMd5Hash(value);

            if (!hash.isEmpty()) {
                copyHashToClipboard(hash);

                final String message = MainModelImpl.getInstance().getMd5Message(activity, value, hash);

                displayHashToast(message);
                displayHashText(message);
            }

            editText.setText("");
        }
    }
}
