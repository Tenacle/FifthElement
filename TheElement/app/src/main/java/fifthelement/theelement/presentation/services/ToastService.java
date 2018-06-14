package fifthelement.theelement.presentation.services;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Toast;

public class ToastService {
    Context context;
    private static Toast toast;

    public ToastService(Context context) {
        this.context = context;
    }

    public void sendToast(String message, String colour) {
        if (toast != null) toast.cancel();
        int chosenColor = getColour(colour);
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.getBackground().setColorFilter(chosenColor, PorterDuff.Mode.SRC_IN);
        toast.show();
    }

    public void sendToast(String message) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private int getColour(String colour) {
        switch(colour) {
            case "RED":
                return Color.RED;
            case "BLUE":
                return Color.BLUE;
            case "BLACK":
                return Color.BLACK;
            case "GREEN":
                return Color.GREEN;
             default:
                return Color.LTGRAY;
        }
    }
}
