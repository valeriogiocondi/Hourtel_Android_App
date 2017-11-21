package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import com.hourtel.user.hourtel.R;

/**
 * Created by valerio on 31/05/17.
 */

public class ArrayAdapter_LeftMenu extends ArrayAdapter<String> {

    Context parentContext = null;

    public ArrayAdapter_LeftMenu(Context context, String[] List)  {

        super(context, R.layout.list_adapter_home_left_menu, List);
        parentContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = LayoutInflater.from(getContext());
        View customView = layout.inflate(R.layout.list_adapter_home_left_menu, parent, false);

        TextView voiceTextView = (TextView) customView.findViewById(R.id.voice);

        voiceTextView.setText(getItem(position));

        return customView;
    }

}
