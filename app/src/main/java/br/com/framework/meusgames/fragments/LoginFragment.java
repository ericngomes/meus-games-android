package br.com.framework.meusgames.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.com.framework.meusgames.CadastroActivity;
import br.com.framework.meusgames.GamesActivity;
import br.com.framework.meusgames.R;
import br.com.framework.meusgames.modelos.Usuario;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by felipe.arimateia on 12/08/2015.
 */
public class LoginFragment extends Fragment {

    @Bind(R.id.edit_login)
    EditText editLogin;
    @Bind(R.id.edit_senha)
    EditText editSenha;
    private LoginFragmentListener listener;

    public static LoginFragment newInstance(LoginFragmentListener listener) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setListener(listener);
        return loginFragment;
    }

    public void setListener(LoginFragmentListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface LoginFragmentListener {
        void onLogin(Usuario usuario);
    }

    @OnClick(R.id.btn_ok)
    public void enviarLogin(View view) {
        if (!validaForm()) {
           listener.onLogin(new Usuario()); //TODO: Mudar retorno quando tiver o servico
        }
    }

    @OnClick(R.id.btn_cadastro)
    public void cadastrarUsuario(View view) {
        Intent intent = new Intent(getActivity(), CadastroActivity.class);
        startActivity(intent);
    }

    private boolean validaForm() {

        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        boolean error = false;

        if (TextUtils.isEmpty(login)) {
            editLogin.setError(getString(R.string.msg_error_login));
            error = true;

            YoYo.with(Techniques.Shake)
                    .duration(300)
                    .playOn(editLogin);
        }

        if (TextUtils.isEmpty(senha)) {
            editSenha.setError(getString(R.string.msg_error_senha));
            error = true;

            YoYo.with(Techniques.Shake)
                    .duration(300)
                    .playOn(editSenha);
        }

        return error;
    }
}
