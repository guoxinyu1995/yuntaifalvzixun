package com.ytfu.yuntaifawu.helper;

import com.hyphenate.chat.EMMessage;

public class CustomMessageHelper {
    public static boolean isGoodsChatType(EMMessage emMessage) {
        String TYPE = emMessage.getStringAttribute("type", null);
        if (TYPE == null){
            return false;
        }
        if (TYPE.equals("type")){
            return true;
        }

        return false;
    }

}
