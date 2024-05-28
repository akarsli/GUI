import java.awt.*;

public class Bean {
    private static final int COMPONENT_X = 10;
    private static final int COMPONENT_Y= 50;
    private static final int MENUBAR_HEIGHT = 30;
    private static String[] newItem={};
    private static final Color COLOR = new Color(30, 136, 229);

    public static int getComponentX() {
        return COMPONENT_X;
    }

    public static int getComponentY() {
        return COMPONENT_Y;
    }

    public static int getMenubarHeight(){
        return MENUBAR_HEIGHT;
    }

    public static String[] getNewItem(){
        return newItem;
    }

    public static Color getColor(){
        return COLOR;
    }
}
