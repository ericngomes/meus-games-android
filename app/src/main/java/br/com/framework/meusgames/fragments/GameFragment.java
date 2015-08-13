package br.com.framework.meusgames.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.framework.meusgames.R;
import br.com.framework.meusgames.modelos.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by felipe.arimateia on 12/08/2015.
 */
public class GameFragment extends Fragment {

    @Bind(R.id.img_game)
    ImageView imgGame;
    @Bind(R.id.text_nome)
    TextView textNome;
    @Bind(R.id.text_categoria)
    TextView textCategoria;
    @Bind(R.id.text_ano)
    TextView textAno;
    @Bind(R.id.comentarios)
    TableLayout comentarios;

    private Game game;

    public static GameFragment newInstance(Game game) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);

        GameFragment fragment = new GameFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_game, menu);

        MenuItem itemFavoritar = menu.findItem(R.id.action_favoritar);

        if (true) { //TODO: Fazer validacao no banco
            itemFavoritar.setIcon(R.drawable.ic_favorite_white_24dp);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_favoritar) {
            Toast.makeText(getActivity(), R.string.msg_sucesso_favoritar, Toast.LENGTH_SHORT).show();
            item.setIcon(R.drawable.ic_favorite_white_24dp);
            return true;
        }

        if (id == R.id.action_add_biblioteca) {
            Toast.makeText(getActivity(), R.string.msg_sucesso_add_biblioteca, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        game = (Game)args.getSerializable("game");

        addComentarios();

        textNome.setText(game.getNome());
        textAno.setText(getString(R.string.lbl_ano, game.getAno()));

        if (game.getCategorias() != null && game.getCategorias().size() > 0) {
            textCategoria.setText(TextUtils.join(" ,", game.getCategorias()));
        }

        Picasso.with(getActivity())
                .load(game.getImage())
                .into(imgGame);
    }

    private void addComentarios() {
        for (int i = 0; i < 3; i++) {

            TableRow row = (TableRow) LayoutInflater.from(getActivity())
                    .inflate(R.layout.row_comentario, comentarios,false);

            ImageView imgUser = (ImageView)row.findViewById(R.id.img_user);
            TextView nome = (TextView)row.findViewById(R.id.nome_usuario);
            TextView comentario = (TextView)row.findViewById(R.id.comentario);

            Picasso.with(getActivity()).load("124234234")
                    .error(R.drawable.user_placeholder).into(imgUser);

            nome.setText("Nome do Usuario");
            comentario.setText("Comentario");

            comentarios.addView(row);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
