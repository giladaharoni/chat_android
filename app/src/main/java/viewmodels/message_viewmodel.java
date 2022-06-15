package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class message_viewmodel extends ViewModel {

    private MutableLiveData<message> messages;

    public MutableLiveData<message> getMessages() {
        if(messages == null) {
            messages = new MutableLiveData<message>();
        }
        return messages;
    }
}
