package com.example.qldapm.evtranslator;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListMessagesActivity extends AppCompatActivity {

    ArrayList<Message> m_ListMessage;
    MyAdapter m_Adapter;
    ListView m_lv;
    String m_text = ""; // Nội dung tin nhắn cần dịch

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);
        m_ListMessage = new ArrayList<Message>();

        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c = getContentResolver().query(uri, null, null ,null,null);
        startManagingCursor(c);

        // Đọc tin nhắn từ máy
        if(c.moveToFirst())
        {
            for(int i=0; i < c.getCount(); i++)
            {
                Message message = new Message();
                message.setText(c.getString(c.getColumnIndexOrThrow("body")).toString());
                message.setPhoneNumner(c.getString(c.getColumnIndexOrThrow("address")).toString());
                message.setImage(getResources().getDrawable(R.drawable.image_message));
                m_ListMessage.add(message);
                c.moveToNext();
            }
        }
        c.close();

        // Hiển thị tin nhắn lên listview
        m_Adapter = new MyAdapter(this, R.layout.list_message_layout, m_ListMessage);
        m_lv = (ListView)findViewById(R.id.listView_listMessage);
        m_lv.setAdapter(m_Adapter);

        final TextView txt1 =(TextView) findViewById(R.id.textView_message);
        m_lv.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                m_text = m_ListMessage.get(arg2).getText();
                                txt1.setText("Message: " + m_text);
                            }
                        }
                );

        // Sự kiện dịch tin nhắn
        Button button = (Button) findViewById(R.id.button_translate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), m_text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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
