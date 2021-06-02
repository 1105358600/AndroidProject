package com.wsine.west.exp7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_info);

        TextView tvTitle = (TextView)this.findViewById(R.id.detail_title);
        final EditText edtNo = (EditText)this.findViewById(R.id.Et_no);
        final EditText edtName = (EditText)this.findViewById(R.id.Et_name);
        final EditText edtSex = (EditText)this.findViewById(R.id.Et_sex);
        final EditText edtPNumber = (EditText)this.findViewById(R.id.Et_phone);

        final Bundle bundle = getIntent().getExtras();
        final boolean addOrNot = bundle.getBoolean("AddorNot");
        final int[] data=bundle.getIntArray("data");
        if (addOrNot) {
            tvTitle.setText(getResources().getString(R.string.titleAdd));
            edtNo.setText("");
            edtName.setText("");
            edtSex.setText("");
            edtPNumber.setText("");
        } else {
            tvTitle.setText(getResources().getString(R.string.titleModify));
            edtNo.setText(bundle.getString("oldNo"));
            edtName.setText(bundle.getString("oldName"));
            edtSex.setText(bundle.getString("oldSex"));
            edtPNumber.setText(bundle.getString("oldPNumber"));
        }

        Button btnConfirm = (Button)this.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newNo = edtNo.getText().toString();
                String newName = edtName.getText().toString();
                String newSex = edtSex.getText().toString();
                String newPNumber = edtPNumber.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("_oldNo",bundle.getString("oldNo"));
                intent.putExtra("_newNo", newNo);
                intent.putExtra("_newName", newName);
                intent.putExtra("_newSex", newSex);
                intent.putExtra("_newPNumber", newPNumber);

                for(int i=0;bundle.getString("No"+i)!=null;i++)
                {   System.out.println("第"+i+"行");
                    System.out.println(bundle.getString("No"+i));
                    System.out.println(newNo);
                    if (newNo.isEmpty()
                            || newPNumber.isEmpty()
                            || newName.isEmpty()) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.msgWarning),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(newNo.equals(bundle.getString("No"+i))&&!newNo.equals(bundle.getString("oldNo")))
                    {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.msgWarning2),Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                int resultCode = 0;
                if (addOrNot)   resultCode = 1;
                else            resultCode = 2;

                DetailActivity.this.setResult(resultCode, intent);
                DetailActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            int resultCode = 0;
            DetailActivity.this.setResult(resultCode);
            DetailActivity.this.finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
