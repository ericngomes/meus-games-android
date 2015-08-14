package br.com.framework.meusgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import br.com.framework.meusgames.modelos.Game;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroGameActivity extends BaseActivity {

    @Bind(R.id.img_game)
    ImageView imgGame;

    @Bind(R.id.edit_nome)
    EditText editNome;

    @Bind(R.id.edit_descricao)
    EditText editDescricao;
    @Bind(R.id.edit_ano)
    EditText editAno;
    @Bind(R.id.edit_pontuacao)
    EditText editPontuacao;
    @Bind(R.id.edit_site)
    EditText editSite;
    @Bind(R.id.edit_categoria)
    EditText editCategoria;
    @Bind(R.id.edit_plataforma)
    EditText editPlataforma;

    private Uri mImageCaptureUri;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_game);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_game, menu);
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

    @OnClick(R.id.img_game)
    void onClickImageGame(View view) {
        openDialogFoto();
    }

    private void openDialogFoto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.title_dialog_foto);
        builder.setMessage(R.string.message_dialog_foto); //De onde você deseja obter a foto?
        builder.setIcon(R.mipmap.ic_launcher);

        builder.setPositiveButton(R.string.galeria, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                abrirGaleria();
            }
        });

        builder.setNegativeButton(R.string.camera, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                abrirCamera();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void abrirGaleria() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 101);

    }

    private void abrirCamera() {

        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "tmp_meugame_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        takePictureIntent.putExtra("return-data", true);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 102);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == 101 && data != null) {
            Uri uriFoto = data.getData();
            game.setImage(uriFoto.toString());
        } else if (requestCode == 102 && mImageCaptureUri != null) {
            game.setImage(mImageCaptureUri.toString());
        }
    }
}
