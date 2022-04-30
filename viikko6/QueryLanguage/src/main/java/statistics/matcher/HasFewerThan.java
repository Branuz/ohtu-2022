package statistics.matcher;

import java.lang.reflect.Method;

import statistics.Player;

public class HasFewerThan implements Matcher {

    private int value;
    private String methodName;

    public HasFewerThan(int value, String text) {
        this.value = value;
        this.methodName = "get" + Character.toUpperCase(text.charAt(0)) + text.substring(1, text.length());
    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(methodName);
            int playersValue = (Integer) method.invoke(p);
            
            return playersValue < value;

        } catch (Exception e) {
            System.out.println(e);
            throw new IllegalStateException("Error in the player field!");
        }
    }
    
}
