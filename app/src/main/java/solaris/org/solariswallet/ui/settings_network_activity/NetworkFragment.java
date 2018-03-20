package solaris.org.solariswallet.ui.settings_network_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.solarisj.core.Peer;

import java.util.List;

import solaris.org.solariswallet.R;
import solaris.org.solariswallet.ui.base.BaseRecyclerFragment;
import solaris.org.solariswallet.ui.base.tools.adapter.BaseRecyclerAdapter;

/**
 * Created by ming on 2018.3.19.
 */

public class NetworkFragment extends BaseRecyclerFragment<Peer> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setEmptyText("No connection to any node.");
        setEmptyTextColor(Color.parseColor("#cccccc"));
        return view;
    }

    @Override
    protected List<Peer> onLoading() {
        return solarisModule.listConnectedPeers();
    }

    @Override
    protected BaseRecyclerAdapter<Peer, ? extends NetworkViewHolder> initAdapter() {
        return new BaseRecyclerAdapter<Peer, NetworkViewHolder>(getActivity()) {
            @Override
            protected NetworkViewHolder createHolder(View itemView, int type) {
                return new NetworkViewHolder(itemView);
            }

            @Override
            protected int getCardViewResource(int type) {
                return R.layout.network_row;
            }

            @Override
            protected void bindHolder(NetworkViewHolder holder, Peer data, int position) {
                holder.address.setText(data.getAddress().toString());
                holder.network_ip.setText(data.getPeerVersionMessage().subVer);
                holder.protocol.setText("protocol:"+data.getPeerVersionMessage().clientVersion);
                holder.blocks.setText(data.getBestHeight()+" Blocks");
                holder.speed.setText(data.getLastPingTime()+"ms");
            }
        };
    }
}
