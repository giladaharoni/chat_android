package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chat_android.R;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import viewmodels.contact;
import viewmodels.message;

public class contacts_adapter extends RecyclerView.Adapter<contacts_adapter.contactsViewHolder> {

    private final LayoutInflater inflater;
    private List<contact> contacts;
    public contacts_adapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public contactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new contactsViewHolder(inflater.inflate(R.layout.contact_pre_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull contacts_adapter.contactsViewHolder holder, int position) {
        if(contacts != null) {
            String pattern = " HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            final contact current = contacts.get(position);
            holder.lastMessage.setText(current.getLast());
            holder.time.setText(simpleDateFormat.format(current.getLastDate()));
            holder.name.setText(current.getName());
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class contactsViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView lastMessage;
        private TextView time;
        public contactsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name_conversation);
            time = itemView.findViewById(R.id.time_conv);
            lastMessage = itemView.findViewById(R.id.lastm);
        }
    }
    public void setContacts(List<contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

}
