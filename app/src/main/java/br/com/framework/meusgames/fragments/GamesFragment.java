package br.com.framework.meusgames.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.framework.meusgames.CadastroGameActivity;
import br.com.framework.meusgames.GameActivity;
import br.com.framework.meusgames.R;
import br.com.framework.meusgames.adapters.GamesAdapter;
import br.com.framework.meusgames.modelos.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by felipe.arimateia on 12/08/2015.
 */
public class GamesFragment extends Fragment implements GamesAdapter.GamesAdapterListener {

    @Bind(R.id.lista_games)
    RecyclerView listaGames;

    public static GamesFragment newInstance() {
        GamesFragment fragment = new GamesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_games, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_game) {
            Intent intent = new Intent(getActivity(), CadastroGameActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_games, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        GamesAdapter adapter = new GamesAdapter(games, getActivity(), this);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        listaGames.setLayoutManager(layoutManager);
        listaGames.setHasFixedSize(true);
        listaGames.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onGameSelected(Game game) {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }
}
