package aj.tabletop;

import aj.tabletop.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		boolean checked = item.isChecked();
		DrawView view = (DrawView)findViewById(R.id.static_layer);
		view.setDrawable(!checked);
		item.setIcon(checked ? R.drawable.pen_up : R.drawable.pen_down);
		item.setChecked(!checked);
		
		return true;
	}
}
