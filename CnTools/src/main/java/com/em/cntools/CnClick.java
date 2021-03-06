package com.em.cntools;

/**
 * @author yemint
 * <p>
 * 防止控件连续多次被点击
 */
public class CnClick {

    private static long lastClickTime = 0;
    private static int lastButtonId = -1;
    private static long DIFF = 1000;    //时间间隔

    private CnClick() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException(" FmClick cannot be instantiated");
    }

    /**
     * 判断两次点击的间隔，如果小于1s，则认为是多次无效点击（任意两个view，固定时长1s）
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击（任意两个view，自定义间隔时长）
     *
     * @return
     */
    public static boolean isFastDoubleClick(long diff) {
        return isFastDoubleClick(-1, diff);
    }

    /**
     * 判断两次点击的间隔，如果小于1s，则认为是多次无效点击（同一个view，固定时长1s）
     *
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击（同一按钮，自定义间隔时长）
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            CnLog.d("isFastDoubleClick", "短时间内view被多次点击");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

}
