package br.com.framework.meusgames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.framework.meusgames.fragments.GameFragment;
import br.com.framework.meusgames.modelos.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends BaseActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        initToolbar();

        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra("game");

        GameFragment fragment = GameFragment.newInstance(game);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }
}
