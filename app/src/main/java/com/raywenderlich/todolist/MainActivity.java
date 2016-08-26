/*
 * Copyright (c) 2015 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
  private static final String TAG = MainActivity.class.getName();
  private final int ADD_TASK_REQUEST = 1;

  private ArrayList<String> mList;
  private ArrayAdapter<String> mAdapter;
  private TextView mDateTimeTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // 1
    super.onCreate(savedInstanceState);

    // 2 -Make the activity full screen
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // 3
    setContentView(R.layout.activity_main);

    // 4
    mDateTimeTextView = (TextView) findViewById(R.id.dateTimeTextView);
    final Button addTaskBtn = (Button) findViewById(R.id.addTaskBtn);
    final ListView listview = (ListView) findViewById(R.id.taskListview);
    mList = new ArrayList<String>();

    // 5
    mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
    listview.setAdapter(mAdapter);

    // 6
    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

      }
    });
  }

  public void addTaskClicked(View view) {
      Intent intent = new Intent(MainActivity.this, TaskDescriptionActivity.class);
      startActivityForResult(intent, ADD_TASK_REQUEST);

  }

  private static String getCurrentTimeStamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdf.format(now);
    return strDate;
  }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 1 - Check which request you're responding to
        if (requestCode == ADD_TASK_REQUEST) {
            // 2 - Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // 3 - The user entered a task. Add a task to the list.
                String task = data.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION);
                mList.add(task);
                // 4
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
