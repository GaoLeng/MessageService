package com.jkinfo.message;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/test")
public class Hello {

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String register() {
        JMessageClient client = new JMessageClient(Constant.APPKEY, Constant.MASTER_SECRET);
        try {
            List<RegisterInfo> users = new ArrayList<>();

            RegisterInfo user = RegisterInfo.newBuilder()
                    .setUsername("user1")
                    .setPassword("user1")
                    .build();

            users.add(user);

            RegisterInfo[] regUsers = new RegisterInfo[users.size()];

            String res = client.registerUsers(users.toArray(regUsers));
            return res;
        } catch (APIConnectionException | APIRequestException e) {
            return e.getMessage();
        }
    }
}