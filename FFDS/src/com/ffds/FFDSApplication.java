package com.ffds;

import com.ffds.service.FfdsService;

public class FFDSApplication {
    public static void main(String[] args) {
        FfdsService ffdsService =new FfdsService();

        ffdsService.register_user("Pralove", "M", "phoneNumber-1", "HSR");
        ffdsService.register_user("Nitesh", "M", "phoneNumber-2", "BTM");
        ffdsService.register_user("Vatsal", "M",  "phoneNumber-3", "BTM");

        ffdsService.user_login("phoneNumber-1");
        ffdsService.register_restaurant("Food Court-1", "BTM/HSR", "NI Thali", 100, 5);
        ffdsService.register_restaurant("Food Court-2", "BTM", "Burger", 120, 3);
        ffdsService.user_login("phoneNumber-2");
        ffdsService.register_restaurant("Food Court-3", "HSR", "SI Thali", 150, 1);
        ffdsService.user_login("phoneNumber-3");
        ffdsService.show_restaurant("price");

        ffdsService.place_order("Food Court-1", 2);
        ffdsService.place_order("Food Court-2", 7);
        ffdsService.create_review("Food Court-2", 3, "Good Food");
        ffdsService.create_review("Food Court-1", 5, "Nice Food");
        ffdsService.show_restaurant("rating");
        ffdsService.user_login("phoneNumber-1");
        ffdsService.update_quantity("Food Court-2", 5);
        ffdsService.update_location("Food Court-2", "BTM/HSR");
        ffdsService.order_history("phoneNumber-1");
        ffdsService.order_history("phoneNumber-2");
        ffdsService.order_history("phoneNumber-3");

    }
}
