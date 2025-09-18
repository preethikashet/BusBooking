package com.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="notificationservice", path= "/api/notify")

public interface ResponseToBookingClient {

    @PostMapping("/send")
    void sendNotification(@RequestParam("userId") int userid, @RequestParam("message") String message);
}
