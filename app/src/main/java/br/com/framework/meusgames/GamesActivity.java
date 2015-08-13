package br.com.framework.meusgames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.framework.meusgames.adapters.GamesAdapter;
import br.com.framework.meusgames.modelos.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GamesActivity extends BaseActivity implements GamesAdapter.GamesAdapterListener {

    @Bind(R.id.lista_games)
    RecyclerView listaGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmet_games);
        ButterKnife.bind(this);
        initToolbar();

        Game game = new Game();
        game.setNome("Fifa 2015");
        game.setAno(2015);
        game.setPontuacao(9.5);
        game.setImage("http://1.bp.blogspot.com/-qFHfS541928/U8N6owcj97I/AAAAAAAAAN8/v9eupqDpxHM/s1600/www.gettgame.com.jpeg");

        Game game2 = new Game();
        game2.setNome("Dota 2");
        game2.setAno(2013);
        game2.setPontuacao(10);
        game2.setImage("http://www.readytocut.com/community/attachments/awww-freelogovectors-net_wp_content_uploads_2014_05_dota_2_logo-jpg.2813/");

        List<Game> games = new ArrayList<>();
        games.add(game);
        games.add(game2);

        GamesAdapter adapter = new GamesAdapter(games, this, this);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        listaGames.setLayoutManager(layoutManager);
        listaGames.setHasFixedSize(true);
        listaGames.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_game) {
            Intent intent = new Intent(this, CadastroGameActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGameSelected(Game game) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
