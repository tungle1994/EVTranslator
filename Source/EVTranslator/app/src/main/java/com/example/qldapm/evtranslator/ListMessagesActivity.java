package com.example.qldapm.evtranslator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tùng Lê on 12-Nov-15.
 */
public class ListMessagesActivity extends AppCompatActivity {

    ArrayList<Message> m_ListMessage;
    MyAdapter m_Adapter;
    ListView lv;
    private String[][] m_Message = {{"Name", "0989897873"}, {"Name", "0967995843"}, {"Name", "0907955843"}, {"Name", "0967885811"}, {"Name", "0988885231"}};
    private int[] m_Image = {R.drawable.image_message, R.drawable.image_message, R.drawable.image_message, R.drawable.image_message, R.drawable.image_message};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_ListMessage = new ArrayList<Message>();
        for(int i = 0; i < m_Message.length; i++)
        {
            Message message = new Message();
            message.setName(m_Message[i][0]);
            message.setPhoneNumner(m_Message[i][1]);
            message.setImage(getResources().getDrawable(m_Image[i]));
            m_ListMessage.add(message);
        }
        m_Adapter = new MyAdapter(this, R.layout.list_message_layout, m_ListMessage);
        lv = (ListView)findViewById(R.id.listView_listMessage);
        lv.setAdapter(m_Adapter);

        final TextView txt1 =(TextView) findViewById(R.id.textView_message);
        lv.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                txt1.setText("Message: " + m_Message[arg2][0]);
                            }
                        }
                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

