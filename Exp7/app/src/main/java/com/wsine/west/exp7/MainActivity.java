package com.wsine.west.exp7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
    private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySimpleAdapter mySimpleAdapter=updateListView();
        final ArrayList<Map<String, String>> mData=mySimpleAdapter.getmData();

        Button btnAdd = (Button)this.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                int i=-1;
                for (Map<String, String> m : mData){
                    i++;
                    Collection<String> valueCollection = m.values();
                    final  int  size = valueCollection.size();

                    List<String> valueList =  new  ArrayList<String>(valueCollection);

                    String[] valueArray =  new  String[size];
                    m.values().toArray(valueArray);
                    bundle.putString("No"+i, valueArray[3]);
                }
                bundle.putBoolean("AddorNot", true);
                intent.putExtras(bundle);
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
            }
        });

        ListView lv = (ListView)this.findViewById(R.id.lv_contact);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("AddorNot", false);
                int i=-1;
                for (Map<String, String> m : mData){
                    i++;
                    Collection<String> valueCollection = m.values();
                    final  int  size = valueCollection.size();

                    List<String> valueList =  new  ArrayList<String>(valueCollection);

                    String[] valueArray =  new  String[size];
                    m.values().toArray(valueArray);
                    bundle.putString("No"+i, valueArray[3]);
                    }
                bundle.putString("oldNo", map.get("no"));
                bundle.putString("oldName", map.get("name"));
                bundle.putString("oldSex", map.get("sex"));
                bundle.putString("oldPNumber", map.get("pnumber"));
                intent.putExtras(bundle);
                int requestCode = 2;
                startActivityForResult(intent, requestCode);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                final HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            myDatabaseHelper.delete(new Contact(map.get("no"), map.get("name"), map.get("sex"),map.get("pnumber")));
                            updateListView();
                        } catch (Exception e) {
                            Log.d("Hint", "Remove failed!");
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing to do
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void setData(List<Map<String, String>> mDataList) {
        Map<String, String> mData;
        Cursor c = myDatabaseHelper.query();
        while (c.moveToNext()) {
            mData = new HashMap<String, String>();
            mData.put("no", c.getString(c.getColumnIndex("_no")));
            mData.put("name", c.getString(c.getColumnIndex("_name")));
            mData.put("sex", c.getString(c.getColumnIndex("_sex")));
            mData.put("pnumber", c.getString(c.getColumnIndex("_pnumber")));
            mDataList.add(mData);

        }
    }

    private MySimpleAdapter updateListView() {
        dataList.clear();
        setData(dataList);
        MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(this, dataList, R.layout.contact_item,
                new String[] { "no", "name","sex", "pnumber" },
                new int[] { R.id.contact_no, R.id.contact_name,R.id.contact_sex, R.id.contact_phonenumber });
        ListView lv = (ListView)this.findViewById(R.id.lv_contact);
        lv.setAdapter(mySimpleAdapter);
        return  mySimpleAdapter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Hint", "requestCode = " + requestCode);
        Log.d("Hint", "resultCode = " + resultCode);
        if (resultCode == 0)
            return;
        String oldNo= data.getStringExtra("_oldNo");
        String newNo = data.getStringExtra("_newNo");
        String newName = data.getStringExtra("_newName");
        String newSex = data.getStringExtra("_newSex");
        String newPNumber = data.getStringExtra("_newPNumber");
        switch (requestCode) {
            case 1:
                myDatabaseHelper.insert(new Contact(newNo, newName,newSex, newPNumber));
                break;
            case 2:
                int i=myDatabaseHelper.update(new Contact(newNo, newName,newSex, newPNumber),oldNo);
                Toast.makeText(this,""+i+"个修改成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        updateListView();
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
