package aj.tabletop;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	DrawView drawView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.requestFocus();
	}
}
