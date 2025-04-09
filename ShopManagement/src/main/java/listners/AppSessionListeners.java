package listners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppSessionListeners implements HttpSessionListener {

    public static int SESSION_COUNT = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SESSION_COUNT++;  // Tăng số lượng session
        System.out.println("Session được tạo: " + se.getSession().getId());
        System.out.println("Tổng số session hiện tại: " + SESSION_COUNT);
        // Không gọi HttpSessionListener.super.sessionCreated(se) vì không có default implementation
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SESSION_COUNT--;  // Giảm số lượng session
        System.out.println("Session đã bị hủy: " + se.getSession().getId());
        System.out.println("Tổng số session hiện tại: " + SESSION_COUNT);
        // Không gọi HttpSessionListener.super.sessionDestroyed(se) vì không có default implementation
    }
}
