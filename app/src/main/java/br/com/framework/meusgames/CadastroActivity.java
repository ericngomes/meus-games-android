package br.com.framework.meusgames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity {

    @Bind(R.id.img_usuario)
    ImageView imgUsuario;
    @Bind(R.id.edit_nome)
    EditText editNome;
    @Bind(R.id.edit_data_nasc)
    EditText editDataNasc;
    @Bind(R.id.edit_email)
    EditText editEmail;
    @Bind(R.id.edit_endereco)
    EditText editEndereco;
    @Bind(R.id.edit_senha)
    EditText editSenha;
    @Bind(R.id.edit_confirma_senha)
    EditText editConfirmaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_ok)
    public void onClickSalvar(View view) {
        if(validaForm()) {
            finish();
        }
    }

    private boolean validaForm() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
