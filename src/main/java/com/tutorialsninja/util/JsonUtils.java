package com.tutorialsninja.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorialsninja.pojo.RegistrationForm;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonUtils {

    public static RegistrationForm getJsonNode(int index){
        ObjectMapper mapper = new ObjectMapper();
        FileReader fr = null;
        try {
            fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\UserData.json");
            List<RegistrationForm> users = null;
            users = mapper.readValue(fr,new TypeReference<List<RegistrationForm>>(){});
            return users.get(index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
