package de.dotwee.md5dialog.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.dotwee.md5dialog.R;
import de.dotwee.md5dialog.model.MainModel;
import de.dotwee.md5dialog.model.MainModelImpl;
import de.dotwee.md5dialog.view.MainActivity;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
public final class MainPresenterImpl implements MainPresenter {
    // --Commented out by Inspection (13.11.2015 15:43):private static final String LOG_TAG = "MainPresenterImpl";
    private final EditText editText;
    private final TextView textView;
    private final Activity activity;
    private final MainModel mainModel;

    public MainPresenterImpl(Activity activity) {

        if (!(activity instanceof MainActivity)) {
            throw new RuntimeException("Parameter activity is not an instance of MainAcitivty");
        }

        this.textView = (TextView) activity.findViewById(R.id.textViewHash);
        this.editText = (EditText) activity.findViewById(R.id.editText);
        this.mainModel = MainModelImpl.getInstance();
        this.activity = activity;
    }

    /**
     * This method reacts on clicks on the positive button.
     */
    @Override
    public void onButtonPositive() {

        if (editText != null) {
            String value = editText.getText().toString();
            String hash = mainModel.getMd5Hash(value);

            if (!hash.isEmpty()) {

                final String message = MainModelImpl.getInstance().getMd5Message(value, hash);

                displayHashToast(message);
                displayHash(message);
            }

            editText.setText("");
        }
    }

    /**
     * This method displays the generated hash as a message on a textview.
     *
     * @param message The generated hash to display.
     */
    @Override
    public void displayHash(@NonNull String message) {

        if (textView != null) {

            if (textView.getVisibility() != View.VISIBLE) {
                textView.setVisibility(View.VISIBLE);
            }

            textView.setText(message);
        }
    }

    /**
     * This method displays the generated hash as a message on a {@link Toast}.
     *
     * @param message the message to display.
     */
    @Override
    public void displayHashToast(@NonNull String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
