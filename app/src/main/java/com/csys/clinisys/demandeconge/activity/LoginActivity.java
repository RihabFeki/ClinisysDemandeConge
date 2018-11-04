package com.csys.clinisys.demandeconge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.helper.Alerte;
import com.csys.clinisys.demandeconge.helper.KeyboardUtil;
import com.csys.clinisys.demandeconge.helper.OtherFunction;
import com.csys.clinisys.demandeconge.model.Authentification;
import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.model.User;
import com.csys.clinisys.demandeconge.webservice.DemandeCongeWSService;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    TextInputLayout txtInputUsername, txtInputPassword;
    TextView txtNomClinique, txtMsg;
    EditText txtUserName, txtPassWord;
    Button btnValider, btnChangerCli;
    Clinique clinique = new Clinique();
    ArrayList<Clinique> cliniqueList = new ArrayList<Clinique>();
    User user = new User();

    CliniqueDAO cliniqueDAO;
    UserDAO userDAO;
    MaterialDialog dialog;
    Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNomClinique = (TextView) findViewById(R.id.txtNomClinique);
        txtUserName = (EditText) findViewById(R.id.txtUsername);
        txtUserName.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        txtUserName.addTextChangedListener(new EdiLoginTextListener());
        txtPassWord = (EditText) findViewById(R.id.txtPassword);
        txtPassWord.addTextChangedListener(new EdiPasswordTextListener());
        txtMsg = (TextView) findViewById(R.id.msg);
        btnValider = (Button) findViewById(R.id.btnValider);
        btnChangerCli = (Button) findViewById(R.id.btnChangerCli);
        txtInputUsername = (TextInputLayout) findViewById(R.id.txtInputUsername);
        txtInputPassword = (TextInputLayout) findViewById(R.id.txtInputPassword);
        mainHandler = new Handler(getBaseContext().getMainLooper());
        clinique = (Clinique) getIntent().getSerializableExtra("Clinique");
        cliniqueDAO = new CliniqueDAO(getBaseContext());
        userDAO = new UserDAO(getBaseContext());
        cliniqueList = cliniqueDAO.getAllClinique();
        OtherFunction.strictMode();
        txtNomClinique.setText(clinique.getNomCli());

        dialog = new MaterialDialog.Builder(LoginActivity.this)
                .title("Authentification")
                .content("en cours ...")
                .progress(true, 0)
                .build();
        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProgressDialog();
                new Thread(new Runnable() {
                    public void run() {
                        String login = String.valueOf(txtUserName.getText());
                        String password = String.valueOf(txtPassWord.getText());
                        if (login.length() == 0) {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtInputUsername.setError("Veuillez saisir votre nom d'utilisateur !");
                                    hideProgressDialog();
                                }
                            });
                        } else if (password.length() == 0) {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtInputPassword.setError("Veuillez saisir votre mots de passe !");
                                    hideProgressDialog();
                                }
                            });
                        } else {
                            Authentification authentification = DemandeCongeWSService.GetAuthentification(login, password);
                            if (!authentification.getActif().equalsIgnoreCase("-1")) {
                                KeyboardUtil.hideSoftKeyboard(LoginActivity.this);
                                user.setActive(true);
                                user.setCodeCli(clinique.getCodCli());
                                user.setUserName(authentification.getUserName());
                                user.setPassWord(authentification.getPassWord());
                                user.setMatricule(authentification.getMatricule());
                                user.setNomemp(authentification.getNomemp());
                                user.setPrenemp(authentification.getPrenemp());
                                user.setMatriculeresp(authentification.getMatriculeresp());

                                userDAO.deleteAllUser();
                                userDAO.addUser(user);
                                clinique.setActive(true);
                                cliniqueDAO.updateCliniqueDesactiveAll(cliniqueList);
                                cliniqueDAO.updateClinique(clinique);
                                hideProgressDialog();
                                Intent intent = new Intent(getBaseContext(), ListeDemandeCongeActivity.class);
                               startActivity(intent);


                                LoginActivity.this.finish();

                            } else {
                                hideProgressDialog();
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Alerte.getAlerte(LoginActivity.this, false, "Merci de vérifier votre login et mot de passe");
                                    }
                                });
                            }
                        }

                    }
                }).start();
            }
        });

        btnChangerCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliniqueDAO.deleteAllClinique();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

    public void getProgressDialog() {
        try {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    dialog.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hideProgressDialog() {
        try {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    dialog.hide();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //TODO getProgressDialog

    //TODO EdiLoginTextListener
    private class EdiLoginTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                txtInputUsername.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    //TODO EdiPasswordTextListener
    private class EdiPasswordTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                txtInputPassword.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

}

