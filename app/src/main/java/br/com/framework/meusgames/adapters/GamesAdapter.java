package br.com.framework.meusgames.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.framework.meusgames.R;
import br.com.framework.meusgames.modelos.Game;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by felipe.arimateia on 10/08/2015.
 */
public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> games;
    private Context context;
    private GamesAdapterListener listener;

    public GamesAdapter(@NonNull List<Game> games, Context context,
                        GamesAdapterListener listener) {
        this.games = games;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Game game = games.get(position);
        viewHolder.nomeGame.setText(game.getNome());
        viewHolder.anoGame.setText(context.getString(R.string.lbl_ano, game.getAno()));
        viewHolder.pontuacaoGame.setText(context.getString(R.string.lbl_pontuacao, game.getPontuacao()));
        Picasso.with(context).load(game.getImage()).into(viewHolder.imageGame);

        viewHolder.cardView.setTag(game);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_view)
        CardView cardView;

        @Bind(R.id.image_game)
        ImageView imageGame;
        @Bind(R.id.nome_game)
        TextView nomeGame;
        @Bind(R.id.ano_game)
        TextView anoGame;
        @Bind(R.id.pontucao_game)
        TextView pontuacaoGame;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        void onClickCardView(View view) {

            if (listener != null) {
                Game game = (Game)view.getTag();
                listener.onGameSelected(game);
            }
        }
    }

    public interface GamesAdapterListener {
        void onGameSelected(Game game);
    }
}
