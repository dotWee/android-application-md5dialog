package de.dotwee.md5dialog.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.dotwee.md5dialog.R;
import de.dotwee.md5dialog.presenter.MainPresenter;
import de.dotwee.md5dialog.presenter.MainPresenterImpl;

/**
 * Created by Lukas Wolfsteiner on 13.11.2015.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    // --Commented out by Inspection (13.11.2015 15:43):private static final String LOG_TAG = "MainActivity";
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);

        Button button = (Button) findViewById(R.id.buttonPositive);
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonPositive:
                mainPresenter.onButtonPositive();
                break;
        }
    }
}
