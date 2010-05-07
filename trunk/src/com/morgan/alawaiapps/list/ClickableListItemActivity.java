/**
 * Grabbed from Android Snippets
 * http://www.androidsnippets.org/snippets/125/
 */

package com.morgan.alawaiapps.list; 

import java.util.ArrayList;  
  import java.util.List;  
  import android.app.ListActivity;  
  import android.content.Context;  
  import android.graphics.Bitmap;  
  import android.graphics.BitmapFactory;  
  import android.os.Bundle;  
  import android.view.View;  
  import android.widget.ImageView;  
  import android.widget.TextView;  
  import com.morgan.alawaiapps.list.ClickableListAdapter;  
  import com.morgan.alawaiapps.list.ClickableListAdapter.ViewHolder;  
    
  /** 
   * An example how to implement the ClickableListAdapter for a list view layout containing 
   * a TextView and an ImageView. 
   * @author poss3x 
   */  
  public class ClickableListItemActivity extends ListActivity {  
    
      /** 
       * Our data class. This data will be bound to  
       * MyViewHolder, which in turn is used for the  
       * ListView.  
       */  
      static class MyData {  
          public MyData(String t, boolean e) {  
              text = t;  
              enable = e;  
          }  
    
          String text;  
          boolean enable;  
      }  
        
      /** 
       Our referencing the view elements 
       of our ListView layout 
       */  
      static class MyViewHolder extends ViewHolder {  
    
          public MyViewHolder(TextView t, ImageView i) {  
              text = t;  
              icon = i;  
          }  
          TextView text;  
          ImageView icon;  
      }  
    
      /** 
       * The implementation of ClickableListAdapter 
       */  
      private class MyClickableListAdapter extends ClickableListAdapter {  
          public MyClickableListAdapter(Context context, int viewid,  
                  List<MyData> objects) {  
              super(context, viewid, objects);  
              // nothing to do  
          }  
    
          protected void bindHolder(ViewHolder h) {  
              // Binding the holder keeps our data up to date.  
              // In contrast to createHolder this method is called for all items  
              // So, be aware when doing a lot of heavy stuff here
              // we simply transfer our object's data to the list item representatives  
              MyViewHolder mvh = (MyViewHolder) h;  
              MyData mo = (MyData)mvh.data;   
              mvh.icon.setImageBitmap(  
                      mo.enable ? ClickableListItemActivity.this.mIconEnabled  
                              : ClickableListItemActivity.this.mIconDisabled);  
              mvh.text.setText(mo.text);  
    
          }  
    
          @Override  
          protected ViewHolder createHolder(View v) {  
              // createHolder will be called only as long, as the ListView is not filled  
              // entirely. That is, where we gain our performance:  
              // We use the relatively costly findViewById() methods and  
              // bind the view's reference to the holder objects.  
              TextView text = (TextView) v.findViewById(R.id.listitem_text);  
              ImageView icon = (ImageView) v.findViewById(R.id.listitem_icon);  
              ViewHolder mvh = new MyViewHolder(text, icon);  
    
              // Additionally, we make some icons clickable  
              // Mind, that item becomes clickable, when adding a click listener (see API)  
              // so, it is not necessary to use the android:clickable attribute in XML  
              icon.setOnClickListener(new ClickableListAdapter.OnClickListener(mvh) {  
    
                  public void onClick(View v, ViewHolder viewHolder) {  
                      // we toggle the enabled state and also switch the icon  
                      MyViewHolder mvh = (MyViewHolder) viewHolder;  
                      MyData mo = (MyData) mvh.data;  
                      mo.enable = !mo.enable; // toggle  
                      ImageView icon = (ImageView) v;  
                      icon.setImageBitmap(  
                              mo.enable ? ClickableListItemActivity.this.mIconEnabled  
                                      : ClickableListItemActivity.this.mIconDisabled);  
                  }  
              });  
    
              // for text we implement a long click listener  
              text.setOnLongClickListener(new ClickableListAdapter.OnLongClickListener(mvh) {  
    
                  @Override  
                  public void onLongClick(View v, ViewHolder viewHolder) {  
                        
                      MyViewHolder mvh = (MyViewHolder) viewHolder;  
                      MyData mo = (MyData)mvh.data;  
                        
                      // we toggle an '*' in our text element  
                      String s = mo.text;  
                      if (s.charAt(0) == '*') {  
                          mo.text = s.substring(1);  
                      } else {  
                          mo.text = '*' + s;  
                      }  
                      mvh.text.setText(mo.text);  
                  }  
    
              });  
    
              return mvh; // finally, we return our new holder  
          }  
    
      }  
    
            
      @Override  
      public void onCreate(Bundle savedInstanceState) {  
          super.onCreate(savedInstanceState);  
    
          // preloading our icons  
          mIconEnabled = BitmapFactory.decodeResource(this.getResources(),  
                  R.drawable.ok);  
          mIconDisabled = BitmapFactory.decodeResource(this.getResources(),  
                  R.drawable.delete);  
            
          // fill list with some items...  
          // to demonstrate the performance we create a bunch of data objects  
          for (int i = 0; i < 25; ++i) {  
              mObjectList.add(new MyData("Some Text " + i, true));  
          }  
          //here we set our adapter  
          setListAdapter(new MyClickableListAdapter(this,  
                  R.layout.clickablelistitemview, mObjectList));  
    
      }  
    
      // --------------- field section -----------------  
      private Bitmap mIconEnabled;  
      private Bitmap mIconDisabled;  
    
      private List<MyData> mObjectList = new ArrayList<MyData>();  
    
  }  