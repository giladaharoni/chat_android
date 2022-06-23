package com.example.chat_android;

import android.os.Bundle;

import java.util.Properties;

public class Notification_recieved_listener {
    interface on_notification_recieved{
        void func_recieved(); //turn on the function b
    }
    private on_notification_recieved obj;
    public void set_obj(on_notification_recieved ob){
        this.obj = ob;
    }
    public void notifiction_recieved(){
        if(this.obj != null){
            this.obj.func_recieved();
        }
    }
}
