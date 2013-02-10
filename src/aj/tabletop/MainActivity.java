package aj.tabletop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener
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

		RadioGroup group = (RadioGroup)menu.findItem(R.id.menu_mode).getActionView();
		group.setOnCheckedChangeListener(this);
		group.check(R.id.btn_draw);
		
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		DrawView drawView = (DrawView)findViewById(R.id.static_layer);
		switch (checkedId)
		{
		case R.id.btn_draw:
			drawView.setMode(Mode.Draw);
			break;

		case R.id.btn_erase:
			drawView.setMode(Mode.Erase);
			break;
		}
	}
}
