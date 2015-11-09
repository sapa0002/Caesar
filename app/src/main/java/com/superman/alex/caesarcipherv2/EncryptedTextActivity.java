package com.superman.alex.caesarcipherv2;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import util.CaesarCipher;

import static com.superman.alex.caesarcipherv2.Constants.ABOUT_DIALOG_TAG;
import static com.superman.alex.caesarcipherv2.Constants.LOG_TAG;
import static com.superman.alex.caesarcipherv2.Constants.ROTATIONS;
import static com.superman.alex.caesarcipherv2.Constants.THE_MESSAGE;

/**
 *  Encrypting and descrypting user inputs
 *  @author Abix S.(sapa0002@alqonquinlive.com)
 */
public class EncryptedTextActivity extends AppCompatActivity {

    private EditText mDecryptedMsg;
    private Button mClearMsg;

    private DialogFragment mInfoDialog;
    private CharacterPickerDialog mRotationDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_text);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  Commented this two lines to avoid the exception, when user clicks the "FAB"
        //setSupportActionBar(toolbar);

        mDecryptedMsg = (EditText) findViewById(R.id.editMsg2);
        mClearMsg = (Button) findViewById(R.id.clearBtn2);

        mClearMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDecryptedMsg.setText("");
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlainTextActivity.class);
                String userMsg = mDecryptedMsg.getText().toString();
                String message = CaesarCipher.encryptAndDecrypt(userMsg);
                intent.putExtra(THE_MESSAGE, message);
                Log.i(LOG_TAG, "EncryptedTextActivity -> PlainTextActivity :: The decrypted message");
                startActivity(intent);
            }
        });

        mDecryptedMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Nothing TO-DO
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Nothing TO-DO
            }

            @Override
            public void afterTextChanged(Editable s) {
                fab.setEnabled(s.length() != 0);
            }
        });


        //The dialog is instantiated once, and shown each time
        //when user taps the "Wrench" icon.
        mInfoDialog = new UserNameDialog();

        Intent intent = getIntent();
        String message = intent.getStringExtra(Constants.THE_MESSAGE);
        mDecryptedMsg.setText(message);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //A new dialog is create just one more time, when user
        //clicks the "Wrench" icon.
        if (id == R.id.action_about) {
            mInfoDialog.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        if (id == R.id.action_rotation) {
            mRotationDialog = new CharacterPickerDialog(this, new View(this), null, ROTATIONS, false) {
                public void onClick(View v) {
                    dismiss();
                }

                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    dismiss();
                }
            };
            mRotationDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



























